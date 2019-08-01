<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"%>
    <%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

</head>
<body>
<fieldset>
    <legend><b><spring:message code="conference.data"/></b></legend>
    <p><acme:showtext code="conference.title" value="${conference.title}" fieldset="false"/></p>
    <p><acme:showtext code="conference.acronym" value="${conference.acronym}" fieldset="false"/></p>
    <p><acme:showtext code="conference.venue" value="${conference.venue}" fieldset="false"/></p>
    <p><acme:showtext code="conference.submissionDeadline" value="${conference.submissionDeadline}" fieldset="false"/></p>
    <p><acme:showtext code="conference.notificationDeadline" value="${conference.notificationDeadline}" fieldset="false"/></p>
    <p><acme:showtext code="conference.cameraReadyDeadline" value="${conference.cameraReadyDeadline}" fieldset="false"/></p>
    <p><acme:showtext code="conference.startDate" value="${conference.startDate}" fieldset="false"/></p>
    <p><acme:showtext code="conference.endDate" value="${conference.endDate}" fieldset="false"/></p>
    <p><acme:showtext code="conference.summary" value="${conference.summary}" fieldset="false"/></p>
    <p><acme:showtext code="conference.fee" value="${conference.fee}" fieldset="false"/></p>
    <p><acme:showtext code="conference.category" value="${conference.category}" fieldset="false"/></p>

</fieldset>
<br>
<acme:cancel code="button.goBack" url="paper/author/list.do"/>
</body>
</html>
