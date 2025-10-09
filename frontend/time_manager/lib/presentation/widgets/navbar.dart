import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:time_manager/core/constants/app_colors.dart';
import 'package:time_manager/presentation/cubits/navigation/navbar_cubit.dart';
import 'package:time_manager/presentation/cubits/navigation/navbar_state.dart';

class NavBar extends StatelessWidget {
  const NavBar({super.key});

  @override
  Widget build(BuildContext context) {
    final size = MediaQuery.sizeOf(context);
    final icons = const [
      Icons.bar_chart_rounded,
      Icons.work_history_rounded,
      Icons.group_rounded,
      Icons.person_rounded,
    ];

    return Container(
      margin: EdgeInsets.all(size.height * 0.02),
      padding: EdgeInsets.symmetric(vertical: size.height * 0.01),
      decoration: BoxDecoration(
        color: AppColors.accent,
        borderRadius: BorderRadius.circular(20),
      ),
      child: BlocBuilder<NavCubit, NavState>(
        builder: (context, state) {
          return Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: List.generate(icons.length, (index) {
              final isSelected = index == state.index;
              return IconButton(
                onPressed: () => context.read<NavCubit>().changeTab(index),
                icon: Icon(
                  icons[index],
                  size: size.width * 0.08,
                  color: isSelected ? AppColors.primary : AppColors.backgroundDark,
                ),
                
              );
            }),
          );
        },
      ),
    );
  }
}
