
package com.liferay.docs.guestbook.service.classname;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.model.Guestbook;

public class ClassnameService {

	public static Class<Entry> getEntryClass() {

		return Entry.class;
	}

	public static String getEntryClassName() {

		return Entry.class.getName();
	}

	public static Class<Guestbook> getGuestbookClass() {

		return Guestbook.class;
	}

	public static String getGuestbookClassName() {

		return Guestbook.class.getName();
	}
}
