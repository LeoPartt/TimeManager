import 'package:flutter/material.dart';

/// A reusable circular avatar widget for user profile images or icons.
class AppAvatar extends StatelessWidget {
  final String? imageUrl;
  final double radius;
  final IconData fallbackIcon;
  final Color? backgroundColor;
  final Color? iconColor;
  final VoidCallback? onTap;
  final bool showBorder;

  const AppAvatar({
    super.key,
    this.imageUrl,
    this.radius = 40,
    this.fallbackIcon = Icons.person_outline,
    this.backgroundColor,
    this.iconColor,
    this.onTap,
    this.showBorder = false,
  });

  @override
  Widget build(BuildContext context) {
    final colorScheme = Theme.of(context).colorScheme;

    final bgColor = backgroundColor ?? colorScheme.surface;
    final borderColor = colorScheme.primary.withOpacity(0.3);
    final iconCol = iconColor ?? colorScheme.primary;

    final avatar = CircleAvatar(
      radius: radius,
      backgroundColor: bgColor,
      backgroundImage:
          (imageUrl != null && imageUrl!.isNotEmpty) ? NetworkImage(imageUrl!) : null,
      child: (imageUrl == null || imageUrl!.isEmpty)
          ? Icon(fallbackIcon, color: iconCol, size: radius * 0.9)
          : null,
    );

    return GestureDetector(
      onTap: onTap,
      child: showBorder
          ? Container(
              decoration: BoxDecoration(
                shape: BoxShape.circle,
                
                border: Border.all(color: borderColor, width: 2),
                boxShadow: const [
                                    BoxShadow(
                                      color: Color.fromRGBO(0, 0, 0, 0.35),
                                      offset: Offset(0, 2),
                                      blurRadius: 6,
                                    ),
                                  ],
              ),
              
              padding: const EdgeInsets.all(2),
              child: avatar,
            )
          : avatar,
    );
  }
}
