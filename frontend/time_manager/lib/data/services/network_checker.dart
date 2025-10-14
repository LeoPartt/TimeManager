import 'dart:io';
//Vérifie si l’app a une connexion internet avant de lancer un appel API.

class NetworkChecker {
  Future<bool> hasConnection() async {
    try {
      final result = await InternetAddress.lookup('google.com');
      return result.isNotEmpty && result.first.rawAddress.isNotEmpty;
    } catch (_) {
      return false;
    }
  }
}
