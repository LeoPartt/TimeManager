import 'package:time_manager/domain/entities/schedule.dart';

abstract class ClockRepository {
  Future<Clock> clockIn();
  Future<Clock> clockOut();
  Future<Clock?> getClockStatus();
}
