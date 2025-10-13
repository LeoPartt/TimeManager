import 'package:flutter/material.dart';
import 'package:time_manager/core/constants/app_colors.dart';

class AppSearchBar extends StatelessWidget {
  final TextEditingController controller;
  final ValueChanged<String>? onChanged;
  final String hintText;

  const AppSearchBar({
    super.key,
    required this.controller,
    this.onChanged,
    this.hintText = "User or team...",
  });

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.sizeOf(context);

    return Container(
      width: size.width * 0.9,
      height: size.height * 0.06,
      decoration: BoxDecoration(
        color: AppColors.accent,
        borderRadius: BorderRadius.circular(12),
        border: Border.all(
          width: 2,
        ),
      ),
        child: TextField(
          controller: controller,
          onChanged: onChanged,
          style: TextStyle(
            color: AppColors.textPrimary,
            fontSize: size.width * 0.045,
          ),
          decoration: InputDecoration(
            border: InputBorder.none,
            filled: false,
            prefixIcon: const Icon(
              Icons.search_rounded,
              color: AppColors.textPrimary,
            ),
            hintText: hintText,
            hintStyle: TextStyle(
              color: AppColors.textPrimary.withValues(alpha: 0.75),
            ),
            contentPadding: EdgeInsets.symmetric(vertical: size.height * 0.015),
          ),
        )
    );
  }
}
