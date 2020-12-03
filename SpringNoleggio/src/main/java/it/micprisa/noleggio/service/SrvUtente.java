/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.micprisa.noleggio.service;

import it.micprisa.noleggio.eccezioni.EccezioniInput;
import it.micprisa.noleggio.model.Utente;

/**
 *
 * @author mprisacar
 */
public interface SrvUtente {
    public void checkPassword (String password, Utente utente)throws EccezioniInput;
    public Utente cercaUtente(long id);
}
