import 'package:flutter/material.dart';
import 'app_colors.dart';
import 'app_sizes.dart';

/// Defines all text styles used in the Time Manager app.
///
/// Each text style is designed to be scalable and consistent.
class AppTextStyles {
  // ───────────────────────────────
  //  Titles & Headings
  // ───────────────────────────────
  static TextStyle heading1(BuildContext context) => TextStyle(
        fontSize: AppSizes.responsiveText(context, AppSizes.textDisplay),
        fontWeight: FontWeight.bold,
        color: AppColors.textPrimary,
      );

  static TextStyle heading2(BuildContext context) => TextStyle(
        fontSize: AppSizes.responsiveText(context, AppSizes.textXxl),
        fontWeight: FontWeight.w700,
        color: AppColors.textPrimary,
      );

  static TextStyle heading3(BuildContext context) => TextStyle(
        fontSize: AppSizes.responsiveText(context, AppSizes.textXl),
        fontWeight: FontWeight.w600,
        color: AppColors.textPrimary,
      );

  // ───────────────────────────────
  //  Body Texts
  // ───────────────────────────────
  static TextStyle bodyLarge(BuildContext context) => TextStyle(
        fontSize: AppSizes.responsiveText(context, AppSizes.textLg),
        color: AppColors.textPrimary,
        height: 1.4,
      );

  static TextStyle bodyMedium(BuildContext context) => TextStyle(
        fontSize: AppSizes.responsiveText(context, AppSizes.textMd),
        color: AppColors.textSecondary,
        height: 1.4,
      );

  static TextStyle bodySmall(BuildContext context) => TextStyle(
        fontSize: AppSizes.responsiveText(context, AppSizes.textSm),
        color: AppColors.textSecondary,
      );

  // ───────────────────────────────
  //  Labels & Captions
  // ───────────────────────────────
  static TextStyle label(BuildContext context) => TextStyle(
        fontSize: AppSizes.responsiveText(context, AppSizes.textSm),
        fontWeight: FontWeight.w500,
        color: AppColors.secondary,
      );

  static TextStyle caption(BuildContext context) => TextStyle(
        fontSize: AppSizes.responsiveText(context, AppSizes.textXs),
        color: AppColors.textSecondary,
        fontStyle: FontStyle.italic,
      );

  // ───────────────────────────────
  //  Buttons
  // ───────────────────────────────
  static TextStyle buttonPrimary(BuildContext context) => TextStyle(
        fontSize: AppSizes.responsiveText(context, AppSizes.textLg),
        fontWeight: FontWeight.bold,
        color: Colors.white,
        letterSpacing: 0.5,
      );

  static TextStyle buttonSecondary(BuildContext context) => TextStyle(
        fontSize: AppSizes.responsiveText(context, AppSizes.textLg),
        fontWeight: FontWeight.w600,
        color: AppColors.primary,
      );

  // ───────────────────────────────
  //  Error / Info messages
  // ───────────────────────────────
  static TextStyle error(BuildContext context) => TextStyle(
        fontSize: AppSizes.responsiveText(context, AppSizes.textSm),
        color: AppColors.error,
        fontWeight: FontWeight.w600,
      );

  static TextStyle info(BuildContext context) => TextStyle(
        fontSize: AppSizes.responsiveText(context, AppSizes.textSm),
        color: AppColors.info,
      );
}
