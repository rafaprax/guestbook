
package com.liferay.docs.guestbook.asset;

import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.GuestbookServiceUtil;
import com.liferay.docs.guestbook.service.permission.GuestbookPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.BaseAssetRendererFactory;

public class GuestbookAssetRendererFactory extends BaseAssetRendererFactory {

	public static final String TYPE = "guestbook";

	@Override
	public AssetRenderer getAssetRenderer(long classPK, int type)
		throws PortalException, SystemException {

		return new GuestbookAssetRenderer(
			GuestbookServiceUtil.findByPrimaryKey(classPK));
	}

	@Override
	public String getClassName() {

		return Guestbook.class.getName();
	}

	@Override
	public String getType() {

		return TYPE;
	}

	@Override
	public boolean hasPermission(
		PermissionChecker permissionChecker, long classPK, String actionId)
		throws Exception {

		Guestbook guestbook = GuestbookServiceUtil.findByPrimaryKey(classPK);

		return GuestbookPermission.contains(
			permissionChecker, guestbook, actionId);
	}

	@Override
	public boolean isLinkable() {

		return true;
	}
}
