<%@include file="/html/init.jsp"%>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/guestbook/view.jsp" />
</portlet:renderURL>

<portlet:actionURL name="addEntry" var="addEntryURL" />

<aui:form action="${addEntryURL}" name="entry-form">

	<aui:fieldset>
		<aui:input name="entry-name" label="name"/>
		<aui:input name="entry-email" label="email"/>
		<aui:input name="entry-message" label="message"/>
		<aui:input name='entry-guestbook-id' type='hidden' value='${param.guestbookId}' />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit"></aui:button>
		<aui:button type="cancel" onClick="${viewURL}"></aui:button>
	</aui:button-row>
</aui:form>