
package com.liferay.docs.guestbook.service.asset;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetTag;
import com.liferay.portlet.asset.service.AssetTagLocalServiceUtil;

import java.util.List;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

public class PortletBreadcrumbUtil {

	public static boolean addBreadcrumbGuestbook(
		PortletRequest request, Guestbook guestbook, String currentURL)
		throws SystemException {

		HttpServletRequest servletRequest =
			PortalUtil.getHttpServletRequest(request);

		PortalUtil.addPortletBreadcrumbEntry(
			servletRequest, guestbook.getName(), currentURL);

		PortalUtil.setPageSubtitle(guestbook.getName(), servletRequest);
		PortalUtil.setPageDescription(guestbook.getName(), servletRequest);

		List<AssetTag> assetTags =
			AssetTagLocalServiceUtil.getTags(
				Guestbook.class.getName(), guestbook.getGuestbookId());
		PortalUtil.setPageKeywords(
			ListUtil.toString(assetTags, "name"), servletRequest);

		return true;
	}

	public static boolean addBreadcrumbEntry(
		PortletRequest request, Entry entry, String currentURL)
		throws SystemException {

		HttpServletRequest servletRequest =
			PortalUtil.getHttpServletRequest(request);

		PortalUtil.addPortletBreadcrumbEntry(
			servletRequest, entry.getName(), currentURL);

		PortalUtil.setPageSubtitle(entry.getName(), servletRequest);
		PortalUtil.setPageDescription(entry.getName(), servletRequest);

		List<AssetTag> assetTags =
			AssetTagLocalServiceUtil.getTags(
				Guestbook.class.getName(), entry.getGuestbookId());
		PortalUtil.setPageKeywords(
			ListUtil.toString(assetTags, "name"), servletRequest);

		return true;
	}
}
