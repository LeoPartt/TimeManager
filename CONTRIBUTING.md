# Contributing Guide

Thank you for your interest in this project! This document describes the conventions and contribution process.

## Table of Contents

- [Branch Structure](#branch-structure)
- [Branch Naming Conventions](#branch-naming-conventions)
- [Commit Conventions](#commit-conventions)
- [Contribution Workflow](#contribution-workflow)
- [Pull Requests](#pull-requests)

## Branch Structure

The project uses a hierarchical branch structure:

- **`main`**: Production branch (protected, no direct push)
- **`develop`**: Main development branch (protected, no direct push)
- **`frontend`**: Branch dedicated to frontend development
- **`backend`**: Branch dedicated to backend development
- **`feature/*`**: Temporary branches for new features (deleted after merge)

## Branch Naming Conventions

All feature branches must follow the format:

```
feature/<descriptive-name>
```

### Examples:
```
feature/user-authentication
feature/payment-integration
feature/dashboard-redesign
feature/api-rate-limiting
```

### Best Practices:
- Use descriptive and concise names
- Use hyphens (`-`) to separate words
- Avoid special characters
- Use English preferably

## Commit Conventions

Commit messages must follow the **Conventional Commits** format:

### General Format:

```
<type>(<scope>): <description>
```

or

```
<type>: <description>
```

### Commit Types:

- **`feature`**: Adding a new feature
- **`fix`**: Bug fix
- **`docs`**: Documentation changes
- **`style`**: Formatting changes (spaces, commas, etc.)
- **`refactor`**: Code refactoring without functionality changes
- **`test`**: Adding or modifying tests
- **`chore`**: Maintenance tasks (dependencies, config, etc.)
- **`perf`**: Performance improvements

### Examples:

```bash
feature(auth): add JWT authentication
feature: implement user profile page
fix(api): resolve null pointer exception in user endpoint
fix: correct typo in login form
docs: update installation instructions
refactor(database): optimize query performance
test(user): add unit tests for user service
chore: update dependencies to latest versions
```

### Best Practices:
- Use imperative present tense ("add" not "added")
- Do not put a period at the end
- First line should not exceed 72 characters
- Add a detailed description if necessary (skip a line after the title)

## Contribution Workflow

### 1. Create a Feature Branch

```bash
# From develop, frontend or backend depending on context
git checkout develop
git pull origin develop
git checkout -b feature/my-new-feature
```

### 2. Develop and Commit

```bash
# Make your changes
git add .
git commit -m "feature(module): feature description"
```

### 3. Push the Branch

```bash
git push origin feature/my-new-feature
```

### 4. Create a Pull Request

- Open a PR to the appropriate branch (`develop`, `frontend`, or `backend`)
- Clearly describe the changes made
- Reference related issues if applicable
- Wait for code review

### 5. After Merge

The `feature/*` branch will be automatically deleted after merge.

## Pull Requests

### Checklist Before Submitting:

- [ ] Code compiles without errors
- [ ] Tests pass
- [ ] Code follows project conventions
- [ ] Documentation is updated if necessary
- [ ] Commits follow naming conventions
- [ ] Branch is up to date with target branch

### PR Format:

```markdown
## Description
[Clear description of changes]

## Type of Change
- [ ] Feature
- [ ] Fix
- [ ] Documentation
- [ ] Refactoring

## Tests
[Description of tests performed]

## Screenshots (if applicable)
[Screenshots]
```

## Questions

If you have any questions, feel free to open an issue or contact the development team.

---

Thank you for contributing to the project!