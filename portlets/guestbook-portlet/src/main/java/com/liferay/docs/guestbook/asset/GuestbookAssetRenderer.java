
package com.liferay.docs.guestbook.asset;

import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.permission.GuestbookPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.BaseAssetRenderer;

import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

public class GuestbookAssetRenderer extends BaseAssetRenderer {

	private Guestbook guestbook;
	private Log log;

	public GuestbookAssetRenderer(Guestbook guestbook) {

		this.log = LogFactoryUtil.getLog(GuestbookAssetRenderer.class);
		this.guestbook = guestbook;
	}

	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) {

		boolean contains = false;

		try {
			contains =
				GuestbookPermission.contains(
					permissionChecker, guestbook, ActionKeys.UPDATE);
		}
		catch (PortalException | SystemException e) {
			log.error(e.getLocalizedMessage());
		}
		return contains;
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) {

		boolean contains = false;

		try {
			contains =
				GuestbookPermission.contains(
					permissionChecker, guestbook, ActionKeys.VIEW);
		}
		catch (PortalException | SystemException e) {
			log.error(e.getLocalizedMessage());
		}
		return contains;
	}

	@Override
	public String getClassName() {

		return Guestbook.class.getName();
	}

	@Override
	public long getClassPK() {

		return guestbook.getGuestbookId();
	}

	@Override
	public long getGroupId() {

		return guestbook.getGroupId();
	}

	@Override
	public String getSummary(Locale locale) {

		return "Name: " + guestbook.getName();
	}

	@Override
	public String getTitle(Locale locale) {

		return guestbook.getName();
	}

	@Override
	public long getUserId() {

		return guestbook.getUserId();
	}

	@Override
	public String getUserName() {

		return guestbook.getUserName();
	}

	@Override
	public String getUuid() {

		return guestbook.getUuid();
	}

	@Override
	protected String getIconPath(ThemeDisplay themeDisplay) {

		return themeDisplay.getURLPortal() + "/guestbook-portlet/guestbook.png";

	}

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse,
		String template)
		throws Exception {

		if (template.equals(TEMPLATE_FULL_CONTENT)) {
			renderRequest.setAttribute("gb_guestbook", guestbook);

			return "/html/guestbookadmin/" + template + ".jsp";
		}
		else {
			return null;
		}
	}
}
