
package com.liferay.docs.guestbook.service.asset;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.model.Guestbook;

import javax.portlet.RenderRequest;

public class AssetRendererUtil {

	public static Entry getEntry(RenderRequest request) {

		return (Entry) request.getAttribute("gb_entry");
	}

	public static Guestbook getGuestbook(RenderRequest request) {

		return (Guestbook) request.getAttribute("gb_guestbook");
	}
}
