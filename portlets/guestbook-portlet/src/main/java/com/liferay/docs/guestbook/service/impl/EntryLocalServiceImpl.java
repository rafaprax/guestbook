
package com.liferay.docs.guestbook.service.impl;

import com.liferay.docs.guestbook.EntryEmailException;
import com.liferay.docs.guestbook.EntryMessageException;
import com.liferay.docs.guestbook.EntryNameException;
import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.service.base.EntryLocalServiceBaseImpl;
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
 * The implementation of the entry local service. <p> All custom service methods
 * should be put in this class. Whenever methods are added, rerun ServiceBuilder
 * to copy their definitions into the
 * {@link com.liferay.docs.guestbook.service.EntryLocalService} interface. <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM. </p>
 *
 * @author Diogo Peixoto
 * @see com.liferay.docs.guestbook.service.base.EntryLocalServiceBaseImpl
 * @see com.liferay.docs.guestbook.service.EntryLocalServiceUtil
 */
public class EntryLocalServiceImpl extends EntryLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use
	 * {@link com.liferay.docs.guestbook.service.EntryLocalServiceUtil} to
	 * access the entry local service.
	 */

	public Entry add(Entry entry, ServiceContext serviceContext)
		throws PortalException, SystemException {

		boolean addResource = false;
		if (entry.getEntryId() <= 0) {
			addResource = true;
		}

		setAttributes(entry, serviceContext);
		validate(entry);

		// Resource
		if (addResource) {
			resourceLocalService.addResources(
				serviceContext.getCompanyId(),
				serviceContext.getScopeGroupId(), serviceContext.getUserId(),
				Entry.class.getName(), entry.getEntryId(), false, true, true);

		}
		else {
			resourceLocalService.updateResources(
				serviceContext.getCompanyId(),
				serviceContext.getScopeGroupId(), Entry.class.getName(),
				entry.getEntryId(), serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}

		entry = super.updateEntry(entry);

		// Indexer
		updateEntryIndexer(entry, false);

		return entry;
	}

	public Entry delete(Entry entry)
		throws PortalException, SystemException {

		// Resource
		resourceLocalService.deleteResource(
			entry.getCompanyId(), Entry.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, entry.getEntryId());

		entry = super.deleteEntry(entry);

		// Indexer
		updateEntryIndexer(entry, true);

		return entry;
	}

	public int countByGroupIdGuestbookId(long groupId, long guestbookId)
		throws SystemException {

		return entryPersistence.countByG_G(groupId, guestbookId);
	}

	public List<Entry> findByGroupIdGuestbookId(long groupId, long guestbookId)
		throws SystemException {

		return entryPersistence.findByG_G(groupId, guestbookId);
	}

	public List<Entry> findByGroupIdGuestbookId(
		long groupId, long guestbookId, int start, int end)
		throws SystemException {

		return entryPersistence.findByG_G(groupId, guestbookId, start, end);
	}

	private void validate(Entry entry)
		throws PortalException, SystemException {

		if (Validator.isNull(entry.getName())) {
			throw new EntryNameException();
		}

		if (!Validator.isEmailAddress(entry.getEmail())) {
			throw new EntryEmailException();
		}

		if (Validator.isNull(entry.getMessage())) {
			throw new EntryMessageException();
		}

		guestbookPersistence.findByPrimaryKey(entry.getGuestbookId());
	}

	private void setAttributes(Entry entry, ServiceContext serviceContext)
		throws NoSuchUserException, SystemException {

		User user =
			userPersistence.findByPrimaryKey(serviceContext.getUserId());

		Date now = new Date();

		if (entry.getEntryId() <= 0) {
			long entryId = counterLocalService.increment(Entry.class.getName());

			entry.setEntryId(entryId);
			entry.setUuid(serviceContext.getUuid());

			entry.setCompanyId(user.getCompanyId());
			entry.setGroupId(serviceContext.getScopeGroupId());
			entry.setUserId(user.getUserId());
			entry.setUserName(user.getFullName());
			entry.setCreateDate(serviceContext.getCreateDate(now));
		}

		entry.setModifiedDate(serviceContext.getModifiedDate(now));

		entry.setExpandoBridgeAttributes(serviceContext);
	}

	private void updateEntryIndexer(Entry entry, boolean delete)
		throws SearchException {

		Indexer indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(Entry.class.getName());

		if (delete) {
			indexer.delete(entry);
		}
		else {
			indexer.reindex(entry);
		}
	}
}
