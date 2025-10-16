import 'package:time_manager/data/datasources/local/local_storage_service.dart';
import 'package:time_manager/data/datasources/remote/account_api.dart';
import 'package:time_manager/data/models/account_model.dart';
import 'package:time_manager/data/models/user_model.dart';
import 'package:time_manager/domain/entities/user.dart';
import 'package:time_manager/domain/repositories/account_repository.dart';

class AccountRepositoryImpl implements AccountRepository {
  final AccountApi api;
  final LocalStorageService storage;
  
  AccountRepositoryImpl( {required this.api, required this.storage});

  @override
  Future<User> login(String username, String password) async {
    final response = await api.login(username, password);
    print("🔐 LOGIN RESPONSE: $response");

    // Parse the response as an AccountModel (token + expirationTime)
    final account = AccountModel.fromJson(response);

    if (account.token.isNotEmpty) {
      await storage.saveToken(account.token);
    }

    // Return a minimal User domain entity (no full profile info yet)
    // The full user info can later be fetched via `GetUserProfile`
    return User(
      id: 0, // placeholder (since backend doesn't return it)
      username: username,
      email: '', 
      firstName: '', 
      lastName: '', // unknown for now
     // token: account.token,
    );
  }

  // ─────────────────────────────────────────────
  // 🔹 REGISTER
  // ─────────────────────────────────────────────
  @override
  Future<User> register(String username, String email, String password) async {
    final response = await api.register(username, email, password);
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

 
}
