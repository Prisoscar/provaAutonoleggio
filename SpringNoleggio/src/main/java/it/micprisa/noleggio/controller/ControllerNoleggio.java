package it.micprisa.noleggio.controller;

import it.micprisa.noleggio.eccezioni.EccezioniInput;
import it.micprisa.noleggio.model.Auto;
import it.micprisa.noleggio.model.OfficinaConvenzionata;
import it.micprisa.noleggio.model.Stato;
import it.micprisa.noleggio.model.StoriaStati;
import it.micprisa.noleggio.service.SrvAuto;
import it.micprisa.noleggio.service.SrvOfficinaConvenzionata;
import it.micprisa.noleggio.service.SrvStato;
import it.micprisa.noleggio.service.SrvStoriaStati;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class ControllerNoleggio {

    @Autowired
    SrvAuto srvAuto;

    @Autowired
    SrvStoriaStati srvStoriaStati;
    
    @Autowired
    SrvStato srvStato;
    
    @Autowired
    SrvOfficinaConvenzionata srvOfficinaConvenzionata;
    
    @RequestMapping("/noleggio")
    public String noleggio(
            ModelMap model,
            @RequestParam("id") Long id
    ) {
        Auto auto = srvAuto.cercaAuto(id);
        Date oggiMeno24H = new Date(System.currentTimeMillis() - 86400000L);
        model.addAttribute("auto", auto);
        model.addAttribute("listaStati", srvStoriaStati.listaStoriaStatiAuto(auto, oggiMeno24H));
        return "noleggio";
    }

    @RequestMapping("/creaNoleggio")
    public String creaNoleggio(
            ModelMap model,
            @RequestParam("dataInizio") Date dataInizio,
            @RequestParam("dataFine") Date dataFine,
            @RequestParam("prezzo") Double prezzo,
            @RequestParam("idAuto") Long idAuto,
            @RequestParam("nominativoUtilizzatore") String nominativoUtilizzatore,
            @RequestParam("partitaIvaOCodiceFiscale") String partitaIvaOCodiceFiscale
    ) {
        Auto auto = srvAuto.cercaAuto(idAuto);
        Stato stato = srvStato.cercaStato(2L);
        Date oggiMeno24H = new Date(System.currentTimeMillis() - 86400000L);
        try {
            StoriaStati storiaStati = srvStoriaStati.creaStoriaStati(0L, dataInizio, dataFine, "Da Implementare", prezzo, nominativoUtilizzatore, partitaIvaOCodiceFiscale, stato, auto);
            srvStoriaStati.checkDate(storiaStati, srvStoriaStati.listaStoriaStatiAuto(auto), true, 0L);
            srvStoriaStati.nuovoStoriaStati(storiaStati);
            model.addAttribute("storiaStati", storiaStati);
            model.addAttribute("auto", srvAuto.cercaAuto(idAuto));
            model.addAttribute("fatto", "Noleggio");
            return "fine";
        } catch (EccezioniInput e) {
            model.addAttribute("messaggio", e.getMessage());
            model.addAttribute("auto", srvAuto.cercaAuto(idAuto));
            model.addAttribute("listaStati", srvStoriaStati.listaStoriaStatiAuto(auto, oggiMeno24H));
            return "noleggio";
        }
    }

    @RequestMapping("/aggiungiManutenzione")
    public String aggiungiManutenzione(
            ModelMap model,
            @RequestParam("dataInizio") Date dataInizio,
            @RequestParam("dataFine") Date dataFine,
            @RequestParam("costo") Double costo,
            @RequestParam("idAuto") Long idAuto,
            @RequestParam("idOfficina") String idOfficina
    ) {
        Auto auto = srvAuto.cercaAuto(idAuto);
        Stato stato = srvStato.cercaStato(4L);
        Date oggiMeno24H = new Date(System.currentTimeMillis() - 86400000L);
        try {
            OfficinaConvenzionata officina = srvOfficinaConvenzionata.cercaOfficina(Long.parseLong(idOfficina));
            StoriaStati storiaStati = srvStoriaStati.creaStoriaStati(0L, dataInizio, dataFine, "Da Implementare", costo, officina.getNomeOfficina(), officina.getPartitaIva(), stato, auto);
            srvStoriaStati.checkDate(storiaStati, srvStoriaStati.listaStoriaStatiAuto(auto), true, 0L);
            srvStoriaStati.nuovoStoriaStati(storiaStati);
            model.addAttribute("storiaStati", storiaStati);
            model.addAttribute("auto", srvAuto.cercaAuto(idAuto));
            model.addAttribute("fatto", "Manutenzione");
            return "fine";
        } catch (EccezioniInput e) {
            model.addAttribute("messaggio", e.getMessage());
            model.addAttribute("listaStati", srvStoriaStati.listaStoriaStatiAuto(auto, oggiMeno24H));
            model.addAttribute("auto", srvAuto.cercaAuto(idAuto));
            return "riparazioneManutenzione";
        } catch (NumberFormatException e){
            
            model.addAttribute("messaggio", "Scegliere un'officina");
            model.addAttribute("listaStati", srvStoriaStati.listaStoriaStatiAuto(auto, oggiMeno24H));
            model.addAttribute("auto", srvAuto.cercaAuto(idAuto));
            return "riparazioneManutenzione";
        }
    }

    @RequestMapping("/aggiungiRiparazione")
    public String aggiungiRiparazione(
            ModelMap model,
            @RequestParam("dataInizio") Date dataInizio,
            @RequestParam("dataFine") Date dataFine,
            @RequestParam("costo") Double costo,
            @RequestParam("idAuto") Long idAuto,
            @RequestParam("idOfficina") String idOfficina
    ) {
        Auto auto = srvAuto.cercaAuto(idAuto);
        Stato stato = srvStato.cercaStato(3L);
        Date oggiMeno24H = new Date(System.currentTimeMillis() - 86400000L);
        try {
            OfficinaConvenzionata officina = srvOfficinaConvenzionata.cercaOfficina(Long.parseLong(idOfficina));
            StoriaStati storiaStati = srvStoriaStati.creaStoriaStati(0L, dataInizio, dataFine, "Da Implementare", costo, officina.getNomeOfficina(), officina.getPartitaIva(), stato, auto);
            srvStoriaStati.checkDate(storiaStati, srvStoriaStati.listaStoriaStatiAuto(auto), true, 0L);
            srvStoriaStati.nuovoStoriaStati(storiaStati);
            model.addAttribute("auto", srvAuto.cercaAuto(idAuto));
            model.addAttribute("fatto", "Riparazione");
            return "fine";
        } catch (EccezioniInput e) {
            model.addAttribute("messaggio", e.getMessage());
            model.addAttribute("auto", srvAuto.cercaAuto(idAuto));
            model.addAttribute("listaStati", srvStoriaStati.listaStoriaStatiAuto(auto, oggiMeno24H));
            return "riparazioneManutenzione";
        }catch (NumberFormatException e){
            
            model.addAttribute("messaggio", "Scegliere un'officina");
            model.addAttribute("listaStati", srvStoriaStati.listaStoriaStatiAuto(auto, oggiMeno24H));
            model.addAttribute("auto", srvAuto.cercaAuto(idAuto));
            return "riparazioneManutenzione";
        }
    }

    @RequestMapping("/modificaRimuovi")
    public String modificaRimuovi(
            ModelMap model,
            @RequestParam("idAuto") Long idAuto
    ) {
        Auto auto = srvAuto.cercaAuto(idAuto);
        Date oggiMeno24H = new Date(System.currentTimeMillis() - 86400000L);
        model.addAttribute("auto", srvAuto.cercaAuto(idAuto));
        model.addAttribute("listaStati", srvStoriaStati.listaStoriaStatiAuto(auto, oggiMeno24H));
        return "modificaRimuovi";
    }

    @RequestMapping("/modificaStato")
    public String modificaStato(
            ModelMap model,
            @RequestParam("id") Long id,
            @RequestParam("dataInizio") Date dataInizio,
            @RequestParam("dataFine") Date dataFine,
            @RequestParam("costo") Double costo,
            @RequestParam("idAuto") Long idAuto,
            @RequestParam("idStato") Long idStato,
            @RequestParam("nominativoUtilizzatore") String nominativoUtilizzatore,
            @RequestParam("partitaIvaOCodiceFiscale") String partitaIvaOCodiceFiscale
    ) {
        Date oggiMeno24H = new Date(System.currentTimeMillis() - 86400000L);
        Auto auto = srvAuto.cercaAuto(idAuto);
        Stato stato = srvStato.cercaStato(idStato);
        try {
            StoriaStati storiaStati = srvStoriaStati.creaStoriaStati(id, dataInizio, dataFine, "Da Implementare", costo, nominativoUtilizzatore, partitaIvaOCodiceFiscale, stato, auto);
            srvStoriaStati.checkDate(storiaStati, srvStoriaStati.listaStoriaStatiAuto(auto), false, id);
            srvStoriaStati.modifica(storiaStati);
            model.addAttribute("auto", auto);
            model.addAttribute("listaStati", srvStoriaStati.listaStoriaStatiAuto(auto, oggiMeno24H));
            model.addAttribute("auto", srvAuto.cercaAuto(idAuto));
            model.addAttribute("fatto", "Riparazione");
        } catch (EccezioniInput e) {
            model.addAttribute("messaggio", e.getMessage());
            model.addAttribute("auto", srvAuto.cercaAuto(idAuto));
        }
        return "modificaRimuovi";
    }

    @RequestMapping("/cancellaStato")
    public String eliminaStato(
            ModelMap model,
            @RequestParam("id") Long id,
            @RequestParam("idAuto") Long idAuto
    ) {
        srvStoriaStati.elimina(id);
        Date oggiMeno24H = new Date(System.currentTimeMillis() - 86400000L);
        Auto auto = srvAuto.cercaAuto(idAuto);
        model.addAttribute("auto", auto);
        model.addAttribute("listaStati", srvStoriaStati.listaStoriaStatiAuto(auto, oggiMeno24H));
        model.addAttribute("fatto", "Riparazione");
        return "modificaRimuovi";
    }
}
