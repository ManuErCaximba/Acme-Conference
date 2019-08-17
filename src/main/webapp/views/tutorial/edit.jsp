<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>


<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<security:authorize access="hasRole('ADMIN')">
    <form:form action="tutorial/administrator/edit.do" modelAttribute="tutorial">

        <form:input type ="hidden" path="id" readonly="true"/>

        <acme:textbox code="tutorial.title" path="title" />

        <acme:textbox code="tutorial.startMoment" path="startMoment" />

        <acme:textbox code="tutorial.duration" path="duration" />

        <acme:textbox code="tutorial.summary" path="summary"  />

        <acme:textarea path="attachments" code="tutorial.attachments"/>

        <acme:select path="" code="" items="" itemLabel=""

        <acme:textbox code="conference.startDate" path="startDate" />

        <acme:textbox code="conference.endDate" path="endDate" />

        <acme:textbox code="conference.summary" path="summary" />

        <acme:textbox code="conference.fee" path="fee" />

        <form:label path="category">
            <spring:message code="conference.category"/>
        </form:label>
        <form:select path="category">
            <jstl:if test="${lang =='es'}">
                <form:options items="${categories}" itemValue="id" itemLabel="nameEs"/>
            </jstl:if>
            <jstl:if test="${lang == 'en'}">
                <form:options items="${categories}" itemValue="id" itemLabel="nameEn"/>
            </jstl:if>
        </form:select>
        <br/>


        <acme:submit name="saveDraft" code="conference.saveDraft"/>

        <acme:submit name="saveFinal" code="conference.saveFinal"/>

        <jstl:if test="${conference.id != 0}">
            <acme:cancel code="conference.delete" url="conference/administrator/delete.do?conferenceId=${conference.id}"/>
        </jstl:if>

        <input type="button" name="cancel"
               value="<spring:message code="button.goBack" />"
               onclick="javascript: window.history.back();" />


    </form:form>
