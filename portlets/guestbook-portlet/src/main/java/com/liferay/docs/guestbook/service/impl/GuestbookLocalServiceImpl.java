
package com.liferay.docs.guestbook.service.impl;

import com.liferay.docs.guestbook.GuestbookNameException;
import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.EntryLocalServiceUtil;
import com.liferay.docs.guestbook.service.EntryServiceUtil;
import com.liferay.docs.guestbook.service.base.GuestbookLocalServiceBaseImpl;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

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

		guestbook = super.updateGuestbook(guestbook);

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

		resourceLocalService.deleteResource(
			guestbook.getCompanyId(), Entry.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, guestbook.getGuestbookId());

		guestbook = super.deleteGuestbook(guestbook);

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
		throws PortalException {

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
