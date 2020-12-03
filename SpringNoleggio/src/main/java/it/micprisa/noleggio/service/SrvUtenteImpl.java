/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.micprisa.noleggio.service;

import it.micprisa.noleggio.eccezioni.EccezioniInput;
import it.micprisa.noleggio.model.Utente;
import it.micprisa.noleggio.repository.RepUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author mprisacar
 */
@Service
public class SrvUtenteImpl implements SrvUtente {

    @Autowired
    RepUtente repUtente;

    @Override
    public void checkPassword(String password, Utente utente) throws EccezioniInput {
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        if (!bCrypt.matches(password, utente.getPassword())) {
            throw new EccezioniInput("Errore inserimento dati: password errata!");
        }
    }

    @Override
    public Utente cercaUtente(long id) {
        return repUtente.findById(id).get();
    }

}
