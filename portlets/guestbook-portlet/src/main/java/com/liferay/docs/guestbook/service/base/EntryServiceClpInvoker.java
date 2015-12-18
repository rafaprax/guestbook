package com.liferay.docs.guestbook.service.base;

import com.liferay.docs.guestbook.service.EntryServiceUtil;

import java.util.Arrays;

/**
 * @author Diogo Peixoto
 * @generated
 */
public class EntryServiceClpInvoker {
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

    public EntryServiceClpInvoker() {
        _methodName34 = "getBeanIdentifier";

        _methodParameterTypes34 = new String[] {  };

        _methodName35 = "setBeanIdentifier";

        _methodParameterTypes35 = new String[] { "java.lang.String" };

        _methodName40 = "add";

        _methodParameterTypes40 = new String[] {
                "com.liferay.docs.guestbook.model.Entry",
                "com.liferay.portal.service.ServiceContext"
            };

        _methodName41 = "delete";

        _methodParameterTypes41 = new String[] {
                "com.liferay.docs.guestbook.model.Entry"
            };

        _methodName42 = "countByGroupIdGuestbookId";

        _methodParameterTypes42 = new String[] { "long", "long" };

        _methodName43 = "findByPrimaryKey";

        _methodParameterTypes43 = new String[] { "long" };

        _methodName44 = "findByGroupIdGuestbookId";

        _methodParameterTypes44 = new String[] { "long", "long", "int", "int" };

        _methodName45 = "findByGroupIdGuestbookIdName";

        _methodParameterTypes45 = new String[] {
                "long", "long", "java.lang.String"
            };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName34.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
            return EntryServiceUtil.getBeanIdentifier();
        }

        if (_methodName35.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
            EntryServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        if (_methodName40.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
            return EntryServiceUtil.add((com.liferay.docs.guestbook.model.Entry) arguments[0],
                (com.liferay.portal.service.ServiceContext) arguments[1]);
        }

        if (_methodName41.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
            return EntryServiceUtil.delete((com.liferay.docs.guestbook.model.Entry) arguments[0]);
        }

        if (_methodName42.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
            return EntryServiceUtil.countByGroupIdGuestbookId(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue());
        }

        if (_methodName43.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
            return EntryServiceUtil.findByPrimaryKey(((Long) arguments[0]).longValue());
        }

        if (_methodName44.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
            return EntryServiceUtil.findByGroupIdGuestbookId(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                ((Integer) arguments[2]).intValue(),
                ((Integer) arguments[3]).intValue());
        }

        if (_methodName45.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
            return EntryServiceUtil.findByGroupIdGuestbookIdName(((Long) arguments[0]).longValue(),
                ((Long) arguments[1]).longValue(),
                (java.lang.String) arguments[2]);
        }

        throw new UnsupportedOperationException();
    }
}
