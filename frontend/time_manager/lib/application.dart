import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:provider/provider.dart';
import 'package:time_manager/presentation/cubits/account/auth_cubit.dart';
import 'package:time_manager/presentation/cubits/user/user_cubit.dart';
import 'package:time_manager/presentation/cubits/navigation/navbar_cubit.dart';
import 'core/theme/theme_switcher.dart';
import 'presentation/routes/app_router.dart';
import 'initialization/locator.dart';

class Application extends StatelessWidget {
  const Application({super.key});

  @override
  Widget build(BuildContext context) {
    final appRouter = locator<AppRouter>();

    return Consumer<ThemeSwitcher>(
      builder: (context, themeSwitcher, _) {
        return MultiBlocProvider(
          providers: [
            BlocProvider<AuthCubit>(
              create: (_) => locator<AuthCubit>(),
            ),
            BlocProvider<UserCubit>(
              create: (_) => locator<UserCubit>(),
            ),
            BlocProvider<NavCubit>(
              create: (_) => NavCubit(),
            ),
          ],
          child: MaterialApp.router(
            title: 'Time Manager',
            theme: themeSwitcher.currentTheme,
            routerConfig: appRouter.config(),
            debugShowCheckedModeBanner: false,
          ),
        );
      },
    );
  }
}
