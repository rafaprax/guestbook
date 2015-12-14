<%@include file="/html/init.jsp"%>

<c:set var="keywords" value="${param.keywords}"/>

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="mvcPath" value="/html/guestbook/view_search.jsp" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL varImpl="viewURL">
	<portlet:param name="mvcPath" value="/html/guestbook/view.jsp" />
</liferay-portlet:renderURL>

<aui:form action="${searchURL}" method="get" name="search-form">
    <liferay-portlet:renderURLParams varImpl="searchURL" />

    <liferay-ui:header backURL="${viewURL}" title="search"/>

    <div class="search-form">
        <span class="aui-search-bar">
            <aui:input inlineField="true" label="" name="keywords" size="30" title="search-entries" type="text" />

            <aui:button type="submit" value="search" />
        </span>
    </div>
</aui:form>

<liferay-ui:search-container delta="10" emptyResultsMessage="no-entries-were-found" searchContainer="${searchContainer:newEntrySearchContainer(renderRequest,keywords)}">

        <liferay-ui:search-container-row
                className="com.liferay.docs.guestbook.model.Entry"
                keyProperty="entryId" modelVar="entry" escapedModel="true">

                <liferay-ui:search-container-column-text property="message" />
                <liferay-ui:search-container-column-text property="name" />

                <liferay-ui:search-container-column-jsp path="/html/guestbook/guestbook_actions.jsp" align="right" />
        </liferay-ui:search-container-row>
        <liferay-ui:search-iterator />
</liferay-ui:search-container>