import 'dart:convert';
import 'package:time_manager/data/datasources/local/local_storage_service.dart';
import 'package:time_manager/data/datasources/remote/user_api.dart';
import 'package:time_manager/data/models/user_model.dart';
import 'package:time_manager/domain/entities/user.dart';
import 'package:time_manager/domain/repositories/user_repository.dart';
import 'package:time_manager/domain/usecases/user/update_user_profile.dart';

class UserRepositoryImpl implements UserRepository {
  final UserApi api;
  final LocalStorageService storage;

  UserRepositoryImpl({
    required this.api,
    required this.storage,
  });


  @override
  Future<User> getUserProfile() async {
    final data = await api.getProfile();
    final dto = UserModel.fromJson(data);
    await storage.saveUser(jsonEncode(dto.toJson()));
    return dto.toDomain();
  }

  @override
  Future<User> updateUserProfile(UpdateUserProfileParams params) async {
    final body = <String, dynamic>{};
    if (params.username != null) body['username'] = params.username;
    if (params.email != null) body['email'] = params.email;
    if (params.avatarUrl != null) body['avatarUrl'] = params.avatarUrl;
    if (params.phone != null) body['phone'] = params.phone;

    final data = await api.updateProfile(body);
    final dto = UserModel.fromJson(data);
    await storage.saveUser(jsonEncode(dto.toJson()));
    return dto.toDomain();
  }

  @override
  Future<void> deleteUser() async {
    await api.deleteUser();
    await storage.clear(); // efface user et token
  }
}
