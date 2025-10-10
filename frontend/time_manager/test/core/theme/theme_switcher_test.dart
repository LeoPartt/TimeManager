import 'package:flutter_test/flutter_test.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:time_manager/core/theme/theme_switcher.dart';

void main() {
  TestWidgetsFlutterBinding.ensureInitialized();

  group('ThemeSwitcher', () {
    test('loads default light mode when no preference is set', () async {
      SharedPreferences.setMockInitialValues({});
      final switcher = ThemeSwitcher();

      await switcher.loadTheme();

      expect(switcher.isDarkMode, false);
    });

    test('toggles dark mode and saves preference', () async {
      SharedPreferences.setMockInitialValues({});
      final switcher = ThemeSwitcher();

      await switcher.loadTheme();
      final initial = switcher.isDarkMode;

      await switcher.toggleTheme();

      expect(switcher.isDarkMode, !initial);

      final prefs = await SharedPreferences.getInstance();
      expect(prefs.getBool('isDarkMode'), switcher.isDarkMode);
    });

    test('setTheme forces a specific mode and notifies listeners', () async {
      SharedPreferences.setMockInitialValues({});
      final switcher = ThemeSwitcher();

      bool notified = false;
      switcher.addListener(() => notified = true);

      await switcher.setTheme(true);

      expect(switcher.isDarkMode, true);
      expect(notified, true);
    });
  });
}
