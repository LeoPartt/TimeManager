import 'package:flutter/material.dart';

/// Utility class that helps ensure accessibility consistency across the app.
///
/// It adapts color contrast, text scaling, and semantics to improve
/// usability for all users (especially with visual impairments).
class AccessibilityUtils {
  /// Ensures sufficient color contrast based on the current theme brightness.
  ///
  /// If the background color is too dark or too bright, this method
  /// returns a text color that ensures readable contrast.
  static Color ensureContrast(BuildContext context, Color background) {
    final luminance = background.computeLuminance();
    final isDarkMode = Theme.of(context).brightness == Brightness.dark;

    // Return contrasting color depending on mode and luminance
    if (luminance < 0.5) {
      return Colors.white; // background too dark → white text
    } else {
      return isDarkMode ? Colors.grey[200]! : Colors.black; // background clear
    }
  }

  /// Wraps a widget with semantic information for screen readers.
  ///
  /// This helps assistive technologies describe widgets verbally.
  static Widget withLabel({
    required String label,
    required Widget child,
    bool hidden = false,
  }) {
    return Semantics(
      label: label,
      excludeSemantics: hidden,
      child: child,
    );
  }

  /// Scales a text size according to user accessibility settings.
  ///
  /// Respects the device's textScaleFactor and stays within safe bounds.
  static double accessibleText(BuildContext context, double baseSize) {
    final scale = MediaQuery.of(context).textScaleFactor.clamp(0.8, 1.4);
    return baseSize * scale;
  }

  /// Returns true if the system text scale is large (e.g., accessibility zoom)
  static bool isTextZoomed(BuildContext context) {
    final scale = MediaQuery.of(context).textScaleFactor;
    return scale > 1.2;
  }

  /// Adds a semantic tooltip for non-text widgets (e.g., icons, buttons)
  static Widget withTooltip({
    required String tooltip,
    required Widget child,
  }) {
    return Tooltip(
      message: tooltip,
      waitDuration: const Duration(milliseconds: 500),
      child: Semantics(
        label: tooltip,
        child: child,
      ),
    );
  }
}
