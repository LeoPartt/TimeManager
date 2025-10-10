import 'package:flutter_secure_storage/flutter_secure_storage.dart';

//Gère tout ce qui est stocké en local sur le téléphone :
//(token, utilisateur courant, cache, préférences…).
class LocalStorageService {
  final FlutterSecureStorage _storage = const FlutterSecureStorage();

  static const _keyToken = 'access_token';
  static const _keyUser = 'user_data';

  Future<void> saveToken(String token) async {
    await _storage.write(key: _keyToken, value: token);
  }

  Future<String?> getToken() async {
    return _storage.read(key: _keyToken);
  }

  Future<void> deleteToken() async {
    await _storage.delete(key: _keyToken);
  }

  Future<void> saveUser(String userJson) async {
    await _storage.write(key: _keyUser, value: userJson);
  }

  Future<String?> getUser() async {
    return _storage.read(key: _keyUser);
  }

  Future<void> clear() async {
    await _storage.deleteAll();
  }
}
