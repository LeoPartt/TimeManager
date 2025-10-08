import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:time_manager/core/widgets/app_button.dart';
import 'package:time_manager/presentation/routes/app_router.dart';
import 'package:time_manager/presentation/widgets/header.dart';
import 'package:time_manager/presentation/widgets/navbar.dart';

@RoutePage()
class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          const Header(label: "DASHBOARD"),
          const SizedBox(height: 100),
          AppButton(label: "Settings", onPressed: () {context.pushRoute(SettingsRoute());},),
          const NavBar(onItemTapped: onselected, selectedIndex: 1)
        ],
      ),
    );
  }
}

void onselected (int index) {
  index = 2;
}