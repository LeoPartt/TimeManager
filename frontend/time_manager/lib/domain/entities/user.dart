import 'package:freezed_annotation/freezed_annotation.dart';

part 'user.freezed.dart';

@freezed
abstract class User with _$User {
  const factory User({
    required int id,
    required String username,
    required String email,
    //String? avatarUrl,
    String? phoneNumber,
    required final String firstName,
    required final String lastName,
    String? role,
  }) = _User;
}
