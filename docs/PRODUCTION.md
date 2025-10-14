# Production & Release Guide

## Release Workflow

```
1. Update version in frontend/time_manager/pubspec.yaml
2. Create PR develop → main
3. Merge to main
   ↓ (automatic)
4. Tag created (vX.Y.Z)
5. APK built and published to GitHub Releases
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
- Validate version is greater than latest
- Create tag `v0.2.0`
- Build APK
- Publish to GitHub Releases as `time-manager-0.2.0.apk`

## Server Deployment

### Setup

```bash
# Clone repo
git clone https://github.com/your-org/time_manager.git
cd time_manager

# Configure environment
cp .env.prod.example .env
nano .env  # Edit with your credentials

# Deploy
docker compose -f compose.prod.yaml up -d --build
```

### Update

```bash
git pull origin main
docker compose -f compose.prod.yaml up -d --build
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
