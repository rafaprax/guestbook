package com.liferay.docs.guestbook.service.http;

import com.liferay.docs.guestbook.service.EntryServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link com.liferay.docs.guestbook.service.EntryServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.docs.guestbook.model.EntrySoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.docs.guestbook.model.Entry}, that is translated to a
 * {@link com.liferay.docs.guestbook.model.EntrySoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Diogo Peixoto
 * @see EntryServiceHttp
 * @see com.liferay.docs.guestbook.model.EntrySoap
 * @see com.liferay.docs.guestbook.service.EntryServiceUtil
 * @generated
 */
public class EntryServiceSoap {
    private static Log _log = LogFactoryUtil.getLog(EntryServiceSoap.class);

    public static com.liferay.docs.guestbook.model.EntrySoap add(
        com.liferay.docs.guestbook.model.EntrySoap entry,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws RemoteException {
        try {
            com.liferay.docs.guestbook.model.Entry returnValue = EntryServiceUtil.add(com.liferay.docs.guestbook.model.impl.EntryModelImpl.toModel(
                        entry), serviceContext);

            return com.liferay.docs.guestbook.model.EntrySoap.toSoapModel(returnValue);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static com.liferay.docs.guestbook.model.EntrySoap delete(
        com.liferay.docs.guestbook.model.EntrySoap entry)
        throws RemoteException {
        try {
            com.liferay.docs.guestbook.model.Entry returnValue = EntryServiceUtil.delete(com.liferay.docs.guestbook.model.impl.EntryModelImpl.toModel(
                        entry));

            return com.liferay.docs.guestbook.model.EntrySoap.toSoapModel(returnValue);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static int countByGroupIdGuestbookId(long groupId, long guestbookId)
        throws RemoteException {
        try {
            int returnValue = EntryServiceUtil.countByGroupIdGuestbookId(groupId,
                    guestbookId);

            return returnValue;
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static com.liferay.docs.guestbook.model.EntrySoap findByPrimaryKey(
        long entryId) throws RemoteException {
        try {
            com.liferay.docs.guestbook.model.Entry returnValue = EntryServiceUtil.findByPrimaryKey(entryId);

            return com.liferay.docs.guestbook.model.EntrySoap.toSoapModel(returnValue);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static com.liferay.docs.guestbook.model.EntrySoap[] findByGroupIdGuestbookId(
        long groupId, long guestbookId, int start, int end)
        throws RemoteException {
        try {
            java.util.List<com.liferay.docs.guestbook.model.Entry> returnValue = EntryServiceUtil.findByGroupIdGuestbookId(groupId,
                    guestbookId, start, end);

            return com.liferay.docs.guestbook.model.EntrySoap.toSoapModels(returnValue);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }
}
