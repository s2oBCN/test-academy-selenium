# intelliJ

## Requirements
* Java JDK 8 installed
* IntelliJ installed

## Initial steps
* Import Project as Gradle with the default config. 
 * To verify that we are up to date with all the dependencies, that all compiles oK and to generate the Test runners, execute the Gradle task _testClasses_:
 
 ![IntelliJ](images/intellij-testCompile.png)
 
## Running the tests
* In src\test\java\tests\... you will find the jUnit classes to execute the tests. Those classes are automatically generated after execute the gradle task _testClasses_

 ![IntelliJ](images/intellij-testexecution.png)

## Plugins

* Builder Generator

   ![IntelliJ - Builder Generator](images/intellij-builderGenerator-plugin.png)

* UUID Generator

   ![IntelliJ - UUID Generator](images/intellij-uuid-generator.png)

* Cucumber

   ![IntelliJ - UUID Generator](images/intellij-cucumber.png)

## Update IDE:     
* Refresh project:

 ![IntelliJ](images/intellij-rebuild.png)
 
## Troubleshooting & FAQ
- Trying to execute a test get this error: "Command line too long"

![IntelliJ](images/errorExecution0.png)
Then click on "Default Junit config" and set  __Shorten Command="JAR manifest" __

## Tutorials
See https://www.jetbrains.com/help/idea/mastering-keyboard-shortcuts.html
 