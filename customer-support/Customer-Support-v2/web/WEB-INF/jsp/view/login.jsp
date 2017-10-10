<%--
  Created by IntelliJ IDEA.
  User: 11981
  Date: 2017/10/10
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<template:loggedOut htmlTitle="Log In" bodyTitle="Log In">
    You must log in to access the customer support site.<br/>
    <c:if test="${loginFailed}">
        <b>The username and password you entered are not correct. Please try again</b><br/><br/>
    </c:if>
    <form method="post" action="<c:url value="/login" />">
        Username<br/>
        <input type="text" name="username" /><br /><br />
        <input type="text" name="password" /><br /><br />
        <input type="submit" value="Log In" />
    </form>
</template:loggedOut>
