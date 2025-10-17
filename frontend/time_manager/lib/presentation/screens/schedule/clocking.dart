import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:intl/intl.dart';
import 'package:time_manager/core/constants/app_colors.dart';
import 'package:time_manager/core/constants/app_sizes.dart';
import 'package:time_manager/core/utils/extensions/context_extensions.dart';
import 'package:time_manager/core/widgets/app_button.dart';
import 'package:time_manager/l10n/app_localizations.dart';
import 'package:time_manager/presentation/cubits/clock/clock_cubit.dart';
import 'package:time_manager/presentation/cubits/clock/clock_state.dart';
import 'package:time_manager/presentation/widgets/header.dart';
import 'package:time_manager/presentation/widgets/navbar.dart';

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
    final isTablet = size.width >= 600;

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
          resizeToAvoidBottomInset: true,
          body: SafeArea(
            child: SingleChildScrollView(
              padding: EdgeInsets.symmetric(
                vertical: AppSizes.responsiveHeight(context, AppSizes.p24),
                horizontal: AppSizes.responsiveWidth(context, AppSizes.p24),
              ),
              child: Center(
                child: ConstrainedBox(
                  constraints: BoxConstraints(
                    maxWidth: isTablet ? 600 : double.infinity,
                  ),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      // ────────────── HEADER ──────────────
                      Header(label: isClockedIn ? tr.clockout : tr.clockin),
                      SizedBox(height: AppSizes.responsiveHeight(context, AppSizes.p24)),

                      // ────────────── CARD CONTAINER ──────────────
                      Container(
                        width: double.infinity,
                        padding: EdgeInsets.all(AppSizes.responsiveWidth(context, AppSizes.p20)),
                        decoration: BoxDecoration(
                          color: AppColors.accent,
                          borderRadius: BorderRadius.circular(AppSizes.r24),
                          boxShadow: [
                            BoxShadow(
                              color: AppColors.shadow.withOpacity(0.2),
                              blurRadius: 10,
                              offset: const Offset(0, 4),
                            ),
                          ],
                        ),
                        child: Column(
                          children: [
                            Icon(
                              Icons.access_time,
                              size: AppSizes.responsiveWidth(
                                context,
                                isTablet ? 100 : 80,
                              ),
                              color: Colors.black,
                            ),
                            SizedBox(height: AppSizes.responsiveHeight(context, AppSizes.p24)),

                            // ────────────── TIME INPUT ──────────────
                            TextField(
                              controller: _timeController,
                              readOnly: true,
                              onTap: () => _selectTime(context),
                              textAlign: TextAlign.center,
                              style: TextStyle(
                                fontSize: AppSizes.responsiveText(context, AppSizes.textLg),
                              ),
                              decoration: InputDecoration(
                                prefixIcon: const Icon(Icons.schedule),
                                hintText: isClockedIn ? tr.departure : tr.arrival,
                                hintStyle: TextStyle(
                                  fontSize: AppSizes.responsiveText(context, AppSizes.textMd),
                                ),
                                border: OutlineInputBorder(
                                  borderRadius: BorderRadius.circular(AppSizes.r16),
                                  borderSide: BorderSide(
                                    color: AppColors.secondary,
                                    width: 2,
                                  ),
                                ),
                                filled: true,
                                fillColor: AppColors.primary,
                              ),
                            ),

                            SizedBox(height: AppSizes.responsiveHeight(context, AppSizes.p24)),

                            // ────────────── ACTION BUTTON ──────────────
                            AppButton(
                              fullSize: true,
                              isLoading: isLoading,
                              label: isClockedIn ? tr.clockout : tr.clockin,
                              onPressed: () async {
                                final picked = _timeController.text;
                                if (picked.isNotEmpty) {
                                  final parsedTime = TimeOfDay.fromDateTime(
                                    DateFormat.jm().parse(picked),
                                  );

                                  await context
                                      .read<ClockCubit>()
                                      .toggleClockState(parsedTime);

                                  _timeController.clear();
                                } else {
                                  context.showSnack("⚠️ ${tr.validate}", isError: true);
                                }
                              },
                            ),
                          ],
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ),
          ),
        );
      },
    );
  }
}
