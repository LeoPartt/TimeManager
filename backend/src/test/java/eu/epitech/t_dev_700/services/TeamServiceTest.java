package eu.epitech.t_dev_700.services;

import eu.epitech.t_dev_700.entities.AccountEntity;
import eu.epitech.t_dev_700.entities.UserEntity;
import eu.epitech.t_dev_700.services.components.UserComponent;
import eu.epitech.t_dev_700.services.exceptions.ResourceNotFound;
import eu.epitech.t_dev_700.entities.TeamEntity;
import eu.epitech.t_dev_700.mappers.TeamMapper;
import eu.epitech.t_dev_700.models.TeamModels;
import eu.epitech.t_dev_700.repositories.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private TeamMapper teamMapper;

    @Mock
    private MembershipService membershipService;

    @InjectMocks
    private TeamService teamService;

    @InjectMocks
    private UserComponent userComponent;

    private TeamEntity teamEntity;
    private TeamModels.TeamResponse teamResponse;
    private TeamModels.PostTeamRequest postRequest;
    private TeamModels.PutTeamRequest putRequest;
    private TeamModels.PatchTeamRequest patchRequest;

    @BeforeEach
    void setUp() {
        teamEntity = new TeamEntity();
        teamEntity.setId(1L);
        teamEntity.setName("Development Team");
        teamEntity.setDescription("A team of developers");

        teamResponse = new TeamModels.TeamResponse(
                1L,
                "Development Team",
                "A team of developers"
        );

        postRequest = new TeamModels.PostTeamRequest(
                "Development Team",
                "A team of developers"
        );

        putRequest = new TeamModels.PutTeamRequest(
                "Development Team Updated",
                "Updated description"
        );

        patchRequest = new TeamModels.PatchTeamRequest(
                null,
                "Patched description"
        );

        UserEntity user = new UserEntity();
        user.setAccount(new AccountEntity());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(
                new UsernamePasswordAuthenticationToken(user.getAccount(), null, List.of())
        );
        SecurityContextHolder.setContext(context);
    }

    @Test
    void testList_shouldReturnAllTeams() {
        List<TeamEntity> entities = Collections.singletonList(teamEntity);
        TeamModels.TeamResponse[] models = new TeamModels.TeamResponse[]{teamResponse};

        when(teamRepository.findAll()).thenReturn(entities);
        when(teamMapper.listEntity(entities)).thenReturn(models);

        TeamModels.TeamResponse[] result = teamService.list();

        assertThat(result).hasSize(1);
        assertThat(result[0]).isEqualTo(teamResponse);
        verify(teamRepository).findAll();
        verify(teamMapper).listEntity(entities);
    }

    @Test
    void testGet_whenTeamExists_shouldReturnTeam() {
        when(teamRepository.findById(1L)).thenReturn(Optional.of(teamEntity));
        when(teamMapper.toModel(teamEntity)).thenReturn(teamResponse);

        TeamModels.TeamResponse result = teamService.get(1L);

        assertThat(result).isEqualTo(teamResponse);
        verify(teamRepository).findById(1L);
        verify(teamMapper).toModel(teamEntity);
    }

    @Test
    void testGet_whenTeamNotExists_shouldThrowException() {
        when(teamRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> teamService.get(999L))
                .isInstanceOf(ResourceNotFound.class);

        verify(teamRepository).findById(999L);
        verify(teamMapper, never()).toModel(any());
    }

    @Test
    void testCreate_shouldCreateTeam() {
        when(teamMapper.createEntity(postRequest)).thenReturn(teamEntity);
        when(teamRepository.save(teamEntity)).thenReturn(teamEntity);
        when(teamMapper.toModel(teamEntity)).thenReturn(teamResponse);

        TeamModels.TeamResponse result = teamService.create(postRequest);

        assertThat(result).isEqualTo(teamResponse);
        verify(teamMapper).createEntity(postRequest);
        verify(teamRepository).save(teamEntity);
        verify(teamMapper).toModel(teamEntity);
    }

    @Test
    void testReplace_whenTeamExists_shouldReplaceTeam() {
        when(teamRepository.findById(1L)).thenReturn(Optional.of(teamEntity));
        doNothing().when(teamMapper).replaceEntity(teamEntity, putRequest);
        when(teamRepository.save(teamEntity)).thenReturn(teamEntity);
        when(teamMapper.toModel(teamEntity)).thenReturn(teamResponse);

        TeamModels.TeamResponse result = teamService.replace(1L, putRequest);

        assertThat(result).isEqualTo(teamResponse);
        verify(teamRepository).findById(1L);
        verify(teamMapper).replaceEntity(teamEntity, putRequest);
        verify(teamRepository).save(teamEntity);
        verify(teamMapper).toModel(teamEntity);
    }

    @Test
    void testReplace_whenTeamNotExists_shouldThrowException() {
        when(teamRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> teamService.replace(999L, putRequest))
                .isInstanceOf(ResourceNotFound.class);

        verify(teamRepository).findById(999L);
        verify(teamMapper, never()).replaceEntity(any(), any());
        verify(teamRepository, never()).save(any());
    }

    @Test
    void testUpdate_whenTeamExists_shouldUpdateTeam() {
        when(teamRepository.findById(1L)).thenReturn(Optional.of(teamEntity));
        doNothing().when(teamMapper).updateEntity(teamEntity, patchRequest);
        when(teamRepository.save(teamEntity)).thenReturn(teamEntity);
        when(teamMapper.toModel(teamEntity)).thenReturn(teamResponse);

        TeamModels.TeamResponse result = teamService.update(1L, patchRequest);

        assertThat(result).isEqualTo(teamResponse);
        verify(teamRepository).findById(1L);
        verify(teamMapper).updateEntity(teamEntity, patchRequest);
        verify(teamRepository).save(teamEntity);
        verify(teamMapper).toModel(teamEntity);
    }

    @Test
    void testUpdate_whenTeamNotExists_shouldThrowException() {
        when(teamRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> teamService.update(999L, patchRequest))
                .isInstanceOf(ResourceNotFound.class);

        verify(teamRepository).findById(999L);
        verify(teamMapper, never()).updateEntity(any(), any());
        verify(teamRepository, never()).save(any());
    }

    @Test
    void testDelete_whenTeamExists_shouldDeleteTeam() {
        when(teamRepository.findById(1L)).thenReturn(Optional.of(teamEntity));
        doNothing().when(teamRepository).delete(teamEntity);

        teamService.delete(1L);

        verify(teamRepository).findById(1L);
        verify(teamRepository).delete(teamEntity);
    }

    @Test
    void testDelete_whenTeamNotExists_shouldThrowException() {
        when(teamRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> teamService.delete(999L))
                .isInstanceOf(ResourceNotFound.class);

        verify(teamRepository).findById(999L);
        verify(teamRepository, never()).delete(any());
    }

    @Test
    void testGetOrThrow_whenTeamExists_shouldReturnTeam() {
        when(teamRepository.findById(1L)).thenReturn(Optional.of(teamEntity));

        TeamEntity result = teamService.findEntityOrThrow(1L);

        assertThat(result).isEqualTo(teamEntity);
        verify(teamRepository).findById(1L);
    }

    @Test
    void testGetOrThrow_whenTeamNotExists_shouldThrowException() {
        when(teamRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> teamService.findEntityOrThrow(999L))
                .isInstanceOf(ResourceNotFound.class)
                .hasMessageContaining("team")
                .hasMessageContaining("999");

        verify(teamRepository).findById(999L);
    }
}
