package com.liferay.docs.guestbook.service.base;

import com.liferay.docs.guestbook.service.EntryServiceUtil;

import java.util.Arrays;

/**
 * @author Diogo Peixoto
 * @generated
 */
public class EntryServiceClpInvoker {
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

    public EntryServiceClpInvoker() {
        _methodName24 = "getBeanIdentifier";

        _methodParameterTypes24 = new String[] {  };

        _methodName25 = "setBeanIdentifier";

        _methodParameterTypes25 = new String[] { "java.lang.String" };

        _methodName30 = "add";

        _methodParameterTypes30 = new String[] {
                "com.liferay.docs.guestbook.model.Entry",
                "com.liferay.portal.service.ServiceContext"
            };

        _methodName31 = "countByGroupIdGuestbookId";

        _methodParameterTypes31 = new String[] { "long", "long" };

        _methodName32 = "findByGroupIdGuestbookId";

        _methodParameterTypes32 = new String[] { "long", "long", "int", "int" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName24.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
            return EntryServiceUtil.getBeanIdentifier();
        }

        if (_methodName25.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
            EntryServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName30.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
            return EntryServiceUtil.add((com.liferay.docs.guestbook.model.Entry) arguments[0],
                (com.liferay.portal.service.ServiceContext) arguments[1]);
        }

        if (_methodName31.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
            return EntryServiceUtil.countByGroupIdGuestbookId(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName32.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
            return EntryServiceUtil.findByGroupIdGuestbookId(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Integer) arguments[2]).intValue(),
                ((Integer) arguments[3]).intValue());
        }

        throw new UnsupportedOperationException();
    }
}
