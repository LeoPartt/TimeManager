import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:time_manager/domain/usecases/user/update_user_profile.dart';
import 'package:time_manager/presentation/cubits/user/user_cubit.dart';
import 'package:time_manager/presentation/cubits/user/user_state.dart';
import 'package:time_manager/core/constants/app_strings.dart';

@RoutePage()
class ProfileScreen extends StatefulWidget {
  const ProfileScreen({super.key});

  @override
  State<ProfileScreen> createState() => _ProfileScreenState();
}

class _ProfileScreenState extends State<ProfileScreen> {
  final nameCtrl = TextEditingController();
  final emailCtrl = TextEditingController();
  final phoneCtrl = TextEditingController();

  @override
  void initState() {
    super.initState();
    context.read<UserCubit>().loadProfile();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text(AppStrings.dashboard)),
      body: BlocBuilder<UserCubit, UserState>(
        builder: (context, state) {
          return state.when(
            initial: () => const Center(child: Text("No data yet")),
            loading: () => const Center(child: CircularProgressIndicator()),
            error: (msg) => Center(child: Text(msg)),
            loaded: (user) {
              nameCtrl.text = user.name;
              emailCtrl.text = user.email;
              phoneCtrl.text = user.phone ?? "";

              return Padding(
                padding: const EdgeInsets.all(24.0),
                child: Column(
                  children: [
                    TextField(
                      controller: nameCtrl,
                      decoration: const InputDecoration(labelText: "Name"),
                    ),
                    TextField(
                      controller: emailCtrl,
                      decoration: const InputDecoration(labelText: "Email"),
                    ),
                    TextField(
                      controller: phoneCtrl,
                      decoration: const InputDecoration(labelText: "Phone"),
                    ),
                    const SizedBox(height: 20),
                    ElevatedButton(
                      onPressed: () {
                        final params = UpdateUserProfileParams(
                          name: nameCtrl.text,
                          email: emailCtrl.text,
                          phone: phoneCtrl.text,
                        );
                        context.read<UserCubit>().updateProfile(params);
                      },
                      child: const Text("Save Changes"),
                    ),
                    const SizedBox(height: 10),
                    OutlinedButton(
                      onPressed: () =>
                          context.read<UserCubit>().removeAccount(),
                      child: const Text("Delete Account"),
                    ),
                  ],
                ),
              );
            },
          );
        },
      ),
    );
  }
}
