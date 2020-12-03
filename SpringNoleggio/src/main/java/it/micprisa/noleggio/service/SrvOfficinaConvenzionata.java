
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.micprisa.noleggio.service;

import it.micprisa.noleggio.eccezioni.EccezioniInput;
import it.micprisa.noleggio.model.OfficinaConvenzionata;
import java.util.List;

public interface SrvOfficinaConvenzionata {

    public List<OfficinaConvenzionata> listaOfficine(byte count);

    public OfficinaConvenzionata creaOfficina(long id, String nomeOfficina, String partitaIva) throws EccezioniInput;

    public void nuovaOfficina(OfficinaConvenzionata officinaConvenzionata);

    public OfficinaConvenzionata cercaOfficina(long id);

    public void modificaOfficina(OfficinaConvenzionata officinaConvenzionata);

    public void eliminaOfficina(long id);
}
