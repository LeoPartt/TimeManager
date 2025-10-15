package eu.epitech.t_dev_700.services;

import eu.epitech.t_dev_700.entities.MembershipEntity;
import eu.epitech.t_dev_700.entities.UserEntity;
import eu.epitech.t_dev_700.repositories.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public List<MembershipEntity> getMembershipsOf(UserEntity user) {
        return membershipRepository.findByUser(user);
    }

    public boolean isUserMemberOfTeam(UserEntity user, Long teamId) {
        return membershipRepository.existsByUserAndTeam_Id(user, teamId);
    }

    public boolean isUserManagerOfTeam(UserEntity user, Long teamId) {
        return membershipRepository.existsByUserAndTeam_IdAndRole(user, teamId, MembershipEntity.TeamRole.MANAGER);
    }

    public boolean isUserManager(UserEntity user) {
        return membershipRepository.existsByUserAndRole(user, MembershipEntity.TeamRole.MANAGER);
    }

    public boolean isUserManagerOfOther(UserEntity user, Long userId) {
        return !membershipRepository.existsMembershipOfUserIdOnTeamsManagedByOther(userId, user);
    }
}
