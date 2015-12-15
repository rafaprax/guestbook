<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@include file="/html/init.jsp"%>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/guestbookadmin/view.jsp"></portlet:param>
</portlet:renderURL>

<liferay-ui:header backURL="${viewURL}" title="guestbook" />

<c:set var="guestbook" value="${guestbook:findGuestbookByPrimaryKey(param.guestbookId)}"/>
<c:set var="currentURL" value="${lfr:getCurrentURL(renderRequest)}"/>
<c:set var="assetEntry" value="${lfr:getAssetEntry(classnameServices:getGuestbookClassName(),guestbookId)}"/>

${lfr:addBreadcrumbGuestbook(renderRequest,guestbook,currentURL)}

<dl>
	<dt>Name</dt>
	<dd>${guestbook.name}</dd>
</dl>

<c:if test="${themeDisplay.isSignedIn()}">
	<liferay-ui:panel-container extended="false" id="guestbookCollaborationPanelContainer" persistState="true">
		<liferay-ui:panel collapsible="true" extended="true"
			id="guestbookCollaborationPanel" persistState="true"
			title='<%=LanguageUtil.get(pageContext, "collaboration")%>'>
			<liferay-ui:ratings className="${classnameServices:getGuestbookClassName()}"
				classPK="${guestbook.guestbookId}" type="stars" />

			<br />

			<portlet:actionURL name="invokeTaglibDiscussion" var="discussionURL" />

			<liferay-ui:discussion className="${classnameServices:getGuestbookClassName()}"
				classPK="${guestbook.guestbookId}"
				formAction="${discussionURL}" formName="fm2"
				ratingsEnabled="true" redirect="${currentURL}"
				subject="${guestbook.name}"
				userId="${guestbook.userId}" />

		</liferay-ui:panel>
	</liferay-ui:panel-container>
</c:if>

<liferay-ui:asset-links
	assetEntryId="${assetEntry.entryId}"
	className="${classnameServices:getGuestbookClassName()}"
	classPK="${guestbook.guestbookId}" />