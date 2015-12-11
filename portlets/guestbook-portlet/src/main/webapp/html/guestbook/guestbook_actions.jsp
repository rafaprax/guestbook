<%@include file="/html/init.jsp"%>

<c:set var="entry" value="${searchContainer:getObject(renderRequest)}" />

<liferay-ui:icon-menu>

	<c:if test="${permissionChecker:canPerfomActionEntry(permissionChecker,entry,'UPDATE')}">
		
		<portlet:renderURL var="editURL">
			<portlet:param name="entryId" value="${entry.entryId }" />
			<portlet:param name="mvcPath" value="/html/guestbook/edit_entry.jsp" />
		</portlet:renderURL>

		<liferay-ui:icon image="edit" message="edit" url="${editURL}" />
	</c:if>

	<c:if test="${permissionChecker:canPerfomActionEntry(permissionChecker,entry,'PERMISSIONS')}">

		<liferay-security:permissionsURL
			modelResource="${classnameServices:getEntryClassName()}"
			modelResourceDescription="${entry.message}"
			resourcePrimKey="${entry.entryId}"
			var="permissionsURL" />

		<liferay-ui:icon image="permissions" url="${permissionsURL}" />

	</c:if>

	<c:if test="${permissionChecker:canPerfomActionEntry(permissionChecker,entry,'DELETE')}">

		<portlet:actionURL name="deleteEntry" var="deleteURL">
			<portlet:param name="entryId" value="${entry.entryId}" />
			<portlet:param name="guestbookId" value="${entry.guestbookId}" />
		</portlet:actionURL>

		<liferay-ui:icon-delete url="${deleteURL}" />
	</c:if>

</liferay-ui:icon-menu>
