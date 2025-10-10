import 'package:flutter/material.dart';
//import 'app_colors.dart';

import '../constants/app_colors.dart';

/// Defines the light and dark themes for the Time Manager app.
///
/// Using a single file keeps both variants synchronized and easy to maintain.
class AppTheme {
  // ───────────────────────────────
  //  Light Theme
  // ───────────────────────────────
  static ThemeData get lightTheme => ThemeData(
        brightness: Brightness.light,
        colorScheme: const ColorScheme.light(
          primary: AppColors.primary,
          secondary: AppColors.accent,
          surface: Colors.white,
          error: AppColors.error,
        ),
        scaffoldBackgroundColor: AppColors.backgroundLight,
        appBarTheme: const AppBarTheme(
          backgroundColor: AppColors.primary,
          foregroundColor: Colors.white,
          elevation: 0,
        ),
        textTheme: _textTheme(isDark: false),
        inputDecorationTheme: _inputTheme(isDark: false),
        cardColor: AppColors.cardLight,
        iconTheme: const IconThemeData(color: AppColors.primary),
        elevatedButtonTheme: ElevatedButtonThemeData(
          style: ElevatedButton.styleFrom(
            backgroundColor: AppColors.primary,
            foregroundColor: Colors.white,
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(8),
            ),
            padding: const EdgeInsets.symmetric(vertical: 12, horizontal: 24),
          ),
        ),
      );

  // ───────────────────────────────
  //  Dark Theme
  // ───────────────────────────────
  static ThemeData get darkTheme => ThemeData(
        brightness: Brightness.dark,
        colorScheme: const ColorScheme.dark(
          primary: AppColors.primary,
          secondary: AppColors.secondary,
          surface: AppColors.cardDark,
          error: AppColors.error,
        ),
        scaffoldBackgroundColor: AppColors.backgroundDark,
        appBarTheme: const AppBarTheme(
          backgroundColor: AppColors.secondary,
          foregroundColor: Colors.white,
          elevation: 0,
        ),
        textTheme: _textTheme(isDark: true),
        inputDecorationTheme: _inputTheme(isDark: true),
        cardColor: AppColors.cardDark,
        iconTheme: const IconThemeData(color: AppColors.accent),
        elevatedButtonTheme: ElevatedButtonThemeData(
          style: ElevatedButton.styleFrom(
            backgroundColor: AppColors.primary,
            foregroundColor: Colors.white,
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(8),
            ),
            padding: const EdgeInsets.symmetric(vertical: 12, horizontal: 24),
          ),
        ),
      );

  // ───────────────────────────────
  //  Text Theme Helper
  // ───────────────────────────────
  static TextTheme _textTheme({required bool isDark}) => TextTheme(
        displayLarge: TextStyle(
          color: isDark ? AppColors.textLight : AppColors.textPrimary,
          fontWeight: FontWeight.bold,
          fontSize: 32,
        ),
        headlineMedium: TextStyle(
          color: isDark ? AppColors.textLight : AppColors.textPrimary,
          fontWeight: FontWeight.w600,
          fontSize: 20,
        ),
        bodyLarge: TextStyle(
          color: isDark ? AppColors.textLight : AppColors.textPrimary,
          fontSize: 16,
        ),
        bodyMedium: TextStyle(
          color: isDark ? AppColors.textSecondary : AppColors.textSecondary,
          fontSize: 14,
        ),
      );

  // ───────────────────────────────
  //  Input Theme Helper
  // ───────────────────────────────
  static InputDecorationTheme _inputTheme({required bool isDark}) =>
      InputDecorationTheme(
        filled: true,
        fillColor:
            isDark ? AppColors.cardDark : AppColors.backgroundLight,
        contentPadding:
            const EdgeInsets.symmetric(vertical: 12, horizontal: 16),
        border: OutlineInputBorder(
          borderSide: BorderSide(
            color: isDark ? AppColors.primary : AppColors.accent,
          ),
          borderRadius: BorderRadius.circular(12),
        ),
        enabledBorder: OutlineInputBorder(
          borderSide: BorderSide(
            color: isDark ? AppColors.accent : AppColors.borderLight,
          ),
          borderRadius: BorderRadius.circular(12),
        ),
        focusedBorder: OutlineInputBorder(
          borderSide: const BorderSide(color: AppColors.primary, width: 2),
          borderRadius: BorderRadius.circular(12),
        ),
        hintStyle: TextStyle(
          color: isDark ? AppColors.accent : AppColors.textSecondary,
        ),
      );
}

