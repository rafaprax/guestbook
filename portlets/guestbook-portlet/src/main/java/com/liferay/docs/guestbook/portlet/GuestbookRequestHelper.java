
package com.liferay.docs.guestbook.portlet;

import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil;
import com.liferay.docs.guestbook.service.GuestbookServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;

import javax.portlet.ActionRequest;
import javax.portlet.RenderRequest;

public class GuestbookRequestHelper {

	public static Guestbook getGuestbookFromRequest(ActionRequest request)
		throws SystemException {

		long guestbookId =
			ParamUtil.getLong(request, CustomWebKeys.GUESTBOOK_PARAM_ID);

		Guestbook guestbook;
		if (guestbookId > 0) {
			guestbook = GuestbookServiceUtil.findByPrimaryKey(guestbookId);
		}
		else {
			guestbook = GuestbookLocalServiceUtil.createGuestbook(0);
		}

		String name =
			ParamUtil.getString(
				request, CustomWebKeys.GUESTBOOK_PARAM_NAME);
		guestbook.setName(name);

		return guestbook;
	}

	public static String getGuestbookNameFromRequest(RenderRequest renderRequest) {

		return ParamUtil.getString(
			renderRequest, CustomWebKeys.ENTRY_PARAM_GUESTBOOK_NAME,
			StringPool.BLANK);
	}
}
