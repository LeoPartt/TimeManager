import 'package:freezed_annotation/freezed_annotation.dart';

part 'clock_state.freezed.dart';

@freezed
class ClockState with _$ClockState {
  const factory ClockState.clockIn() = ClockInState;
  const factory ClockState.clockOut() = ClockOutState;
}
