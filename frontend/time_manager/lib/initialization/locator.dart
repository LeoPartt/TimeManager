import 'package:get_it/get_it.dart';
import 'package:time_manager/data/datasources/local/local_storage_service.dart';
import 'package:time_manager/data/datasources/remote/account_api.dart';
import 'package:time_manager/data/datasources/remote/user_api.dart';
import 'package:time_manager/data/repositories_impl/account_repository_impl.dart';
import 'package:time_manager/data/repositories_impl/user_repository_impl.dart';
import 'package:time_manager/data/services/auth_header_service.dart';
import 'package:time_manager/data/services/http_client.dart';
import 'package:time_manager/domain/repositories/account_repository.dart';
import 'package:time_manager/domain/repositories/user_repository.dart';
import 'package:time_manager/domain/usecases/account/login_user.dart';
import 'package:time_manager/domain/usecases/account/logout_user.dart';
import 'package:time_manager/domain/usecases/account/register_account.dart';
import 'package:time_manager/domain/usecases/user/create_user.dart';
import 'package:time_manager/domain/usecases/user/delete_user.dart';
import 'package:time_manager/domain/usecases/user/get_user_profile.dart';
import 'package:time_manager/domain/usecases/user/update_user_profile.dart';
import 'package:time_manager/presentation/cubits/account/auth_cubit.dart';
import 'package:time_manager/presentation/cubits/user/user_cubit.dart';

import '../presentation/routes/app_router.dart';

final GetIt locator = GetIt.instance;

/// Dependency injection setup using GetIt.
Future<void> setupLocator() async {
  // ─────────────────────────────────────────────
  // CORE SERVICES
  // ─────────────────────────────────────────────
  locator.registerLazySingleton<LocalStorageService>(
    () => LocalStorageService(),
  );
  locator.registerLazySingleton<AuthHeaderService>(
    () => AuthHeaderService(locator()),
  );
  locator.registerLazySingleton<ApiClient>(
    () => ApiClient(authHeaderService: locator<AuthHeaderService>()),
  );

  // ─────────────────────────────────────────────
  // ACCOUNT FEATURE
  // ─────────────────────────────────────────────
  locator.registerLazySingleton<AccountApi>(
    () => AccountApi(locator<ApiClient>()),
  );
  locator.registerLazySingleton<AccountRepository>(
    () => AccountRepositoryImpl(
      api: locator<AccountApi>(),
      storage: locator<LocalStorageService>(),
    ),
  );

  locator.registerFactory(() => LoginUser(locator<AccountRepository>()));
  locator.registerFactory(() => RegisterUser(locator<AccountRepository>()));
  locator.registerFactory(() => LogoutUser(locator<AccountRepository>()));

  locator.registerFactory(
    () => AuthCubit(
      loginUser: locator<LoginUser>(),
      registerUser: locator<RegisterUser>(),
      logoutUser: locator<LogoutUser>(),
    ),
  );

  // ─────────────────────────────────────────────
  // USER FEATURE
  // ─────────────────────────────────────────────
  locator.registerLazySingleton<UserApi>(() => UserApi(locator<ApiClient>()));
  locator.registerLazySingleton<UserRepository>(
    () => UserRepositoryImpl(
      api: locator<UserApi>(),
      storage: locator<LocalStorageService>(),
    ),
  );

  locator.registerFactory(() => GetUserProfile(locator<UserRepository>()));
  locator.registerFactory(() => UpdateUserProfile(locator<UserRepository>()));
  locator.registerFactory(() => DeleteUser(locator<UserRepository>()));
  locator.registerFactory(() => CreateUser(locator<UserRepository>()));

  locator.registerFactory(
    () => UserCubit(
      getUserProfile: locator<GetUserProfile>(),
      updateUserProfile: locator<UpdateUserProfile>(),
      deleteUser: locator<DeleteUser>(), createUserUsecase: locator<CreateUser>(),
    ),
  );

  // Router
  locator.registerLazySingleton(() => AppRouter());
}
