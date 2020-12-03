<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Noleggio Auto</title>
        <link rel="stylesheet" href="css/common.css"> 
    </head>
    <body>
        <%@include  file="/WEB-INF//html/header.html" %>
        <h1>Noleggio auto: ${auto.marca.descrizione} ${auto.modello} ${auto.targa} ${auto.cilindrata}</h1>
        <p style = "color: red;">${messaggio} </p>
        <h2>
            <ul>
                <c:forEach items="${listaStati}" var="item">
                    <li>${item.dataInizio} ${item.dataFine} ${item.stato.descrizione}</li>
                    </c:forEach>
            </ul>
        </h2>
        <form action="creaNoleggio" method="post">
            <table>
                <tr>
                    <td>Data Inizio</td>
                    <td>Data Fine</td>
                    <td>Prezzo</td>
                    <td>Nome e Cognome Cliente</td>
                    <td>Codice Fiscale</td>
                    <td></td>
                </tr>
                <tr>
                    <td><input type="date" name="dataInizio" required></td>
                    <td><input type="date" name="dataFine" required></td>
                    <td><input type="number" name="prezzo" required></td>
                    <td><input type="text" name="nominativoUtilizzatore" required></td>
                    <td>
                        <input type="text" name="partitaIvaOCodiceFiscale" required>
                        <input type="hidden" name="idAuto" value="${auto.id}">
                    </td>
                    <td><input class="submit" type="submit" value="OK"></td>
                </tr>
            </table>
        </form>
        <form action = "modificaRimuovi" method = "post">
            <input type = "hidden" name =  "idAuto" value = "${auto.id}">
            <input class="submit" type = "submit" value = "Modifica/Rimuovi Stato Auto"> 
        </form>
    </body>
</html>
