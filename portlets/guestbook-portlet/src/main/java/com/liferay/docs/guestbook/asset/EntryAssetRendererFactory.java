
package com.liferay.docs.guestbook.asset;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.service.EntryServiceUtil;
import com.liferay.docs.guestbook.service.permission.EntryPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.BaseAssetRendererFactory;

public class EntryAssetRendererFactory extends BaseAssetRendererFactory {

	public static final String TYPE = "entry";

	@Override
	public AssetRenderer getAssetRenderer(long classPK, int type)
		throws PortalException, SystemException {

		return new EntryAssetRenderer(
			EntryServiceUtil.findByPrimaryKey(classPK));
	}

	@Override
	public String getClassName() {

		return Entry.class.getName();
	}

	@Override
	public String getType() {

		return TYPE;
	}

	@Override
	public boolean hasPermission(
		PermissionChecker permissionChecker, long classPK, String actionId)
		throws Exception {

		Entry entry = EntryServiceUtil.findByPrimaryKey(classPK);

		return EntryPermission.contains(permissionChecker, entry, actionId);
	}

	@Override
	public boolean isLinkable() {

		return true;
	}
}
