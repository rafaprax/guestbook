
package com.liferay.docs.guestbook.service.searchcontainer;

import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.RenderRequest;

public class SearchContainerService {

	public static Object getObject(RenderRequest request) {

		ResultRow row =
			(ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

		return row.getObject();
	}
}
