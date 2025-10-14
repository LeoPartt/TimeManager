import 'package:intl/intl.dart';

/// Provides consistent date formatting across the app.
class DateFormatter {
  static String formatDate(DateTime date) {
    return DateFormat('dd/MM/yyyy').format(date);
  }

  static String formatTime(DateTime date) {
    return DateFormat('HH:mm').format(date);
  }

  static String formatFull(DateTime date) {
    return DateFormat('dd MMM yyyy, HH:mm').format(date);
  }

  static String formatForApi(DateTime date) {
    return DateFormat('yyyy-MM-ddTHH:mm:ss').format(date);
  }
}
