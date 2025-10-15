import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:time_manager/core/constants/app_colors.dart';
import 'package:time_manager/core/constants/app_sizes.dart';
import 'package:time_manager/core/utils/extensions/context_extensions.dart';
import 'package:time_manager/core/widgets/app_avatars.dart';
import 'package:time_manager/core/widgets/app_button.dart';
import 'package:time_manager/core/widgets/app_label_container.dart';
import 'package:time_manager/l10n/app_localizations.dart';
import 'package:time_manager/presentation/cubits/user/user_cubit.dart';
import 'package:time_manager/presentation/cubits/user/user_state.dart';
import 'package:time_manager/presentation/routes/app_router.dart';
import 'package:time_manager/presentation/widgets/header.dart';
import 'package:time_manager/presentation/widgets/navbar.dart';
import 'package:time_manager/core/widgets/app_loader.dart';

@RoutePage()
class UserScreen extends StatefulWidget {
  const UserScreen({super.key});

  @override
  State<UserScreen> createState() => _UserScreenState();
}

class _UserScreenState extends State<UserScreen> {
  @override
  void initState() {
    super.initState();
    // Charge le profil de l'utilisateur connecté (ID extrait du token ou stockage local)
    context.read<UserCubit>().getUser(1); // TODO: remplacer 1 par le vrai ID
  }

  @override
  Widget build(BuildContext context) {
    final tr = AppLocalizations.of(context)!;

    return Scaffold(
      bottomNavigationBar: const NavBar(),
      body: SafeArea(
        child: BlocConsumer<UserCubit, UserState>(
          listener: (context, state) {
            state.whenOrNull(
              deleted: () {
                context.showSnack("✅ User deleted successfully!");
                context.router.pop();
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
              final isPrivileged = user.role == 'ADMIN' || user.role == 'MANAGER';

              return ListView(
                children: [
                  Column(
                    children: [
                      Header(label: tr.me),
                      SizedBox(
                        height: AppSizes.responsiveHeight(context, AppSizes.p32),
                      ),
                      Center(
                        child: Container(
                          width: AppSizes.responsiveWidth(
                            context,
                            0.9 * MediaQuery.of(context).size.width,
                          ),
                          padding: EdgeInsets.symmetric(
                            horizontal: AppSizes.responsiveWidth(
                                context, 0.06 * MediaQuery.of(context).size.width),
                            vertical: AppSizes.responsiveHeight(
                                context, 0.03 * MediaQuery.of(context).size.height),
                          ),
                          decoration: BoxDecoration(
                            color: AppColors.accent,
                            borderRadius: BorderRadius.circular(28),
                            boxShadow: const [
                              BoxShadow(
                                color: Color.fromRGBO(0, 0, 0, 0.25),
                                offset: Offset(0, 4),
                                blurRadius: 12,
                              ),
                            ],
                          ),
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.stretch,
                            mainAxisSize: MainAxisSize.min,
                            children: [
                                AppAvatar(
                                   radius: 40,
                                fallbackIcon: Icons.person_outline_rounded,
                              
                                ),
                              const SizedBox(height: AppSizes.p20),

                              // Champs utilisateur
                              AppLabelContainer(
                                  label: "First name: ${user.firstName}",
                                  fullSize: true),
                              const SizedBox(height: 10),
                              AppLabelContainer(
                                  label: "Last name: ${user.lastName}",
                                  fullSize: true),
                              const SizedBox(height: 10),
                              AppLabelContainer(
                                  label: "Email: ${user.email}", fullSize: true),
                              const SizedBox(height: 10),
                              AppLabelContainer(
                                  label: "Phone: ${user.phoneNumber ?? '-'}",
                                  fullSize: true),
                              const SizedBox(height: 20),

                              // Boutons d’action
                              Row(
                                children: [
                                  Expanded(
                                    child: AppButton(
                                      label: tr.modify,
                                      fullSize: true,
                                      onPressed: () => context.pushRoute(
                                        UserEditRoute(userId: user.id),
                                      ),
                                    ),
                                  ),

                                  // ✅ On affiche le bouton Delete seulement pour admin/manager
                                  if (isPrivileged) ...[
                                    SizedBox(
                                      width: AppSizes.responsiveWidth(
                                          context,
                                          0.04 *
                                              MediaQuery.of(context).size.width),
                                    ),
                                    Expanded(
                                      child: AppButton(
                                        label: tr.delete,
                                        fullSize: true,
                                        isLoading: state is UserLoading,
                                        onPressed: () => context
                                            .read<UserCubit>()
                                            .deleteUser(user.id),
                                      ),
                                    ),
                                  ],
                                ],
                              ),
                            ],
                          ),
                        ),
                      ),
                    ],
                  ),
                ],
              );
            }

        

            return const SizedBox.shrink();
          },
        ),
      ),
    );
  }
}
