package com.liferay.docs.guestbook.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.security.ac.AccessControlled;
import com.liferay.portal.service.BaseService;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service interface for Entry. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Diogo Peixoto
 * @see EntryServiceUtil
 * @see com.liferay.docs.guestbook.service.base.EntryServiceBaseImpl
 * @see com.liferay.docs.guestbook.service.impl.EntryServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface EntryService extends BaseService, InvokableService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link EntryServiceUtil} to access the entry remote service. Add custom service methods to {@link com.liferay.docs.guestbook.service.impl.EntryServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier();

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier);

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable;

    public com.liferay.docs.guestbook.model.Entry add(
        com.liferay.docs.guestbook.model.Entry entry,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.liferay.docs.guestbook.model.Entry delete(
        com.liferay.docs.guestbook.model.Entry entry)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public int countByGroupIdGuestbookId(long groupId, long guestbookId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.liferay.docs.guestbook.model.Entry findByPrimaryKey(long entryId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.liferay.docs.guestbook.model.Entry> findByGroupIdGuestbookId(
        long groupId, long guestbookId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;
}
