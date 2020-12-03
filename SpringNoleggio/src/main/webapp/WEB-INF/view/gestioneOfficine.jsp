<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Gestione Officine</title>
        <link rel="stylesheet" href="../css/common.css"> 
        <c:if test = "${nuova || elimina}">
            <link rel="stylesheet" href="../css/homePopup.css"> 
        </c:if>
    </head>
    <body>
        <%@include  file="/WEB-INF//html/header.html" %>
        <div class ="popupOuter">
            <p style = "color: red;">${erroreInput}</p>
            <h1>Gestione Officine</h1>
            <table>
                <tr>
                <form action="/gestioneOfficine/ordinaXNome" method = "post">
                    <td>N°</td>
                    <td>
                        <input class="submit" type='submit' value='Nome'>
                        <input type ="hidden" name ="contatore" value="${contatore + 1}">
                    </td>
                    <td>Partita Iva</td>
                    <td></td>
                </form>
                </tr>
                <c:forEach items="${listaOfficine}" var="elemento" varStatus="conta">
                    <tr>
                    <form action="" method="post"> 
                        <td>${conta.count}</td>
                        <td><input type = "text" name = "nomeOfficina" value= "${elemento.nomeOfficina}"></td>
                        <td>
                            <input type = "text" name = "partitaIva" value= "${elemento.partitaIva}">
                            <input type ="hidden" name ="contatore" value="${contatore}">
                            <input type ="hidden" name ="id" value="${elemento.id}">
                        </td>
                        <td>
                            <input class="submit" type="submit" value="Mod" formaction = "/gestioneOfficine/modificaOfficina">
                            <input class="submit" type="submit" value="Canc" formaction = "/gestioneOfficine/eliminaOfficina">
                        </td>
                    </form>
                    </tr>
                </c:forEach>

            </table>
            <form action="/gestioneOfficine/nuovaOfficina" method="post">
                <input type ="hidden" name ="contatore" value="${contatore}">
                <input class="submit" type="submit" value="Nuova">
            </form>
            Torna alla <a href="/">home</a>
        </div>
        <c:if test = "${nuova}">
            <div class = "popup">
                <form action ="" method ="post">
                    <h3 class="popupLabel">Aggiungi nuova officina</h3>
                    <br>
                    <p class = "popupLabel" style = "color: red;">${erroreInput}</p>
                    <p class="popupLabel">Inserisci nome officina</p>
                    <input class ="popupInput" type ="text" name ="nomeOfficina">
                    <p class="popupLabel">Inserisci partita iva officina</p>
                    <input class ="popupInput" type ="text" name ="partitaIva">
                    <br><br>
                    <div class="popupButtons">
                        <input type ="hidden" name ="contatore" value="${contatore}">
                        <input class = "popupButtonRight" type ="submit" name ="conferma" value = "conferma" formaction="/gestioneOfficine/confermaAggiunta">
                        <input class = "popupButtonLeft" type ="submit" name =" annulla " value = "annulla" formaction="/gestioneOfficine/annullaAggiunta">
                    </div>
                </form>
            </div>
        </c:if>
        <c:if test = "${elimina}">
            <%@include  file="/WEB-INF//html/popupConAdminPass.html" %>
        </c:if>
    </body>
</html>
