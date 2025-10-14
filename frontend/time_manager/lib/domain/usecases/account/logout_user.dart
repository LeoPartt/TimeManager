import 'package:time_manager/domain/repositories/account_repository.dart';

class LogoutUser {
  final AccountRepository repository;
  LogoutUser(this.repository);

  Future<void> call() async {
    await repository.logout();
  }
}
