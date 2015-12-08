<%@include file="/html/init.jsp"%>

<c:set var="guestbook" value="${searchContainer:getObject(renderRequest)}" />

<liferay-ui:icon-menu>

	<c:if test="${permissionChecker:canPerfomActionGuestbook(permissionChecker,guestbook,'UPDATE')}">
		
		<portlet:renderURL var="editURL">
			<portlet:param name="guestbookId" value="${guestbook.guestbookId }" />
			<portlet:param name="mvcPath" value="/html/guestbookadmin/edit_guestbook.jsp" />
		</portlet:renderURL>

		<liferay-ui:icon image="edit" message="edit" url="${editURL}" />
	</c:if>

	<c:if test="${permissionChecker:canPerfomActionGuestbook(permissionChecker,guestbook,'PERMISSIONS')}">

		<liferay-security:permissionsURL
			modelResource="${classnameServices:getGuestbookClassname()}"
			modelResourceDescription="${guestbook.name}"
			resourcePrimKey="${guestbook.guestbookId}"
			var="permissionsURL" />

		<liferay-ui:icon image="permissions" url="${permissionsURL}" />

	</c:if>

	<c:if test="${permissionChecker:canPerfomActionGuestbook(permissionChecker,guestbook,'DELETE')}">

		<portlet:actionURL name="deleteGuestbook" var="deleteURL">
			<portlet:param name="guestbookId" value="${guestbook.guestbookId}" />
		</portlet:actionURL>

		<liferay-ui:icon-delete url="${deleteURL}" />
	</c:if>

</liferay-ui:icon-menu>
