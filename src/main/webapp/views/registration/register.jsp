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
    <form:form action="registration/author/create.do" modelAttribute="registrationForm">
        <form:hidden path="id" />
        <input type="hidden" name="conferenceId" value="${conferenceId}" readonly>
        <br>

        <jstl:if test="${errorNumber == 1}">
            <form:errors cssClass="error" path="expirationYear" element="div" /> <div id="error.expirationYear" class="error"><spring:message code="error.expirationYear"/></div>
        </jstl:if>
        <jstl:if test="${errorNumber == 2}">
            <form:errors cssClass="error" path="expirationMonth" element="div" /> <div id="error.expirationMonth" class="error"><spring:message code="error.expirationMonth"/></div>
        </jstl:if>

        <br>

        <fieldset>

            <legend><spring:message code="registration.creditCard" /></legend>

            <acme:textboxbsa code="registration.creditCard.holderName" path="holderName"/>

            <form:label path="brandName">
                <b><spring:message code="registration.creditCard.brandName" /> *</b>
            </form:label>
            <br>
            <form:select path="brandName" items="${brandList}"/>
            <form:errors cssClass="error" path="brandName"/>

            <acme:textboxbsa code="registration.creditCard.number" path="number"/>
            <acme:textboxbsa code="registration.creditCard.expirationMonth" path="expirationMonth"/>

            <form:label path="expirationYear">
                <b><spring:message code="registration.creditCard.expirationYear" /> *</b>
            </form:label>
            <br>
            <form:input path="expirationYear" placeholder="YYYY"/>
            <form:errors cssClass="error" path="expirationYear"/>

            <acme:textboxbsa code="registration.creditCard.CVV" path="CVV"/>

        </fieldset>

        <br>
        <br>

        <acme:submit name="save" code="registration.save"/>


    </form:form>
</security:authorize>