/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.micprisa.noleggio.service;

import it.micprisa.noleggio.eccezioni.EccezioniInput;
import it.micprisa.noleggio.model.Auto;
import it.micprisa.noleggio.model.Marca;
import java.util.List;

public interface SrvAuto {

    public Auto cercaAuto(Long id);

    public void aggiungiAuto(Auto auto);

    public List<Auto> lista();

    public Auto creaAuto(String id, Marca marca, String modello, String cilindrata, String targa) throws EccezioniInput;

    public List<Auto> listaFiltrataXMarcaOCilindrata(String idMarca, long contatoreMarcaString, long contatoreCilindrataString);

    public void cancella(Long id);

    public void modifica(Auto auto);

    public List<Auto> ordinaXCilindrata();

    public long settaContatoreMarca(String contatoreMarca);

    public long settaContatoreCilindrata(String contatoreCilindrata);

    public String settaIdMarca(String idMarca);
}
