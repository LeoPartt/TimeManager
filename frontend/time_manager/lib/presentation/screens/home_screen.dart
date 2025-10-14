import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:time_manager/core/widgets/app_button.dart';
import 'package:time_manager/core/widgets/app_label_container.dart';
import 'package:time_manager/presentation/routes/app_router.dart';
import 'package:time_manager/presentation/widgets/header.dart';
import 'package:time_manager/presentation/widgets/navbar.dart';

@RoutePage()
class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        bottomNavigationBar: const NavBar(),
        body: Container(
          width: double.infinity,
          alignment: Alignment.topCenter,
          child: Column(
            children: [
              const Header(label: "DASHBOARD"),
              const SizedBox(height: 100),
              AppLabelContainer(label: "First name", fullSize: true),
              AppLabelContainer(label: "Last name", fullSize: false),
              AppButton(label: "Settings", fullSize: true, onPressed: () {context.pushRoute(SettingsRoute());},),
              AppButton(label: "user", fullSize: false, onPressed: () {context.pushRoute(UserRoute());},)
            ],
        ),
        )
      );
  }
}