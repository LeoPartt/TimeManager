import 'package:flutter/material.dart';
import 'package:time_manager/core/constants/app_colors.dart';
import 'package:time_manager/core/constants/app_sizes.dart';

class AppButton extends StatelessWidget {
  final String label;
  final VoidCallback onPressed;
  final bool fullSize;

  const AppButton({
    super.key,
    required this.label,
    required this.onPressed,
    required this.fullSize,
  });

  @override
  Widget build(BuildContext context) {
    final w = fullSize ? AppSizes.appContainerWidth(context) : AppSizes.appSmallContainerWidth(context);
    final h = fullSize ? AppSizes.appContainerHeight(context) : AppSizes.appSmallContainerHeight(context);
    final r = BorderRadius.circular(AppSizes.r16);

    return Material( 
      color: Colors.transparent,

      child: Ink(    
        width: w,
        height: h,
        decoration: BoxDecoration(
        color: AppColors.secondary,
          borderRadius: r,
          border: Border.all(
            color: AppColors.shadow.withValues(alpha: 0.6),
            width: 2,
          )
        ),
        child: InkWell( 
          borderRadius: r,
          onTap: onPressed,
          splashColor: AppColors.primary.withValues(alpha : 0.2),
          highlightColor: AppColors.primary.withValues(alpha : 0.1),
          child: Center(
            child: FittedBox(
              fit: BoxFit.scaleDown,
              child: Text(
                label,
                style: TextStyle(
                  fontSize: fullSize ? AppSizes.textDisplay : AppSizes.textXxl,
                  fontWeight: FontWeight.bold,
                  color: AppColors.textPrimary,
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }
}
