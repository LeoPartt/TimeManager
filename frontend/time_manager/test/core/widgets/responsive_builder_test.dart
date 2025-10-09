import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:time_manager/core/widgets/responsive_builder.dart';

void main() {
 testWidgets('ResponsiveBuilder shows mobile layout by default', (tester) async {
  await tester.pumpWidget(
    MediaQuery(
      data: const MediaQueryData(size: Size(400, 800)), // ✅ width < 600 => mobile
      child: MaterialApp(
        home: ResponsiveBuilder(
          mobile: const Text('Mobile'),
          tablet: const Text('Tablet'),
          desktop: const Text('Desktop'),
        ),
      ),
    ),
  );

  expect(find.text('Mobile'), findsOneWidget);
});


  testWidgets('ResponsiveBuilder switches to tablet layout', (tester) async {
    await tester.pumpWidget(
      MediaQuery(
        data: const MediaQueryData(size: Size(800, 1280)),
        child: MaterialApp(
          home: ResponsiveBuilder(
            mobile: const Text('Mobile'),
            tablet: const Text('Tablet'),
            desktop: const Text('Desktop'),
          ),
        ),
      ),
    );

    expect(find.text('Tablet'), findsOneWidget);
  });

  testWidgets('ResponsiveBuilder switches to desktop layout', (tester) async {
    await tester.pumpWidget(
      MediaQuery(
        data: const MediaQueryData(size: Size(1300, 1000)),
        child: MaterialApp(
          home: ResponsiveBuilder(
            mobile: const Text('Mobile'),
            tablet: const Text('Tablet'),
            desktop: const Text('Desktop'),
          ),
        ),
      ),
    );

    expect(find.text('Desktop'), findsOneWidget);
  });
}
