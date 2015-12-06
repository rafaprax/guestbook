<%@include file="/html/init.jsp"%>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/guestbook/view.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL name="addGuestbook" var="addGuestbookURL" />

<aui:form action="${addGuestbookURL}" name="guestbook-form">

	<aui:fieldset>
		<aui:input name="guestbook-name" label="name"/>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
		<aui:button type="cancel" onClick="${viewURL}" />
	</aui:button-row>
</aui:form>