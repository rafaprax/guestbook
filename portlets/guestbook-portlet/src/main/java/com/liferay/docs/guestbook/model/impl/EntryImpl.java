
package com.liferay.docs.guestbook.model.impl;

import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.GuestbookServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The extended model implementation for the Entry service. Represents a row in
 * the &quot;GB_Entry&quot; database table, with each column mapped to a
 * property of this class. <p> Helper methods and all application logic should
 * be put in this class. Whenever methods are added, rerun ServiceBuilder to
 * copy their definitions into the
 * {@link com.liferay.docs.guestbook.model.Entry} interface. </p>
 *
 * @author Diogo Peixoto
 */
public class EntryImpl extends EntryBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS: Never reference this class directly. All methods
	 * that expect a entry model instance should use the {@link
	 * com.liferay.docs.guestbook.model.Entry} interface instead.
	 */
	private Guestbook guestbook;

	public EntryImpl() {

	}

	public Guestbook getGuestbook()
		throws SystemException {

		if (guestbook == null) {
			guestbook = GuestbookServiceUtil.findByPrimaryKey(getGuestbookId());
		}
		return guestbook;
	}

	public void setGuestbook(Guestbook guestbook) {

		this.guestbook = guestbook;
	}
}
