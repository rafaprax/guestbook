
package com.liferay.docs.guestbook.service.classname;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.model.Guestbook;

public class ClassnameService {

	public static Class<Entry> getEntryClassname() {

		return Entry.class;
	}

	public static Class<Guestbook> getGuestbookClassname() {

		return Guestbook.class;
	}
}
