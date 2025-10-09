package eu.epitech.t_dev_700.repositories;

import eu.epitech.t_dev_700.entities.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<MembershipEntity, Long> {

}