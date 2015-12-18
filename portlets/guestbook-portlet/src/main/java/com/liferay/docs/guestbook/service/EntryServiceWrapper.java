package com.liferay.docs.guestbook.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EntryService}.
 *
 * @author Diogo Peixoto
 * @see EntryService
 * @generated
 */
public class EntryServiceWrapper implements EntryService,
    ServiceWrapper<EntryService> {
    private EntryService _entryService;

    public EntryServiceWrapper(EntryService entryService) {
        _entryService = entryService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _entryService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _entryService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _entryService.invokeMethod(name, parameterTypes, arguments);
    }

    @Override
    public com.liferay.docs.guestbook.model.Entry add(
        com.liferay.docs.guestbook.model.Entry entry,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _entryService.add(entry, serviceContext);
    }

    @Override
    public com.liferay.docs.guestbook.model.Entry delete(
        com.liferay.docs.guestbook.model.Entry entry)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _entryService.delete(entry);
    }

    @Override
    public int countByGroupIdGuestbookId(long groupId, long guestbookId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _entryService.countByGroupIdGuestbookId(groupId, guestbookId);
    }

    @Override
    public com.liferay.docs.guestbook.model.Entry findByPrimaryKey(long entryId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _entryService.findByPrimaryKey(entryId);
    }

    @Override
    public java.util.List<com.liferay.docs.guestbook.model.Entry> findByGroupIdGuestbookId(
        long groupId, long guestbookId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _entryService.findByGroupIdGuestbookId(groupId, guestbookId,
            start, end);
    }

    @Override
    public java.util.List<com.liferay.docs.guestbook.model.Entry> findByGroupIdGuestbookIdName(
        long groupId, long guestbookId, java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _entryService.findByGroupIdGuestbookIdName(groupId, guestbookId,
            name);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public EntryService getWrappedEntryService() {
        return _entryService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedEntryService(EntryService entryService) {
        _entryService = entryService;
    }

    @Override
    public EntryService getWrappedService() {
        return _entryService;
    }

    @Override
    public void setWrappedService(EntryService entryService) {
        _entryService = entryService;
    }
}
