<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Modifica/Rimuovi</title>
        <link rel="stylesheet" href="css/common.css"> 
    </head>
    <body>
        <%@include  file="/WEB-INF//html/header.html" %>
        <h1>Gestione Stati Auto</h1>
        <p style = "color: red;">${messaggio} </p>
        <table>
            <tr>
                <td>Data Inizio</td>
                <td>Data Fine</td>
                <td>Tipo</td>
                <td>Costo</td>
                <td>Nome</td>
                <td>Codice Fiscale/Partita Iva</td>
                <td></td>
            </tr>
                <c:forEach items="${listaStati}" var="elemento" varStatus="conta">
                    <tr>
                    <form action="" method="post"> 
                        <td><input type="date" name="dataInizio" value="${elemento.dataInizio}"></td>
                        <td><input type="date" name="dataFine" value="${elemento.dataFine}"></td>
                        <td>"${elemento.stato.descrizione}"</td>
                        <td><input type="number" name="costo" value="${elemento.costo}"></td>
                        <td><input type="text" name="nominativoUtilizzatore" value="${elemento.nominativoUtilizzatore}"></td>
                        <td>
                            <input type="text" name="partitaIvaOCodiceFiscale" value="${elemento.partitaIvaOCodiceFiscale}">
                            <input type="hidden" name="id" value="${elemento.id}">
                            <input type="hidden" name="idAuto" value="${auto.id}">
                            <input type="hidden" name="idStato" value="${elemento.stato.id}">
                        </td>
                        <td>
                            <input class="submit" type="submit" value="Mod" formaction = "modificaStato">
                            <input class="submit" type="submit" value="Canc" formaction = "cancellaStato">
                        </td>
                    </form>
                    </tr>
                </c:forEach>
        </table>
        Torna alla <a href="/">home</a>
    </body>
</html>
