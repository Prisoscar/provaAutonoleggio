package it.micprisa.noleggio.controller;

import it.micprisa.noleggio.eccezioni.EccezioniInput;
import it.micprisa.noleggio.model.Auto;
import it.micprisa.noleggio.model.Marca;
import it.micprisa.noleggio.model.Utente;
import it.micprisa.noleggio.service.SrvAuto;
import it.micprisa.noleggio.service.SrvStoriaStati;
import it.micprisa.noleggio.service.SrvUtente;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import it.micprisa.noleggio.service.SrvMarca;
import it.micprisa.noleggio.service.SrvOfficinaConvenzionata;

@Controller
@RequestMapping("/")    //qualunque richiesta verrà presa in carico da questgo controller
public class HelloController {

    @Autowired
    SrvAuto srvAuto;

    @Autowired
    SrvUtente srvUtente;

    @Autowired
    SrvMarca srvMarca;

    @Autowired
    SrvStoriaStati srvStoriaStati;

    @Autowired
    SrvOfficinaConvenzionata srvOfficinaConvenzionata;

    @ModelAttribute
    public void creaListaMarche(
            Model model,
            @RequestParam(name = "contatore", required = false) String contatore,
            @RequestParam(name = "contatoreCilindrata", required = false) String contatoreCilindrata,
            @RequestParam(name = "idMarca", required = false) String idMarca
    ) {
        String marca = srvAuto.settaIdMarca(idMarca);
        long contMarca = srvAuto.settaContatoreMarca(contatore);
        long contCilindrata = srvAuto.settaContatoreCilindrata(contatoreCilindrata);
        model.addAttribute("listaMarche", srvMarca.listaMarche());
        model.addAttribute("idMarca", marca);
        model.addAttribute("contatore", contMarca);
        model.addAttribute("contatoreCilindrata", contCilindrata);
        model.addAttribute("listaAuto", srvAuto.listaFiltrataXMarcaOCilindrata(marca, contMarca, contCilindrata));
    }

    @RequestMapping(method = RequestMethod.GET)
    public String first() {
        return "home";
    }

    @RequestMapping("/gestioneVeicoli")
    public String gestioneVeicoli() {
        return "home";
    }

    @RequestMapping("/cancella")
    public String cancella(
            ModelMap model,
            @RequestParam("id") long id
    ) {
        Auto auto = srvAuto.cercaAuto(id);
        model.addAttribute("id", auto.getId());
        model.addAttribute("oggetto", auto.getMarca().getDescrizione() + " " + auto.getModello() + " " + auto.getTarga());
        model.addAttribute("visibile", true);
        model.addAttribute("azione", "Eliminare");
        return "home";
    }

    @RequestMapping("/annullaCancellazione")
    public String annullaCancellazione(
            ModelMap model
    ) {
        model.addAttribute("visibile", false);
        return "home";
    }

    @RequestMapping("/confermaCancellazione")
    public String confermaCancellazione(
            ModelMap model,
            @RequestParam("id") long id,
            @RequestParam("password") String password
    ) {
        System.out.println(id);
        Utente admin = srvUtente.cercaUtente(1L);
        Auto auto = srvAuto.cercaAuto(id);
        try {
            srvUtente.checkPassword(password, admin);
            System.out.println("pass verificata"); 
            srvStoriaStati.cancellaStorieStatiAuto(auto);
            srvAuto.cancella(id);
        } catch (EccezioniInput e) {
            model.addAttribute("visibile", true);
            model.addAttribute("errorePass", e.getMessage());
            model.addAttribute("oggetto", auto.getMarca().getDescrizione() + " " + auto.getModello() + " " + auto.getTarga());
            model.addAttribute("id", id);
            model.addAttribute("azione", "Eliminare");
        }
        //model.addAttribute("listaAuto", srvAuto.lista());
        return "home";
    }

    @RequestMapping("/modifica")
    public String modifica(
            ModelMap model,
            @RequestParam("id") String id,
            @RequestParam("idMarcaDaManeggiare") long idMarca,
            @RequestParam("modello") String modello,
            @RequestParam("cilindrata") String cilindrata,
            @RequestParam("targa") String targa
    ) {
        try {
            Marca marca = srvMarca.cercaMarca(idMarca);
            Auto auto = srvAuto.creaAuto(id, marca, modello, cilindrata, targa);
            srvAuto.modifica(auto);
        } catch (EccezioniInput e) {
            model.addAttribute("messaggio", e.getMessage());
        }
        return "home";
    }

    @RequestMapping("/nuovaAuto")
    public String nuovaAuto(
            ModelMap model,
            @RequestParam("marca") String idMarca,
            @RequestParam("modello") String modello,
            @RequestParam("cilindrata") String cilindrata,
            @RequestParam("targa") String targa,
            @RequestParam("contatore") String contatoreMarca,
            @RequestParam("contatoreCilindrata") String contatoreCilindrata,
            @RequestParam("idMarca") String idMarcaSelezionata
    ) {
        try {
            Marca marca = srvMarca.cercaMarca(Long.parseLong(idMarca));
            Auto auto = srvAuto.creaAuto("0", marca, modello, cilindrata, targa);
            srvAuto.aggiungiAuto(auto);
        } catch (EccezioniInput e) {
            model.addAttribute("messaggio", e.getMessage());
        } catch (NumberFormatException e) {
            model.addAttribute("messaggio", "Inserire marca");
        }
        model.addAttribute("listaAuto", srvAuto.listaFiltrataXMarcaOCilindrata(idMarcaSelezionata, Byte.parseByte(contatoreMarca), Byte.parseByte(contatoreCilindrata)));
        return "home";
    }

    @RequestMapping("/listaFiltrataXMarca")
    public String listaFiltrataXMarca(
            ModelMap model
    ) {
        return "home";
    }

    @RequestMapping("/ordinaXMarca")
    public String ordinaXMarca(
            ModelMap model
    ) {
        return "home";
    }

    @RequestMapping("/ordinaXCilindrata")
    public String ordinaXCilindrata(
            ModelMap model
    ) {
        return "home";
    }

    @RequestMapping("/riparazioneManutenzione")
    public String riparazioneManutenzione(
            ModelMap model,
            @RequestParam("id") Long id
    ) {
        Date oggiMeno24H = new Date(System.currentTimeMillis() - 86400000L);
        Auto auto = srvAuto.cercaAuto(id);
        model.addAttribute("auto", auto);
        model.addAttribute("listaStati", srvStoriaStati.listaStoriaStatiAuto(auto, oggiMeno24H));
        model.addAttribute("listaOfficine", srvOfficinaConvenzionata.listaOfficine((byte) 0));
        return "riparazioneManutenzione";
    }

    @RequestMapping("/gestioneOfficine")
    public String gestioneOfficine(
            ModelMap model
    ) {
        model.addAttribute("listaOfficine", srvOfficinaConvenzionata.listaOfficine((byte) 0));
        model.addAttribute("contatore", 0);
        return "gestioneOfficine";
    }
}
