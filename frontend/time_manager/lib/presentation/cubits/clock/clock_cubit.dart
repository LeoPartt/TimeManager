import 'package:flutter_bloc/flutter_bloc.dart';
import 'clock_state.dart';

class ClockCubit extends Cubit<ClockState> {
  ClockCubit() : super(const ClockInState());

  void toggleClockState() {
    if (state is ClockInState) {
      emit(const ClockOutState());
    } else {
      emit(const ClockInState());
    }
  }
}
