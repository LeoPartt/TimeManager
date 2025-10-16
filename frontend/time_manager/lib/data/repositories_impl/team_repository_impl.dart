import 'dart:convert';
import 'package:time_manager/data/datasources/local/local_storage_service.dart';
import 'package:time_manager/data/datasources/remote/team_api.dart';
import 'package:time_manager/data/models/team_model.dart';
import 'package:time_manager/domain/entities/team.dart';
import 'package:time_manager/domain/repositories/team_repository.dart';
import 'package:time_manager/domain/usecases/team/update_team.dart';

class TeamRepositoryImpl implements TeamRepository {
  final TeamApi api;
  final LocalStorageService storage;

  TeamRepositoryImpl({
    required this.api,
    required this.storage,
  });

  @override
  Future<List<Team>> getTeams() async {
    final list = await api.getTeams();
    return list
        .map((e) => TeamModel.fromJson(e as Map<String, dynamic>).toDomain())
        .toList();
  }

  @override
  Future<Team> getTeam(int id) async {
    final data = await api.getTeam(id);
    final dto = TeamModel.fromJson(data);
    await storage.saveData('last_team', jsonEncode(dto.toJson()));
    return dto.toDomain();
  }

  @override
  Future<Team> createTeam({
    required String name,
    String? description,
  }) async {
    final data = await api.createTeam({
      'name': name,
      'description': description,
    });
    final dto = TeamModel.fromJson(data);
    await storage.saveData('last_team', jsonEncode(dto.toJson()));
    return dto.toDomain();
  }

  @override
  Future<Team> updateTeam(UpdateTeamParams params) async {
    final body = <String, dynamic>{};
    if (params.name != null) body['name'] = params.name;
    if (params.description != null) body['description'] = params.description;

    final data = await api.updateTeam(params.id, body);
    final dto = TeamModel.fromJson(data);
    await storage.saveData('last_team', jsonEncode(dto.toJson()));
    return dto.toDomain();
  }

  @override
  Future<void> deleteTeam(int id) async {
    await api.deleteTeam(id);
    await storage.removeData('last_team');
  }
}
