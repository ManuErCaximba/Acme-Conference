<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<security:authorize access="hasRole('AUTHOR')">
    <form:form action="registration/author/create.do" modelAttribute="registration">

        <form:input type ="hidden" path="id" readonly="true"/>

        <acme:textbox code="registration.creditCard.holderName" path="registration.creditCard.holderName" />

        <acme:textbox code="registration.creditCard.brandName" path="registration.creditCard.brandName" />

        <acme:textbox code="registration.creditCard.number" path="registration.creditCard.number" />

        <acme:textbox code="registration.creditCard.expirationMonth" path="registration.creditCard.expirationMonth" />

        <acme:textbox code="registration.creditCard.expirationYear" path="registration.creditCard.expirationYear" />

        <acme:textbox code="registration.creditCard.CVV" path="registration.creditCard.CVV" />

        <acme:submit name="save" code="registration.save"/>


    </form:form>
</security:authorize>