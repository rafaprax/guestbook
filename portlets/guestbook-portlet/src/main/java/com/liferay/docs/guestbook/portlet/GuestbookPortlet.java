
package com.liferay.docs.guestbook.portlet;

import com.liferay.docs.guestbook.NoSuchGuestbookException;
import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.EntryLocalServiceUtil;
import com.liferay.docs.guestbook.service.EntryServiceUtil;
import com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil;
import com.liferay.docs.guestbook.service.GuestbookServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactory;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
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
			Guestbook guestbook = null;

			ServiceContext serviceContext =
				ServiceContextFactory.getInstance(
					Guestbook.class.getName(), renderRequest);

			List<Guestbook> guestbooks =
				GuestbookServiceUtil.findByGroupId(serviceContext.getScopeGroupId());

			// Generate Default Guestbook
			if (guestbooks.size() == 0) {
				guestbook = generateDefaultGuestbook(serviceContext);

			}
			else {
				guestbook =
					(Guestbook) renderRequest.getAttribute(CustomWebKeys.GUESTBOOK_ATTRIBUTE);

				if (Validator.isNull(guestbook)) {
					String guestbookName =
						GuestbookRequestHelper.getGuestbookNameFromRequest(renderRequest);

					if (Validator.isBlank(guestbookName)) {
						guestbook = guestbooks.get(0);
					}
					else {
						OrderByComparatorFactory orderByComparatorFactory =
							OrderByComparatorFactoryUtil.getOrderByComparatorFactory();
						OrderByComparator orderByComparator =
							orderByComparatorFactory.create(
								"guestbook", "name", true);

						guestbook =
							GuestbookServiceUtil.findByGroupIdName(
								serviceContext.getScopeGroupId(),
								guestbookName, orderByComparator);
					}
				}
			}
			renderRequest.setAttribute(
				CustomWebKeys.GUESTBOOK_ATTRIBUTE, guestbook);
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
			Entry entry = getEntryFromRequest(request, serviceContext);

			EntryServiceUtil.add(entry, serviceContext);

			SessionMessages.add(request, MessageKeys.ENTRY_ADDED);

			response.setRenderParameter(
				CustomWebKeys.ENTRY_PARAM_GUESTBOOK_NAME,
				entry.getGuestbook().getName());

		}
		catch (Exception e) {
			SessionErrors.add(request, e.getClass().getName());

			PortalUtil.copyRequestParameters(request, response);
			response.setRenderParameter("mvcPath", PagePaths.ENTRY_PAGE_EDIT);
		}

	}

	public void deleteEntry(ActionRequest request, ActionResponse response)
		throws PortalException, SystemException {

		ServiceContext serviceContext =
			ServiceContextFactory.getInstance(Entry.class.getName(), request);

		try {
			Entry entry = getEntryFromRequest(request, serviceContext);

			EntryServiceUtil.delete(entry);

			response.setRenderParameter(
				CustomWebKeys.ENTRY_PARAM_GUESTBOOK_NAME,
				entry.getGuestbook().getName());
		}
		catch (Exception e) {
			SessionErrors.add(request, e.getClass().getName());
		}
	}

	public void addGuestbook(ActionRequest request, ActionResponse response)
		throws PortalException, SystemException {

		ServiceContext serviceContext =
			ServiceContextFactory.getInstance(
				Guestbook.class.getName(), request);

		try {
			Guestbook guestbook =
				GuestbookRequestHelper.getGuestbookFromRequest(request);
			GuestbookServiceUtil.add(guestbook, serviceContext);

			SessionMessages.add(request, MessageKeys.GUESTBOOK_ADDED);

		}
		catch (Exception e) {
			SessionErrors.add(request, e.getMessage());

			response.setRenderParameter(
				"mvcPath", PagePaths.GUESTBOOK_PAGE_EDIT);
		}
	}

	private Guestbook generateDefaultGuestbook(ServiceContext serviceContext)
		throws PortalException, SystemException {

		Guestbook guestbook = GuestbookLocalServiceUtil.createGuestbook(0);
		guestbook.setName("Main");

		return GuestbookServiceUtil.add(guestbook, serviceContext);
	}

	private Entry getEntryFromRequest(
		ActionRequest request, ServiceContext serviceContext)
		throws SystemException, NoSuchGuestbookException {

		long entryId = ParamUtil.getLong(request, CustomWebKeys.ENTRY_PARAM_ID);

		Entry entry;
		if (entryId > 0) {
			entry = EntryServiceUtil.findByPrimaryKey(entryId);
		}
		else {
			entry = EntryLocalServiceUtil.createEntry(0);
		}

		String name =
			ParamUtil.getString(request, CustomWebKeys.ENTRY_PARAM_NAME);
		String email =
			ParamUtil.getString(request, CustomWebKeys.ENTRY_PARAM_EMAIL);
		String message =
			ParamUtil.getString(request, CustomWebKeys.ENTRY_PARAM_MESSAGE);
		entry.setName(name);
		entry.setEmail(email);
		entry.setMessage(message);

		OrderByComparatorFactory orderByComparatorFactory =
			OrderByComparatorFactoryUtil.getOrderByComparatorFactory();
		OrderByComparator orderByComparator =
			orderByComparatorFactory.create("guestbook", "name", true);

		String guestbookName =
			ParamUtil.getString(
				request, CustomWebKeys.ENTRY_PARAM_GUESTBOOK_NAME);
		Guestbook guestbook =
			GuestbookServiceUtil.findByGroupIdName(
				serviceContext.getScopeGroupId(), guestbookName,
				orderByComparator);
		entry.setGuestbookId(guestbook.getGuestbookId());
		entry.setGuestbook(guestbook);

		return entry;
	}
}
