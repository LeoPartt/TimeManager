import 'package:time_manager/data/services/http_client.dart';

class UserApi {
  final ApiClient client;

  UserApi(this.client);

  Future<Map<String, dynamic>> getProfile() =>
      client.get('/api/users/me');

  Future<Map<String, dynamic>> updateProfile(Map<String, dynamic> body) =>
      client.patch('/api/users/me', body);

  Future<void> deleteUser() =>
      client.delete('/api/users/me');
}
