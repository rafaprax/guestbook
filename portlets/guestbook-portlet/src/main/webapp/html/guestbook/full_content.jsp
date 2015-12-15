<%@include file="/html/init.jsp"%>

<c:set var="entry" value="${assetRendererUtil:getEntry(renderRequest)}" />
<c:set var="guestbook" value="${guestbook:findGuestbookByPrimaryKey(entry.guestbookId)}" />

<dl>
	<dt>Guestbook</dt>
	<dd>${guestbook.name}</dd>
	<dt>Name</dt>
	<dd>${entry.name}</dd>
	<dt>Message</dt>
	<dd>${entry.message}</dd>
</dl>