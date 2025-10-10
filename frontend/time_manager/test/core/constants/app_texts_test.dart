import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:time_manager/core/theme/app_theme.dart';
import 'package:time_manager/core/constants/app_colors.dart';

void main() {
  group('AppTheme', () {
    test('lightTheme and darkTheme return valid ThemeData', () {
      final light = AppTheme.lightTheme;
      final dark = AppTheme.darkTheme;

      expect(light, isA<ThemeData>());
      expect(dark, isA<ThemeData>());
    });

    test('dark theme uses dark brightness', () {
      expect(AppTheme.darkTheme.brightness, Brightness.dark);
    });

    test('primary color matches AppColors.primary', () {
      final color = AppTheme.lightTheme.colorScheme.primary;
      expect(color, AppColors.primary);
    });
  });
}
