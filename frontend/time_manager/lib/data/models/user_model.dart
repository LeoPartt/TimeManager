import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:time_manager/domain/entities/user.dart';

part 'user_model.freezed.dart';
part 'user_model.g.dart';

@freezed
abstract class UserModel with _$UserModel {
  const factory UserModel({
    required int id,
    required String name,
    required String email,
    String? avatarUrl,
    String? role,
    String? phone,

    String? token,
  }) = _UserModel;

  factory UserModel.fromJson(Map<String, dynamic> json) =>
      _$UserModelFromJson(json);
}

extension UserModelX on UserModel {
  User toDomain() => User(
        id: id,
        name: name,
        email: email,
        avatarUrl: avatarUrl,
        role: role,
        phone: phone,
        token: token,
      );
}
