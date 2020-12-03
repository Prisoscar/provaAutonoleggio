/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.micprisa.noleggio.repository;

import it.micprisa.noleggio.model.Auto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepAuto extends JpaRepository<Auto, Long> {

    public List<Auto> findByOrderByCilindrataAsc();

    public List<Auto> findByOrderByCilindrataDesc();

    @Query("SELECT a FROM Auto a, Marca m WHERE a.marca = m.id ORDER BY m.descrizione ASC")
    public List<Auto> listaMarcaAsc();

    @Query("SELECT a FROM Auto a, Marca m WHERE a.marca = m.id ORDER BY m.descrizione DESC")
    public List<Auto> listaMarcaDesc();

    @Query("SELECT a FROM Auto a, Marca m WHERE a.marca = m.id AND m.id = ?1 ORDER BY m.descrizione ASC")
    public List<Auto> listaMarcaAsc(Long id);

    @Query("SELECT a FROM Auto a, Marca m WHERE a.marca = m.id AND m.id = ?1 ORDER BY m.descrizione DESC")
    public List<Auto> listaMarcaDesc(Long id);

    @Query("SELECT a FROM Auto a WHERE a.marca.id = ?1 ORDER BY a.cilindrata ASC")
    public List<Auto> listaCilindrataAsc(long id);

    @Query("SELECT a FROM Auto a WHERE a.marca.id = ?1 ORDER BY a.cilindrata DESC")
    public List<Auto> listaCilindrataDesc(long id);

}
