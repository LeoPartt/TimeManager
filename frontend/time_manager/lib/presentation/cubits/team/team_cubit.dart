import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:time_manager/domain/usecases/team/create_team.dart';
import 'team_state.dart';

class TeamCubit extends Cubit<TeamState> {
  final CreateTeam createTeamUseCase;

  TeamCubit({required this.createTeamUseCase}) : super(const TeamState.initial());

  Future<void> createTeam({required String name, required String description}) async {
    emit(const TeamState.loading());
    try {
      final team = await createTeamUseCase(name: name, description: description);
      emit(TeamState.loaded(team));
    } catch (e) {
      emit(TeamState.error(e.toString()));
    }
  }
}
