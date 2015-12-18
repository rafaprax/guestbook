
package com.liferay.docs.guestbook.service.impl;

import com.liferay.docs.guestbook.GuestbookNameException;
import com.liferay.docs.guestbook.NoSuchGuestbookException;
import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.portlet.MessageKeys;
import com.liferay.docs.guestbook.service.EntryLocalServiceUtil;
import com.liferay.docs.guestbook.service.EntryServiceUtil;
import com.liferay.docs.guestbook.service.base.GuestbookLocalServiceBaseImpl;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLinkConstants;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the guestbook local service. <p> All custom service
 * methods should be put in this class. Whenever methods are added, rerun
 * ServiceBuilder to copy their definitions into the
 * {@link com.liferay.docs.guestbook.service.GuestbookLocalService} interface.
 * <p> This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM. </p>
 *
 * @author Diogo Peixoto
 * @see com.liferay.docs.guestbook.service.base.GuestbookLocalServiceBaseImpl
 * @see com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil
 */
public class GuestbookLocalServiceImpl extends GuestbookLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use
	 * {@link com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil} to
	 * access the guestbook local service.
	 */

	public Guestbook add(Guestbook guestbook, ServiceContext serviceContext)
		throws SystemException, PortalException {

		boolean addResource = false;
		if (guestbook.getGuestbookId() <= 0) {
			addResource = true;
		}

		setAttributes(guestbook, serviceContext);
		validate(guestbook);

		// Resources
		if (addResource) {
			resourceLocalService.addResources(
				serviceContext.getCompanyId(),
				serviceContext.getScopeGroupId(), serviceContext.getUserId(),
				Guestbook.class.getName(), guestbook.getGuestbookId(), false,
				true, true);
		}
		else {
			resourceLocalService.updateResources(
				serviceContext.getCompanyId(),
				serviceContext.getScopeGroupId(), Guestbook.class.getName(),
				guestbook.getGuestbookId(),
				serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}

		// Asset Framework
		AssetEntry assetEntry =
			assetEntryLocalService.updateEntry(
				serviceContext.getUserId(), serviceContext.getScopeGroupId(),
				guestbook.getCreateDate(), guestbook.getModifiedDate(),
				Guestbook.class.getName(), guestbook.getGuestbookId(),
				guestbook.getUuid(), 0, serviceContext.getAssetCategoryIds(),
				serviceContext.getAssetTagNames(), true, null, null, null,
				ContentTypes.TEXT_HTML, guestbook.getName(), null, null, null,
				null, 0, 0, null, false);

		assetLinkLocalService.updateLinks(
			serviceContext.getUserId(), assetEntry.getEntryId(),
			serviceContext.getAssetLinkEntryIds(),
			AssetLinkConstants.TYPE_RELATED);

		guestbook = super.updateGuestbook(guestbook);

		// Indexer
		updateGuestbookIndexer(guestbook, false);

		return guestbook;
	}

	public Guestbook delete(Guestbook guestbook)
		throws PortalException, SystemException {

		List<Entry> entries =
			EntryLocalServiceUtil.findByGroupIdGuestbookId(
				guestbook.getGroupId(), guestbook.getGuestbookId());

		for (Entry entry : entries) {
			EntryServiceUtil.delete(entry);
		}

		// Resource
		resourceLocalService.deleteResource(
			guestbook.getCompanyId(), Entry.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, guestbook.getGuestbookId());

		// Asset Framework
		AssetEntry assetEntry =
			assetEntryLocalService.fetchEntry(
				Guestbook.class.getName(), guestbook.getGuestbookId());

		assetLinkLocalService.deleteLinks(assetEntry.getEntryId());
		assetEntryLocalService.deleteEntry(assetEntry);

		guestbook = super.deleteGuestbook(guestbook);

		// Indexer
		updateGuestbookIndexer(guestbook, true);

		return guestbook;
	}

	public int countByGroupId(long groupId)
		throws SystemException {

		return guestbookPersistence.countByGroupId(groupId);
	}

	public List<Guestbook> findByGroupId(long groupId)
		throws SystemException {

		return guestbookPersistence.findByGroupId(groupId);
	}

	public List<Guestbook> findByGroupId(long groupId, int start, int end)
		throws SystemException {

		return guestbookPersistence.findByGroupId(groupId, start, end);
	}

	public Guestbook findByGroupIdName(
		long groupId, String name, OrderByComparator orderByComparator)
		throws SystemException, NoSuchGuestbookException {

		return guestbookPersistence.fetchByG_N_First(
			groupId, name, orderByComparator);
	}

	private void setAttributes(
		Guestbook guestbook, ServiceContext serviceContext)
		throws SystemException, NoSuchUserException {

		User user =
			userPersistence.findByPrimaryKey(serviceContext.getUserId());

		Date now = new Date();

		if (guestbook.getGuestbookId() <= 0) {
			long guestbookId =
				counterLocalService.increment(Guestbook.class.getName());

			guestbook.setGuestbookId(guestbookId);
			guestbook.setUuid(serviceContext.getUuid());

			guestbook.setCompanyId(user.getCompanyId());
			guestbook.setGroupId(serviceContext.getScopeGroupId());
			guestbook.setUserId(user.getUserId());
			guestbook.setUserName(user.getFullName());
			guestbook.setCreateDate(serviceContext.getCreateDate(now));
		}

		guestbook.setModifiedDate(serviceContext.getModifiedDate(now));

		guestbook.setExpandoBridgeAttributes(serviceContext);
	}

	private void validate(Guestbook guestbook)
		throws PortalException, SystemException {

		List<Guestbook> guestbooks =
			guestbookPersistence.findByG_N(
				guestbook.getGroupId(), guestbook.getName());

		if (!guestbooks.isEmpty()) {
			throw new PortalException(MessageKeys.GUESTBOOK_EXISTING_NAME);
		}

		if (Validator.isNull(guestbook.getName())) {
			throw new GuestbookNameException();
		}
	}

	private void updateGuestbookIndexer(Guestbook guestbook, boolean delete)
		throws SearchException {

		Indexer indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(Guestbook.class.getName());

		if (delete) {
			indexer.delete(guestbook);
		}
		else {
			indexer.reindex(guestbook);
		}
	}
}
