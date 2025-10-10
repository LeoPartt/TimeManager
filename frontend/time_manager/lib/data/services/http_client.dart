import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:time_manager/core/exceptions/network_exception.dart';
import 'package:time_manager/data/services/auth_header_service.dart';
import 'package:time_manager/initialization/environment.dart';

/// Centralized HTTP client for the app.

class ApiClient {
  final AuthHeaderService authHeaderService;

  ApiClient({required this.authHeaderService});

  String get _baseUrl => Environment.baseUrl;

  // ───────────────────────────────
  // Generic HTTP methods
  // ───────────────────────────────

  Future<Map<String, dynamic>> get(String endpoint) async {
    final headers = await authHeaderService.buildHeaders();
    final response = await http.get(Uri.parse('$_baseUrl$endpoint'), headers: headers);
    return _handleResponse(response);
  }

  Future<Map<String, dynamic>> post(String endpoint, Map<String, dynamic> body) async {
    final headers = await authHeaderService.buildHeaders();
    final response = await http.post(
      Uri.parse('$_baseUrl$endpoint'),
      headers: headers,
      body: jsonEncode(body),
    );
    return _handleResponse(response);
  }

  Future<Map<String, dynamic>> patch(String endpoint, Map<String, dynamic> body) async {
    final headers = await authHeaderService.buildHeaders();
    final response = await http.patch(
      Uri.parse('$_baseUrl$endpoint'),
      headers: headers,
      body: jsonEncode(body),
    );
    return _handleResponse(response);
  }

  Future<void> delete(String endpoint) async {
    final headers = await authHeaderService.buildHeaders();
    final response = await http.delete(Uri.parse('$_baseUrl$endpoint'), headers: headers);

    if (response.statusCode < 200 || response.statusCode >= 300) {
      throw NetworkException.fromStatusCode(response.statusCode);
    }
  }

  // ───────────────────────────────
  //  Private helper
  // ───────────────────────────────
  Map<String, dynamic> _handleResponse(http.Response response) {
    if (response.statusCode >= 200 && response.statusCode < 300) {
      if (response.body.isEmpty) return {};
      try {
        return jsonDecode(response.body) as Map<String, dynamic>;
      } catch (_) {
        return {'data': response.body};
      }
    }
    throw NetworkException.fromStatusCode(response.statusCode);
  }
}
