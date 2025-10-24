# Production & Release Guide

## Release Workflow

```
1. Update version in frontend/time_manager/pubspec.yaml
2. Create PR develop → main
3. Merge to main
   ↓ (automatic)
4. Backend Docker image published to GHCR
5. Tag created (vX.Y.Z)
6. APK built and published to GitHub Releases
```

## Semantic Versioning

Follow [semver](https://semver.org/): `MAJOR.MINOR.PATCH`

- **MAJOR**: Breaking changes (1.0.0 → 2.0.0)
- **MINOR**: New features (0.1.0 → 0.2.0)
- **PATCH**: Bug fixes (0.1.0 → 0.1.1)

## Creating a Release

1. Update version in `frontend/time_manager/pubspec.yaml`:
```yaml
version: 0.2.0
```

1. Commit and push:
```bash
git add frontend/time_manager/pubspec.yaml
git commit -m "chore: bump version to 0.2.0"
git push origin develop
```

1. Create PR `develop → main` and merge

The pipeline will:
- Build and publish backend Docker image to GHCR
- Validate version is greater than latest
- Create tag `v0.2.0`
- Build APK
- Publish to GitHub Releases as `time-manager-0.2.0.apk`

## Server Deployment

The backend Docker image is **automatically published to GitHub Container Registry** on every push to `main`.

No need to clone the repository or build locally!

### Setup

```bash
# Create project directory
mkdir -p ~/time_manager && cd ~/time_manager

# Download configuration files
curl -O https://raw.githubusercontent.com/LeoPartt/time_manager/main/compose.prod.yaml
curl -O https://raw.githubusercontent.com/LeoPartt/time_manager/main/.env.prod.example

# Configure environment
mv .env.prod.example .env
nano .env  # Edit with your production credentials

# Deploy (automatically pulls image from GHCR)
docker compose -f compose.prod.yaml up -d
```

**Image location:** `ghcr.io/leopartt/time_manager/backend:latest`

### Update

When a new version is pushed to `main`, update the server:

```bash
cd ~/time_manager

# Pull latest image
docker compose -f compose.prod.yaml pull

# Restart with new image
docker compose -f compose.prod.yaml up -d
```

### Common Commands

```bash
# View logs
docker compose -f compose.prod.yaml logs -f

# Check status
docker compose -f compose.prod.yaml ps

# Restart service
docker compose -f compose.prod.yaml restart backend

# Stop all
docker compose -f compose.prod.yaml down
```

## Troubleshooting

**Version error**: Ensure version in `pubspec.yaml` is higher than latest tag
```bash
git tag --sort=-v:refname | head -1
```

**Build fails**: Check GitHub Actions logs and verify code compiles locally:
```bash
cd frontend/time_manager
flutter pub get
flutter build apk --release
```

**Manual release** (if needed):
```bash
git tag -a v0.2.0 -m "Release v0.2.0"
git push origin v0.2.0
```
