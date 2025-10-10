import 'package:time_manager/core/constants/app_strings.dart';
import 'app_exception.dart';

/// Represents network or API-related exceptions.
class NetworkException extends AppException {
  final int? statusCode;

  NetworkException(super.message, {this.statusCode, super.details});

  factory NetworkException.fromStatusCode(int? code) {
    switch (code) {
      case 400:
        return NetworkException(AppStrings.badRequest, statusCode: code);
      case 401:
        return NetworkException(AppStrings.unauthorized, statusCode: code);
      case 404:
        return NetworkException(AppStrings.notFound, statusCode: code);
      case 500:
        return NetworkException(AppStrings.serverError, statusCode: code);
      default:
        return NetworkException(AppStrings.networkError(code), statusCode: code);
    }
  }

  @override
  String toString() => 'NetworkException($statusCode): $message';
}
