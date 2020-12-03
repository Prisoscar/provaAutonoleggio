package it.micprisa.noleggio.controller;

import it.micprisa.noleggio.eccezioni.EccezioniInput;
import it.micprisa.noleggio.model.Marca;
import it.micprisa.noleggio.service.SrvAuto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import it.micprisa.noleggio.service.SrvMarca;

@Controller
@RequestMapping("/")
public class ControllerMarcheStati {

    @Autowired
    SrvMarca srvMarca;

    @Autowired
    SrvAuto srAuto;
    
    @RequestMapping("/gestioneMarche")
    public String gestioneMarche (
            ModelMap model
    ){
        model.addAttribute("listaMarche", srvMarca.listaMarche());
        model.addAttribute("countOrdina", 0);
        return "gestioneMarche";
    }
    
    @RequestMapping("/ordinaXMarche")
    public String ordinaXMarche(
            ModelMap model,
            @RequestParam("countOrdina") byte countOrdina
    ){
        model.addAttribute("listaMarche", srvMarca.ordinaXMarche((byte)countOrdina));
        model.addAttribute("countOrdina", (byte)countOrdina);
        return "gestioneMarche";
    }
    
    @RequestMapping("/modificaMarca")
    public String modificaMarca(
            ModelMap model,
            @RequestParam("countOrdina") byte countOrdina,
            @RequestParam("id") long id,
            @RequestParam("descrizione") String descrizione
    ){
        try{
        Marca marca = srvMarca.creaMarca(id, descrizione);
        srvMarca.modificaMarca(marca);
        }catch(EccezioniInput e){
            model.addAttribute("messaggio", e.getMessage());
            
        }
        model.addAttribute("listaMarche", srvMarca.ordinaXMarche(countOrdina));
        model.addAttribute("countOrdina", (byte)countOrdina);
        return "gestioneMarche";
    }
    
    @RequestMapping("/nuovaMarca")
    public String nuovaMarca(
            ModelMap model,
            @RequestParam("countOrdina") byte countOrdina,
            @RequestParam("descrizione") String descrizione
    ){
        try{
        Marca marca = srvMarca.creaMarca(0L, descrizione);
        srvMarca.aggiungiMarca(marca);
        }catch (EccezioniInput e){
            model.addAttribute("messaggio", e.getMessage());
        }
        model.addAttribute("listaMarche", srvMarca.ordinaXMarche(countOrdina));
        model.addAttribute("countOrdina", (byte)countOrdina);
        return "gestioneMarche";
    }
}
