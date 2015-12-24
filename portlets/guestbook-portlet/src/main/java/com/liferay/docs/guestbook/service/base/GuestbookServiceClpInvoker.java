package com.liferay.docs.guestbook.service.base;

import com.liferay.docs.guestbook.service.GuestbookServiceUtil;

import java.util.Arrays;

/**
 * @author Diogo Peixoto
 * @generated
 */
public class GuestbookServiceClpInvoker {
    private String _methodName34;
    private String[] _methodParameterTypes34;
    private String _methodName35;
    private String[] _methodParameterTypes35;
    private String _methodName40;
    private String[] _methodParameterTypes40;
    private String _methodName41;
    private String[] _methodParameterTypes41;
    private String _methodName42;
    private String[] _methodParameterTypes42;
    private String _methodName43;
    private String[] _methodParameterTypes43;
    private String _methodName44;
    private String[] _methodParameterTypes44;
    private String _methodName45;
    private String[] _methodParameterTypes45;
    private String _methodName46;
    private String[] _methodParameterTypes46;
    private String _methodName47;
    private String[] _methodParameterTypes47;

    public GuestbookServiceClpInvoker() {
        _methodName34 = "getBeanIdentifier";

        _methodParameterTypes34 = new String[] {  };

        _methodName35 = "setBeanIdentifier";

        _methodParameterTypes35 = new String[] { "java.lang.String" };

        _methodName40 = "add";

        _methodParameterTypes40 = new String[] {
                "com.liferay.docs.guestbook.model.Guestbook",
                "com.liferay.portal.service.ServiceContext"
            };

        _methodName41 = "delete";

        _methodParameterTypes41 = new String[] {
                "com.liferay.docs.guestbook.model.Guestbook"
            };

        _methodName42 = "filterCountByGroupId";

        _methodParameterTypes42 = new String[] { "long" };

        _methodName43 = "findByGroupId";

        _methodParameterTypes43 = new String[] { "long" };

        _methodName44 = "findByGroupId";

        _methodParameterTypes44 = new String[] { "long", "int", "int" };

        _methodName45 = "findByGroupId";

        _methodParameterTypes45 = new String[] { "long", "int" };

        _methodName46 = "findByGroupIdName";

        _methodParameterTypes46 = new String[] {
                "long", "java.lang.String",
                "com.liferay.portal.kernel.util.OrderByComparator"
            };

        _methodName47 = "findByPrimaryKey";

        _methodParameterTypes47 = new String[] { "long" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName34.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
            return GuestbookServiceUtil.getBeanIdentifier();
        }

        if (_methodName35.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
            GuestbookServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName40.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
            return GuestbookServiceUtil.add((com.liferay.docs.guestbook.model.Guestbook) arguments[0],
                (com.liferay.portal.service.ServiceContext) arguments[1]);
        }

        if (_methodName41.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
            return GuestbookServiceUtil.delete((com.liferay.docs.guestbook.model.Guestbook) arguments[0]);
        }

        if (_methodName42.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
            return GuestbookServiceUtil.filterCountByGroupId(((Long) arguments[0]).longValue());
        }

        if (_methodName43.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
            return GuestbookServiceUtil.findByGroupId(((Long) arguments[0]).longValue());
        }

        if (_methodName44.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
            return GuestbookServiceUtil.findByGroupId(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue(),
                ((Integer) arguments[2]).intValue());
        }

        if (_methodName45.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
            return GuestbookServiceUtil.findByGroupId(((Long) arguments[0]).longValue(),
                ((Integer) arguments[1]).intValue());
        }

        if (_methodName46.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
            return GuestbookServiceUtil.findByGroupIdName(((Long) arguments[0]).longValue(),
                (java.lang.String) arguments[1],
                (com.liferay.portal.kernel.util.OrderByComparator) arguments[2]);
        }

        if (_methodName47.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
            return GuestbookServiceUtil.findByPrimaryKey(((Long) arguments[0]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
