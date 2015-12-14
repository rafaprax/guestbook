
package com.liferay.docs.guestbook.asset;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.service.permission.EntryPermission;
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

public class EntryAssetRenderer extends BaseAssetRenderer {

	private Entry entry;
	private Log log;

	public EntryAssetRenderer(Entry entry) {

		this.log = LogFactoryUtil.getLog(EntryAssetRenderer.class);
		this.entry = entry;
	}

	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) {

		boolean contains = false;

		try {
			contains =
				EntryPermission.contains(
					permissionChecker, entry, ActionKeys.UPDATE);
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
				EntryPermission.contains(
					permissionChecker, entry, ActionKeys.VIEW);
		}
		catch (PortalException | SystemException e) {
			log.error(e.getLocalizedMessage());
		}

		return contains;
	}

	@Override
	public String getClassName() {

		return Entry.class.getName();
	}

	@Override
	public long getClassPK() {

		return entry.getEntryId();
	}

	@Override
	public long getGroupId() {

		return entry.getGroupId();
	}

	@Override
	public String getSummary(Locale locale) {

		return "Name: " + entry.getName() + ". Message: " + entry.getMessage();
	}

	@Override
	public String getTitle(Locale locale) {

		return entry.getMessage();
	}

	@Override
	public long getUserId() {

		return entry.getUserId();
	}

	@Override
	public String getUserName() {

		return entry.getUserName();
	}

	@Override
	public String getUuid() {

		return entry.getUuid();
	}

	@Override
	protected String getIconPath(ThemeDisplay themeDisplay) {

		return themeDisplay.getURLPortal() + "/guestbook-portlet/entry.png";

	}

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse,
		String template)
		throws Exception {

		if (template.equals(TEMPLATE_FULL_CONTENT)) {
			renderRequest.setAttribute("gb_entry", entry);

			return "/html/guestbook/" + template + ".jsp";
		}
		else {
			return null;
		}
	}
}
