<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Riparazione/Manutenzione</title>
        <link rel="stylesheet" href="css/common.css"> 
    </head>
    <body>
        <%@include  file="/WEB-INF//html/header.html" %>
        <h1>Riparazione/Manutenzione auto: ${auto.marca.descrizione} ${auto.modello} ${auto.targa} ${auto.cilindrata}</h1>
        <h2>
            <ul>
                <c:forEach items="${listaStati}" var="item">
                    <li>${item.dataInizio} ${item.dataFine} ${item.stato.descrizione}</li>
                    </c:forEach>
            </ul>
        </h2>    
        <p style = "color: red;">${messaggio} </p>
        <form actin = "" method = "post">
            <table>
                <tr>
                    <td>Data Inizio</td>
                    <td>Data Fine</td>
                    <td>Prezzo</td>
                    <td>Nome Officina</td>
                    <td></td>
                </tr>
                <tr>
                    <td><input type="date" name="dataInizio" required></td>
                    <td><input type="date" name="dataFine" required></td>
                    <td><input type="number" name="costo" required></td>
                    <td>
                        <select name="idOfficina">
                            <option selected value="" required>Seleziona marca</option>
                            <c:forEach items="${listaOfficine}" var="elemento">
                                <option value =${elemento.id}>${elemento.nomeOfficina}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" name="idAuto" value="${auto.id}">
                    </td>
                    <td>
                        <input class="submit" type = "submit" value = "aggiungi manutenzione" formaction = "aggiungiManutenzione">
                        <input class="submit" type = "submit" value = "aggiungi riparazione" formaction = "aggiungiRiparazione">
                    </td>
                </tr>
            </table>
        </form>
        <form action = "modificaRimuovi" method = "post">
            <input type = "hidden" name =  "idAuto" value = "${auto.id}">
            <input class="submit" type = "submit" value = "Modifica/Rimuovi Stato Auto"> 
        </form>
    </body>
</html>