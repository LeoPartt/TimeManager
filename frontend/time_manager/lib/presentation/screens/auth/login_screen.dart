import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:time_manager/core/constants/app_strings.dart';
import 'package:time_manager/presentation/cubits/account/auth_cubit.dart';
import 'package:time_manager/presentation/cubits/account/auth_state.dart';

@RoutePage()
class LoginScreen extends StatefulWidget {
  const LoginScreen({super.key});

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  final emailController = TextEditingController();
  final passwordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: BlocConsumer<AuthCubit, AuthState>(
        listener: (context, state) {
          if (state is AuthError) {
            ScaffoldMessenger.of(context)
                .showSnackBar(SnackBar(content: Text(state.message)));
          }
        },
        builder: (context, state) {
          if (state is AuthLoading) {
            return const Center(child: CircularProgressIndicator());
          }
          return Padding(
            padding: const EdgeInsets.all(24),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(AppStrings.loginTitle,
                    style: Theme.of(context).textTheme.headlineMedium),
                const SizedBox(height: 20),
                TextField(
                  controller: emailController,
                  decoration:
                      const InputDecoration(labelText: AppStrings.emailLabel),
                ),
                const SizedBox(height: 12),
                TextField(
                  controller: passwordController,
                  obscureText: true,
                  decoration:
                      const InputDecoration(labelText: AppStrings.passwordLabel),
                ),
                const SizedBox(height: 20),
                ElevatedButton(
                  onPressed: () {
                    context
                        .read<AuthCubit>()
                        .login(emailController.text, passwordController.text);
                  },
                  child: const Text(AppStrings.loginButton),
                ),
              ],
            ),
          );
        },
      ),
    );
  }
}
