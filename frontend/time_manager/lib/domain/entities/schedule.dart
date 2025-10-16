class Clock {
  final int id;
  final DateTime? arrivalTs;
  final DateTime? departureTs;

  const Clock({
    required this.id,
    required this.arrivalTs,
    this.departureTs,
  });

    bool get isClockedIn => departureTs == null;

}
