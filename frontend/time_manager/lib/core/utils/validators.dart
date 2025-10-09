import 'package:time_manager/core/utils/extensions/string_extensions.dart';

/// Provides reusable validation functions for form inputs.
class Validators {
 static String? validateEmail(String? value) {
    if (value == null || value.isEmpty) {
      return "L'adresse e-mail est requise";
    }
    if (!value.isValidEmail) {
      return "Adresse e-mail invalide";
    }
    return null;
  }

  static String? validatePassword(String? value) {
    if (value == null || value.isEmpty) {
      return "Le mot de passe est requis";
    }
    if (value.length < 6) {
      return "Le mot de passe doit comporter au moins 6 caractères";
    }
    return null;
  }

  static String? validateNotEmpty(String? value, String fieldName) {
    if (value == null || value.isEmpty) {
      return "Le champ '$fieldName' est obligatoire";
    }
    return null;
  }
}
