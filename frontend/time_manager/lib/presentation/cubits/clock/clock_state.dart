

import 'package:freezed_annotation/freezed_annotation.dart';
import 'package:time_manager/domain/entities/schedule.dart';


part 'clock_state.freezed.dart';

@freezed
class ClockState with _$ClockState {
  const factory ClockState.initial() = _Initial;
  const factory ClockState.loading() = ClockLoading;
  const factory ClockState.clockedIn(Clock clock) = ClockedIn;
  const factory ClockState.clockedOut(Clock clock) = ClockedOut;
  const factory ClockState.error(String message) = ClockedError;
}
