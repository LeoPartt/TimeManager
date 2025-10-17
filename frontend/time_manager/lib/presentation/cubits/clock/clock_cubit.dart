

import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:time_manager/domain/entities/schedule.dart';
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

  Future<void> clockIn(TimeOfDay selectedTime) async {
    emit(const ClockState.loading());
    try {
      final now = DateTime.now();
    final dt = DateTime(now.year, now.month, now.day, selectedTime.hour, selectedTime.minute);
    
      //final clock = await clockInUseCase(dt);
       await clockInUseCase(dt);
      emit(ClockState.clockedIn(Clock(arrivalTs: dt, departureTs: null)));
    } catch (e) {
      emit(ClockState.error('Clock-in failed: $e'));
    }
  }

  Future<void> clockOut(TimeOfDay selectedTime) async {
    emit(const ClockState.loading());
    try {
       final now = DateTime.now();
    final dt = DateTime(now.year, now.month, now.day, selectedTime.hour, selectedTime.minute);
    
      //final clock = await clockOutUseCase(dt);
       await clockOutUseCase(dt);
      emit(ClockState.clockedOut(Clock(arrivalTs: null, departureTs: dt)));
    } catch (e) {
      emit(ClockState.error('Clock-out failed: $e'));
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

   Future<void> toggleClockState(TimeOfDay timestamp) async {
    final current = state;
    
    if (current is ClockedIn) {
      await clockOut(timestamp);
    } else {
      await clockIn(timestamp);
    }
  }
}
