
package com.liferay.docs.guestbook.service.permission;

import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

public class GuestbookPermission {

	public static void check(
		PermissionChecker permissionChecker, Guestbook guestbook,
		String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, guestbook, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, Guestbook guestbook,
		String actionId)
		throws PortalException, SystemException {

		try {

			return permissionChecker.hasPermission(
				guestbook.getGroupId(), Guestbook.class.getName(),
				guestbook.getGuestbookId(), actionId);
		}
		catch (Exception e) {
			System.out.println(guestbook);
			throw new PortalException();
		}
	}
}
