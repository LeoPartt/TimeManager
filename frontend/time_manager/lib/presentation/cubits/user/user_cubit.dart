import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:time_manager/domain/usecases/user/create_user.dart';

import 'package:time_manager/domain/usecases/user/delete_user.dart';
import 'package:time_manager/domain/usecases/user/get_user.dart';
import 'package:time_manager/domain/usecases/user/get_user_profile.dart';
import 'package:time_manager/domain/usecases/user/get_users.dart';
import 'package:time_manager/domain/usecases/user/update_user_profile.dart';
import 'user_state.dart';

class UserCubit extends Cubit<UserState> {
  final GetUserProfile getUserProfile;
  final UpdateUserProfile updateUserProfile;
  final DeleteUser deleteUser;
  final CreateUser createUserUsecase;
  final GetUser getUserUseCase;
  final GetUsers getUsersUseCase;

  UserCubit({
    required this.getUserProfile,
    required this.updateUserProfile,
    required this.deleteUser,
    required this.getUserUseCase,
    required this.getUsersUseCase,

    required this.createUserUsecase,
  }) : super(const UserState.initial());

  Future<void> loadProfile() async {
    emit(const UserState.loading());
    try {
      final user = await getUserProfile();
      emit(UserState.loaded(user));
    } catch (e) {
      emit(UserState.error(e.toString()));
    }
  }

  Future<void> getUser(int id) async {
    emit(const UserState.loading());
    try {
      final user = await getUserUseCase(id);
      emit(UserState.loaded(user));
    } catch (e) {
      emit(UserState.error(e.toString()));
    }
  }

  Future<void> getUsers() async {
   emit(const UserState.loading());
    try {
      final users = await getUsersUseCase();
      emit(UserState.listLoaded(users));
    } catch (e) {
      emit(UserState.error(e.toString()));
    }
  }

  Future<void> updateProfile(UpdateUserProfileParams params) async {
    emit(const UserState.loading());
    try {
      final user = await updateUserProfile(params);
      emit(UserState.loaded(user));
    } catch (e) {
      emit(UserState.error(e.toString()));
    }
  }

  Future<void> createUser({
    required String username,
    required String password,
    required String firstName,
    required String lastName,
    required String email,
    required String phoneNumber,
  }) async {
    emit(const UserState.loading());
    try {
      final user = await createUserUsecase(
        username: username,
        password: password,
        firstName: firstName,
        lastName: lastName,
        email: email,
        phoneNumber: phoneNumber,
      );
      emit(UserState.loaded(user));
    } catch (e) {
      emit(UserState.error(e.toString()));
    }
  }

  Future<void> removeAccount(int id) async {
    emit(const UserState.loading());
    try {
      await deleteUser(id);
      emit(const UserState.deleted());
    } catch (e) {
      emit(UserState.error(e.toString()));
    }
  }
}
