import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:time_manager/core/exceptions/network_exception.dart';

class AccountApi {
  final String baseUrl;
  AccountApi(this.baseUrl);

  Future<Map<String, dynamic>> login(String email, String password) async {
    final res = await http.post(
      Uri.parse('$baseUrl/api/auth/login'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode({'email': email, 'password': password}),
    );
    if (res.statusCode == 200) return jsonDecode(res.body);
    throw NetworkException.fromStatusCode(res.statusCode);
  }

  Future<Map<String, dynamic>> register(String name, String email, String password) async {
    final res = await http.post(
      Uri.parse('$baseUrl/api/auth/register'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode({'name': name, 'email': email, 'password': password}),
    );
    if (res.statusCode == 201) return jsonDecode(res.body);
    throw NetworkException.fromStatusCode(res.statusCode);
  }
}
