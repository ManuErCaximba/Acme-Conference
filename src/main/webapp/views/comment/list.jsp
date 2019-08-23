<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table name="comments" id="row" requestURI="${requestURI}"
               pagesize="5" class="displaytag">

    <spring:message code="comment.moment" var="moment"/>
    <display:column title="${moment}" sortable="true">
        <jstl:out value="${row.moment}"/>
    </display:column>

    <spring:message code="comment.title" var="body"/>
    <display:column title="${body}">
        <jstl:out value="${row.title}"/>
    </display:column>

    <spring:message code="comment.body" var="body"/>
    <display:column title="${body}">
        <jstl:out value="${row.body}"/>
    </display:column>

    <spring:message code="comment.author" var="author"/>
    <display:column title="${author}">
        <jstl:if test="${row.author != null}">
            <a href="profile/show.do?actorId=${row.author.id}">
            <spring:message code="comment.author"/></a>
        </jstl:if>
    </display:column>

</display:table>