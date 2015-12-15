<%@include file="/html/init.jsp"%>

<c:set var="entry" value="${guestbook:findEntryByPrimaryKey(param.entryId)}"/>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/guestbook/view.jsp" />
</portlet:renderURL>

<portlet:actionURL name="addEntry" var="addEntryURL" />

<aui:model-context bean="${entry}" model="${classnameServices:getEntryClass()}" />

<aui:form action="${addEntryURL}" name="entry-form">

	<aui:fieldset>
		<aui:input name="name" label="name"/>
		<aui:input name="email" label="email"/>
		<aui:input name="message" label="message"/>
		<aui:input name='guestbookId' type='hidden' value='${param.guestbookId}' />
		<aui:input name="entryId" type="hidden" value="${entry.entryId}" />
	</aui:fieldset>

	<liferay-ui:asset-categories-error />
	<liferay-ui:asset-tags-error />
	<liferay-ui:panel defaultState="closed" extended="false" id="entryCategorizationPanel" persistState="true" title="categorization">
		<aui:fieldset>
			<aui:input name="categories" type="assetCategories" />
			<aui:input name="tags" type="assetTags" />
		</aui:fieldset>
	</liferay-ui:panel>

	<liferay-ui:panel defaultState="closed" extended="false" id="entryAssetLinksPanel" persistState="true" title="related-assets">
		<aui:fieldset>
			<liferay-ui:input-asset-links className="${classnameServices:getEntryClassName()}" classPK="${entryId}" />
		</aui:fieldset>
	</liferay-ui:panel>

	<aui:button-row>
		<aui:button type="submit"/>
		<aui:button type="cancel" onClick="${viewURL}"/>
	</aui:button-row>
</aui:form>