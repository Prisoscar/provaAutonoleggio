<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Home</title> 
        <link rel="stylesheet" href="css/common.css"> 
        <link rel="stylesheet" href="css/login.css"> 
    </head>
    <body>
        <div class="outer">
            <h1>Effettua Login</h1>
            <form class="formLogin" action="" method ="post">
                <input class="insertLogin" type ="text" name="userName">
                <br>
                <input class="insertLogin" type="password" name ="password">
                <br>
                <input class="submitLogin" type="submit" value ="Login" formaction="login">
                <br><br>
                <input class="submitLogin" type="submit" value ="Aggiungi nuovo dipendente" formaction="nuovoDipendente">
            </form>
        </div>
    </body>
</html>
