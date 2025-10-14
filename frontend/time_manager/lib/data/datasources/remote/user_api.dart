import 'package:time_manager/core/constants/api_endpoints.dart';

import 'package:time_manager/core/exceptions/network_exception.dart';
import 'package:time_manager/data/services/http_client.dart';

/// Provides user-related API operations.
class UserApi {
  final ApiClient client;

  UserApi(this.client);

  Future<Map<String, dynamic>> getProfile() async {
    try {
      return await client.get(ApiEndpoints.userProfile);
    } on NetworkException {
      rethrow;
    } catch (e) {
      throw NetworkException('Unexpected error fetching user profile: $e');
    }
  }
   Future<Map<String, dynamic>> createUser(Map<String, dynamic> body) async {
    //
     try {
      return client.post(ApiEndpoints.users, body);
    } on NetworkException {
      rethrow;
    } catch (e) {
      throw NetworkException('Unexpected error updating profile: $e');
    }
  
  }

  Future<Map<String, dynamic>> updateProfile(Map<String, dynamic> body) async {
    try {
      return await client.patch(ApiEndpoints.updateProfile, body);
    } on NetworkException {
      rethrow;
    } catch (e) {
      throw NetworkException('Unexpected error updating profile: $e');
    }
  }

  Future<void> deleteUser() async {
    try {
      await client.delete(ApiEndpoints.userProfile);
    } on NetworkException {
      rethrow;
    } catch (e) {
      throw NetworkException('Unexpected error deleting user: $e');
    }
  }
}
