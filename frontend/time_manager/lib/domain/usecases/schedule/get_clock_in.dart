
import 'package:time_manager/domain/entities/schedule.dart';
import 'package:time_manager/domain/repositories/schedule_repository.dart';

class ClockIn {
  final ClockRepository repository;
  ClockIn(this.repository);

  Future<Clock> call() => repository.clockIn();
}
