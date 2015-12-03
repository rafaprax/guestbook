
package com.liferay.docs.guestbook.portlet;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.EntryLocalServiceUtil;
import com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class GuestbookPortlet extends MVCPortlet {

	@Override
	public void render(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException, IOException {

		try {
			ServiceContext serviceContext =
				ServiceContextFactory.getInstance(
					Guestbook.class.getName(), renderRequest);

			long guestbookId =
				ParamUtil.getLong(
					renderRequest, ParamKeys.ENTRY_PARAM_GUESTBOOK_ID);

			List<Guestbook> guestbooks =
				GuestbookLocalServiceUtil.findByGroupId(serviceContext.getScopeGroupId());

			if (guestbooks.size() == 0) {
				Guestbook guestbook = generateDefaultGuestbook();
				guestbookId = guestbook.getGuestbookId();
			}

			if (!(guestbookId > 0)) {
				guestbookId = guestbooks.get(0).getGuestbookId();
			}

			renderRequest.setAttribute(
				ParamKeys.ENTRY_PARAM_GUESTBOOK_ID, guestbookId);

		}
		catch (Exception e) {
			throw new PortletException(e);
		}
		super.render(renderRequest, renderResponse);

	}

	public void addEntry(ActionRequest request, ActionResponse response)
		throws PortalException, SystemException {

		ServiceContext serviceContext =
			ServiceContextFactory.getInstance(Entry.class.getName(), request);

		try {
			Entry entry = getEntryFromRequest(request);

			EntryLocalServiceUtil.add(entry, serviceContext);

			SessionMessages.add(request, MessageKeys.ENTRY_ADDED);

			response.setRenderParameter(
				ParamKeys.ENTRY_PARAM_GUESTBOOK_ID,
				Long.toString(entry.getGuestbookId()));

		}
		catch (Exception e) {
			SessionErrors.add(request, e.getClass().getName());

			PortalUtil.copyRequestParameters(request, response);
			response.setRenderParameter("mvcPath", PagePaths.ENTRY_PAGE_EDIT);
		}

	}

	public void addGuestbook(ActionRequest request, ActionResponse response)
		throws PortalException, SystemException {

		ServiceContext serviceContext =
			ServiceContextFactory.getInstance(
				Guestbook.class.getName(), request);

		try {
			Guestbook guestbook = getGuestbookFromRequest(request);
			GuestbookLocalServiceUtil.add(guestbook, serviceContext);

			SessionMessages.add(request, MessageKeys.GUESTBOOK_ADDED);

		}
		catch (Exception e) {
			SessionErrors.add(request, e.getClass().getName());

			response.setRenderParameter(
				"mvcPath", PagePaths.GUESTBOOK_PAGE_EDIT);
		}
	}

	private Guestbook generateDefaultGuestbook() {

		Guestbook guestbook = GuestbookLocalServiceUtil.createGuestbook(0);
		guestbook.setName("Main");

		return guestbook;
	}

	private Entry getEntryFromRequest(ActionRequest request) {

		Entry entry = EntryLocalServiceUtil.createEntry(0);

		String userName =
			ParamUtil.getString(request, ParamKeys.ENTRY_PARAM_NAME);
		String email =
			ParamUtil.getString(request, ParamKeys.ENTRY_PARAM_EMAIL);
		String message =
			ParamUtil.getString(request, ParamKeys.ENTRY_PARAM_MESSAGE);
		long guestbookId =
			ParamUtil.getLong(request, ParamKeys.ENTRY_PARAM_GUESTBOOK_ID);

		entry.setUserName(userName);
		entry.setEmail(email);
		entry.setMessage(message);
		entry.setGuestbookId(guestbookId);

		return entry;
	}

	private Guestbook getGuestbookFromRequest(ActionRequest request) {

		Guestbook guestbook = GuestbookLocalServiceUtil.createGuestbook(0);

		String name =
			ParamUtil.getString(request, ParamKeys.GUESTBOOK_PARAM_NAME);
		guestbook.setName(name);

		return guestbook;
	}
}
