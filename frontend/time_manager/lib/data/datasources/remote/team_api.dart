import 'package:time_manager/core/constants/api_endpoints.dart';
import 'package:time_manager/core/exceptions/network_exception.dart';
import 'package:time_manager/data/services/http_client.dart';

/// Provides team-related API operations.
class TeamApi {
  final ApiClient client;

  TeamApi(this.client);

  /// ───────────────────────────────
  /// Get all teams
  /// ───────────────────────────────
  Future<List<dynamic>> getTeams() async {
    try {
      final res = await client.get(ApiEndpoints.teams);

      // Format flexible : { data: [...] } ou liste brute
      if (res.containsKey('data') && res['data'] is List) {
        return res['data'] as List<dynamic>;
      }
      if (res is List) {
        return res as List<dynamic>;
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
      // Tu as un endpoint spécifique /teams/create
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
}
