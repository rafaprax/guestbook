
package com.liferay.docs.guestbook.service.searchcontainer;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.portlet.searchcontainer.EntrySearchContainer;
import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PortalUtil;

import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;

public class SearchContainerService {

	public static Object getObject(RenderRequest request) {

		ResultRow row =
			(ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

		return row.getObject();
	}

	public static SearchContainer<Entry> newEntrySearchContainer(
		PortletRequest portletRequest, String keywords) {

		HttpServletRequest httpServletRequest =
			PortalUtil.getHttpServletRequest(portletRequest);

		return new EntrySearchContainer(httpServletRequest, keywords);
	}
}
