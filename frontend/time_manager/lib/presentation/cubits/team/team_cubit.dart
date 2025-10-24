import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:time_manager/domain/usecases/team/create_team.dart';
import 'package:time_manager/domain/usecases/team/get_teams.dart';
import 'team_state.dart';

class TeamCubit extends Cubit<TeamState> {
  final CreateTeam createTeamUseCase;
  final GetTeams getTeamsUseCase;


  TeamCubit({required this.createTeamUseCase, required this.getTeamsUseCase}) : super(const TeamState.initial());

  Future<void> createTeam({required String name, required String description}) async {
    emit(const TeamState.loading());
    try {
      final team = await createTeamUseCase(name: name, description: description);
      emit(TeamState.loaded(team));
    } catch (e) {
      emit(TeamState.error(e.toString()));
    }
  }

  Future<void> getTeams() async {
    emit(const TeamState.loading());
    try {
      final teams = await getTeamsUseCase();
      emit(TeamState.loadedTeams(teams));
    } catch (e) {
      emit(TeamState.error(e.toString()));
    }
  }
}
