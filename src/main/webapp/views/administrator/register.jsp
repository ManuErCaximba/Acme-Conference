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

    <spring:message code="actor.firstMessage" />

    <form:form id="myform" action="administrator/register.do" modelAttribute="administratorForm">

        <form:hidden path="id" />

        <br>
        <fieldset>

            <legend><spring:message code="actor.accountData" /></legend>

            <acme:textboxbsa code="actor.username" path="username"/>
            <acme:textboxbsa code="actor.password" path="password"/>
            <acme:textboxbsa code="actor.confirmPass" path="confirmPass"/>

        </fieldset>

        <br>
        <fieldset>

            <legend><spring:message code="actor.personalData" /></legend>

            <acme:textboxbsa code="actor.name" path="name"/>
            <acme:textboxbs code="actor.middleName" path="middleName"/>
            <acme:textboxbsa code="actor.surname" path="surname"/>
            <acme:textboxbs code="actor.phoneNumber" path="phoneNumber"/>
            <acme:textboxbsa code="actor.email" path="email"/>
            <acme:textboxbs code="actor.photo" path="photo"/>
            <acme:textboxbs code="actor.address" path="address"/>

        </fieldset>

        <script type="text/javascript">
            function phoneValidation(){
                var phoneNumber = document.getElementById("phoneNumber").value;
                var regexPN = /^(\d\d\d\d+)$/;
                var regex1 = /^((\+[1-9][0-9]{0,2}) \(([1-9][0-9]{0,2})\) (\d\d\d\d+))$/;
                var regex2 = /^(\+[1-9][0-9]{0,2}) (\d\d\d\d+)$/;

                if (regexPN.test(phoneNumber)) {
                    return document.getElementById("myform").submit();
                } else if(regex1.test(phoneNumber)) {
                    return document.getElementById("myform").submit();
                }else if(regex2.test(phoneNumber)){
                    return document.getElementById("myform").submit();
                }else{
                    var confirm = window.confirm('<spring:message code = "actor.confirm"/>');
                    if(!confirm){
                        return window.history.back();
                    }else{
                        return document.getElementById("myform").submit();
                    }
                }
            }
        </script>

        <br>

        <div class=terms>
            <input type="checkbox" required name="terms">
            <label for="terms"><spring:message code="terms"/></label>
        </div>

        <br>
        <acme:submit name="save" code="button.save"/>
        <acme:cancel code="button.cancel" url="/"/>

    </form:form>

</security:authorize>
</body>
</html>
