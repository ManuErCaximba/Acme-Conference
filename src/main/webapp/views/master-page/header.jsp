<%--
 * header.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="#"><img src="${configuration.banner}" alt="${configuration.systemName}" /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/register.do"><spring:message code="master.page.administrator.register" /></a></li>
					<li><a href="conference/administrator/create.do"><spring:message code="master.page.conference.create" /></a></li>
					<li><a href="conference/administrator/listConferenceAdminMenu.do"><spring:message code="master.page.administrator.conferences" /></a></li>
					<li><a href="category/administrator/list.do"><spring:message code="master.page.administrator.categories" /></a></li>
				</ul>
			</li>
			<li><a href="configuration/administrator/show.do"><spring:message code="master.page.administrator.configuration" /></a></li>
		</security:authorize>

		<security:authorize access="hasRole('SPONSOR')">
			<li><a class="fNiv"><spring:message	code="master.page.sponsorship" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="sponsorship/sponsor/list.do"><spring:message code="master.page.sponsorship.list" /></a></li>
					<li><a href="sponsorship/sponsor/create.do"><spring:message code="master.page.sponsorship.create" /></a></li>
				</ul>
			</li>
		</security:authorize>

		<security:authorize access="hasRole('AUTHOR')">
			<li><a class="fNiv"><spring:message	code="master.page.paper" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="paper/author/list.do"><spring:message code="master.page.paper.list" /></a></li>
					<li><a href="paper/author/create.do"><spring:message code="master.page.paper.create" /></a></li>
				</ul>
			</li>
		</security:authorize>

		<security:authorize access="permitAll">
			<li><a class="fNiv"><spring:message	code="master.page.conferences" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="conference/listForthcoming.do"><spring:message code="master.page.conference.listForthcoming" /></a></li>
					<li><a href="conference/listPast.do"><spring:message code="master.page.conference.listPast" /></a></li>
					<li><a href="conference/listPast.do"><spring:message code="master.page.conference.listRunning" /></a></li>
				</ul>
			</li>
		</security:authorize>

		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" />
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="profile/display.do"><spring:message code="master.page.profile.display" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

