import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:time_manager/core/widgets/app_button.dart';
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
          AppButton(label: "test", onPressed: () {test();},),
          const NavBar(onItemTapped: onselected, selectedIndex: 1)
        ],
      ),
    );
  }
}

void test() {
  
}
void onselected (int index) {
  index = 2;
}