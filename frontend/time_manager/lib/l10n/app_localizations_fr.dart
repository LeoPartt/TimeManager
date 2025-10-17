// ignore: unused_import
import 'package:intl/intl.dart' as intl;
import 'app_localizations.dart';

// ignore_for_file: type=lint

/// The translations for French (`fr`).
class AppLocalizationsFr extends AppLocalizations {
  AppLocalizationsFr([String locale = 'fr']) : super(locale);

  @override
  String get dashboard => 'Tableau de bord';

  @override
  String get login => 'Connexion';

  @override
  String get logout => 'Déconnexion';

  @override
  String get settings => 'PARAMÈTRES';

  @override
  String get darkMode => 'Mode sombre';

  @override
  String get currentThemeDark => 'Thème actuel : sombre';

  @override
  String get currentThemeLight => 'Thème actuel : clair';

  @override
  String get language => 'Langue';

  @override
  String get english => 'Anglais';

  @override
  String get french => 'Français';

  @override
  String get me => 'MOI';

  @override
  String get modify => 'Modifier';

  @override
  String get successful => 'avec succés';

  @override
  String get delete => 'Supprimer';

  @override
  String get clockin => 'POINTAGE ARRIVÉE';

  @override
  String get clockout => 'POINTAGE SORTIE';

  @override
  String get validate => 'Valider';

  @override
  String get arrival => 'Heure d\'arrivée';

  @override
  String get registerTitle => 'Créer un utilisateur';

  @override
  String get welcome => 'Bienvenue';

  @override
  String get teams => 'Équipes';

  @override
  String get teamDetails => 'Détails de l\'équipe';

  @override
  String get reports => 'Rapports';

  @override
  String get kpiTitle => 'Indicateurs de performance';

  @override
  String get loading => 'Chargement...';

  @override
  String get error => 'Une erreur est survenue';

  @override
  String get emailLabel => 'Adresse e-mail';

  @override
  String get passwordLabel => 'Mot de passe';

  @override
  String get userNameLabel => 'Nom d\'utilisateur';

  @override
  String get firstNameLabel => 'Prénom';

  @override
  String get phoneNumberLabel => 'Numéro de téléphone';

  @override
  String get lastNameLabel => 'Nom';

  @override
  String get loginButton => 'Se connecter';

  @override
  String get registerButton => 'Créer';

  @override
  String get emailRequired => 'L\'adresse e-mail est obligatoire';

  @override
  String get invalidEmail => 'Adresse e-mail invalide';

  @override
  String get passwordRequired => 'Le mot de passe est obligatoire';

  @override
  String get shortPassword => 'Le mot de passe doit comporter au moins 6 caractères';

  @override
  String get fieldRequired => 'Le champ ne peut pas être vide';

  @override
  String get departure => 'Heure de départ';

  @override
  String get badRequest => 'Requête invalide (400)';

  @override
  String get unauthorized => 'Accès non autorisé (401)';

  @override
  String get notFound => 'Ressource non trouvée (404)';

  @override
  String get serverError => 'Erreur du serveur (500)';

  @override
  String get forgotPassword => 'Mot de passe oublié ?';

  @override
  String get management => 'GESTION';

  @override
  String get addanewuser => 'Nouvel utilisateur';

  @override
  String get addanewteam => 'Nouvelle équipe';

  @override
  String get searchbarhint => 'Utilisateur ou équipe...';

  @override
  String networkError(int code) {
    return 'Erreur réseau ($code)';
  }

  @override
  String fieldIsRequired(String field) {
    return 'Le champ \'$field\' est obligatoire.';
  }
}
