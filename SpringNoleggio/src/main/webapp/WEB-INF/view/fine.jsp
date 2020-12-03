<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Completato</title>
        <link rel="stylesheet" href="css/common.css"> 
    </head>
    <body>
        <%@include  file="/WEB-INF//html/header.html" %>
        <h1>${fatto} aggiunto con successo con successo per l'auto</h1>
        <h2>
            ${auto.marca.descrizione} ${auto.modello} ${auto.targa} ${auto.cilindrata}
            <br/>
            Dal ${storiaStati.dataInizio} Al ${storiaStati.dataFine} Prezzo ${storiaStati.costo} € 
        </h2>
        Torna alla <a href="/">home</a>
</body>
</html>
