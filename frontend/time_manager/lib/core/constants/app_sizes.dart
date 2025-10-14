import 'package:flutter/material.dart';

/// Global responsive sizing system for Time Manager.
///
/// Centralizes all paddings, radius, icon, text and layout sizes.
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
  static const double r32 = 32.0;

  // ───────────────────────────────
  // 🔹 Icon sizes
  // ───────────────────────────────
  static const double iconSmall = 16.0;
  static const double iconMedium = 24.0;
  static const double iconLarge = 32.0;
  static const double iconXl = 48.0;

  /// Responsive icon sizing
  static double responsiveIcon(BuildContext context, double size) {
    final width = MediaQuery.of(context).size.width;
    if (width < 350) return size * 0.85;
    if (width > 600) return size * 1.2;
    return size;
  }

  // ───────────────────────────────
  // 🔹 Text sizes (base, scalable)
  // ───────────────────────────────
  static const double textXs = 10.0;
  static const double textSm = 12.0;
  static const double textMd = 14.0;
  static const double textLg = 16.0;
  static const double textXl = 20.0;
  static const double textXxl = 24.0;
  static const double textDisplay = 32.0;

  /// Responsive text scaling
  static double responsiveText(BuildContext context, double size) {
    if (size <= 0) return size;
    final ts = MediaQuery.textScalerOf(context);
    final scaled = ts.scale(size);
    return scaled.clamp(size * 0.9, size * 1.2).toDouble();
  }

  // ───────────────────────────────
  // 🔹 Layout helpers (width/height)
  // ───────────────────────────────
  static double responsiveWidth(BuildContext context, double size) {
    final width = MediaQuery.of(context).size.width;
    if (width < 350) return size * 0.85; // Small phones
    if (width > 600) return size * 1.15; // Tablets
    return size;
  }

  static double responsiveHeight(BuildContext context, double size) {
    final height = MediaQuery.of(context).size.height;
    if (height < 650) return size * 0.9;
    if (height > 900) return size * 1.1;
    return size;
  }

  // ───────────────────────────────
  // 🔹 Special containers (cards, forms)
  // ───────────────────────────────
  static double cardWidth(BuildContext context) =>
      responsiveWidth(context, 600);

  static double cardHeight(BuildContext context) =>
      responsiveHeight(context, 300);

  static double buttonHeight(BuildContext context) =>
      responsiveHeight(context, 48);
}
