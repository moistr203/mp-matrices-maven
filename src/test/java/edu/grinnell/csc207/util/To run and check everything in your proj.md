To run and check everything in your project, including compiling, running tests, and reviewing code style, you can follow these steps:

1. Ensure Proper Configuration:

First, ensure that your project is properly configured for Gradle to handle compilation, testing, and style checking.

Here’s a checklist:

	•	Build Script (build.gradle): Ensure it contains the right dependencies and configurations for Java compilation, JUnit testing, and style checking.
	•	Test Classes: Ensure your test classes follow the naming conventions (e.g., ending with Test) and that the test methods are properly annotated with @Test.

2. Run All Tasks:

Use the following Gradle command to run all the main tasks (compiling the project, running the tests, and performing any other defined tasks like style checks):

gradle clean build --warning-mode all

This command does the following:

	•	clean: Removes previous build files to ensure a fresh build.
	•	build: Compiles the project, runs all tests, and generates the final build artifact (e.g., a JAR file).
	•	–warning-mode all: Displays all deprecation warnings or issues.

3. Check Code Style:

If you have configured style checks (e.g., using Checkstyle), it will automatically run as part of the build task. Make sure that you have the maven-checkstyle-plugin or equivalent in your Gradle build script.

If you want to run style checks separately, you can use:

gradle checkstyleMain checkstyleTest

This will check the style for both the main code and the test code. If checkstyle is configured, it will report any style violations.

4. Run Tests:

If you just want to run the tests, you can use:

gradle test

This will run all tests and give you a detailed report on any that fail or succeed. The results will be available in the build/reports/tests/test/index.html file, which you can open in a browser to view a detailed report.

5. Review Warnings and Errors:

Make sure to carefully review the output from the Gradle command. If there are warnings or errors, they will be listed in the console output. Fix any warnings related to unchecked types or unsafe operations if they appear.

6. Check Test Coverage (Optional):

To check the coverage of your tests (if you have a coverage plugin like Jacoco configured), you can use:

gradle jacocoTestReport

This will generate a report showing how much of your code is covered by the tests. The report will typically be available in build/reports/jacoco/test/html/index.html.

Summary of Commands:

	•	To run everything (clean, compile, test, build):

gradle clean build --warning-mode all


	•	To run only the tests:

gradle test


	•	To check code style (if Checkstyle is configured):

gradle checkstyleMain checkstyleTest


	•	To check test coverage (if Jacoco is configured):

gradle jacocoTestReport



These commands should help you fully run and verify everything in your project. Let me know if you encounter any issues during this process!