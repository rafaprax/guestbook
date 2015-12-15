<%@page import="com.liferay.docs.guestbook.model.Entry"%>
<%@include file="/html/init.jsp"%>

<c:set var="guestbook" value="${guestbook:findGuestbookByPrimaryKey(param.guestbookId)}"/>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/guestbookadmin/view.jsp" />
</portlet:renderURL>

<portlet:actionURL name="addGuestbook" var="addGuestbookURL" />

<aui:model-context bean="${guestbook}" model="${classnameServices:getGuestbookClass()}" />

<aui:form action="${addGuestbookURL}" name="guestbook-form">

	<aui:fieldset>
		<aui:input name="name" label="name"/>
		<aui:input name='guestbookId' type='hidden' value='${param.guestbookId}' />
	</aui:fieldset>

	<liferay-ui:asset-categories-error />
	<liferay-ui:asset-tags-error />
	<liferay-ui:panel defaultState="closed" extended="false" id="guestbookCategorizationPanel" persistState="true" title="categorization">
		<aui:fieldset>
			<aui:input name="categories" type="assetCategories" />
			<aui:input name="tags" type="assetTags" />
		</aui:fieldset>
	</liferay-ui:panel>

	<liferay-ui:panel defaultState="closed" extended="false" id="guestbookAssetLinksPanel" persistState="true" title="related-assets">
		<aui:fieldset>
			<liferay-ui:input-asset-links className="${classnameServices:getGuestbookClassName()}" classPK="${param.guestbookId}"/>
		</aui:fieldset>
	</liferay-ui:panel>
	
	<aui:button-row>
		<aui:button type="submit"/>
		<aui:button type="cancel" onClick="${viewURL}"/>
	</aui:button-row>
</aui:form>