/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.micprisa.noleggio.service;

import it.micprisa.noleggio.model.Stato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.micprisa.noleggio.repository.RepStato;

@Service
public class SrvStatoImpl implements SrvStato {

    @Autowired
    RepStato repStato;
    

    @Override
    public Stato cercaStato(long idStato) {
        return repStato.findById(idStato).get();
    }


}
