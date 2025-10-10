import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:time_manager/core/widgets/app_card.dart';

void main() {
  testWidgets('AppCard renders child correctly', (tester) async {
    await tester.pumpWidget(
      MaterialApp(
        home: Scaffold(

          body: Material(
            child: AppCard(
              child: const Text('Hello Card'),
            ),
          ),
        ),
      ),
    );

    expect(find.text('Hello Card'), findsOneWidget);
  });

  testWidgets('AppCard responds to tap', (tester) async {
    bool tapped = false;
    await tester.pumpWidget(
      MaterialApp(
        home: Scaffold(
          body: Material(
            child: AppCard(
              onTap: () => tapped = true,
              child: const Text('Tap Me'),
            ),
          ),
        ),
      ),
    );

    await tester.tap(find.text('Tap Me'));
    await tester.pumpAndSettle();

    expect(tapped, true);
  });
}
