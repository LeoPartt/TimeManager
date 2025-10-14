import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:time_manager/core/constants/app_strings.dart';
import 'package:time_manager/core/constants/app_sizes.dart';
//import 'package:time_manager/core/utils/validators.dart';
import 'package:time_manager/core/utils/extensions/context_extensions.dart';
import 'package:time_manager/core/widgets/app_avatars.dart';
import 'package:time_manager/presentation/cubits/account/auth_cubit.dart';
import 'package:time_manager/presentation/cubits/account/auth_state.dart';
import 'package:time_manager/core/widgets/app_loader.dart';
import 'package:time_manager/core/widgets/app_input_field.dart';
import 'package:time_manager/core/widgets/app_card.dart';
import 'package:time_manager/presentation/routes/app_router.dart';

@RoutePage()
class LoginScreen extends StatefulWidget {
  const LoginScreen({super.key});

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  final _formKey = GlobalKey<FormState>();
  final _usernameController = TextEditingController();
  final _passwordController = TextEditingController();

  @override
  void dispose() {
    _usernameController.dispose();
    _passwordController.dispose();
    super.dispose();
  }

  void _onLoginPressed(BuildContext context) {
    if (_formKey.currentState?.validate() ?? false) {
      context.read<AuthCubit>().login(
        username: _usernameController.text.trim(),
        password: _passwordController.text.trim(),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    final colorScheme = theme.colorScheme;

    return Scaffold(
      body: BlocConsumer<AuthCubit, AuthState>(
        listener: (context, state) {
          state.maybeWhen(
            error: (message) => context.showSnack(message, isError: true),
            authenticated: (_){context.showSnack("Login successful!");
            context.pushRoute(HomeRoute());} ,
            orElse: () {},
          );
        },
        builder: (context, state) {
          return Stack(
            children: [
              Center(
                child: SingleChildScrollView(
                  padding: const EdgeInsets.all(AppSizes.p24),
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      // ──────────── Card ────────────
                      AppCard(
                        padding: const EdgeInsets.all(AppSizes.p24),
                        child: Form(
                          key: _formKey,
                          child: Column(
                            mainAxisSize: MainAxisSize.min,
                            children: [
                              // ──────────── Icon ────────────
                              AppAvatar(
                                radius: 40,
                                fallbackIcon: Icons.person_outline_rounded,
                              ),

                              const SizedBox(height: AppSizes.p24),

                              // ──────────── Username ────────────
                              AppInputField(
                                controller: _usernameController,
                                label: AppStrings.userNameLabel,
                                icon: Icons.person,
                                keyboardType: TextInputType.name,

                                textInputAction: TextInputAction.next,
                              ),
                              const SizedBox(height: AppSizes.p16),

                              // ──────────── Password ────────────
                              AppInputField(
                                controller: _passwordController,
                                label: AppStrings.passwordLabel,
                                icon: Icons.lock_outline,
                                obscureText: true,
                                //validator: Validators.validatePassword,
                                textInputAction: TextInputAction.done,
                                onSubmitted: () => _onLoginPressed(context),
                              ),
                              const SizedBox(height: AppSizes.p24),

                              // ──────────── Button ────────────
                              SizedBox(
                                width: double.infinity,
                                child: ElevatedButton(
                                  onPressed: state.maybeWhen(
                                    loading: () => null,
                                    orElse: () =>
                                        () => _onLoginPressed(context),
                                  ),
                                  style: ElevatedButton.styleFrom(
                                    backgroundColor: colorScheme.primary,
                                    padding: const EdgeInsets.symmetric(
                                      vertical: 14,
                                    ),
                                    shape: RoundedRectangleBorder(
                                      borderRadius: BorderRadius.circular(
                                        AppSizes.r8,
                                      ),
                                    ),
                                  ),
                                  child: Text(
                                    AppStrings.loginButton,
                                    style: Theme.of(context)
                                        .textTheme
                                        .titleMedium
                                        ?.copyWith(
                                          color: colorScheme.onPrimary,
                                          fontWeight: FontWeight.bold,
                                        ),
                                  ),
                                ),
                              ),
                            ],
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ),

              // ──────────── Loader ────────────
              if (state is AuthLoading) const Center(child: AppLoader()),
            ],
          );
        },
      ),
    );
  }
}
