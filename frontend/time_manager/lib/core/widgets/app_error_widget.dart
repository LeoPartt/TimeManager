import 'package:flutter/material.dart';
import '../constants/app_sizes.dart';

class AppErrorWidget extends StatelessWidget {
  final String message;
  final VoidCallback? onRetry;
  final String retryLabel;
  final IconData icon;

  const AppErrorWidget({
    super.key,
    required this.message,
    this.onRetry,
    this.retryLabel = "Retry",
    this.icon = Icons.error_outline,
  });

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    return Center(
      child: Padding(
        padding: EdgeInsets.all(
          AppSizes.responsiveWidth(context, AppSizes.p24),
        ),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Icon(
              icon,
              size: AppSizes.responsiveWidth(context, 64),
              color: theme.colorScheme.error,
            ),
            SizedBox(height: AppSizes.responsiveHeight(context, 16)),
            Text(
              message,
              textAlign: TextAlign.center,
              style: theme.textTheme.bodyLarge?.copyWith(
                fontSize: AppSizes.responsiveText(context, AppSizes.textLg),
                color: theme.colorScheme.onSurface,
                fontWeight: FontWeight.w600,
              ),
            ),
            SizedBox(height: AppSizes.responsiveHeight(context, 20)),
            if (onRetry != null)
              ElevatedButton.icon(
                onPressed: onRetry,
                icon: const Icon(Icons.refresh),
                label: Text(
                  retryLabel,
                  style: theme.textTheme.labelLarge?.copyWith(
                    fontSize: AppSizes.responsiveText(context, AppSizes.textMd),
                  ),
                ),
              ),
          ],
        ),
      ),
    );
  }
}
