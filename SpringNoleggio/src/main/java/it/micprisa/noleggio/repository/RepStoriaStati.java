package it.micprisa.noleggio.repository;

import it.micprisa.noleggio.model.Auto;
import it.micprisa.noleggio.model.StoriaStati;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepStoriaStati extends JpaRepository <StoriaStati, Long>{

    public List<StoriaStati> findByAuto(Auto auto);

    public List<StoriaStati> findByAutoAndDataFineAfter(Auto auto, Date oggiMeno24H);

    public List<StoriaStati> findByDataFineAfter(Date oggiMeno24H);

    public List<StoriaStati> findByDataFineGreaterThan(Date oggiMeno24H);

    public void deleteByAuto(Auto auto);
    
}
