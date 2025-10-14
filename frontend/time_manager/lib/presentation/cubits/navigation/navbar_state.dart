class NavState {
  final int index;

  const NavState({required this.index});

  NavState copyWith({int? index}) => NavState(index: index ?? this.index);

  List<Object> get props => [index];
}
