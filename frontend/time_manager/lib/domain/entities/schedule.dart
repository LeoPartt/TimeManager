class Clock {
  final int id;
  final DateTime? arrivalTs;
  final DateTime? departureTs;
  final int userId;

  const Clock({
    required this.id,
    this.arrivalTs,
    this.departureTs,
    required this.userId,
  });
}
