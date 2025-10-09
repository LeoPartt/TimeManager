import 'package:time_manager/core/constants/app_strings.dart';
import 'package:time_manager/core/utils/extensions/string_extensions.dart';

/// Provides reusable validation functions for form inputs.
class Validators {
  static String? validateEmail(String? value) {
    if (value == null || value.isEmpty) {
      return AppStrings.emailRequired;
    }
    if (!value.isValidEmail) {
      return AppStrings.invalidEmail;
    }
    return null;
  }

  static String? validatePassword(String? value) {
    if (value == null || value.isEmpty) {
      return AppStrings.passwordRequired;
    }
    if (value.length < 6) {
      return AppStrings.shortPassword;
    }
    return null;
  }

  static String? validateNotEmpty(String? value, String fieldName) {
    if (value == null || value.isEmpty) {
      return AppStrings.fieldIsRequired(fieldName);
    }
    return null;
  }
}
