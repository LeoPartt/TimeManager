import 'package:freezed_annotation/freezed_annotation.dart';

part 'user.freezed.dart';

@freezed
abstract class User with _$User {
  const factory User({
    required int id,
    required String name,
    required String email,
    String? avatarUrl,
    String? phone,
    String? role,
    String? token, // si renvoyé par ton API (facultatif)
  }) = _User;
}
