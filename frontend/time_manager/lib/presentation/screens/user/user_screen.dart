import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:time_manager/core/constants/app_colors.dart';
import 'package:time_manager/core/widgets/app_button.dart';
import 'package:time_manager/core/widgets/app_label_container.dart';
import 'package:time_manager/l10n/app_localizations.dart';
import 'package:time_manager/presentation/routes/app_router.dart';
import 'package:time_manager/presentation/widgets/header.dart';
import 'package:time_manager/presentation/widgets/navbar.dart';

@RoutePage()
class UserScreen extends StatelessWidget {
  const UserScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.sizeOf(context);
    final tr = AppLocalizations.of(context)!;

  return Scaffold(
        bottomNavigationBar: const NavBar(),
        body: SafeArea(
          child: SingleChildScrollView( 
            child: Column(
              children: [
                Header(label: tr.me),
                SizedBox(height: size.height * 0.04),
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
                      boxShadow: const [
                        BoxShadow(
                          color: Color.fromRGBO(0, 0, 0, 0.25),
                          offset: Offset(0, 4),
                          blurRadius: 12,
                        ),
                      ],
                    ),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.stretch,
                      mainAxisSize: MainAxisSize.min,
                      children: [
                        // avatar/icon centré
                        Align(
                          alignment: Alignment.center,
                          child: Builder(
                            builder: (context) {
                              final size = MediaQuery.sizeOf(context);
                              final double r = size.width * 0.10; 

                              return Container(
                                width: r * 2,
                                height: r * 2,
                                decoration: BoxDecoration(
                                  color: AppColors.accent,
                                  shape: BoxShape.circle,
                                  border: Border.all(
                                    color: AppColors.shadow.withValues(alpha: 0.35),   
                                    width: 3,
                                  ),
                                  boxShadow: [
                                    BoxShadow(
                                      color: AppColors.shadow.withValues(alpha: 0.35),
                                      offset: Offset(0, 2),
                                      blurRadius: 6,
                                    ),
                                  ],
                                ),
                                child: Icon(
                                  Icons.person_outline_rounded,
                                  size: r * 1.2,
                                  color: Colors.black,
                                ),
                              );
                            },
                          ),
                        ),
                        SizedBox(height: size.height * 0.03),

                        // champs
                        AppLabelContainer(label: "First name", fullSize: true),
                        SizedBox(height: size.height * 0.015),
                        AppLabelContainer(label: "Last name", fullSize: true),
                        SizedBox(height: size.height * 0.015),
                        AppLabelContainer(label: "Email", fullSize: true),
                        SizedBox(height: size.height * 0.015),
                        AppLabelContainer(label: "Phone number", fullSize: true),
                        SizedBox(height: size.height * 0.03),

                        Row(
                          children: [
                            Expanded(
                              child: AppButton(
                                label: tr.modify,
                                fullSize: true,
                                onPressed: () => context.pushRoute(const SettingsRoute()),
                              ),
                            ),
                            SizedBox(width: size.width * 0.04),
                            Expanded(
                              child: AppButton(
                                label: tr.delete,
                                fullSize: true,
                                onPressed: () => {context.pushRoute(const ProfileRoute())},
                              ),
                            ),
                          ],
                        ),
                      ],
                    ),
                  ),
                ),

                SizedBox(height: size.height * 0.02),
              ],
            ),
          ),
        ),
      );
  }
}
