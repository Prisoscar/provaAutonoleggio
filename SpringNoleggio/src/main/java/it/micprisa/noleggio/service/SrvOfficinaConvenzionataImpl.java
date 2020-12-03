/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.micprisa.noleggio.service;

import it.micprisa.noleggio.eccezioni.EccezioniInput;
import it.micprisa.noleggio.model.OfficinaConvenzionata;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.micprisa.noleggio.repository.RepOfficinaConvenzionata;

@Service
public class SrvOfficinaConvenzionataImpl implements SrvOfficinaConvenzionata {

    @Autowired
    RepOfficinaConvenzionata repOfficinaConvenzionata;

    @Override
    public List<OfficinaConvenzionata> listaOfficine(byte count) {
        if (count % 2 == 0) {
            return repOfficinaConvenzionata.findByOrderByNomeOfficinaAsc();
        } else {
            return repOfficinaConvenzionata.findByOrderByNomeOfficinaDesc();
        }
    }

    @Override
    public OfficinaConvenzionata creaOfficina(long id, String nomeOfficina, String partitaIva) throws EccezioniInput{
        if(nomeOfficina.equals("") || partitaIva.equals("")){
            throw new EccezioniInput("Nome officina e partita iva obbligatori");
        }
        return new OfficinaConvenzionata(id, nomeOfficina, partitaIva);
    }

    @Override
    public void nuovaOfficina(OfficinaConvenzionata officinaConvenzionata) {
        repOfficinaConvenzionata.save(officinaConvenzionata);
    }

    @Override
    public OfficinaConvenzionata cercaOfficina(long id) {
        return repOfficinaConvenzionata.findById(id).get();
    }

    @Override
    public void modificaOfficina(OfficinaConvenzionata officinaConvenzionata) {
        repOfficinaConvenzionata.save(officinaConvenzionata);
    }

    @Override
    public void eliminaOfficina(long id) {
        repOfficinaConvenzionata.deleteById(id);
    }
}
