/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="auto")
public class Auto implements Serializable{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="modello")
    private String modello;
    @Column(name="cilindrata")
    private Double cilindrata;
    @Column(name="targa")
    private String targa;
    
    @ManyToOne
    @JoinColumn(name="idMarca")
    private Marca marca;
    
    @OneToMany(mappedBy="auto", fetch = FetchType.LAZY)
    private List<StoriaStati> storiaStati = new ArrayList<>();
    
    public Auto() {
    }

    public Auto(Long id, String modello, Double cilindrata, String targa, Marca marca) {
        this.id = id;
        this.modello = modello;
        this.cilindrata = cilindrata;
        this.targa = targa;
        this.marca = marca;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public Double getCilindrata() {
        return cilindrata;
    }

    public void setCilindrata(Double cilindrata) {
        this.cilindrata = cilindrata;
    }

    @Override
    public String toString() {
        return marca+" "+modello+" "+cilindrata;
    }
    
    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public List<StoriaStati> getStoriaStati() {
        return storiaStati;
    }

    public void setStoriaStati(List<StoriaStati> storiaStati) {
        this.storiaStati = storiaStati;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    
    
}
