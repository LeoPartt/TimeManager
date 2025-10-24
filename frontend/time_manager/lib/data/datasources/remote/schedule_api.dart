import 'package:time_manager/data/services/http_client.dart';
import 'package:time_manager/core/constants/api_endpoints.dart';

class ClockApi {
  final ApiClient client;
  ClockApi(this.client);

  Future<Map<String, dynamic>> clockIn() async =>
      client.post(ApiEndpoints.clockIn, {});

  Future<Map<String, dynamic>> clockOut() async =>
      client.post(ApiEndpoints.clockOut, {});

  Future<dynamic> getClockStatus() async =>
      client.get(ApiEndpoints.clockStatus);
}
