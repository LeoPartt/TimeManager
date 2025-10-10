import 'package:flutter_bloc/flutter_bloc.dart';
import 'navbar_state.dart';

class NavCubit extends Cubit<NavState> {
  NavCubit() : super(const NavState(index: 0));

  void changeTab(int newIndex) {
    if (newIndex != state.index) {
      emit(state.copyWith(index: newIndex));
    }
  }
}
