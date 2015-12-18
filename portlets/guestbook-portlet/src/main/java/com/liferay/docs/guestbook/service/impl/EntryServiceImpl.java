
package com.liferay.docs.guestbook.service.impl;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.service.base.EntryServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The implementation of the entry remote service. <p> All custom service
 * methods should be put in this class. Whenever methods are added, rerun
 * ServiceBuilder to copy their definitions into the
 * {@link com.liferay.docs.guestbook.service.EntryService} interface. <p> This
 * is a remote service. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely. </p>
 *
 * @author Diogo Peixoto
 * @see com.liferay.docs.guestbook.service.base.EntryServiceBaseImpl
 * @see com.liferay.docs.guestbook.service.EntryServiceUtil
 */
public class EntryServiceImpl extends EntryServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use
	 * {@link com.liferay.docs.guestbook.service.EntryServiceUtil} to access the
	 * entry remote service.
	 */

	public Entry add(Entry entry, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return entryLocalService.add(entry, serviceContext);
	}

	public Entry delete(Entry entry)
		throws PortalException, SystemException {

		return entryLocalService.delete(entry);
	}

	public int countByGroupIdGuestbookId(long groupId, long guestbookId)
		throws SystemException {

		return entryLocalService.countByGroupIdGuestbookId(groupId, guestbookId);
	}

	public Entry findByPrimaryKey(long entryId)
		throws SystemException {

		return entryLocalService.fetchEntry(entryId);
	}

	public List<Entry> findByGroupIdGuestbookId(
		long groupId, long guestbookId, int start, int end)
		throws SystemException {

		return entryLocalService.findByGroupIdGuestbookId(
			groupId, guestbookId, start, end);
	}

	public List<Entry> findByGroupIdGuestbookIdName(
		long groupId, long guestbookId, String name)
		throws SystemException {

		return entryLocalService.findByGroupIdGuestbookIdName(
			groupId, guestbookId, name);
	}
}
