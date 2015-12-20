package com.liferay.docs.guestbook.service.http;

import com.liferay.docs.guestbook.service.GuestbookServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link com.liferay.docs.guestbook.service.GuestbookServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.docs.guestbook.model.GuestbookSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.docs.guestbook.model.Guestbook}, that is translated to a
 * {@link com.liferay.docs.guestbook.model.GuestbookSoap}. Methods that SOAP cannot
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
 * @see GuestbookServiceHttp
 * @see com.liferay.docs.guestbook.model.GuestbookSoap
 * @see com.liferay.docs.guestbook.service.GuestbookServiceUtil
 * @generated
 */
public class GuestbookServiceSoap {
    private static Log _log = LogFactoryUtil.getLog(GuestbookServiceSoap.class);

    public static com.liferay.docs.guestbook.model.GuestbookSoap add(
        com.liferay.docs.guestbook.model.GuestbookSoap guestbook,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws RemoteException {
        try {
            com.liferay.docs.guestbook.model.Guestbook returnValue = GuestbookServiceUtil.add(com.liferay.docs.guestbook.model.impl.GuestbookModelImpl.toModel(
                        guestbook), serviceContext);

            return com.liferay.docs.guestbook.model.GuestbookSoap.toSoapModel(returnValue);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static com.liferay.docs.guestbook.model.GuestbookSoap delete(
        com.liferay.docs.guestbook.model.GuestbookSoap guestbook)
        throws RemoteException {
        try {
            com.liferay.docs.guestbook.model.Guestbook returnValue = GuestbookServiceUtil.delete(com.liferay.docs.guestbook.model.impl.GuestbookModelImpl.toModel(
                        guestbook));

            return com.liferay.docs.guestbook.model.GuestbookSoap.toSoapModel(returnValue);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static int countByGroupId(long groupId) throws RemoteException {
        try {
            int returnValue = GuestbookServiceUtil.countByGroupId(groupId);

            return returnValue;
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static com.liferay.docs.guestbook.model.GuestbookSoap[] findByGroupId(
        long groupId) throws RemoteException {
        try {
            java.util.List<com.liferay.docs.guestbook.model.Guestbook> returnValue =
                GuestbookServiceUtil.findByGroupId(groupId);

            return com.liferay.docs.guestbook.model.GuestbookSoap.toSoapModels(returnValue);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static com.liferay.docs.guestbook.model.GuestbookSoap[] findByGroupId(
        long groupId, int start, int end) throws RemoteException {
        try {
            java.util.List<com.liferay.docs.guestbook.model.Guestbook> returnValue =
                GuestbookServiceUtil.findByGroupId(groupId, start, end);

            return com.liferay.docs.guestbook.model.GuestbookSoap.toSoapModels(returnValue);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static com.liferay.docs.guestbook.model.GuestbookSoap[] findByGroupId(
        long groupId, int status) throws RemoteException {
        try {
            java.util.List<com.liferay.docs.guestbook.model.Guestbook> returnValue =
                GuestbookServiceUtil.findByGroupId(groupId, status);

            return com.liferay.docs.guestbook.model.GuestbookSoap.toSoapModels(returnValue);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static com.liferay.docs.guestbook.model.GuestbookSoap findByGroupIdName(
        long groupId, java.lang.String name,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws RemoteException {
        try {
            com.liferay.docs.guestbook.model.Guestbook returnValue = GuestbookServiceUtil.findByGroupIdName(groupId,
                    name, orderByComparator);

            return com.liferay.docs.guestbook.model.GuestbookSoap.toSoapModel(returnValue);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static com.liferay.docs.guestbook.model.GuestbookSoap findByPrimaryKey(
        long guestbookId) throws RemoteException {
        try {
            com.liferay.docs.guestbook.model.Guestbook returnValue = GuestbookServiceUtil.findByPrimaryKey(guestbookId);

            return com.liferay.docs.guestbook.model.GuestbookSoap.toSoapModel(returnValue);
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }
}
