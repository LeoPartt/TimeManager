import 'package:time_manager/domain/entities/team.dart';
import 'package:time_manager/domain/usecases/team/update_team.dart';

abstract class TeamRepository {
  Future<List<Team>> getTeams();
  Future<Team> getTeam(int id);
  Future<Team> createTeam({
    required String name,
    String? description,
  });
  Future<Team> updateTeam(UpdateTeamParams params);
  Future<void> deleteTeam(int id);
}
