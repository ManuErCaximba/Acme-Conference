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

<security:authorize access="hasRole('ADMIN')">
    <display:table name="topics" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
        <spring:message code="topic.name" var="columnTitle"/>
        <jstl:if test="${lang=='es' }">
            <display:column title="${columnTitle}">
                <jstl:out value="${row.nameEs}"/>
            </display:column>
        </jstl:if>

        <jstl:if test="${lang=='en' }">
            <display:column title="${columnTitle}">
                <jstl:out value="${row.nameEn}"/>
            </display:column>
        </jstl:if>

        <spring:message code="topic.view" var="columnTitle"/>
        <display:column title="${columnTitle}">
            <a href="topic/show.do?topicId=${row.id}">
                <spring:message code="category.view"/>
            </a>
        </display:column>

        <spring:message code="topic.edit" var="columnTitle"/>
        <display:column title="${columnTitle}">
            <jstl:if test="${row.nameEn != 'Default'}">
                <a href="topic/administrator/edit.do?topicId=${row.id}">
                    <spring:message code="category.edit"/>
                </a>
            </jstl:if>
        </display:column>

        <spring:message code="topic.delete" var="columnTitle"/>
        <display:column title="${columnTitle}">
            <jstl:if test="${topic.id != 0}">
                <acme:cancel code="topic.delete" url="topic/administrator/delete.do?topicId=${row.id}"/>
            </jstl:if>
        </display:column>
    </display:table>

    <input type="button" value="<spring:message code="topic.create" />"
           onclick="javascript: relativeRedir('topic/administrator/create.do');" />
</security:authorize>