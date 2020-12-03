
package it.micprisa.noleggio.service;

import it.micprisa.noleggio.eccezioni.EccezioniInput;
import it.micprisa.noleggio.model.Auto;
import it.micprisa.noleggio.model.Stato;
import it.micprisa.noleggio.model.StoriaStati;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.micprisa.noleggio.repository.RepStoriaStati;

@Service
public class SrvStoriaStatiImpl implements SrvStoriaStati {

    @Autowired
    RepStoriaStati repStoriaStati;

    @Override
    public StoriaStati creaStoriaStati(long id, Date dataInizio, Date dataFine, String descrizione, double costo, String nominativoUtilizzatore, String partitaIvaOCodiceFiscale, Stato stato, Auto auto) throws EccezioniInput {
        if (costo < 0 || Double.isNaN(costo)) {
            throw new EccezioniInput("Errore inserimento dati: costo errato!");
        }
        return new StoriaStati(id, dataInizio, dataFine, descrizione, costo, nominativoUtilizzatore, partitaIvaOCodiceFiscale, stato, auto);
    }

    @Override
    public void checkDate(StoriaStati storiaStati, List<StoriaStati> listaStoriaStatiAuto, boolean consideraDataInizio, long ignora) throws EccezioniInput {
        Date oggiMeno24H = new Date(System.currentTimeMillis() - 86400000L);

        if (consideraDataInizio) {
            if (storiaStati.getDataInizio().before(oggiMeno24H)) {
                throw new EccezioniInput("Errore inserimento dati: data inizio inserita antecedente a oggi!");
            }
        }
        if (storiaStati.getDataFine().before(oggiMeno24H)) {
            throw new EccezioniInput("Errore inserimento dati: data fine inserita antecedente a oggi!");
        }
        if (storiaStati.getDataFine().before(storiaStati.getDataInizio())) {
            throw new EccezioniInput("Errore inserimento dati: data di fine operazione antecedente a data di inizio operazione!");
        }
        if (listaStoriaStatiAuto.parallelStream().filter(stat
                -> ((stat.getId() != ignora) && ((storiaStati.getDataInizio().after(stat.getDataInizio()) && storiaStati.getDataInizio().before(stat.getDataFine())) || (storiaStati.getDataFine().after(stat.getDataInizio()) && storiaStati.getDataFine().before(stat.getDataFine()))))
        ).findFirst().orElse(null) != null) {
            throw new EccezioniInput("Errore inserimento dati: inizio o fine operazione durante intervallo di non disponibilità dell'auto!");
        }

        if (listaStoriaStatiAuto.parallelStream().filter(stat
                -> ((stat.getId() != ignora) && (!(storiaStati.getDataInizio().after(stat.getDataFine())) && !(stat.getDataFine().after(storiaStati.getDataInizio()))))
        ).findFirst().orElse(null) != null) {
            throw new EccezioniInput("Errore inserimento dati: inizio operazione sovrapposta a data di fine di altra operazione!");
        }

        if (listaStoriaStatiAuto.parallelStream().filter(stat
                -> ((stat.getId() != ignora) && (!(storiaStati.getDataFine().after(stat.getDataInizio())) && !(stat.getDataInizio().after(storiaStati.getDataFine()))))
        ).findFirst().orElse(null) != null) {
            throw new EccezioniInput("Errore inserimento dati: fine operazione sovrapposta a data di inizio di altra operazione!");
        }

        if (listaStoriaStatiAuto.parallelStream().filter(stat
                -> ((stat.getId() != ignora) && (stat.getDataInizio().after(storiaStati.getDataInizio()) && stat.getDataFine().before(storiaStati.getDataFine())))
        ).findFirst().orElse(null) != null) {
            throw new EccezioniInput("Errore inserimento dati: tra l'inizio e la fine dell'operazione l'auto non è disponibile");
        }
    }

    @Override
    public void nuovoStoriaStati(StoriaStati storiaStati) {
        repStoriaStati.save((storiaStati));
    }

    @Override
    public List<StoriaStati> listaStoriaStatiAuto(Auto auto) {
        return repStoriaStati.findByAuto(auto);
    }

    @Override
    public List<StoriaStati> listaStoriaStatiAuto(Auto auto, Date oggiMeno24H) {
        return repStoriaStati.findByAutoAndDataFineAfter(auto, oggiMeno24H);
    }

    @Override
    public void modifica(StoriaStati storiaStati) {
        repStoriaStati.save((storiaStati));
    }

    @Override
    public void elimina(Long id) {
        repStoriaStati.deleteById(id);
    }

    @Override
    public StoriaStati cercaStoriaStati(Long id) {
        return repStoriaStati.findById(id).get();
    }

    @Override
    public void cancellaStorieStatiAuto(Auto auto) {
        //repStoriaStati.deleteByAuto(auto);
        repStoriaStati.findByAuto(auto).forEach(storiaStati ->
        repStoriaStati.deleteById(storiaStati.getId())
        );
    }

}
