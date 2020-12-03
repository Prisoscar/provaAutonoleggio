/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.micprisa.noleggio.repository;

import it.micprisa.noleggio.model.Marca;
import it.micprisa.noleggio.model.OfficinaConvenzionata;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author priac
 */
public interface RepOfficinaConvenzionata extends JpaRepository <OfficinaConvenzionata, Long>{
    public List<OfficinaConvenzionata> findByOrderByNomeOfficinaAsc();
    public List<OfficinaConvenzionata> findByOrderByNomeOfficinaDesc();
}
