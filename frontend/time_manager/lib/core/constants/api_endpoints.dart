
import 'package:time_manager/initialization/environment.dart';

/// Centralized definition of all backend API endpoints.
class ApiEndpoints {
  // ───────────────────────────────
  //  Base URL (comes from Environment)
  // ───────────────────────────────
  static String get base => Environment.baseUrl;

  // ───────────────────────────────
  //  Root API prefix
  // ───────────────────────────────
  static const String _api = '/api';

  // ───────────────────────────────
  //  AUTH endpoints
  // ───────────────────────────────
  static String get login => '$_api/auth/login';
  static String get register => '$_api/auth/register';
  static String get logout => '$_api/auth/logout';
  static String get refresh => '$_api/auth/refresh';

  // ───────────────────────────────
  //  USER endpoints
  // ───────────────────────────────
  static String get users => '$_api/users';
  static String get userProfile => '$_api/users/me';
  static String userById(int id) => '$_api/users/$id';
  static String get updateProfile => '$_api/users';

  // ───────────────────────────────
  //  TEAMS endpoints
  // ───────────────────────────────
  static String get teams => '$_api/teams';
  static String teamById(int id) => '$_api/teams/$id';
  static String get createTeam => '$_api/teams/create';

  // ───────────────────────────────
  //  MEMBERSHIPS endpoints
  // ───────────────────────────────
  static String get memberships => '$_api/memberships';
  static String membershipById(int id) => '$_api/memberships/$id';

  // ───────────────────────────────
  //  SCHEDULE endpoints
  // ───────────────────────────────
  static String get schedules => '$_api/clocks';
  static String scheduleById(int id) => '$_api/schedules/$id';
  // static String get clockIn => '$_api/clocks';
  // static String get clockOut => '$_api/clocks';
  static String get history => '$_api/schedules/history';
   
  static String get clockStatus => '$_api/clocks/status';

  // ───────────────────────────────
  // REPORTS / KPI endpoints
  // ───────────────────────────────
  static String get reports => '$_api/reports';
  static String get kpis => '$_api/reports/kpis';
}
