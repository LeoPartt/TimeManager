import 'package:flutter/material.dart';
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
        final theme = Theme.of(context);

  final colorScheme = theme.colorScheme;
    final textTheme = theme.textTheme;

    final width = AppSizes.responsiveWidth(context, 350);
    final height = AppSizes.responsiveHeight(context, 56);
    final radius = BorderRadius.circular(AppSizes.r16);

    final backgroundColor = colorScheme.secondary;
    final borderColor = colorScheme.primary.withValues(alpha: 0.35);
    final iconColor = colorScheme.onSurface.withValues(alpha:0.8);
   
    return Container(
      width: width,
      height: height,
      decoration: BoxDecoration(
        color: backgroundColor,
        borderRadius: radius,
        border: Border.all(
          color: borderColor,
          width: 2,
        ),
      ),
      child: Center(
        child: TextField(
          controller: controller,
          onChanged: onChanged,
          style: textTheme.bodyLarge?.copyWith(
            fontSize: AppSizes.responsiveText(context, AppSizes.textLg),
            height: 1.3,
          ),

          decoration: InputDecoration(
            filled: false, 
            isDense: true, 
            prefixIcon: Padding(
              padding: EdgeInsets.symmetric(horizontal: AppSizes.p12),
              child: Icon(
                Icons.search_rounded,
                size: AppSizes.responsiveWidth(context, AppSizes.iconMedium),
                color: iconColor,
              ),
            ),
            prefixIconConstraints: const BoxConstraints(minWidth: 40),
            hintText: hintText,
            hintStyle:textTheme.bodyMedium?.copyWith(
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
