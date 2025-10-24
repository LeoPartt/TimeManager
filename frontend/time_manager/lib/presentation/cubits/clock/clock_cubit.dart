

import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:time_manager/domain/usecases/schedule/get_clock_in.dart';
import 'package:time_manager/domain/usecases/schedule/get_clock_out.dart';
import 'package:time_manager/domain/usecases/schedule/get_clock_status.dart';

import 'package:time_manager/presentation/cubits/clock/clock_state.dart';

class ClockCubit extends Cubit<ClockState> {
  final ClockIn clockInUseCase;
  final ClockOut clockOutUseCase;
  final GetClockStatus getStatusUseCase;

  ClockCubit({
    required this.clockInUseCase,
    required this.clockOutUseCase,
    required this.getStatusUseCase,
  }) : super(const ClockState.initial());

  Future<void> clockIn() async {
    emit(const ClockState.loading());
    try {
      final clock = await clockInUseCase();
      emit(ClockState.clockedIn(clock));
    } catch (e) {
      emit(ClockState.error(e.toString()));
    }
  }

  Future<void> clockOut() async {
    emit(const ClockState.loading());
    try {
      final clock = await clockOutUseCase();
      emit(ClockState.clockedOut(clock));
    } catch (e) {
      emit(ClockState.error(e.toString()));
    }
  }

  Future<void> getStatus() async {
    emit(const ClockState.loading());
    try {
      final status = await getStatusUseCase();
      if (status?.departureTs == null) {
        emit(ClockState.clockedIn(status!));
      } else {
        emit(ClockState.clockedOut(status!));
      }
    } catch (e) {
      emit(ClockState.error(e.toString()));
    }
  }

   Future<void> toggleClockState() async {
    final current = state;
    if (current is ClockedIn) {
      await clockOut();
    } else {
      await clockIn();
    }
  }
}
