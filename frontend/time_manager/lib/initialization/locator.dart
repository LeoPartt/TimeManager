import 'package:get_it/get_it.dart';

import '../presentation/routes/app_router.dart';

final GetIt locator = GetIt.instance;

/// Dependency injection setup using GetIt.
Future<void> setupLocator() async {
  // Services
 // locator.registerLazySingleton(() => HttpClientService());

  // APIs
 // locator.registerLazySingleton(() => UserApi(locator<HttpClientService>()));

  // Repositories
  //locator.registerLazySingleton<UserRepository>(() => UserRepositoryImpl(locator<UserApi>()));

  // Router
  locator.registerLazySingleton(() => AppRouter());
}
