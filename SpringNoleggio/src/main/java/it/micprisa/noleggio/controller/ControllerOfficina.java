package it.micprisa.noleggio.controller;

import it.micprisa.noleggio.eccezioni.EccezioniInput;
import it.micprisa.noleggio.model.OfficinaConvenzionata;
import it.micprisa.noleggio.model.Utente;
import it.micprisa.noleggio.service.SrvOfficinaConvenzionata;
import it.micprisa.noleggio.service.SrvUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author mprisacar
 */
@Controller
@RequestMapping("/gestioneOfficine")
public class ControllerOfficina {

    @Autowired
    SrvOfficinaConvenzionata srvOfficinaConvenzionata;

    @Autowired
    SrvUtente srvUtente;

    @ModelAttribute
    public void creaListaOfficine(
            Model model,
            @RequestParam(name = "contatore") byte contatore
    ) {
        model.addAttribute("listaOfficine", srvOfficinaConvenzionata.listaOfficine((byte) contatore));
        model.addAttribute("contatore", (byte)contatore);
    }

    @RequestMapping("/ordinaXNome")
    public String ordinaXNome(
            ModelMap model
    ) {
        return "gestioneOfficine";
    }

    @RequestMapping("/modificaOfficina")
    public String modificaOfficina(
            ModelMap model,
            @RequestParam("id") long id,
            @RequestParam("nomeOfficina") String nomeOfficina,
            @RequestParam("partitaIva") String partitaIva
    ) {
        try {
            srvOfficinaConvenzionata.modificaOfficina(srvOfficinaConvenzionata.creaOfficina(id, nomeOfficina, partitaIva));
        } catch (EccezioniInput ex) {
            model.addAttribute("erroreInput", ex.getMessage());
        }
        return "gestioneOfficine";
    }

    @RequestMapping("/eliminaOfficina")
    public String eliminaOfficina(
            ModelMap model,
            @RequestParam("id") long id
    ) {
        model.addAttribute("id", id);
        model.addAttribute("oggetto", srvOfficinaConvenzionata.cercaOfficina(id).getNomeOfficina());
        model.addAttribute("elimina", true);
        model.addAttribute("azione", "Eliminare");
        return "gestioneOfficine";
    }

    @RequestMapping("/annullaCancellazione")
    public String annullaCancellazione() {
        return "gestioneOfficine";
    }

    @RequestMapping("/confermaCancellazione")
    public String confermaCancellazione(
            ModelMap model,
            @RequestParam("id") long id,
            @RequestParam("password") String password,
            @RequestParam("contatore") byte contatore
    ) {
        Utente admin = srvUtente.cercaUtente(1L);
        try {
            srvUtente.checkPassword(password, admin);
            srvOfficinaConvenzionata.eliminaOfficina(id);
        } catch (EccezioniInput e) {
            model.addAttribute("elimina", true);
            model.addAttribute("oggetto", srvOfficinaConvenzionata.cercaOfficina(id).getNomeOfficina());
            model.addAttribute("errorePass", e.getMessage());
            model.addAttribute("id", id);
            model.addAttribute("azione", "Eliminare");
        }
        model.addAttribute("listaOfficine", srvOfficinaConvenzionata.listaOfficine((byte) contatore));
        return "gestioneOfficine";
    }
    
    @RequestMapping("/nuovaOfficina")
    public String nuovaOfficina(
            ModelMap model
    ) {
        model.addAttribute("nuova", true);
        return "gestioneOfficine";
    }
    
    @RequestMapping("/annullaAggiunta")
    public String annullaAggiunta() {
        return "gestioneOfficine";
    }
    
     @RequestMapping("/confermaAggiunta")
    public String confermaAggiunta(
            ModelMap model,
            @RequestParam("nomeOfficina") String nomeOfficina,
            @RequestParam("partitaIva") String partitaIva,
            @RequestParam("contatore") byte contatore
    ) {
        try {
            srvOfficinaConvenzionata.nuovaOfficina(srvOfficinaConvenzionata.creaOfficina(0L, nomeOfficina, partitaIva));
        } catch (EccezioniInput ex) {
            model.addAttribute("erroreInput", ex.getMessage());
            model.addAttribute("nuova", true);
        }
        model.addAttribute("listaOfficine", srvOfficinaConvenzionata.listaOfficine((byte) contatore));
        return "gestioneOfficine";
    }
}
