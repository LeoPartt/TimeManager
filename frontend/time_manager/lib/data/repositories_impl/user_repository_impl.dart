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
  Future<User> createUser({
    required String username,
    required String password,
    required String firstName,
    required String lastName,
    required String email,
    required String phoneNumber,
  }) async {
    final response = await api.createUser({
      'username': username,
      'password': password,
      'firstName': firstName,
      'lastName': lastName,
      'email': email,
      'phoneNumber': phoneNumber,
    });
    final dto = UserModel.fromJson(response);
    await storage.saveUser(jsonEncode(dto.toJson()));
    return dto.toDomain();
  }

    @override
  Future<User> getUser(int id) async {
    final response = await api.getUser(id);
    final dto = UserModel.fromJson(response);
    await storage.saveUser(jsonEncode(dto.toJson()));

    
    return dto.toDomain();
  }

    @override
  Future<List<User>> getUsers() async {
    final list = await api.getUsers();
    
    return list
        .map((e) => UserModel.fromJson(e as Map<String, dynamic>).toDomain())
        .toList();
  }


  @override
  Future<User> updateUserProfile(UpdateUserProfileParams params) async {

    final body = <String, dynamic>{};
    if (params.username != null) body['username'] = params.username;
    if (params.email != null) body['email'] = params.email;
    //if (params.avatarUrl != null) body['avatarUrl'] = params.avatarUrl;
    if (params.firstName != null) body['firstName'] = params.firstName;
    if (params.phoneNumber != null) body['phoneNumber'] = params.phoneNumber;
    if (params.lastName != null) body['lastName'] = params.lastName;

    final data = await api.updateProfile(params.id, body);
    final dto = UserModel.fromJson(data);
    await storage.saveUser(jsonEncode(dto.toJson()));
    return dto.toDomain();
  }

  @override
  Future<void> deleteUser(int id) async {
    await api.deleteUser(id);
    await storage.clear(); // efface user et token
  }
  
}
