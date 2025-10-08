import 'package:flutter/material.dart';

class NavBar extends StatelessWidget {
  final int selectedIndex;
  final Function(int) onItemTapped;

  const NavBar({
    super.key,
    required this.selectedIndex,
    required this.onItemTapped,
  });

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.sizeOf(context);
    final icons = [
      Icons.bar_chart_rounded,
      Icons.work_history_rounded,
      Icons.group_rounded,
      Icons.person_rounded,
    ];

    return Container(
      margin: EdgeInsets.all(size.height * 0.02),
      padding: EdgeInsets.symmetric(vertical: size.height * 0.01),
      decoration: BoxDecoration(
        color: const Color(0xFF768191),
        borderRadius: BorderRadius.circular(20),
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: List.generate(icons.length, (index) {
          final bool isSelected = index == selectedIndex;
          return IconButton(
            onPressed: () => onItemTapped(index),
            icon: Icon(
              icons[index],
              size: size.width * 0.08,
              color: isSelected
                  ? const Color(0xFF0088FF)
                  : Colors.black,
            ),
          );
        }),
      ),
    );
  }
}
