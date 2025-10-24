import 'package:flutter/material.dart';
import 'package:fl_chart/fl_chart.dart';

class DashboardChart extends StatelessWidget {
  const DashboardChart({super.key});

  @override
  Widget build(BuildContext context) {
    final colorScheme = Theme.of(context).colorScheme;

    return SizedBox(
      height: 200,
      child: BarChart(
        BarChartData(
          alignment: BarChartAlignment.spaceAround,
          barGroups: [
            BarChartGroupData(x: 0, barRods: [
              BarChartRodData(
                toY: 0.85,
                color: colorScheme.primary,
                width: 20,
                borderRadius: BorderRadius.circular(4),
              ),
            ]),
            BarChartGroupData(x: 1, barRods: [
              BarChartRodData(
                toY: 0.95,
                color: colorScheme.secondary,
                width: 20,
                borderRadius: BorderRadius.circular(4),
              ),
            ]),
          ],
          titlesData: FlTitlesData(
            bottomTitles: AxisTitles(
              sideTitles: SideTitles(
                showTitles: true,
                getTitlesWidget: (value, _) {
                  switch (value.toInt()) {
                    case 0:
                      return const Text("Punctuality");
                    case 1:
                      return const Text("Attendance");
                    default:
                      return const SizedBox.shrink();
                  }
                },
              ),
            ),
          ),
        ),
      ),
    );
  }
}
