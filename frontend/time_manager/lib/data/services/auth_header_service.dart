import 'package:time_manager/data/datasources/local/local_storage_service.dart';

/// Builds request headers, automatically adding the JWT token if present.
class AuthHeaderService {
  final LocalStorageService storage;

  AuthHeaderService(this.storage);

  Future<Map<String, String>> buildHeaders({Map<String, String>? extra}) async {
    final token = await storage.getToken();
    return {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      if (token != null && token.isNotEmpty) 'Authorization': 'Bearer $token',
      ...?extra,
    };
  }
}
