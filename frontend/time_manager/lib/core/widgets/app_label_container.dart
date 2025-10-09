import 'package:flutter/material.dart';
import 'package:time_manager/core/constants/app_colors.dart';

class AppLabelContainer extends StatelessWidget {
  final String label;
  final double? width;
  final double? height;
  final BorderRadius? radius;

  const AppLabelContainer({
    super.key,
    required this.label,
    this.width,
    this.height,
    this.radius,
  });

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.sizeOf(context);
    final w = width ?? size.width * 0.6;
    final h = height ?? size.height * 0.08;
    final r = radius ?? BorderRadius.circular(15);

    return Material( 
      color: Colors.transparent,

      child: Container(    
        width: w,
        height: h,
        decoration: BoxDecoration(
        color: AppColors.primary,
        borderRadius: r,
         border: Border.all(
            color: AppColors.secondary,
            width: 5,
          )
        ),
        child: FittedBox(
           fit: BoxFit.scaleDown,
           child: Text(
            label,
            style: TextStyle(
              fontSize: size.width * 0.08,
              fontWeight: FontWeight.bold,
              color: AppColors.textPrimary,
            ),
          ),
        ),
      ),
    );
  }
}
