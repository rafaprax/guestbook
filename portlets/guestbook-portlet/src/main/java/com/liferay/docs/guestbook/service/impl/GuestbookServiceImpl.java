
package com.liferay.docs.guestbook.service.impl;

import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.base.GuestbookServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The implementation of the guestbook remote service. <p> All custom service
 * methods should be put in this class. Whenever methods are added, rerun
 * ServiceBuilder to copy their definitions into the
 * {@link com.liferay.docs.guestbook.service.GuestbookService} interface. <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely. </p>
 *
 * @author Diogo Peixoto
 * @see com.liferay.docs.guestbook.service.base.GuestbookServiceBaseImpl
 * @see com.liferay.docs.guestbook.service.GuestbookServiceUtil
 */
public class GuestbookServiceImpl extends GuestbookServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use
	 * {@link com.liferay.docs.guestbook.service.GuestbookServiceUtil} to access
	 * the guestbook remote service.
	 */

	public Guestbook add(Guestbook guestbook, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return guestbookLocalService.add(guestbook, serviceContext);
	}

	public List<Guestbook> findByGroupId(long groupId)
		throws SystemException {

		return guestbookLocalService.findByGroupId(groupId);
	}

	public Guestbook findByPrimaryKey(long guestbookId)
		throws SystemException {

		return guestbookLocalService.fetchGuestbook(guestbookId);
	}
}
