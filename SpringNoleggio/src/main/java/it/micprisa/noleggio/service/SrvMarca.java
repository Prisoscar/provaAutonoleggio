package it.micprisa.noleggio.service;

import it.micprisa.noleggio.eccezioni.EccezioniInput;
import it.micprisa.noleggio.model.Marca;
import java.util.List;

public interface SrvMarca {
    public List<Marca> listaMarche ();
    public List<Marca> ordinaXMarche(byte countOrdina);
    public void modificaMarca(Marca marca);
    public Marca creaMarca(long id, String descrizione) throws EccezioniInput;
    public Marca cercaMarca(long idMarca);
    public void aggiungiMarca(Marca marca);

}
