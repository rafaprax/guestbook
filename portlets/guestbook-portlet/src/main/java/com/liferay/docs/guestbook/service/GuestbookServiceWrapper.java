package com.liferay.docs.guestbook.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link GuestbookService}.
 *
 * @author Diogo Peixoto
 * @see GuestbookService
 * @generated
 */
public class GuestbookServiceWrapper implements GuestbookService,
    ServiceWrapper<GuestbookService> {
    private GuestbookService _guestbookService;

    public GuestbookServiceWrapper(GuestbookService guestbookService) {
        _guestbookService = guestbookService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _guestbookService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _guestbookService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _guestbookService.invokeMethod(name, parameterTypes, arguments);
    }

    @Override
    public com.liferay.docs.guestbook.model.Guestbook add(
        com.liferay.docs.guestbook.model.Guestbook guestbook,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _guestbookService.add(guestbook, serviceContext);
    }

    @Override
    public com.liferay.docs.guestbook.model.Guestbook delete(
        com.liferay.docs.guestbook.model.Guestbook guestbook)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _guestbookService.delete(guestbook);
    }

    @Override
    public int countByGroupId(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _guestbookService.countByGroupId(groupId);
    }

    @Override
    public java.util.List<com.liferay.docs.guestbook.model.Guestbook> findByGroupId(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _guestbookService.findByGroupId(groupId);
    }

    @Override
    public java.util.List<com.liferay.docs.guestbook.model.Guestbook> findByGroupId(
        long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _guestbookService.findByGroupId(groupId, start, end);
    }

    @Override
    public com.liferay.docs.guestbook.model.Guestbook findByPrimaryKey(
        long guestbookId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _guestbookService.findByPrimaryKey(guestbookId);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public GuestbookService getWrappedGuestbookService() {
        return _guestbookService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedGuestbookService(GuestbookService guestbookService) {
        _guestbookService = guestbookService;
    }

    @Override
    public GuestbookService getWrappedService() {
        return _guestbookService;
    }

    @Override
    public void setWrappedService(GuestbookService guestbookService) {
        _guestbookService = guestbookService;
    }
}
