// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'account_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_AccountModel _$AccountModelFromJson(Map<String, dynamic> json) =>
    _AccountModel(
      token: json['token'] as String,
      expiresIn: json['expiresIn'] as num?,
    );

Map<String, dynamic> _$AccountModelToJson(_AccountModel instance) =>
    <String, dynamic>{'token': instance.token, 'expiresIn': instance.expiresIn};
