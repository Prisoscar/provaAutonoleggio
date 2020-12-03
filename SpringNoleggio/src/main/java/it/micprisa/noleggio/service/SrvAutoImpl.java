package it.micprisa.noleggio.service;

import it.micprisa.noleggio.eccezioni.EccezioniInput;
import it.micprisa.noleggio.model.Auto;
import it.micprisa.noleggio.model.Marca;
import it.micprisa.noleggio.repository.RepAuto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SrvAutoImpl implements SrvAuto {

    @Autowired
    RepAuto repAuto;

    @Override
    public void aggiungiAuto(Auto auto) {
        repAuto.save((auto));
    }

    @Override
    public List<Auto> lista() {
        //SELECT a.* FROM auto a, marca m WHERE a.id_marca = m.id order by m.descrizione asc 
        return repAuto.listaMarcaAsc();
    }

    @Override
    public Auto creaAuto(String id, Marca marca, String modello, String cilindrata, String targa) throws EccezioniInput {
        Auto auto = null;
        if (modello.length() > 0 && targa.length() > 0) {
            try {
                auto = new Auto(Long.parseLong(id), modello, Double.parseDouble(cilindrata), targa, marca);
            } catch (Exception e) {
                throw new EccezioniInput("Errore nei dati numerici. " + e.getMessage());
            }
        } else {
            throw new EccezioniInput("Modello e Targa sono obbligatori.");
        }
        return auto;
    }

    @Override
    public List<Auto> listaFiltrataXMarcaOCilindrata(String idMarca, long contatoreMarca, long contatoreCilindrata) {

        if (contatoreCilindrata != 0) {
            if (contatoreCilindrata % 2 != 0) {
                if (idMarca.length() > 0) {
                    return repAuto.listaCilindrataAsc(Long.parseLong(idMarca));
                } else {
                    return repAuto.findByOrderByCilindrataAsc();
                }
            } else {
                if (idMarca.length() > 0) {
                    return repAuto.listaCilindrataDesc(Long.parseLong(idMarca));
                } else {
                    return repAuto.findByOrderByCilindrataDesc();
                }
            }
        } else {
            if (contatoreMarca % 2 == 0 || contatoreMarca == 0) {
                if (idMarca.length() > 0) {
                    return repAuto.listaMarcaAsc(Long.parseLong(idMarca));
                } else {
                    return repAuto.listaMarcaAsc();
                }
            } else {
                if (idMarca.length() > 0) {
                    return repAuto.listaMarcaDesc(Long.parseLong(idMarca));
                } else {
                    return repAuto.listaMarcaDesc();
                }
            }
        }
    }

    @Override
    public void cancella(Long id) {
        repAuto.deleteById(id);
    }

    @Override
    public void modifica(Auto auto) {
        repAuto.save(auto);
    }

    @Override
    public List<Auto> ordinaXCilindrata() {
        return repAuto.findByOrderByCilindrataAsc();
    }

    @Override
    public Auto cercaAuto(Long id) {
        return repAuto.findById(id).get();
    }

    @Override
    public long settaContatoreMarca(String contatoreMarca) {
        if (contatoreMarca == null || contatoreMarca.equals("")) {
            return 0L;
        } else {
            return Long.parseLong(contatoreMarca);
        }
    }

    @Override
    public long settaContatoreCilindrata(String contatoreCilindrata) {
        if (contatoreCilindrata == null || contatoreCilindrata.equals("")) {
            return 0L;
        } else {
            return Long.parseLong(contatoreCilindrata);
        }
    }

    @Override
    public String settaIdMarca(String idMarca) {
        if (idMarca == null) {
            return "";
        } else {
            return idMarca;
        }
    }
}
