import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:time_manager/presentation/widgets/header.dart';
import 'package:time_manager/presentation/widgets/navbar.dart';
import '../../core/theme/theme_switcher.dart';

@RoutePage()
class SettingsScreen extends StatelessWidget {
  const SettingsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final themeSwitcher = context.watch<ThemeSwitcher>();

    return Scaffold(
      bottomNavigationBar: const NavBar(),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          const Header(label: "SETTINGS"),
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
