import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
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
        return MaterialApp.router(
          title: 'Time Manager',
          theme: themeSwitcher.currentTheme,
          routerConfig: appRouter.config(),
          debugShowCheckedModeBanner: false,
        );
      },
    );
  }
}
