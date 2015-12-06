<%@include file="/html/init.jsp"%>

<c:set var="guestbookId" value="${guestbookId}" scope="request" />

<aui:nav cssClass="nav-tabs">

	<c:forEach items="${guestbook:findGuestbookByGroupId(scopeGroupId)}" var="guestbook">
	
		<portlet:renderURL var="viewPageURL">
			<portlet:param name="mvcPath" value="/html/guestbook/view.jsp" />
			<portlet:param name="guestbookId" value="${guestbook.guestbookId}" />
		</portlet:renderURL>
		
		<c:choose>
			<c:when test="${guestbook.guestbookId == guestbookId}">
				<aui:nav-item cssClass="active" href="${viewPageURL}" label="${guestbook.name}" />
			</c:when>
			
			<c:otherwise>
				<aui:nav-item href="${viewPageURL}" label="${guestbook.name}" />
			</c:otherwise>
		</c:choose>
	</c:forEach>
</aui:nav>

<aui:button-row cssClass="guestbook-buttons">

	<portlet:renderURL var="addGuestbookURL">	
		<portlet:param name="mvcPath" value="/html/guestbook/edit_guestbook.jsp" />
	</portlet:renderURL>

	<portlet:renderURL var="addEntryURL">
		<portlet:param name="mvcPath" value="/html/guestbook/edit_entry.jsp" />
		<portlet:param name="guestbookId" value="${guestbookId}" />
	</portlet:renderURL>

	<aui:button onClick="${addGuestbookURL}" value="Add Guestbook" />
	<aui:button onClick="${addEntryURL}" value="Add Entry"/>

</aui:button-row>

<liferay-ui:search-container>
	<liferay-ui:search-container-results
		results="${guestbook:findEntryByGuestbookId(scopeGroupId, guestbookId, searchContainer.start, searchContainer.end)}"
		total="${guestbook:countEntryByGuestbookId(scopeGroupId, guestbookId)}"/>

	<liferay-ui:search-container-row
		className="com.liferay.docs.guestbook.model.Entry" modelVar="entry">

		<liferay-ui:search-container-column-text property="message" />

		<liferay-ui:search-container-column-text property="name" />

	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>