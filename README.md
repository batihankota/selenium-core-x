# SeleniumCoreX : Advanced Core Automation Tool

During my free time, I prioritize self-improvement over gaming. Given my busy work schedule, I had limited time to develop this automation tool. I dedicated two days over the weekend to its development alongside my regular work commitments. If you encounter any issues, please feel free to notify me. I trust it will serve your needs effectively.

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [Dependencies](#dependencies)
- [Contributing](#contributing)
- [License](#license)

## Installation
To get started with use this project, follow these steps:

1. **Clone the Repository:**
- Clone the repository to your local machine:
```
git clone https://github.com/your-username/your-repository.git
```

2. **Install Dependencies:**
- Navigate to the project directory:
```
cd <your-repository>
```
- Compile the project using Maven:
```
mvn clean install
```

3. **Configure Environment:**
   - Ensure you have Java JDK 11 or higher installed.
   - Make sure you have Maven installed.

4. **Run the Application:**
```
mvn test
```
- This command runs all the tests within the project's test scope.
- It is not affected by previous builds or test runs, so it provides up-to-date results based on any changes made in the project.
- It does not delete or clean any previously generated artifacts or files present in the target directory.
  
```
mvn clean test
```
- This command cleans the project before running the tests within the test scope.
- The clean operation removes compiled output, leftover files from previous test runs, and other temporary files from the target directory.
- By clearing previously compiled classes and other unnecessary files, it ensures that the project is not affected by the previous state and allows the tests to run more reliably.

5. **View Allure Reports:**
- After running the tests, you can view the Allure reports by running:
```
allure serve target/allure-results
```

6. **Explore the Project:**
- Feel free to explore the project's directories and files to understand its structure 

## Usage
To use this project, follow these steps:

- Run the tests and generate Allure reports:
<p>Use on terminal:</p>

```
mvn test
```

- View the Allure reports by running:
<p>Use on terminal:</p>

```
allure serve target/allure-results
```
Explore the test results in the generated Allure report.
Make sure you have Maven and JDK installed on your machine before running the project.

## Dependencies
List of all libraries and dependencies I used in this project:

- **Log4j:** Version 1.2.17
- **Selenium WebDriver:** Version 4.18.1
- **Apache POI:** Version 5.2.4
- **RestAssured:** Version 4.5.1
- **JUnit:** Version 4.13.2
- **Allure JUnit4:** Version 2.14.0
- **AspectJ Weaver:** Version 1.9.21
- **Maven Surefire Plugin:** Version 3.2.5

## Contributing
Here are some guidelines to help you get started:

### Reporting Bugs
If you encounter a bug while using this project, please open an issue on my issue tracker. Include as much detail as possible, including steps to reproduce the bug, expected behavior, and actual behavior.

### Submitting Feature Requests
I welcome feature requests! Please open an issue on your issue tracker and describe the feature you would like to see added to the project. Provide as much detail as possible to help me understand your request.

### Making Code Contributions
1. Fork the repository on GitHub.
2. Clone your forked repository to your local machine.
   `git clone <project's_fork_url>`
3. Create a new branch to work on your feature or bug fix.
   `git checkout -b feature-or-bugfix-name`
4. Make your changes and test them thoroughly.
5. Commit your changes with descriptive commit messages.
   `git commit -m "Add feature or fix bug"`
6. Push your changes to your fork on GitHub.
   `git push origin feature-or-bugfix-name`
7. Create a pull request from your forked repository to the original repository's main or master branch.
8. Provide a clear description of your changes in the pull request, including why the changes are necessary.
9. Wait for feedback and address any comments or requested changes.

Thank you for contributing to my project!

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE.txt) file for details.
