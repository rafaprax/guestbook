<%@include file="/html/init.jsp"%>

<c:set var="guestbook" value="${assetRendererUtil:getGuestbook(renderRequest)}" />

<dl>
	<dt>Name</dt>
	<dd>${guestbook.name}</dd>
</dl>