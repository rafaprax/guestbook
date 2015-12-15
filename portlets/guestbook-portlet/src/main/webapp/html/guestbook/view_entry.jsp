<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@include file="/html/init.jsp"%>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/guestbook/view.jsp"></portlet:param>
</portlet:renderURL>

<liferay-ui:header backURL="${viewURL}" title="entry" />

<c:set var="entry" value="${guestbook:findEntryByPrimaryKey(param.entryId)}"/>
<c:set var="currentURL" value="${lfr:getCurrentURL(renderRequest)}"/>
<c:set var="assetEntry" value="${lfr:getAssetEntry(classnameServices:getEntryClassName(),entry.entryId)}"/>
<c:set var="guestbook" value="${guestbook:findGuestbookByPrimaryKey(entry.guestbookId)}"/>

<dl>
	<dt>Guestbook</dt>
	<dd>${guestbook.name}</dd>
	<dt>Name</dt>
	<dd>${entry.name}</dd>
	<dt>Message</dt>
	<dd>${entry.message}</dd>
</dl>

<c:if test="${themeDisplay.isSignedIn()}">
	<liferay-ui:panel-container extended="false"
		id="entryCollaborationPanelContainer" persistState="true">
		<liferay-ui:panel collapsible="true" extended="true"
			id="entryCollaborationPanel" persistState="true"
			title='<%=LanguageUtil.get(pageContext, "collaboration")%>'>
			<liferay-ui:ratings className="${classnameServices:getEntryClassName()}" classPK="${entry.entryId}" type="stars" />

			<br />

			<portlet:actionURL name="invokeTaglibDiscussion" var="discussionURL" />

			<liferay-ui:discussion className="${classnameServices:getEntryClassName()}"
				classPK="${entry.entryId}" formAction="<%=discussionURL%>" formName="fm2"
				ratingsEnabled="true" redirect="${currentURL}" subject="${entry.message}" userId="${entry.userId}" />

		</liferay-ui:panel>
	</liferay-ui:panel-container>
</c:if>

<liferay-ui:asset-links
	assetEntryId="${assetEntry.entryId}"
	className="${classnameServices:getEntryClassName()}"
	classPK="${entry.entryId}" />