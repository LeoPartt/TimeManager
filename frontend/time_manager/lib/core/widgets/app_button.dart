import 'package:flutter/material.dart';

class AppButton extends StatelessWidget {
  final String label;
  final VoidCallback onPressed;
  final double? width;
  final double? height;
  final BorderRadius? radius;

  const AppButton({
    super.key,
    required this.label,
    required this.onPressed,
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

      child: Ink(    
        width: w,
        height: h,
        decoration: BoxDecoration(
          color: const Color(0xFF768191),
          borderRadius: r,
          border: Border.all(
            color: const Color.fromRGBO(0, 0, 0, 0.35),
            width: 1,
          )
        ),
        child: InkWell( 
          borderRadius: r,
          onTap: onPressed,
          splashColor: Color(0xFF0088FF).withValues(alpha: 0.2),
          highlightColor: Color(0xFF0088FF).withValues(alpha: 0.1),
          child: Center(
            child: FittedBox(
              fit: BoxFit.scaleDown,
              child: Text(
                label,
                style: TextStyle(
                  fontSize: size.width * 0.08,
                  fontWeight: FontWeight.bold,
                  color: Colors.black,
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }
}
