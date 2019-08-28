<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
             pageEncoding="ISO-8859-1" %>

    <%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
    <%@taglib prefix="display" uri="http://displaytag.sf.net" %>
    <%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

</head>
<body>
<security:authorize access="hasRole('ADMIN')">
    <display:table name="registrations" id="row" requestURI="${requestURI}"
                   pagesize="10" class="displaytag">

        <spring:message code="registration.moment" var="title"/>
        <display:column title="${title}">
            <jstl:out value="${row.moment}"/>
        </display:column>

        <spring:message code="registration.author" var="title"/>
        <display:column title="${title}">
            <jstl:out value="${row.author.userAccount.username}"/>
        </display:column>


    </display:table>
    <br>
    <input type="button" name="cancel"
           value="<spring:message code="button.goBack" />"
           onclick="javascript: window.history.back();" />
</security:authorize>

</body>
</html>