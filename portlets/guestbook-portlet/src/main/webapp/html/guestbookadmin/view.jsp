<%@include file="/html/init.jsp"%>

<aui:button-row cssClass="guestbook-admin-buttons">
	<c:if test="${permissionChecker:canAddGuestbookModel(permissionChecker,scopeGroupId,'ADD_GUESTBOOK')}">
		<portlet:renderURL var="addGuestbookURL">
			<portlet:param name="mvcPath" value="/html/guestbookadmin/edit_guestbook.jsp" />
		</portlet:renderURL>
	
		<aui:button onClick="${addGuestbookURL}" value="Add Guestbook" />
	</c:if>
</aui:button-row>

<liferay-ui:search-container>
	<liferay-ui:search-container-results
		results="${guestbook:findGuestbookByGroupIdPaginator(scopeGroupId,searchContainer.getStart(),searchContainer.getEnd())}"
		total="${guestbook:countGuestbookByGroupId(scopeGroupId)}" />

	<liferay-ui:search-container-row modelVar="guestbook" className="com.liferay.docs.guestbook.model.Guestbook" >
	
		<portlet:renderURL var="viewGuestbookURL">
	        <portlet:param name="mvcPath" value="/html/guestbookadmin/view_guestbook.jsp" />
	        <portlet:param name="guestbookId" value="${guestbook.guestbookId}" />
		</portlet:renderURL>

		<liferay-ui:search-container-column-text property="name" href="${viewGuestbookURL}" />
		<liferay-ui:search-container-column-jsp path="/html/guestbookadmin/guestbook_actions.jsp" align="right" />
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator />
</liferay-ui:search-container>