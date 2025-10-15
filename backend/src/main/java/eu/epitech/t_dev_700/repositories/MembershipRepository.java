package eu.epitech.t_dev_700.repositories;

import eu.epitech.t_dev_700.entities.MembershipEntity;
import eu.epitech.t_dev_700.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<MembershipEntity, Long> {

    List<MembershipEntity> findByUser(UserEntity user);

    boolean existsByUserAndTeam_Id(UserEntity user, Long team_id);

    boolean existsByUserAndTeam_IdAndRole(UserEntity currentUser, Long teamId, MembershipEntity.TeamRole role);

    boolean existsByUserAndRole(UserEntity user, MembershipEntity.TeamRole role);

    @Query("""
    select (count(mA) > 0)
    from MembershipEntity mA
    join MembershipEntity mB on mB.team = mA.team
    where mA.user.id = :userId
      and mB.user = :other
      and mB.role = eu.epitech.t_dev_700.entities.MembershipEntity.TeamRole.MANAGER
""")
    boolean existsMembershipOfUserIdOnTeamsManagedByOther(
            @Param("userId") Long userId,
            @Param("other") UserEntity other
    );
}