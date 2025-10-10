import 'package:flutter/material.dart';
import 'package:time_manager/core/constants/app_colors.dart';
import 'package:time_manager/core/constants/app_sizes.dart';

class AppLabelContainer extends StatelessWidget {
  final String label;
  final bool fullSize;

  const AppLabelContainer({
    super.key,
    required this.fullSize,
    required this.label,
  });

  @override
  Widget build(BuildContext context) {
    final w =  fullSize ? AppSizes.appContainerWidth(context) : AppSizes.appSmallContainerWidth(context) ;
    final h =  fullSize ? AppSizes.appContainerHeight(context) : AppSizes.appSmallContainerHeight(context);
    final r = BorderRadius.circular(AppSizes.r16);

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
            width: 4,
          )
        ),
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
    );
  }
}
