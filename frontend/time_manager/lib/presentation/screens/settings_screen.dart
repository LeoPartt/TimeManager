import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:time_manager/core/theme/local_provider.dart';
import 'package:time_manager/l10n/app_localizations.dart';
import 'package:time_manager/presentation/widgets/header.dart';
import 'package:time_manager/presentation/widgets/navbar.dart';
import '../../core/theme/theme_switcher.dart';

@RoutePage()
class SettingsScreen extends StatelessWidget {
  const SettingsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final themeSwitcher = context.watch<ThemeSwitcher>();
    final localeProvider = context.watch<LocaleProvider>();
    final tr = AppLocalizations.of(context)!;
    final size = MediaQuery.sizeOf(context);

    return Scaffold(
      bottomNavigationBar: const NavBar(),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          Header(label: tr.settings),
          SwitchListTile(
            title: Text(tr.darkMode),
            subtitle: Text(
              themeSwitcher.isDarkMode
                  ? tr.currentThemeDark
                  : tr.currentThemeLight,
            ),
            value: themeSwitcher.isDarkMode,
            onChanged: (value) => themeSwitcher.toggleTheme(),
            activeThumbColor: Theme.of(context).colorScheme.primary,
          ),
          SizedBox(height: size.height * 0.04),
          ListTile(
            title: Text(tr.language),
            trailing: DropdownButton<Locale>(
              value: localeProvider.locale,
              items: const [
                DropdownMenuItem(value: Locale('en'), child: Text('English')),
                DropdownMenuItem(value: Locale('fr'), child: Text('Français')),
              ],
              onChanged: (locale) {
                if (locale != null) localeProvider.setLocale(locale);
              },
            ),
          ),
        ],
      ),
    );
  }
}
