import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:time_manager/core/widgets/app_input_field.dart';

void main() {
  testWidgets('AppInputField displays label and icon', (tester) async {
    await tester.pumpWidget(
      MaterialApp(
        home: Scaffold(
          body: AppInputField(
            label: 'Email',
            icon: Icons.email,
          ),
        ),
      ),
    );

    expect(find.text('Email'), findsOneWidget);
    expect(find.byIcon(Icons.email), findsOneWidget);
  });

  testWidgets('AppInputField supports text input', (tester) async {
    final controller = TextEditingController();

    await tester.pumpWidget(
      MaterialApp(
        home: Scaffold(
          body: AppInputField(label: 'Test', controller: controller),
        ),
      ),
    );

    await tester.enterText(find.byType(TextField), 'Hello');
    expect(controller.text, 'Hello');
  });
}
