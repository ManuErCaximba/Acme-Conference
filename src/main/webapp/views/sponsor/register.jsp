
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
    <%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

</head>
<body>
<spring:message code="actor.firstMessage" />
<form:form id="myform" action="sponsor/create.do" modelAttribute="sponsorForm">

    <form:hidden path="id" />
    <fieldset>
        <legend><spring:message code="actor.personalData" /></legend>
        *
        <acme:textbox code="actor.username" path="username"/>
        <br />

        <form:label path="password" >
            <spring:message code="actor.password" />*
        </form:label>
        <form:password path="password" id="password"/>
        <form:errors cssClass="error" path="password" />
        <br />

        <form:label path="confirmPass">
            <spring:message code="actor.confirmPass" />*
        </form:label>
        <form:password path="confirmPass" id="confirmPassword"/>
        <form:errors cssClass="error" path="password" />
        <br />

        *
        <acme:textbox code="actor.name" path="name"/>
        <br />
        *
        <acme:textbox code="actor.surname" path="surname"/>
        <br />

        <acme:textbox code="actor.middleName" path="middleName"/>
        <br />

        <acme:textbox code="actor.photo" path="photo"/>
        <br />
        *
        <acme:textbox code="actor.email" path="email"/>
        <br />
        *
        <acme:textbox code="actor.phoneNumber" path="phoneNumber"/>
        <br />

        <acme:textbox code="actor.address" path="address"/>
        <br />

    </fieldset>


    <acme:submit name="save" code="button.save"/>&nbsp;

    <input type="button" name="cancel"
           value="<spring:message code="button.cancel" />"
           onclick="javascript: relativeRedir('/');" />
    <br />

</form:form>
</body>
</html>