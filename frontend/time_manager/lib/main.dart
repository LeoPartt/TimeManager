import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:time_manager/core/theme/local_provider.dart';
import 'core/theme/theme_switcher.dart';
import 'application.dart';
import 'initialization/app_initializer.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();

  final appInitializer = AppInitializer();
  await appInitializer.preAppRun();

  final themeSwitcher = ThemeSwitcher();
  await themeSwitcher.loadTheme();

  runApp(
    MultiProvider(providers: [
      ChangeNotifierProvider.value(
        value: themeSwitcher,
        child:  Application(),
      ),
      ChangeNotifierProvider(
        create: (_) => LocaleProvider()
      ),
      
    ],
    child: const Application(),
    )
  );

  await appInitializer.postAppRun();
}