# Time Manager – Frontend (Flutter)
## Description du projet

**Time Manager** est une application mobile développée avec Flutter, permettant aux employés d’une entreprise de gérer leurs heures de travail (arrivées, départs, pauses, etc.) et aux managers de suivre leurs équipes, consulter des KPI et générer des rapports de performance.

Ce frontend consomme une **API RESTful Spring Boot** et s’appuie sur une architecture modulaire, scalable et maintenable, selon les principes de la Clean Architecture et des bonnes pratiques DevOps.

## Stack technique

| Composant              | Technologie                                   | Description                                  |
| ---------------------- | --------------------------------------------- | -------------------------------------------- |
| **Framework mobile**   | [Flutter](https://flutter.dev/)               | Développement multiplateforme (Android/iOS)  |
| **Langage**            | Dart                                          | Langage moderne, typé et maintenable         |
| **Architecture**       | Clean Architecture (Domain/Data/Presentation) | Séparation claire des responsabilités        |
| **State Management**   | Bloc / Cubit                                  | Gestion robuste des états                    |
| **Dépendances**        | GetIt                                         | Injection de dépendances                     |
| **API Client**         | Dio                                           | Requêtes HTTP et gestion des erreurs         |
| **Modèles de données** | Freezed + JsonSerializable                    | Génération automatique et immutabilité       |
| **Routing**            | AutoRoute                                     | Navigation déclarative et maintenable        |
| **Stockage local**     | SharedPreferences / Hive                      | Sauvegarde légère et persistante             |
| **CI/CD**              | GitHub Actions                                | Tests, builds et analyse du code automatisés |
| **Docker (optionnel)** | Dockerfile + docker-compose                   | Build et exécution automatisée de l’app      |

## Structure du projet

L’architecture suit les principes de la **Clean Architecture** et du **modular design** :

lib/
├── core/              # Constantes, thèmes, widgets génériques
├── data/              # Accès aux données (API, stockage local)
├── domain/            # Entités, repositories et use cases
├── presentation/      # UI, gestion d’état et navigation
├── initialization/    # App initialization et environnement
├── application.dart   # Configuration globale de l'app
└── main.dart          # Point d'entrée


 Cette structure favorise la lisibilité, la testabilité et la scalabilité.

## Installation et exécution
### Prérequis

Assurez-vous d’avoir installé :

- Flutter SDK (≥ 3.22)

- Dart SDK

- Android Studio ou VS Code

- Un simulateur Android/iOS ou un téléphone physique connecté

### Étapes d’installation
#### Cloner le dépôt
git clone https://github.com/<ton_organisation>/time_manager.git
cd time_manager/frontend

#### Installer les dépendances
flutter pub get

#### Générer le code Freezed, JSON et les assets
flutter pub run build_runner build --delete-conflicting-outputs

#### Lancer l’application
flutter run

## Exécution avec Docker (optionnel)

Un Dockerfile est fourni pour automatiser le build de l’APK.

docker build -t time-manager-frontend .
docker run -p 8081:8081 time-manager-frontend

### Configuration de l’environnement

Le fichier **environment.dart** définit les environnements disponibles :

class Environment {
  static const String environment =
      String.fromEnvironment('env', defaultValue: 'dev');
}


Tu peux passer une variable d’environnement au build :

flutter run --dart-define=env=prod

## Fonctionnalités principales
Rôle	Fonctionnalités
Employé	Connexion / déconnexion, pointage d’arrivée/départ, consultation du temps de travail, édition du profil
Manager	Gestion des équipes, visualisation des KPI (ponctualité, heures moyennes, absences), validation des pointages
Commun	Authentification JWT, thèmes clair/sombre, notifications locales, UX accessible.

## Architecture et gestion d’état

Le projet utilise **Bloc/Cubit** pour la gestion des états :

UI (Screens) 
   ↓
Cubit (State Management)
   ↓
UseCases (Logique métier)
   ↓
Repositories (Interface)
   ↓
DataSources (API / Local)


Cela garantit une séparation nette entre logique métier et interface utilisateur.

## Génération automatique et outils

Ce projet exploite plusieurs générateurs de code :

| Outil              | Utilisation                                 |
| ------------------ | ------------------------------------------- |
| `Freezed`          | Génération des classes immuables et états   |
| `JsonSerializable` | Sérialisation/désérialisation JSON          |
| `FlutterGen`       | Références typées aux assets et fonts       |
| `Build Runner`     | Exécution des générateurs                   |
| `GetIt`            | Injection de dépendances propre et testable |

## Tests

Les tests unitaires et d’intégration sont exécutés via GitHub Actions.

### Lancer les tests localement :
flutter test

**Exemple de test Cubit :**
blocTest<AuthCubit, AuthState>(
  'Émet AuthSuccess quand les identifiants sont corrects',
  build: () => AuthCubit(MockAuthRepository()),
  act: (cubit) => cubit.login('email@test.com', 'password123'),
  expect: () => [AuthLoading(), isA<AuthSuccess>()],
);

## Accessibilité (A11y)

L’application est conçue pour être inclusive et accessible :

Utilisation de **Semantics()** pour les éléments interactifs

Contraste élevé et dark mode supporté

Textes adaptatifs (**MediaQuery.textScaleFactor**)

Navigation clavier et support des lecteurs d’écran

## Contribution

### Fork le projet

### Crée une branche :

**git checkout -b feature/ma-feature**


### Commits selon la norme :

**feat**: ajoute la gestion du profil utilisateur
**fix**: corrige l’erreur d’affichage du dashboard


### Crée une Pull Request vers develop

## Bonnes pratiques adoptées

**Clean Architecture** : séparation claire entre les couches

**Atomicité** : composants simples et réutilisables

**SRP** (Single Responsibility Principle) respecté

**Responsive design et accessibilité**

**CI/CD** : automatisation des builds et tests via GitHub Actions

**Gestion d’erreurs centralisée**(runZonedGuarded + AppInitializer)

## KPI suivis (via Backend)

- Taux de ponctualité

- Temps moyen de travail journalier

- Taux d’absentéisme

- Heures supplémentaires

- Taux d’utilisation de l’application

## Licence

Ce projet est sous licence MIT — libre d’utilisation et de modification avec attribution.

## Auteurs et contributeurs

### **Team NAN_2**

- **Maiva Magnifouet**
- **Quentin Faivret**
