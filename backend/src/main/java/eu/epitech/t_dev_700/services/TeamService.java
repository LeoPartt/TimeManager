package eu.epitech.t_dev_700.services;

import eu.epitech.t_dev_700.controllers.exceptions.ResourceNotFoundException;
import eu.epitech.t_dev_700.entities.TeamEntity;
import eu.epitech.t_dev_700.mappers.TeamMapper;
import eu.epitech.t_dev_700.models.TeamModels;
import eu.epitech.t_dev_700.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final PasswordEncoder passwordEncoder;

    public TeamModels.GetTeamResponse listTeams() {
        return teamMapper.getTeams(teamRepository.findAll());
    }

    public TeamModels.Team createTeam(TeamModels.PostTeamRequest body) {
        TeamEntity entity = teamMapper.createTeam(body, passwordEncoder);
        TeamEntity saved = teamRepository.save(entity);
        return teamMapper.toModel(saved);
    }

    public TeamModels.Team updateTeam(Long id, TeamModels.PutTeamRequest body) {
        TeamEntity entity = getTeamEntityOrThrow(id);
        teamMapper.updateTeam(entity, body);
        TeamEntity saved = teamRepository.save(entity);
        return teamMapper.toModel(saved);
    }

    public void deleteTeam(Long id) {
        teamRepository.delete(getTeamEntityOrThrow(id));
    }

    private TeamEntity getTeamEntityOrThrow(Long id) throws ResourceNotFoundException {
        return teamRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException.supply("Team", id));
    }
}
