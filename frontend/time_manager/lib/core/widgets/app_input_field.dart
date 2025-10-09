import 'package:flutter/material.dart';
import '../constants/app_sizes.dart';

class AppInputField extends StatelessWidget {
  final String label;
  final IconData? icon;
  final bool obscureText;
  final TextEditingController? controller;

  const AppInputField({
    super.key,
    required this.label,
    this.icon,
    this.obscureText = false,
    this.controller,
  });

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    return TextField(
      controller: controller,
      obscureText: obscureText,
      style: theme.textTheme.bodyMedium?.copyWith(
        fontSize: AppSizes.responsiveText(context, AppSizes.textMd),
      ),
      decoration: InputDecoration(
        prefixIcon:
            icon != null ? Icon(icon, color: theme.colorScheme.primary) : null,
        labelText: label,
        labelStyle: theme.textTheme.bodySmall?.copyWith(
          fontSize: AppSizes.responsiveText(context, AppSizes.textSm),
          color: theme.colorScheme.onSurface.withOpacity(0.7),
        ),
        filled: true,
        fillColor: theme.colorScheme.surfaceContainerHighest,
        border: OutlineInputBorder(
          borderRadius: BorderRadius.circular(AppSizes.r12),
          borderSide: BorderSide(color: theme.colorScheme.outline),
        ),
        focusedBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(AppSizes.r12),
          borderSide:
              BorderSide(color: theme.colorScheme.primary, width: 2),
        ),
      ),
    );
  }
}
