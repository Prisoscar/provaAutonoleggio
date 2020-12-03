/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.micprisa.noleggio.service;

import it.micprisa.noleggio.eccezioni.EccezioniInput;
import it.micprisa.noleggio.model.Auto;
import it.micprisa.noleggio.model.Stato;
import it.micprisa.noleggio.model.StoriaStati;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author priac
 */
public interface SrvStoriaStati {

    public StoriaStati creaStoriaStati(long id, Date dataInizio, Date dataFine, String descrizione, double costo, String nominativoUtilizzatore, String partitaIvaOCodiceFiscale, Stato stato, Auto auto) throws EccezioniInput;

    public void checkDate(StoriaStati storiaStati, List<StoriaStati> listaStoriaStatiAuto, boolean consideraDataInizio, long ignora) throws EccezioniInput;

    public void nuovoStoriaStati(StoriaStati storiaStati);

    public List<StoriaStati> listaStoriaStatiAuto(Auto auto);

    public List<StoriaStati> listaStoriaStatiAuto(Auto auto, Date oggiMeno24H);

    public void modifica(StoriaStati storiaStati);

    public void elimina(Long id);

    public StoriaStati cercaStoriaStati(Long id);

    public void cancellaStorieStatiAuto(Auto auto);
}
