import 'package:time_manager/domain/entities/user.dart';
import 'package:time_manager/domain/usecases/user/update_user_profile.dart';

abstract class UserRepository {
  Future<User> getUserProfile();
  Future<User> updateUserProfile(UpdateUserProfileParams params);
  Future<void> deleteUser();
}
