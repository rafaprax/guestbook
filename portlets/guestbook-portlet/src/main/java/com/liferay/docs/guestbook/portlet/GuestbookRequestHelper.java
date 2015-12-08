
package com.liferay.docs.guestbook.portlet;

import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil;
import com.liferay.docs.guestbook.service.GuestbookServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;

public class GuestbookRequestHelper {

	public static Guestbook getGuestbookFromRequest(ActionRequest request)
		throws SystemException {

		long guestbookId =
			ParamUtil.getLong(request, RequestParameterKeys.GUESTBOOK_PARAM_ID);

		Guestbook guestbook;
		if (guestbookId > 0) {
			guestbook = GuestbookServiceUtil.findByPrimaryKey(guestbookId);
		}
		else {
			guestbook = GuestbookLocalServiceUtil.createGuestbook(0);
		}

		String name =
			ParamUtil.getString(
				request, RequestParameterKeys.GUESTBOOK_PARAM_NAME);
		guestbook.setName(name);

		return guestbook;
	}
}
