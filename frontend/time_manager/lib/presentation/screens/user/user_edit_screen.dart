import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:time_manager/core/constants/app_sizes.dart';
import 'package:time_manager/core/utils/extensions/context_extensions.dart';
import 'package:time_manager/core/widgets/app_button.dart';
import 'package:time_manager/core/widgets/app_input_field.dart';
import 'package:time_manager/core/widgets/app_loader.dart';
import 'package:time_manager/domain/usecases/user/update_user_profile.dart';
import 'package:time_manager/presentation/cubits/user/user_cubit.dart';
import 'package:time_manager/presentation/cubits/user/user_state.dart';
import 'package:time_manager/presentation/routes/app_router.dart';

@RoutePage()
class UserEditScreen extends StatefulWidget {
  final int userId;
  const UserEditScreen({super.key, required this.userId});

  @override
  State<UserEditScreen> createState() => _UserEditScreenState();
}

class _UserEditScreenState extends State<UserEditScreen> {
  final _formKey = GlobalKey<FormState>();
  final _usernameController = TextEditingController();
  final _firstNameController = TextEditingController();
  final _lastNameController = TextEditingController();
  final _emailController = TextEditingController();
  final _phoneController = TextEditingController();

  @override
  void initState() {
    super.initState();
    context.read<UserCubit>().getUser(widget.userId);
  }

  @override
  void dispose() {
    _usernameController.dispose();
    _firstNameController.dispose();
    _lastNameController.dispose();
    _emailController.dispose();
    _phoneController.dispose();
    super.dispose();
  }

  void _onSubmit(BuildContext context) {
    if (_formKey.currentState!.validate()) {
      context.read<UserCubit>().updateProfile(
            UpdateUserProfileParams(
              id: widget.userId,
              username: _usernameController.text.trim(),
              firstName: _firstNameController.text.trim(),
              lastName: _lastNameController.text.trim(),
              email: _emailController.text.trim(),
              phoneNumber: _phoneController.text.trim(),
            ),
          );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Edit User")),
      body: BlocConsumer<UserCubit, UserState>(
        listener: (context, state) {
          state.whenOrNull(
            
              updated: (user) {
              context.showSnack("✅ User updated successfully!");
              context.router.push(UserRoute());
            },
            error: (msg) => context.showSnack(msg, isError: true),
          );
        },
        builder: (context, state) {
          if (state is UserLoading) {
            return const Center(child: AppLoader());
          }

          if (state is UserLoaded) {
            final user = state.user;
            _usernameController.text = user.username;
            _firstNameController.text = user.firstName;
            _lastNameController.text = user.lastName;
            _emailController.text = user.email;
            _phoneController.text = user.phoneNumber ?? '';

            return Padding(
              padding: const EdgeInsets.all(AppSizes.p24),
              child: Form(
                key: _formKey,
                child: Column(
                  children: [
                    AppInputField(
                      label: "Username",
                      controller: _usernameController,
                    ),
                    const SizedBox(height: AppSizes.p16),
                    AppInputField(
                      label: "First Name",
                      controller: _firstNameController,
                    ),
                    const SizedBox(height: AppSizes.p16),
                    AppInputField(
                      label: "Last Name",
                      controller: _lastNameController,
                    ),
                    const SizedBox(height: AppSizes.p16),
                    AppInputField(
                      label: "Email",
                      controller: _emailController,
                      keyboardType: TextInputType.emailAddress,
                    ),
                    const SizedBox(height: AppSizes.p16),
                    AppInputField(
                      label: "Phone Number",
                      controller: _phoneController,
                      keyboardType: TextInputType.phone,
                    ),
                    const SizedBox(height: AppSizes.p24),

                    AppButton(
                      label: "Save Changes",
                      fullSize: true,
                      isLoading: state is UserLoading,
                      onPressed: () => _onSubmit(context),
                    ),
                  ],
                ),
              ),
            );
          }

          return const SizedBox.shrink();
        },
      ),
    );
  }
}
