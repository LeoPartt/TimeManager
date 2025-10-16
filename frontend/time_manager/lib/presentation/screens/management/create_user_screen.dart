import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:time_manager/core/constants/app_sizes.dart';
import 'package:time_manager/core/constants/app_strings.dart';
import 'package:time_manager/core/utils/extensions/context_extensions.dart';
import 'package:time_manager/core/utils/validators.dart';
import 'package:time_manager/core/widgets/app_button.dart';
import 'package:time_manager/core/widgets/app_card.dart';
import 'package:time_manager/core/widgets/app_input_field.dart';
import 'package:time_manager/presentation/cubits/user/user_cubit.dart';
import 'package:time_manager/presentation/cubits/user/user_state.dart';
import 'package:time_manager/presentation/routes/app_router.dart';

@RoutePage()
class CreateUserScreen extends StatefulWidget {
  const CreateUserScreen({super.key});

  @override
  State<CreateUserScreen> createState() => _CreateUserScreenState();
}

class _CreateUserScreenState extends State<CreateUserScreen> {
  final _formKey = GlobalKey<FormState>();
  final _usernameController = TextEditingController();
  final _passwordController = TextEditingController();
  final _firstNameController = TextEditingController();
  final _lastNameController = TextEditingController();
  final _emailController = TextEditingController();
  final _phoneController = TextEditingController();

  @override
  void dispose() {
    _usernameController.dispose();
    _passwordController.dispose();
    _firstNameController.dispose();
    _lastNameController.dispose();
    _emailController.dispose();
    _phoneController.dispose();
    super.dispose();
  }

  void _onSubmit(BuildContext context) {
    if (_formKey.currentState!.validate()) {
      context.read<UserCubit>().createUser(
        username: _usernameController.text.trim(),
        password: _passwordController.text.trim(),
        firstName: _firstNameController.text.trim(),
        lastName: _lastNameController.text.trim(),
        email: _emailController.text.trim(),
        phoneNumber: _phoneController.text.trim(),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(AppStrings.registerTitle)),
      body: Padding(
        padding: const EdgeInsets.all(AppSizes.p24),
        child: BlocConsumer<UserCubit, UserState>(
          listener: (context, state) {
            state.whenOrNull(
              loaded: (user) {context.showSnack(
                "✅ User ${user.username} created successfully!",
              );
              context.router.push(ManagementRoute());
              },
              error: (message) => context.showSnack(message, isError: true),
            );
          },
          builder: (context, state) {
            final isLoading = state is UserLoading;
            return Stack(
              children: [
                Center(
                  child: AppCard(
                    padding: const EdgeInsets.all(AppSizes.p24),

                    child: Form(
                      key: _formKey,
                      child: ListView(
                        children: [
                          AppInputField(
                            label: AppStrings.userNameLabel,
                            controller: _usernameController,
                            keyboardType: TextInputType.name,
                            icon: Icons.person,
                            textInputAction: TextInputAction.next,
                          ),
                          const SizedBox(height: AppSizes.p16),
                          AppInputField(
                            label: AppStrings.passwordLabel,
                            controller: _passwordController,
                            obscureText: true,
                            textInputAction: TextInputAction.next,
                            keyboardType: TextInputType.text,
                                icon: Icons.lock_outline,

                            validator: Validators.validatePassword,
                          ),
                          const SizedBox(height: AppSizes.p16),
                          AppInputField(
                                                        textInputAction: TextInputAction.next,
                            icon: Icons.person,

                            label: AppStrings.firstNameLabel,
                            controller: _firstNameController,
                          ),
                          const SizedBox(height: AppSizes.p16),
                          AppInputField(
                                                        icon: Icons.person,

                            label: AppStrings.lastNameLabel,
                            controller: _lastNameController,
                                  textInputAction: TextInputAction.next,
                            keyboardType: TextInputType.name,
                          ),
                          const SizedBox(height: AppSizes.p16),
                          AppInputField(
                            icon: Icons.email_outlined,
                            label: AppStrings.emailLabel,
                            controller: _emailController,
                                  textInputAction: TextInputAction.next,
                            keyboardType: TextInputType.emailAddress,
                            validator: Validators.validateEmail,
                          ),
                          const SizedBox(height: AppSizes.p16),
                          AppInputField(
                            label: AppStrings.phoneNumberLabel,
                            controller: _phoneController,
                            icon: Icons.phone_outlined,
                                   textInputAction: TextInputAction.done,
                            keyboardType: TextInputType.number,
                          ),
                          const SizedBox(height: AppSizes.p24),
                          AppButton(
                            label: "Create User",
                            fullSize: true,
                            isLoading: isLoading,
                            onPressed: () => _onSubmit(context),
                          ),
                        ],
                      ),
                    ),
                  ),
                ),
              ],
            );
          },
        ),
      ),
    );
  }
}
