import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:time_manager/core/widgets/app_error_widget.dart';

void main() {
  testWidgets('AppErrorWidget displays message', (tester) async {
    await tester.pumpWidget(
      const MaterialApp(
        home: Scaffold(
          body: AppErrorWidget(message: 'Critical Error'),
        ),
      ),
    );

    expect(find.text('Critical Error'), findsOneWidget);
  });

  testWidgets('AppErrorWidget triggers retry callback', (tester) async {
    bool retried = false;

    await tester.pumpWidget(
      MaterialApp(
        home: Scaffold(
          body: AppErrorWidget(
            message: 'Error',
            onRetry: () => retried = true,
          ),
        ),
      ),
    );

    await tester.tap(find.text('Retry'));
    await tester.pump();

    expect(retried, true);
  });
}
