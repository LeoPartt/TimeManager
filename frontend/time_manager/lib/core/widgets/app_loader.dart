import 'package:flutter/material.dart';
import '../constants/app_sizes.dart';

class AppLoader extends StatelessWidget {
  final double size;

  const AppLoader({super.key, this.size = 32});

  @override
  Widget build(BuildContext context) {
    final color = Theme.of(context).colorScheme.primary;
    final scaledSize = AppSizes.responsiveHeight(context, size);

    return Center(
      child: SizedBox(
        width: scaledSize,
        height: scaledSize,
        child: CircularProgressIndicator(
          color: color,
          strokeWidth: 3,
        ),
      ),
    );
  }
}
