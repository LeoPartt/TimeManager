import 'package:time_manager/data/datasources/local/local_storage_service.dart';
import 'package:time_manager/data/datasources/remote/account_api.dart';
import 'package:time_manager/data/models/user_model.dart';
import 'package:time_manager/domain/entities/user.dart';
import 'package:time_manager/domain/repositories/account_repository.dart';

class AccountRepositoryImpl implements AccountRepository {
  final AccountApi api;
  final LocalStorageService storage;
  
  AccountRepositoryImpl( {required this.api, required this.storage});

  @override
  Future<User> login(String email, String password) async {
    final response = await api.login(email, password);

    // Parse response into DTO
    final userDto = UserModel.fromJson(response);
    final user = userDto.toDomain();

    // Save token locally if present
    if (user.token != null && user.token!.isNotEmpty) {
      await storage.saveToken(user.token!);
    }

    return user;
  }

  // ─────────────────────────────────────────────
  // 🔹 REGISTER
  // ─────────────────────────────────────────────
  @override
  Future<User> register(String name, String email, String password) async {
    final response = await api.register(name, email, password);
    final userDto = UserModel.fromJson(response);
    return userDto.toDomain();
  }

  // ─────────────────────────────────────────────
  // 🔹 LOGOUT
  // ─────────────────────────────────────────────
  @override
  Future<void> logout() async {
    final token = await storage.getToken();
    if (token != null && token.isNotEmpty) {
      try {
       // await api.logout(token);
      } catch (_) {
        // Even if API call fails, we still clear local session
      }
    }
    await storage.clear();
  }
  @override
  Future<User?> getCurrentUser() async => null;
}
