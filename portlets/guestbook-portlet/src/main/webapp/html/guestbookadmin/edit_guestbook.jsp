<%@page import="com.liferay.docs.guestbook.model.Entry"%>
<%@include file="/html/init.jsp"%>

<c:set var="guestbook" value="${guestbook:findGuestbookByPrimaryKey(param.guestbookId)}"/>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/guestbookadmin/view.jsp" />
</portlet:renderURL>

<portlet:actionURL name="addGuestbook" var="addGuestbookURL" />

<aui:model-context bean="${guestbook}" model="${classnameServices:getGuestbookClassname()}" />

<aui:form action="${addGuestbookURL}" name="guestbook-form">

	<aui:fieldset>
		<aui:input name="name" label="name"/>
		<aui:input name='guestbookId' type='hidden' value='${param.guestbookId}' />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit"/>
		<aui:button type="cancel" onClick="${viewURL}"/>
	</aui:button-row>
</aui:form>