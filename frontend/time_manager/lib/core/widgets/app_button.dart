import 'package:flutter/material.dart';
import 'package:time_manager/core/constants/app_colors.dart';
import 'package:time_manager/core/constants/app_sizes.dart';

class AppButton extends StatelessWidget {
  final String label;
  final VoidCallback onPressed;
  final bool fullSize;
  final bool isLoading;

  const AppButton({
    super.key,
    required this.label,
    required this.onPressed,
    this.isLoading = false,
    this.fullSize = false,
  });

  @override
  Widget build(BuildContext context) {
    final w = fullSize
        ? AppSizes.appContainerWidth(context)
        : AppSizes.appSmallContainerWidth(context);
    final h = fullSize
        ? AppSizes.appContainerHeight(context)
        : AppSizes.appSmallContainerHeight(context);
    final r = BorderRadius.circular(AppSizes.r16);

    return Material(
      color: Colors.transparent,
      child: Ink(
        width: w,
        height: h,
        decoration: BoxDecoration(
          color: isLoading
              ? AppColors.secondary.withValues(alpha: 0.7)
              : AppColors.secondary,
          borderRadius: r,
          border: Border.all(
            color: AppColors.shadow.withValues(alpha: 0.6),
            width: 2,
          ),
        ),
        child: InkWell(
          borderRadius: r,
          onTap: isLoading ? null : onPressed,
          splashColor: AppColors.primary.withValues(alpha: 0.2),
          highlightColor: AppColors.primary.withValues(alpha: 0.1),
          child: Center(
            child: AnimatedSwitcher(
              duration: const Duration(milliseconds: 200),
              transitionBuilder: (child, anim) => FadeTransition(
                opacity: anim,
                child: child,
              ),
              child: isLoading
                  ? Row(
                      key: const ValueKey('loading'),
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        SizedBox(
                          width: fullSize ? 24 : 18,
                          height: fullSize ? 24 : 18,
                          child: const CircularProgressIndicator(
                            strokeWidth: 2,
                            valueColor:
                                AlwaysStoppedAnimation(Colors.white),
                          ),
                        ),
                        const SizedBox(width: 12),
                        Text(
                          _buildLoadingText(label),
                          style: TextStyle(
                            fontSize: fullSize
                                ? AppSizes.textLg
                                : AppSizes.textMd,
                            fontWeight: FontWeight.bold,
                            color: Colors.white,
                          ),
                        ),
                      ],
                    )
                  : FittedBox(
                      key: const ValueKey('label'),
                      fit: BoxFit.scaleDown,
                      child: Text(
                        label,
                        style: TextStyle(
                          fontSize: fullSize
                              ? AppSizes.textDisplay
                              : AppSizes.textXxl,
                          fontWeight: FontWeight.bold,
                          color: AppColors.textPrimary,
                        ),
                      ),
                    ),
            ),
          ),
        ),
      ),
    );
  }

  /// Transforme automatiquement "Create User" → "Creating..."
  static String _buildLoadingText(String text) {
    final parts = text.split(' ');
    if (parts.length == 1) {
      // Ex: "Save" -> "Saving..."
      return text.endsWith('e')
          ? '${text.substring(0, text.length - 1)}ing...'
          : '${text}ing...';
    } else {
      // Ex: "Create User" -> "Creating..."
      return '${parts.first}ing...';
    }
  }
}
