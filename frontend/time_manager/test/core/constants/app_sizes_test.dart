import 'package:flutter/material.dart';

/// Defines global spacing, radius, and size constants for responsiveness.
///
/// Use these constants to ensure consistent spacing and sizing across the app.
class AppSizes {
  // ───────────────────────────────
  // 🔹 Padding & Margins
  // ───────────────────────────────
  static const double p2 = 2.0;
  static const double p4 = 4.0;
  static const double p8 = 8.0;
  static const double p12 = 12.0;
  static const double p16 = 16.0;
  static const double p20 = 20.0;
  static const double p24 = 24.0;
  static const double p32 = 32.0;

  // ───────────────────────────────
  // 🔹 Border Radius
  // ───────────────────────────────
  static const double r4 = 4.0;
  static const double r8 = 8.0;
  static const double r12 = 12.0;
  static const double r16 = 16.0;
  static const double r24 = 24.0;

  // ───────────────────────────────
  // 🔹 Icon sizes
  // ───────────────────────────────
  static const double iconSmall = 16.0;
  static const double iconMedium = 24.0;
  static const double iconLarge = 32.0;

  // ───────────────────────────────
  // 🔹 Text sizes (base, can be scaled responsively)
  // ───────────────────────────────
  static const double textXs = 10.0;
  static const double textSm = 12.0;
  static const double textMd = 14.0;
  static const double textLg = 16.0;
  static const double textXl = 20.0;
  static const double textXxl = 24.0;
  static const double textDisplay = 32.0;

  // ───────────────────────────────
  // 🔹 Responsive utility
  // ───────────────────────────────
  /// Returns responsive width based on screen width ratio.
  static double responsiveWidth(BuildContext context, double size) {
    final width = MediaQuery.of(context).size.width;
    if (width < 350) return size * 0.85; // Small screen
    if (width > 600) return size * 1.15; // Tablet
    return size;
  }

  /// Returns responsive height based on screen height ratio.
  static double responsiveHeight(BuildContext context, double size) {
    final height = MediaQuery.of(context).size.height;
    if (height < 650) return size * 0.9;
    if (height > 900) return size * 1.1;
    return size;
  }

  /// Returns a responsive text size using MediaQuery scaling.
  static double responsiveText(BuildContext context, double size) {
    final scale = MediaQuery.of(context).textScaleFactor;
    return size * scale.clamp(0.9, 1.2);
  }
}
