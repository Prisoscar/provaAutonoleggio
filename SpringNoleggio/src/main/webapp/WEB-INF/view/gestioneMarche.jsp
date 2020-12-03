<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Gestione Marche</title>
        <link rel="stylesheet" href="css/common.css"> 
    </head>
    <body>
        <%@include  file="/WEB-INF//html/header.html" %>
        <h1>Gestione Marche</h1>
        <p style = "color: red;">${messaggio} </p>
        <table>
            <tr>
            <form action="ordinaXMarche" method = "post">
                <td>N°</td>
                <td>
                    <input class="submit" type='submit' value='marca'>
                    <input type ="hidden" name ="countOrdina" value="${countOrdina + 1}">
                </td>
                <td></td>
            </form>
        </tr>
        <c:forEach items="${listaMarche}" var="elemento" varStatus="conta">
            <tr>
            <form action="modificaMarca" method="post"> 
                <td>${conta.count}</td>
                <td>
                    <input type = "text" name = "descrizione" value= "${elemento.descrizione}">
                    <input type ="hidden" name ="countOrdina" value="${countOrdina}">
                    <input type ="hidden" name ="id" value="${elemento.id}">
                </td>
                <td>
                    <input class="submit" type="submit" value="Mod">
                </td>
            </form>
        </tr>
    </c:forEach>
    <tr>
        <td>#</td>
    <form action="nuovaMarca" method="post">
        <td>
            <input type = "text" name = "descrizione" required>
            <input type ="hidden" name ="countOrdina" value="${countOrdina}">
        </td>
        <td>
            <input class="submit" type="submit" value="Nuova">
        </td>
    </form>
</tr>
</table>
Torna alla <a href="/">home</a>
</body>
</html>
