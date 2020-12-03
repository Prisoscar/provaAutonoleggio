package it.micprisa.noleggio.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="officinaConvenzionata")
public class OfficinaConvenzionata implements Serializable {
    @Id
    @Column (name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column (name = "nomeOfficina")
    private String nomeOfficina;
    
    @Column (name = "partitaIva")
    private String partitaIva;    

    public OfficinaConvenzionata(long id, String nomeOfficina, String partitaIva) {
        this.id = id;
        this.nomeOfficina = nomeOfficina;
        this.partitaIva = partitaIva;
    }
    
    public OfficinaConvenzionata (){
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }  

    public String getNomeOfficina() {
        return nomeOfficina;
    }

    public void setNomeOfficina(String nomeOfficina) {
        this.nomeOfficina = nomeOfficina;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }
}
