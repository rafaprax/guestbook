package com.liferay.docs.guestbook.service.base;

import com.liferay.docs.guestbook.service.GuestbookServiceUtil;

import java.util.Arrays;

/**
 * @author Diogo Peixoto
 * @generated
 */
public class GuestbookServiceClpInvoker {
    private String _methodName24;
    private String[] _methodParameterTypes24;
    private String _methodName25;
    private String[] _methodParameterTypes25;
    private String _methodName30;
    private String[] _methodParameterTypes30;
    private String _methodName31;
    private String[] _methodParameterTypes31;
    private String _methodName32;
    private String[] _methodParameterTypes32;

    public GuestbookServiceClpInvoker() {
        _methodName24 = "getBeanIdentifier";

        _methodParameterTypes24 = new String[] {  };

        _methodName25 = "setBeanIdentifier";

        _methodParameterTypes25 = new String[] { "java.lang.String" };

        _methodName30 = "add";

        _methodParameterTypes30 = new String[] {
                "com.liferay.docs.guestbook.model.Guestbook",
                "com.liferay.portal.service.ServiceContext"
            };

        _methodName31 = "findByGroupId";

        _methodParameterTypes31 = new String[] { "long" };

        _methodName32 = "findByPrimaryKey";

        _methodParameterTypes32 = new String[] { "long" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName24.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
            return GuestbookServiceUtil.getBeanIdentifier();
        }

        if (_methodName25.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
            GuestbookServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName30.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
            return GuestbookServiceUtil.add((com.liferay.docs.guestbook.model.Guestbook) arguments[0],
                (com.liferay.portal.service.ServiceContext) arguments[1]);
        }

        if (_methodName31.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
            return GuestbookServiceUtil.findByGroupId(((Long) arguments[0]).longValue());
        }

        if (_methodName32.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
            return GuestbookServiceUtil.findByPrimaryKey(((Long) arguments[0]).longValue());
        }

        throw new UnsupportedOperationException();
    }
}
