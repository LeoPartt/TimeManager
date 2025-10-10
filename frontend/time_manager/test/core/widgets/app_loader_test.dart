import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:time_manager/core/widgets/app_loader.dart';

void main() {
  testWidgets('AppLoader displays a CircularProgressIndicator', (tester) async {
    await tester.pumpWidget(
      const MaterialApp(
        home: Scaffold(body: AppLoader()),
      ),
    );

    expect(find.byType(CircularProgressIndicator), findsOneWidget);
  });
}
