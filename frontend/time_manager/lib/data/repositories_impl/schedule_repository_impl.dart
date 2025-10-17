

import 'package:time_manager/data/datasources/remote/schedule_api.dart';
import 'package:time_manager/data/models/schedule_model.dart';
import 'package:time_manager/domain/entities/schedule.dart';
import 'package:time_manager/domain/repositories/schedule_repository.dart';

class ClockRepositoryImpl implements ClockRepository {
  final ClockApi api;
  ClockRepositoryImpl(this.api);

  @override
  Future<void> clockIn(DateTime timestamp) async {
    //final res = await api.clockIn(timestamp);
   await api.clockIn(timestamp);
    // ClockModel.fromJson(res).toDomain();
  }

  @override
  Future<void> clockOut(DateTime timestamp) async {
    //final res = await api.clockOut(timestamp);
    await api.clockOut(timestamp);
   // return ClockModel.fromJson(res).toDomain();
  }

  @override
  Future<Clock?> getClockStatus() async {
    final res = await api.getClockStatus();
    return ClockModel.fromJson(res).toDomain();
  }
}
