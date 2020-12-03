package it.micprisa.noleggio.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "storiaStati")
public class StoriaStati implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="dataInizio")
    private Date dataInizio;
    
    @Column(name="dataFine")
    private Date dataFine;
    
    @Column (name = "descrizione")
    private String descrizione;
    
    @Column (name = "costo")
    private double costo;
    
    @Column (name = "nominativoUtilizzatore")
    private String nominativoUtilizzatore;
    
    @Column (name = "partitaIvaOCodiceFiscale")
    private String partitaIvaOCodiceFiscale;
    
    @ManyToOne
    @JoinColumn(name="idStato")
    private Stato stato;
    
    @ManyToOne
    @JoinColumn(name="idAuto")
    private Auto auto;

    public StoriaStati() {
    }

    public StoriaStati(Long id, Date dataInizio, Date dataFine, String descrizione, double costo, String nominativoUtilizzatore, String partitaIvaOCodiceFiscale, Stato stato, Auto auto) {
        this.id = id;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.descrizione = descrizione;
        this.costo = costo;
        this.nominativoUtilizzatore = nominativoUtilizzatore;
        this.partitaIvaOCodiceFiscale = partitaIvaOCodiceFiscale;
        this.stato = stato;
        this.auto = auto;
    }
    
    public Long getId() {
        return id;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getNominativoUtilizzatore() {
        return nominativoUtilizzatore;
    }

    public void setNominativoUtilizzatore(String nominativoUtilizzatore) {
        this.nominativoUtilizzatore = nominativoUtilizzatore;
    }

    public String getPartitaIvaOCodiceFiscale() {
        return partitaIvaOCodiceFiscale;
    }

    public void setPartitaIvaOCodiceFiscale(String partitaIvaOCodiceFiscale) {
        this.partitaIvaOCodiceFiscale = partitaIvaOCodiceFiscale;
    }

}
