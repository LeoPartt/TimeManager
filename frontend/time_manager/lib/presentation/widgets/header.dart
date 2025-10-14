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
    final isLandscape = size.width > size.height;
    final w = AppSizes.responsiveWidth(context, 0.9 * MediaQuery.of(context).size.width);
    final h = AppSizes.responsiveHeight(context, isLandscape ? 0.1 * MediaQuery.of(context).size.height : 0.06 * MediaQuery.of(context).size.height );

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
      margin: EdgeInsets.only(top : AppSizes.responsiveHeight(context, 0.03 * MediaQuery.of(context).size.height)),
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
