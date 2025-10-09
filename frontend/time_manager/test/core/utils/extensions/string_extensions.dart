extension StringExtensions on String {
  /// Returns true if the string is a valid email address.
  bool get isValidEmail {
    final regex = RegExp(r'^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$');
    return regex.hasMatch(this);
  }

  /// Capitalizes the first letter.
  String get capitalize {
    if (isEmpty) return this;
    return '${this[0].toUpperCase()}${substring(1)}';
  }

  /// Shortens a long string with ellipsis.
  String get ellipsis {
    if (length <= 20) return this;
    return '${substring(0, 17)}...';
  }
}
