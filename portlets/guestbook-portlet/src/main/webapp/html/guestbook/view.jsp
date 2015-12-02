<%@include file="/html/init.jsp"%>

<aui:button-row cssClass="guestbook-buttons">
	<portlet:renderURL var="addEntryURL">
		<portlet:param name="mvcPath" value="/html/guestbook/edit_entry.jsp" />
	</portlet:renderURL>

	<aui:button onClick="${addEntryURL}" value="entry-add"/>
</aui:button-row>

<jsp:useBean id="entries" class="java.util.ArrayList" scope="request"/>

<liferay-ui:search-container>
    <liferay-ui:search-container-results results="${entries}" />

    <liferay-ui:search-container-row
        className="com.liferay.docs.guestbook.model.Entry"
        modelVar="entry"
    >
        <liferay-ui:search-container-column-text property="message" />

        <liferay-ui:search-container-column-text property="name" />
    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator />
</liferay-ui:search-container>