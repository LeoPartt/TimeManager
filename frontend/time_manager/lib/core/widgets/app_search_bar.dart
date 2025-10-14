import 'package:flutter/material.dart';
import 'package:time_manager/core/constants/app_colors.dart';
import 'package:time_manager/core/constants/app_sizes.dart';

class AppSearchBar extends StatelessWidget {
  final TextEditingController controller;
  final ValueChanged<String>? onChanged;
  final String hintText;

  const AppSearchBar({
    super.key,
    required this.controller,
    this.onChanged,
    required this.hintText,
  });

  @override
  Widget build(BuildContext context) {
    final height = AppSizes.responsiveHeight(context, 56);

    return Container(
      width: AppSizes.responsiveWidth(context, 350),
      height: height,
      decoration: BoxDecoration(
        color: AppColors.accent,
        borderRadius: BorderRadius.circular(AppSizes.r16),
        border: Border.all(
          color: AppColors.primary.withValues(alpha: 0.35),
          width: 2,
        ),
      ),
      child: Center(
        child: TextField(
          controller: controller,
          onChanged: onChanged,
          style: TextStyle(
            color: AppColors.textPrimary,
            fontSize: AppSizes.responsiveText(context, AppSizes.textLg),
            height: 1.2, 
          ),
          decoration: InputDecoration(
            filled: false, 
            isDense: true, 
            prefixIcon: Padding(
              padding: EdgeInsets.symmetric(horizontal: AppSizes.p12),
              child: Icon(
                Icons.search_rounded,
                size: AppSizes.responsiveWidth(context, AppSizes.iconMedium),
                color: AppColors.textPrimary,
              ),
            ),
            prefixIconConstraints: const BoxConstraints(minWidth: 40),
            hintText: hintText,
            hintStyle: TextStyle(
              color: AppColors.textPrimary.withValues(alpha: 0.65),
              fontSize: AppSizes.responsiveText(context, AppSizes.textLg),
            ),
            border: InputBorder.none,
            focusedBorder: InputBorder.none,
            enabledBorder: InputBorder.none,
            contentPadding: EdgeInsets.symmetric(
              vertical: AppSizes.p12, 
              horizontal: AppSizes.p8,
            ),
          ),
        ),
      ),
    );
  }
}
