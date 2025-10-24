// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'schedule_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_ClockModel _$ClockModelFromJson(Map<String, dynamic> json) => _ClockModel(
  id: (json['id'] as num).toInt(),
  arrivalTs: json['arrivalTs'] == null
      ? null
      : DateTime.parse(json['arrivalTs'] as String),
  departureTs: json['departureTs'] == null
      ? null
      : DateTime.parse(json['departureTs'] as String),
  userId: (json['userId'] as num).toInt(),
);

Map<String, dynamic> _$ClockModelToJson(_ClockModel instance) =>
    <String, dynamic>{
      'id': instance.id,
      'arrivalTs': instance.arrivalTs?.toIso8601String(),
      'departureTs': instance.departureTs?.toIso8601String(),
      'userId': instance.userId,
    };
