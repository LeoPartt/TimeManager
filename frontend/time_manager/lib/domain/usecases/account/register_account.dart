import 'package:time_manager/domain/entities/user.dart';
import 'package:time_manager/domain/repositories/account_repository.dart';

class RegisterUser {
  final AccountRepository repository;
  RegisterUser(this.repository);

  Future<User> call(String name, String email, String password) {
    return repository.register(name, email, password);
  }
}
