import 'package:flutter/material.dart';
import 'package:time_manager/core/constants/app_colors.dart';

class Header extends StatelessWidget {
  final String label;

  const Header({
    super.key,
    required this.label,
  });

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.sizeOf(context);
    final w = size.width * 0.9;
    final h = size.height * 0.06;

    return Container(   
      width: w,
      height: h,
      color: AppColors.accent,
      margin: EdgeInsets.only(top : size.height * 0.05),
      child: Center(
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
      )
    );
  }
}
