import 'package:flutter_test/flutter_test.dart';
import 'package:time_manager/core/utils/date_formatter.dart';

void main() {
  group('DateFormatter', () {
    final date = DateTime(2025, 10, 9, 14, 30, 0); // 9 Oct 2025, 14:30:00

    test('formatDate returns correct dd/MM/yyyy format', () {
      final formatted = DateFormatter.formatDate(date);
      expect(formatted, '09/10/2025');
    });

    test('formatTime returns correct HH:mm format', () {
      final formatted = DateFormatter.formatTime(date);
      expect(formatted, '14:30');
    });

    test('formatFull returns readable format', () {
      final formatted = DateFormatter.formatFull(date);
      expect(formatted, contains('2025'));
      expect(formatted, contains('14:30'));
    });

    test('formatForApi returns ISO-like format', () {
      final formatted = DateFormatter.formatForApi(date);
      expect(formatted, startsWith('2025-10-09T14:30'));
    });
  });
}
