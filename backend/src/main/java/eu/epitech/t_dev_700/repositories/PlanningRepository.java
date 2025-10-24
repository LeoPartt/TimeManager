package eu.epitech.t_dev_700.repositories;

import eu.epitech.t_dev_700.entities.PlanningEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanningRepository extends JpaRepository<PlanningEntity, Long> {

}
