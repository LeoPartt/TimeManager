

import 'package:time_manager/domain/entities/schedule.dart';
import 'package:time_manager/domain/repositories/schedule_repository.dart';

class ClockOut {
  final ClockRepository repository;
  ClockOut(this.repository);

  Future<Clock> call() => repository.clockOut();
}
