import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:time_manager/domain/entities/schedule.dart';

part 'schedule_model.freezed.dart';
part 'schedule_model.g.dart';

@freezed
abstract class ClockModel with _$ClockModel {
  const factory ClockModel({
    required int id,
    DateTime? arrivalTs,
    DateTime? departureTs,
    required int userId,
  }) = _ClockModel;

  factory ClockModel.fromJson(Map<String, dynamic> json) =>
      _$ClockModelFromJson(json);
}

extension ClockModelX on ClockModel {
  Clock toDomain() => Clock(
        id: id,
        arrivalTs: arrivalTs,
        departureTs: departureTs,
        userId: userId,
      );
}
