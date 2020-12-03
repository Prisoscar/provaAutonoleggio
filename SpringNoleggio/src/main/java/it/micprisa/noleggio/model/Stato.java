
package it.micprisa.noleggio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="stato")
public class Stato implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="descrizione")
    private String descrizione;
    
    @OneToMany(mappedBy = "stato", fetch = FetchType.LAZY)
    private List<StoriaStati> storiaStati = new ArrayList<>();
    
    public Stato() {
    }
    

    public Stato(Long id, String descrizione) {
        this.id = id;
        this.descrizione = descrizione;
    }
    
    public List<StoriaStati> getStoriaStati() {
        return storiaStati;
    }

    public void setStoriaStati(List<StoriaStati> storiaStati) {
        this.storiaStati = storiaStati;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    
    
}
