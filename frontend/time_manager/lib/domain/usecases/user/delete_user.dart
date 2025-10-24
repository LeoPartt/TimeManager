import 'package:time_manager/domain/repositories/user_repository.dart';

class DeleteUser {
  final UserRepository repository;
  DeleteUser(this.repository);

  Future<void> call(int id) => repository.deleteUser(id);
}
