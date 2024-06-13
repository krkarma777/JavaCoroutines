# Contributing to Our Project

We welcome contributions of all kinds, including code, documentation, bug reports, and feature requests. Thank you for considering contributing to our project!

## Table of Contents

1. [Getting Started](#getting-started)
2. [Code of Conduct](#code-of-conduct)
3. [How to Contribute](#how-to-contribute)
   - [Reporting Bugs](#reporting-bugs)
   - [Suggesting Features](#suggesting-features)
   - [Contributing Code](#contributing-code)
4. [Style Guides](#style-guides)
   - [Git Commit Messages](#git-commit-messages)
   - [Code Style](#code-style)
5. [Pull Request Process](#pull-request-process)
6. [Testing](#testing)
7. [License](#license)

## Getting Started

1. Fork the repository.
2. Clone your fork:

   ```bash
   git clone https://github.com/your-username/your-repo-name.git
   ```

3. Create a new branch for your work:

   ```bash
   git checkout -b my-feature-branch
   ```

4. Set up the development environment. Make sure you have [Java](https://www.oracle.com/java/technologies/javase-downloads.html) and [Gradle](https://gradle.org/install/) installed.

   ```bash
   ./gradlew build
   ```

## Code of Conduct

Please note that this project is released with a [Contributor Code of Conduct](CODE_OF_CONDUCT.md). By participating in this project you agree to abide by its terms.

## How to Contribute

### Reporting Bugs

If you find a bug, please report it by creating a new issue. Include as much detail as possible, including steps to reproduce the issue and any relevant screenshots.

### Suggesting Features

We welcome feature suggestions! To suggest a feature, please create a new issue and describe your idea in detail. Explain why this feature would be useful and how it should work.

### Contributing Code

1. Ensure your code follows the project's style guidelines.
2. Write clear, concise commit messages.
3. Test your changes thoroughly.
4. Submit a pull request.

## Style Guides

### Git Commit Messages

- Use the present tense ("Add feature" not "Added feature").
- Use the imperative mood ("Move cursor to..." not "Moves cursor to...").
- Limit the first line to 72 characters or less.
- Reference issues and pull requests liberally.

### Code Style

- Follow standard Java conventions.
- Use meaningful variable and function names.
- Keep functions small and focused.
- Write comments to explain why certain decisions were made.

## Pull Request Process

1. Ensure that your changes are well-tested.
2. Update the documentation as necessary.
3. Submit your pull request.
4. Ensure that your pull request description clearly describes the problem and solution. Include the issue number if applicable.

## Testing

To run tests, use the following command:

```bash
./gradlew test
```

Ensure that your changes pass all tests before submitting a pull request. Adding new tests for new features or bug fixes is highly encouraged.

## License

By contributing, you agree that your contributions will be licensed under the same license as the project: [MIT License](LICENSE).