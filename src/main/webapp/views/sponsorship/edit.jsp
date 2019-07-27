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
<security:authorize access="hasRole('ADMIN')">

    <form:form id="sponsorshipForm" action="configuration/administrator/save.do" modelAttribute="configuration">

        <form:hidden path="id" />

        <br>
        <fieldset>

            <legend><spring:message code="configuration.data" /></legend>

            <acme:textboxbsa code="configuration.systemName" path="systemName"/>
            <acme:textboxbsa code="configuration.banner" path="banner"/>

            <acme:textboxbsa code="configuration.welcomeEs" path="welcomeEs"/>
            <acme:textboxbsa code="configuration.welcomeEn" path="welcomeEn"/>
            <acme:textboxbsa code="configuration.defaultCC" path="defaultCC"/>

            <br>
            <form:label path="creditCardMakes">
                <b><spring:message code="configuration.creditCardMakes" /> *</b>
                <br>
                <spring:message code="configuration.creditCardMakes"/>
                <br>
            </form:label>
            <form:textarea path="creditCardMakes" />
            <form:errors cssClass="error" path="creditCardMakes" />
            <br/>

            <br>
            <form:label path="topicEs">
                <b><spring:message code="configuration.topicEs" /> *</b>
                <br>
                <spring:message code="configuration.topicEs"/>
                <br>
            </form:label>
            <form:textarea path="topicEs" />
            <form:errors cssClass="error" path="topicEs" />
            <br/>

            <br>
            <form:label path="topicEn">
                <b><spring:message code="configuration.topicEn" /> *</b>
                <br>
                <spring:message code="configuration.topicEn"/>
                <br>
            </form:label>
            <form:textarea path="topicEn" />
            <form:errors cssClass="error" path="topicEn" />
            <br/>

            <br>
            <form:label path="voidWordsEs">
                <b><spring:message code="configuration.voidWordsEs" /> *</b>
                <br>
                <spring:message code="configuration.voidWordsEs"/>
                <br>
            </form:label>
            <form:textarea path="voidWordsEs" />
            <form:errors cssClass="error" path="voidWordsEs" />
            <br/>

            <br>
            <form:label path="voidWordsEn">
                <b><spring:message code="configuration.voidWordsEn" /> *</b>
                <br>
                <spring:message code="configuration.voidWordsEn"/>
                <br>
            </form:label>
            <form:textarea path="voidWordsEn" />
            <form:errors cssClass="error" path="voidWordsEn" />
            <br/>

        </fieldset>

        <br>
        <br>

        <acme:submit name="save" code="button.save"/>
        <acme:cancel code="button.cancel" url="/"/>

    </form:form>

</security:authorize>
</body>
</html>