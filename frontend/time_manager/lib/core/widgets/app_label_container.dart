import 'package:flutter/material.dart';
import 'package:time_manager/core/constants/app_colors.dart';
import 'package:time_manager/core/constants/app_sizes.dart';

class AppLabelContainer extends StatelessWidget {
  final String label;
  final bool fullSize;

  final bool editable;
  final TextEditingController? controller;
  final ValueChanged<String>? onChanged;
  final TextInputType keyboardType;

  const AppLabelContainer({
    super.key,
    required this.label,
    required this.fullSize,
    this.editable = false,
    this.controller,
    this.onChanged,
    this.keyboardType = TextInputType.text,
  });

  @override
  Widget build(BuildContext context) {
    final w = AppSizes.responsiveWidth(context, fullSize ? 300 : 150);
    final h = AppSizes.responsiveHeight(context, fullSize ? 60 : 48);

    final r = BorderRadius.circular(AppSizes.r16);

    return Container(
      width: w,
      height: h,
      padding: const EdgeInsets.symmetric(horizontal: 16),
      decoration: BoxDecoration(
        color: AppColors.primary,
        borderRadius: r,
        border: Border.all(color: AppColors.secondary, width: 4),
      ),
      alignment: Alignment.center,
      child: editable
          ? TextField(
              controller: controller,
              onChanged: onChanged,
              keyboardType: keyboardType,
              style: TextStyle(
                fontSize: fullSize ? AppSizes.textDisplay : AppSizes.textXxl,
                fontWeight: FontWeight.bold,
                color: AppColors.textPrimary,
              ),
              decoration: InputDecoration(
                hintText: label,
                isCollapsed: true,
                border: InputBorder.none,
              ),
            )
          : FittedBox(
              fit: BoxFit.scaleDown,
              alignment: Alignment.centerLeft,
              child: Text(
                label,
                style: TextStyle(
                  fontSize: fullSize ? AppSizes.textDisplay : AppSizes.textXxl,
                  fontWeight: FontWeight.bold,
                  color: AppColors.textPrimary,
                ),
              ),
            ),
    );
  }
}
