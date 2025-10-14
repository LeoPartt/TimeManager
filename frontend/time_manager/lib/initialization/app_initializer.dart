import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'locator.dart';
import 'environment.dart';

/// Responsible for initializing dependencies and services before and after the app runs.
class AppInitializer {
  /// Initialize services, plugins, etc. before the app runs.
  Future<void> preAppRun() async {
    await setupLocator();
    await Environment.load();
  }

  /// Initialize services, plugins, etc. after the app runs.
  Future<void> postAppRun() async {
    // Hide Red Screen of Death in release mode
    if (kReleaseMode) {
      ErrorWidget.builder = (FlutterErrorDetails details) => const SizedBox();
    }
  }
}
