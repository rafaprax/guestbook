
package com.liferay.docs.guestbook.service.impl;

import com.liferay.docs.guestbook.EntryEmailException;
import com.liferay.docs.guestbook.EntryMessageException;
import com.liferay.docs.guestbook.EntryNameException;
import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.service.base.EntryLocalServiceBaseImpl;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
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

		setAttributes(entry, serviceContext);
		validate(entry);

		return super.addEntry(entry);
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

		long entryId = counterLocalService.increment(Entry.class.getName());

		entry.setEntryId(entryId);
		entry.setUuid(serviceContext.getUuid());

		entry.setCompanyId(user.getCompanyId());
		entry.setGroupId(serviceContext.getScopeGroupId());
		entry.setUserId(user.getUserId());
		entry.setUserName(user.getFullName());
		entry.setCreateDate(serviceContext.getCreateDate(now));
		entry.setModifiedDate(serviceContext.getModifiedDate(now));

		entry.setExpandoBridgeAttributes(serviceContext);
	}
}
