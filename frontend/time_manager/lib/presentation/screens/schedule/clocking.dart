import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:time_manager/core/constants/app_colors.dart';
import 'package:time_manager/core/constants/app_sizes.dart';
import 'package:time_manager/core/utils/extensions/context_extensions.dart';
import 'package:time_manager/core/widgets/app_button.dart';
import 'package:time_manager/l10n/app_localizations.dart';
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
    final tr = AppLocalizations.of(context)!;
    final size = MediaQuery.sizeOf(context);
    final isLandscape = size.width > size.height;

    return BlocConsumer<ClockCubit, ClockState>(
      listener: (context, state) {
        state.whenOrNull(
          clockedIn: (_) => context.showSnack("✅ ${tr.clockin} successful!"),
          clockedOut: (_) => context.showSnack("✅ ${tr.clockout} successful!"),
          error: (msg) => context.showSnack("⚠️ $msg", isError: true),
        );
      },

      builder: (context, state) {
        final isLoading = state is ClockLoading;
        final isClockedIn = state is ClockedIn;
        
        return Scaffold(
          bottomNavigationBar: const NavBar(),
          body: SafeArea(
            
        
                child: Stack(
                  children: [
                    SingleChildScrollView( 
                    
                      padding: EdgeInsets.symmetric(
                        vertical: AppSizes.responsiveHeight(context, 0.03 * size.height),
                      ),
                            
                      child: Column(
                        children: [
                          Header(label: isClockedIn ? tr.clockin : tr.clockout), 
                          SizedBox(height: AppSizes.responsiveHeight(context, 0.03 * MediaQuery.of(context).size.height)),
                          Center(
                            child: Container(
                              width: AppSizes.responsiveWidth(context, isLandscape ? 0.6 * MediaQuery.of(context).size.width :  0.9 * MediaQuery.of(context).size.width),
                              padding: EdgeInsets.symmetric(
                                horizontal:AppSizes.responsiveWidth(context, isLandscape ? 0.04 * MediaQuery.of(context).size.width : 0.06 * MediaQuery.of(context).size.width),
                                vertical: AppSizes.responsiveHeight(context, isLandscape ? 0.015 * size.height : 0.03 * MediaQuery.of(context).size.height),
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
                                    size: AppSizes.responsiveWidth(context, isLandscape ? 0.10 * MediaQuery.of(context).size.width : 0.25 * MediaQuery.of(context).size.width),
                                    color: Colors.black,
                                  ),
                                  SizedBox(height: AppSizes.responsiveHeight(context, 0.03 * MediaQuery.of(context).size.height)),
                            
                                  TextField(
                                    controller: _timeController,
                                    readOnly: true,
                                    onTap: () => _selectTime(context),
                                    textAlign: TextAlign.center,
                                    decoration: InputDecoration(
                                      prefixIcon: const Icon(Icons.schedule),
                                      hintText: isClockedIn
                                          ? tr.arrival
                                          : tr.departure,
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
                            
                                  SizedBox(height: AppSizes.responsiveHeight(context, 0.03 * MediaQuery.of(context).size.height)),
                            
                                   AppButton(
                                fullSize: true,
                                isLoading: isLoading,
                                label: isClockedIn
                                    ? tr.clockout
                                    : tr.clockin,
                                onPressed: () async {
                                        await context
                                            .read<ClockCubit>()
                                            .toggleClockState();
                                        _timeController.clear();
                                      },
                              ),
                                ],
                              ),
                            ),
                          ),
                        ],
                      )
                    ),
                  ],
                )
            
          ),
        );
      }
    );
  }
}
