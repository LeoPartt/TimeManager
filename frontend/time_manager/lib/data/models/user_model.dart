import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:time_manager/domain/entities/user.dart';

part 'user_model.freezed.dart';
part 'user_model.g.dart';

@freezed
abstract class UserModel with _$UserModel {
  const factory UserModel({
    required int id,
    required String username,
    required String email,
     required String firstName,
    required String lastName,
    //String? avatarUrl,
    //String? role,
    String? phoneNumber,

    //String? token,
  }) = _UserModel;

  factory UserModel.fromJson(Map<String, dynamic> json) =>
      _$UserModelFromJson(json);
}

extension UserModelX on UserModel {
  User toDomain() => User(
        id: id,
        username: username,
        email: email,
        firstName:firstName,
        lastName:lastName,
        //avatarUrl: avatarUrl,
        //role: role,
        phoneNumber: phoneNumber,
       // token: token,
      );
}
