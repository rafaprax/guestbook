package com.liferay.docs.guestbook.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the Entry service. Represents a row in the &quot;GB_Entry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Diogo Peixoto
 * @see EntryModel
 * @see com.liferay.docs.guestbook.model.impl.EntryImpl
 * @see com.liferay.docs.guestbook.model.impl.EntryModelImpl
 * @generated
 */
public interface Entry extends EntryModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.liferay.docs.guestbook.model.impl.EntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public com.liferay.docs.guestbook.model.Guestbook getGuestbook()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setGuestbook(
        com.liferay.docs.guestbook.model.Guestbook guestbook);
}
