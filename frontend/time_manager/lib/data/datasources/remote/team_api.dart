import 'package:time_manager/core/constants/api_endpoints.dart';
import 'package:time_manager/core/exceptions/network_exception.dart';
import 'package:time_manager/data/services/http_client.dart';

class TeamApi {
  final ApiClient client;

  TeamApi(this.client);

  /// ───────────────────────────────
  /// Get all teams
  /// ───────────────────────────────
  Future<List<dynamic>> getTeams() async {
    try {
      final res = await client.get(ApiEndpoints.teams);

    if (res is List) {
          return res;
        }

        if (res is Map<String, dynamic> &&
            res.containsKey('data') &&
            res['data'] is List) {
          return res['data'] as List<dynamic>;
        }


      throw Exception('Unexpected response format for getTeams(): $res');
    } on NetworkException {
      rethrow;
    } catch (e) {
      throw NetworkException('Unexpected error fetching teams: $e');
    }
  }

  /// ───────────────────────────────
  /// Get one team by its ID
  /// ───────────────────────────────
  Future<Map<String, dynamic>> getTeam(int id) async {
    try {
      return await client.get(ApiEndpoints.teamById(id));
    } on NetworkException {
      rethrow;
    } catch (e) {
      throw NetworkException('Unexpected error fetching team: $e');
    }
  }

  /// ───────────────────────────────
  /// Create a new team
  /// ───────────────────────────────
  Future<Map<String, dynamic>> createTeam(Map<String, dynamic> body) async {
    try {
      return await client.post(ApiEndpoints.createTeam, body);
    } on NetworkException {
      rethrow;
    } catch (e) {
      throw NetworkException('Unexpected error creating team: $e');
    }
  }

  /// ───────────────────────────────
  /// Update (PATCH) a team
  /// ───────────────────────────────
  Future<Map<String, dynamic>> updateTeam(
    int id,
    Map<String, dynamic> body,
  ) async {
    try {
      return await client.patch(ApiEndpoints.teamById(id), body);
    } on NetworkException {
      rethrow;
    } catch (e) {
      throw NetworkException('Unexpected error updating team: $e');
    }
  }

  /// ───────────────────────────────
  /// Delete a team
  /// ───────────────────────────────
  Future<void> deleteTeam(int id) async {
    try {
      await client.delete(ApiEndpoints.teamById(id));
    } on NetworkException {
      rethrow;
    } catch (e) {
      throw NetworkException('Unexpected error deleting team: $e');
    }
  }

  // ────────────────
  // MEMBERS MANAGEMENT
  // ────────────────
  Future<void> addMember(int teamId, int userId) async {
    try {
      await client.post('/api/teams/$teamId/members/$userId', {});
    } on NetworkException {
      rethrow;
    } catch (e) {
      throw NetworkException('Unexpected error adding member: $e');
    }
  }

  Future<void> removeMember(int teamId, int userId) async {
    try {
      await client.delete('/api/teams/$teamId/members/$userId');
    } on NetworkException {
      rethrow;
    } catch (e) {
      throw NetworkException('Unexpected error removing member: $e');
    }
  }

  Future<List<dynamic>> getMembers(int teamId) async {
    try {
      final res = await client.get('/api/teams/$teamId/members');

      if (res.containsKey('data') && res['data'] is List) {
        return res['data'] as List<dynamic>;
      }

      if (res is List) {
        return res;
      }

      throw NetworkException('Unexpected response format: $res');
    } on NetworkException {
      rethrow;
    } catch (e) {
      throw NetworkException('Unexpected error fetching members: $e');
    }
  }

  // ────────────────
  // MANAGER MANAGEMENT
  // ────────────────
  Future<void> assignManager(int teamId, int userId) async {
    try {
      await client.patch('/api/teams/$teamId/manager/$userId', {});
    } on NetworkException {
      rethrow;
    } catch (e) {
      throw NetworkException('Unexpected error assigning manager: $e');
    }
  }

  Future<void> removeManager(int teamId) async {
    try {
      await client.delete('/api/teams/$teamId/manager');
    } on NetworkException {
      rethrow;
    } catch (e) {
      throw NetworkException('Unexpected error removing manager: $e');
    }
  }

  Future<Map<String, dynamic>> getManager(int teamId) async {
    try {
      final res = await client.get('/api/teams/$teamId/manager');

      if (res.containsKey('data') && res['data'] is Map<String, dynamic>) {
        return res['data'] as Map<String, dynamic>;
      }

      return res;

    } on NetworkException {
      rethrow;
    } catch (e) {
      throw NetworkException('Unexpected error fetching manager: $e');
    }
  }
}
