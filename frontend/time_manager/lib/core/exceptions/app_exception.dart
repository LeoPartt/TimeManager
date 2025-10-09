/// Base class for all custom exceptions in the Time Manager app.
///
/// This class provides a consistent way to handle and display errors across layers.
class AppException implements Exception {
  final String message;
  final String? details;

  AppException(this.message, {this.details});

  @override
  String toString() => 'AppException: $message ${details ?? ""}';
}
