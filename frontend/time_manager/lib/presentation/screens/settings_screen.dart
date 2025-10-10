import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../../core/theme/theme_switcher.dart';

@RoutePage()
class SettingsScreen extends StatelessWidget {
  const SettingsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final themeSwitcher = context.watch<ThemeSwitcher>();

    return Scaffold(
      appBar: AppBar(title: const Text('Paramètres')),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          SwitchListTile(
            title: const Text('Mode sombre'),
            subtitle: Text(
              themeSwitcher.isDarkMode
                  ? 'Thème actuel : sombre'
                  : 'Thème actuel : clair',
            ),
            value: themeSwitcher.isDarkMode,
            onChanged: (value) => themeSwitcher.toggleTheme(),
            activeThumbColor: Theme.of(context).colorScheme.primary,
          ),
        ],
      ),
    );
  }
}
