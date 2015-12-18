
package com.liferay.docs.guestbook.portlet;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactory;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;

public class OrderByComparatorUtil {

	public static OrderByComparator getEntryOrderComparatorByName() {

		OrderByComparatorFactory orderByComparatorFactory =
			OrderByComparatorFactoryUtil.getOrderByComparatorFactory();
		OrderByComparator orderByComparator =
			orderByComparatorFactory.create("Entry", "name", true);

		return orderByComparator;
	}
}
