import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:time_manager/domain/entities/user.dart';

part 'user_state.freezed.dart';

@freezed
class UserState with _$UserState {
  const factory UserState.initial() = _Initial;
  const factory UserState.loading() = UserLoading;
  const factory UserState.loaded(User user) = UserLoaded;
    const factory UserState.listLoaded(List<User> users) = UserListLoaded;
    const factory UserState.deleted() = UserDeleted;

  const factory UserState.error(String message) = _Error;
}
