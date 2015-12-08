
package com.liferay.docs.guestbook.portlet;

import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.GuestbookServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

public class GuestbookAdminPortlet extends MVCPortlet {

	public void addGuestbook(ActionRequest request, ActionResponse response)
		throws PortalException, SystemException {

		ServiceContext serviceContext =
			ServiceContextFactory.getInstance(
				Guestbook.class.getName(), request);

		Guestbook guestbook =
			GuestbookRequestHelper.getGuestbookFromRequest(request);

		try {
			boolean newGuestbook = true;
			if (guestbook.getGuestbookId() > 0) {
				newGuestbook = false;
			}
			GuestbookServiceUtil.add(guestbook, serviceContext);

			if (newGuestbook) {
				SessionMessages.add(request, MessageKeys.GUESTBOOK_ADDED);
			}
			else {
				SessionMessages.add(request, MessageKeys.GUESTBOOK_UPDATED);
			}
		}
		catch (PortalException e) {
			SessionErrors.add(request, e.getClass().getName());

			response.setRenderParameter(
				"mvcPath", PagePaths.GUESTBOOK_PAGE_EDIT);
		}
	}

	public void deleteGuestbook(ActionRequest request, ActionResponse response)
		throws PortalException, SystemException {

		try {
			Guestbook guestbook =
				GuestbookRequestHelper.getGuestbookFromRequest(request);

			GuestbookServiceUtil.delete(guestbook);
			SessionMessages.add(request, MessageKeys.GUESTBOOK_DELETED);
		}
		catch (Exception e) {
			SessionErrors.add(request, e.getClass().getName());
		}
	}
}
