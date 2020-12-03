/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.micprisa.noleggio.repository;

import it.micprisa.noleggio.model.Stato;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author priac
 */
public interface RepStato extends JpaRepository <Stato, Long>{
}
