<%@include file="/html/init.jsp"%>

<c:set var="guestbookId" value="${guestbookId}" scope="request" />
<c:set var="guestbookSelected" value="${guestbook:findGuestbookByPrimaryKey(guestbookId)}"/>

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="mvcPath" value="/html/guestbook/view_search.jsp" />
</liferay-portlet:renderURL>

<aui:form action="${searchURL}" method="get" name="search-form">
    <liferay-portlet:renderURLParams varImpl="searchURL" />

    <div class="search-form">
        <span class="aui-search-bar">
            <aui:input inlineField="true" label="" name="keywords" size="30" title="search-entries" type="text" />

            <aui:button type="submit" value="search" />
        </span>
    </div>
</aui:form>

<aui:nav cssClass="nav-tabs">

	<c:forEach items="${guestbook:findGuestbookByGroupId(scopeGroupId)}" var="guestbook">
		<c:if test="${permissionChecker:canPerfomActionGuestbook(permissionChecker,guestbook,'VIEW')}">
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
		</c:if>
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

	<c:if test="${permissionChecker:canAddGuestbookModel(permissionChecker,scopeGroupId,'ADD_GUESTBOOK')}">
		<aui:button onClick="${addGuestbookURL}" value="Add Guestbook" />
	</c:if>
	<c:if test="${permissionChecker:canPerfomActionGuestbook(permissionChecker,guestbookSelected,'ADD_ENTRY')}">
		<aui:button onClick="${addEntryURL}" value="Add Entry"/>
	</c:if>

</aui:button-row>

<liferay-ui:search-container>
	<liferay-ui:search-container-results
		results="${guestbook:findEntryByGuestbookId(scopeGroupId, guestbookId, searchContainer.start, searchContainer.end)}"
		total="${guestbook:countEntryByGuestbookId(scopeGroupId, guestbookId)}"/>

	<liferay-ui:search-container-row
		className="com.liferay.docs.guestbook.model.Entry" modelVar="entry">

		<portlet:renderURL var="viewEntryURL">
	        <portlet:param name="mvcPath" value="/html/guestbook/view_entry.jsp" />
	        <portlet:param name="entryId" value="${entry.entryId}" />
		</portlet:renderURL>

		<liferay-ui:search-container-column-text property="message" href="${viewEntryURL}"/>
		<liferay-ui:search-container-column-text property="name" href="${viewEntryURL}"/>

		<liferay-ui:search-container-column-jsp path="/html/guestbook/guestbook_actions.jsp" align="right" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>