/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.micprisa.noleggio.service;

import it.micprisa.noleggio.eccezioni.EccezioniInput;
import it.micprisa.noleggio.model.Marca;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.micprisa.noleggio.repository.RepMarca;

@Service
public class SrvMarcaImpl implements SrvMarca {

    @Autowired
    RepMarca repMarca;
    
    @Override
    public List<Marca> listaMarche() {
        return repMarca.findByOrderByDescrizioneAsc();
    }

    @Override
    public List<Marca> ordinaXMarche(byte countOrdina) {
        if(countOrdina%2 == 0){
            return repMarca.findByOrderByDescrizioneAsc();
        }else{
            return repMarca.findByOrderByDescrizioneDesc();
        }
    }

    @Override
    public void modificaMarca(Marca marca) {
        repMarca.save(marca);
    }

    @Override
    public Marca creaMarca(long id, String descrizione) throws EccezioniInput {
        if(descrizione.equals("")){
            throw new EccezioniInput("Inserisci la marca!");
        }
        return new Marca(id, descrizione);
    }

    @Override
    public Marca cercaMarca(long idMarca) {
       return repMarca.findById(idMarca).get();
    }

    @Override
    public void aggiungiMarca(Marca marca) {
        repMarca.save(marca);
    }

}
