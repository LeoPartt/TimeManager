package eu.epitech.t_dev_700.services.components;

import eu.epitech.t_dev_700.doc.ApiAuthRoles;
import eu.epitech.t_dev_700.entities.AccountEntity;
import eu.epitech.t_dev_700.entities.UserEntity;
import eu.epitech.t_dev_700.services.MembershipService;
import eu.epitech.t_dev_700.utils.AuthRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("userAuth")
@RequiredArgsConstructor
public class UserAuthorization {

    private final MembershipService membershipService;

    public static UserEntity getCurrentUser(Authentication auth) {
        return ((AccountEntity) auth.getPrincipal()).getUser();
    }

    public static UserEntity getCurrentUser() {
        return getCurrentUser(SecurityContextHolder
                .getContext()
                .getAuthentication());
    }

    @ApiAuthRoles(AuthRole.ADMINISTRATOR)
    public boolean isAdmin(Authentication authentication) {
        return ((AccountEntity) authentication.getPrincipal()).isAdmin();
    }

    @ApiAuthRoles(AuthRole.MEMBER_OF)
    public boolean isTeamMember(Authentication authentication, Long teamId) {
        return isAdmin(authentication) || membershipService.isUserMemberOfTeam(getCurrentUser(authentication), teamId);
    }

    @ApiAuthRoles(AuthRole.MANAGER_OF)
    public boolean isTeamManager(Authentication authentication, Long teamId) {
        return isAdmin(authentication) || membershipService.isUserManagerOfTeam(getCurrentUser(authentication), teamId);
    }

    @ApiAuthRoles(AuthRole.MANAGER)
    public boolean isManager(Authentication authentication) {
        return isAdmin(authentication) || membershipService.isUserManager(getCurrentUser(authentication));
    }

    @ApiAuthRoles({AuthRole.SELF, AuthRole.MANAGER})
    public boolean isSelfOrManager(Authentication authentication, Long userId) {
        UserEntity currentUser = getCurrentUser(authentication);
        return isAdmin(authentication) || isSelf(currentUser, userId) || membershipService.isUserManager(getCurrentUser(authentication));
    }

    @ApiAuthRoles({AuthRole.SELF, AuthRole.MANAGER_OF})
    public boolean isSelfOrManagerOfUser(Authentication authentication, Long userId) {
        UserEntity currentUser = getCurrentUser(authentication);
        return isAdmin(authentication) || isSelf(currentUser, userId) || membershipService.isUserManagerOfOther(currentUser, userId);
    }

    @ApiAuthRoles(AuthRole.MANAGER_OF)
    public boolean isManagerOfUser(Authentication authentication, Long userId) {
        UserEntity currentUser = getCurrentUser(authentication);
        return isAdmin(authentication) || !isSelf(currentUser, userId) && membershipService.isUserManagerOfOther(currentUser, userId);
    }

    @ApiAuthRoles(AuthRole.SELF)
    public boolean isSelf(Authentication authentication, Long userId) {
        return isSelf(getCurrentUser(authentication), userId);
    }

    private boolean isSelf(UserEntity currentUser, Long userId) {
        return currentUser.getId().equals(userId);
    }
}
