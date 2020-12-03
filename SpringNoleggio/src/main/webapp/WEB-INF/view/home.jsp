<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%--
    Da aggiungere a tutti i form:
    <input type='hidden' name = "contatoreCilindrata" value='${contatoreCilindrata}'>
    <input type='hidden' name = "contatore" value='${contatore}'>
    <input type='hidden' name = "idMarca" value='${idMarca}'>

    <c:set var="url">${pageContext.request.requestURL}</c:set>  mi da il link della pagina attuale

<% String s=System.getProperty("user.dir") ;
            pageContext.setAttribute("opp", s, PageContext.APPLICATION_SCOPE); %>
Mi da il percorso nel pc da dove viene eseguito il programma e lo mette nella variabile s poi nell'attribute opp
--%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Gestione Auto</title> 
        <link rel="stylesheet" href="../css/common.css"> 
        <c:if test = "${visibile}">
            <link rel="stylesheet" href="../css/homePopup.css"> 
        </c:if>
    </head>
    <body>
        <div class="popupOuter">
            <%@include  file="/WEB-INF//html/header.html" %>
            <h1>Gestione Auto</h1>
            <form action="" method='post'>
                <p>Marca</p>
                <p>
                    <select name="marca">
                        <option selected value="" required>Seleziona marca</option>
                        <c:forEach items="${listaMarche}" var="elemento">
                            <option value =${elemento.id}>${elemento.descrizione}</option>
                        </c:forEach>
                    </select>
                </p>
                <input type='hidden' name = "contatoreCilindrata" value='${contatoreCilindrata}'>
                <input type='hidden' name = "contatore" value='${contatore}'>
                <input type='hidden' name = "idMarca" value='${idMarca}'>
                <p>Modello</p> <input type='text' name='modello'>
                <p>Cilindrata</p> <input type='text' name='cilindrata'>
                <p>Targa</p> <input type='text' name='targa'>
                <input class="submit" type='submit' value='Nuova Auto' formaction="nuovaAuto">
            </form>
            <p style = "color: red;">${messaggio} </p>           
            <br>
            <form action='listaFiltrataXMarca' method='post'>
                <p>Marca</p>
                <select name="idMarca">
                    <option selected value="">--Tutte le marche--</option>
                    <c:forEach items="${listaMarche}" var="elemento">
                        <option value =${elemento.id}>${elemento.descrizione}</option>
                    </c:forEach>
                </select>
                <input type='hidden' name = "contatoreCilindrata" value='0'>
                <input type='hidden' name = "contatore" value='0'>
                <input class="submit" type='submit' value='FiltroXMarca'>
            </form>
            <br>
            <table>
                <tr>
                    <td>N.</td>
                <form action ="ordinaXMarca"" methodUnterminated [&lt;c:if] tag="post">
                    <td>
                        <input type='hidden' name = "contatore" value='${contatore + 1}'>
                        <input type='hidden' name = "contatoreCilindrata" value="0">
                        <input type='hidden' name = "idMarca" value='${idMarca}'>
                        <input class="submit" type='submit' value='marca'>
                    </td>
                </form>
                <td>Modello</td>
                <form action="ordinaXCilindrata" method="post">
                    <td>
                        <input type='hidden' name = "contatoreCilindrata" value='${contatoreCilindrata + 1}'>
                        <input type='hidden' name = "contatore" value="0">
                        <input type='hidden' name = "idMarca" value='${idMarca}'>
                        <input class="submit" type='submit' value='cilindrata'>
                    </td>
                    <td>Targa</td>
                </form>
                </tr>
                <c:forEach items="${listaAuto}" var="elemento" varStatus="conta">
                    <tr>
                    <form action="" method="post">
                        <td>${conta.count}</td>                       
                        <td><select name="idMarcaDaManeggiare">
                                <c:forEach items="${listaMarche}" var="item">
                                    <c:if test = "${elemento.marca.id == item.id}">
                                        <option selected value =${item.id}>${item.descrizione}</option>
                                    </c:if>
                                    <c:if test = "${elemento.marca.id != item.id}">
                                        <option value =${item.id}>${item.descrizione}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>
                        <td><input type="text" name="modello" value="${elemento.modello}"></td>
                        <td><input type="text" name="cilindrata" value="${elemento.cilindrata}"></td>
                        <td><input type="text" name="targa" value="${elemento.targa}"></td>
                        <td>   
                            <input type='hidden' name = "contatore" value='${contatore}'>
                            <input type='hidden' name = "contatoreCilindrata" value='${contatoreCilindrata}'>
                            <input type="hidden" name="id" value="${elemento.id}">
                            <input type="hidden" name="idMarcaDaManeggiare" value="${elemento.marca.id}">
                            <input type='hidden' name = "idMarca" value='${idMarca}'>
                            <!--in hello controller-->
                            <input class="submit" type="submit" value="Mod" formaction="modifica">
                            <input class="submit" type="submit" value="Can" formaction="cancella">
                            <input class="submit" type="submit" value="Noleggio" formaction="noleggio">
                            <input class="submit" type="submit" value="riparazione/manutenzione" formaction="riparazioneManutenzione">
                        </td>
                    </form>
                    </tr>
                </c:forEach>     
            </table>
        </div>
        <%--La password di admin è HelloWord cryptata con BCrypt--%>
        <c:if test = "${visibile}">
            <%@include  file="/WEB-INF//html/popupConAdminPass.html" %>
        </c:if>
    </body>
</html>
