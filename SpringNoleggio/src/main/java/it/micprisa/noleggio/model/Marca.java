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
@Table(name ="marca")
public class Marca implements Serializable {
    @Id
    @Column (name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column (name = "descrizione")
    private String descrizione;

    @OneToMany(mappedBy="marca", fetch = FetchType.LAZY)
    private List<Auto> auto = new ArrayList<>();
    
    public Marca(long id, String descrizione) {
        this.id = id;
        this.descrizione = descrizione;
    }
    
    public Marca (){
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Auto> getAuto() {
        return auto;
    }

    public void setAuto(List<Auto> auto) {
        this.auto = auto;
    }
    
    
    
}
