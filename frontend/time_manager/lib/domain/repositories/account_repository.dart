import 'package:time_manager/domain/entities/user.dart';

abstract class AccountRepository {
  Future<User> login(String email, String password);
  Future<User> register(String name, String email, String password);
  Future<void> logout();
  Future<User?> getCurrentUser();
}
