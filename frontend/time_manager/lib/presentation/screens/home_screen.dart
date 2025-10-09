import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:time_manager/core/widgets/app_button.dart';
import 'package:time_manager/core/widgets/app_label_container.dart';
import 'package:time_manager/presentation/routes/app_router.dart';
import 'package:time_manager/presentation/widgets/header.dart';
import 'package:time_manager/presentation/widgets/navbar.dart';
import 'package:time_manager/presentation/cubits/navigation/navbar_cubit.dart';

@RoutePage()
class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (_) => NavCubit(),
      child: Scaffold(
        bottomNavigationBar: const NavBar(),
        body: Container(
          width: double.infinity,
          alignment: Alignment.topCenter,
          child: Column(
            children: [
              const Header(label: "DASHBOARD"),
              const SizedBox(height: 100),
              AppLabelContainer(label: "First name"),
              AppLabelContainer(label: "Last name"),
              AppButton(label: "Settings", onPressed: () {context.pushRoute(SettingsRoute());},),
            ],
        ),
        )
      ),
    );
  }
}