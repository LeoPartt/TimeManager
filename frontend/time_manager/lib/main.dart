import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
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
    ChangeNotifierProvider.value(
      value: themeSwitcher,
      child:  Application(),
    ),
  );

  await appInitializer.postAppRun();
}
