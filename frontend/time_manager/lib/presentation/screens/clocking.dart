import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:time_manager/core/constants/app_colors.dart';
import 'package:time_manager/core/widgets/app_button.dart';
import 'package:time_manager/presentation/widgets/header.dart';
import 'package:time_manager/presentation/widgets/navbar.dart';
import 'package:time_manager/presentation/cubits/clock/clock_cubit.dart';
import 'package:time_manager/presentation/cubits/clock/clock_state.dart';

@RoutePage()
class ClockingScreen extends StatefulWidget {
  const ClockingScreen({super.key});

  @override
  State<ClockingScreen> createState() => _ClockingScreenState();
}

class _ClockingScreenState extends State<ClockingScreen> {
  final _timeController = TextEditingController();

  @override
  void dispose() {
    _timeController.dispose();
    super.dispose();
  }

  Future<void> _selectTime(BuildContext context) async {
    final TimeOfDay? picked = await showTimePicker(
      context: context,
      initialTime: TimeOfDay.now(),
    );

    if (picked != null) {
      setState(() {
        _timeController.text = picked.format(context);
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.sizeOf(context);

    return BlocProvider(
      create: (_) => ClockCubit(),
      child: Scaffold(
        bottomNavigationBar: const NavBar(),
        body: SafeArea(
          child: BlocBuilder<ClockCubit, ClockState>(
            builder: (context, state) {
              final isClockIn = state is ClockInState;

              return Column(
                children: [
                  Header(label: isClockIn ? "CLOCK IN" : "CLOCK OUT"),
                  SizedBox(height: size.height * 0.03),
                  Center(
                    child: Container(
                      width: size.width * 0.9,
                      padding: EdgeInsets.symmetric(
                        horizontal: size.width * 0.06,
                        vertical: size.height * 0.03,
                      ),
                      decoration: BoxDecoration(
                        color: AppColors.accent,
                        borderRadius: BorderRadius.circular(28),
                      ),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          Icon(
                            Icons.access_time,
                            size: size.width * 0.25,
                            color: Colors.black,
                          ),
                          SizedBox(height: size.height * 0.03),

                          TextField(
                            controller: _timeController,
                            readOnly: true,
                            onTap: () => _selectTime(context),
                            textAlign: TextAlign.center,
                            decoration: InputDecoration(
                              prefixIcon: const Icon(Icons.schedule),
                              hintText: isClockIn
                                  ? "Arrival time"
                                  : "Departure time",
                              border: OutlineInputBorder(
                                borderRadius: BorderRadius.circular(16),
                                borderSide: BorderSide(
                                  color: AppColors.secondary,
                                  width: 2,
                                ),
                              ),
                              filled: true,
                              fillColor: AppColors.primary,
                            ),
                          ),

                          SizedBox(height: size.height * 0.03),

                          AppButton(
                            fullSize: true,
                            label: "Validate",
                            onPressed: () {

                              debugPrint("Selected time: ${_timeController.text}");
                              context.read<ClockCubit>().toggleClockState();
                              _timeController.clear();
                            },
                          ),
                        ],
                      ),
                    ),
                  ),
                ],
              );
            },
          ),
        ),
      ),
    );
  }
}
