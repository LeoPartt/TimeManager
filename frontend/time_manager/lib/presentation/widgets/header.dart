import 'package:flutter/material.dart';
import 'package:time_manager/core/constants/app_colors.dart';
import 'package:time_manager/core/constants/app_sizes.dart';

class Header extends StatelessWidget {
  final String label;

  const Header({
    super.key,
    required this.label,
  });

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.sizeOf(context);
    final w = AppSizes.dashboardWidth(context);
    final h = AppSizes.dashboardHeight(context);

    return Container(   
      width: w,
      height: h,
      decoration: BoxDecoration(
        color: AppColors.accent,
        boxShadow: const [
                        BoxShadow(
                          color: Color.fromRGBO(0, 0, 0, 0.25),
                          offset: Offset(0, 4),
                          blurRadius: 12,
                        ),]
      ),
      margin: EdgeInsets.only(top : size.height * 0.05),
      child: Center(
        child: FittedBox(
          fit: BoxFit.scaleDown,
          child: Text(
            label,
            style: TextStyle(
              fontSize: AppSizes.textDisplay,
              fontWeight: FontWeight.bold,
              color: AppColors.textPrimary,
            ),
          ),
        ),
      )
    );
  }
}
