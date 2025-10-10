import 'package:time_manager/core/constants/app_strings.dart';
import 'app_exception.dart';

/// Thrown when data validation fails (e.g., empty fields, bad email, etc.).
class ValidationException extends AppException {
  ValidationException(super.message, {super.details});

  static ValidationException emptyField(String field) =>
      ValidationException(AppStrings.fieldIsRequired(field));

  static ValidationException invalidEmail() =>
      ValidationException(AppStrings.invalidEmail);

  static ValidationException shortPassword() =>
      ValidationException(AppStrings.shortPassword);
}
