import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:time_manager/core/constants/app_sizes.dart';

void main() {
  group('AppSizes', () {
    testWidgets('responsiveWidth adjusts based on screen width', (
      tester,
    ) async {
      const double base = 100.0;

      // Petit écran
      await tester.pumpWidget(
        MediaQuery(
          data: const MediaQueryData(size: Size(320, 640)),
          child: Builder(
            builder: (context) {
              final small = AppSizes.responsiveWidth(context, base);
              expect(small, lessThan(base));
              return const SizedBox();
            },
          ),
        ),
      );

      // Grand écran
      await tester.pumpWidget(
        MediaQuery(
          data: const MediaQueryData(size: Size(800, 1280)),
          child: Builder(
            builder: (context) {
              final large = AppSizes.responsiveWidth(context, base);
              expect(large, greaterThan(base));
              return const SizedBox();
            },
          ),
        ),
      );
    });

    testWidgets('responsiveHeight adjusts based on screen height', (
      tester,
    ) async {
      const double base = 200.0;

      // Petit écran
      await tester.pumpWidget(
        MediaQuery(
          data: const MediaQueryData(size: Size(320, 530)),
          child: Builder(
            builder: (context) {
              final small = AppSizes.responsiveHeight(context, base);
              expect(small, lessThan(base));
              return const SizedBox();
            },
          ),
        ),
      );

      // Grand écran
      await tester.pumpWidget(
        MediaQuery(
          data: const MediaQueryData(size: Size(800, 1200)),
          child: Builder(
            builder: (context) {
              final large = AppSizes.responsiveHeight(context, base);
              expect(large, greaterThan(base));
              return const SizedBox();
            },
          ),
        ),
      );
    });

    testWidgets('responsiveText scales with text factor', (tester) async {
      const double baseTextSize = 16.0;

      await tester.pumpWidget(
        MediaQuery(
          data: const MediaQueryData(textScaler: TextScaler.linear(1.2)),
          child: Builder(
            builder: (context) {
              final scaled = AppSizes.responsiveText(context, baseTextSize);
              expect(scaled, greaterThan(baseTextSize));
              return const SizedBox();
            },
          ),
        ),
      );
    });
  });
}
