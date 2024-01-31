# demo-Web-testing-with-Selenium
Project to test Serenity with Selenium and Cucumber

* Demo project to know how to architecture a test project which has some complexity.
* The project is following the Page Object pattern. Where a class express the interface of a page and nothing related to a test can be inside the class. So:
  * No __webElement__ should be exposed from a page object.
  * No assert can be done inside a page object. So, be careful if you write a mehtod to verify anything in a page class, ie returns a boolean, that your are not coupled to a test.
  
# Responsible
Sergio Sacristan extra@sacristan.com.es

## Getting started
You can improve __everything__ in the project with a PR:
* You need to create a git branch, following the naming convention _feature/whatever_
* Make a pull request!
* Thank you in advance for your contribution!!!!
    
### Requirements
* **Git**:  [See the Git best practices](docs/git-best-practices.md).
* Install [Docker](http://docker.io).
* Install [Docker-compose](http://docs.docker.com/compose/install/).
* Clone this repository
* **Java Development Kit**: [See the Java best practices](docs/java-best-practices.md).
* **Gradle**: Use gradle wrapper.
* **IDE**: see [intelliJ](docs/intelliJ.md)/[eclipse](docs/eclipse.md), depending on your prefered IDE.
* **Gherkin**: [Gherkin - best practices & tips](docs/gherkin.md).

### Structure

#### Application mirror
* src/main/java.../__domain__: DTO objects (for the data interface of every page)
* src/main/java.../__pages__: Page objects

#### Application tests

* src/test/resources/__features__: create a structure in order to have all the features of a page in the same folder.
* src/test/java/,,,/__builders__: use the patter _Builder_ and _Data Builder_ in order to encapsulate the managemenet of the data needed for the tests.
* src/test/java/,,,/__domain__: DTO to access the API of the AUT (application under test).
* src/test/java/,,,/__services__: use the pattern _Facade_ to encapsulate the initialization of the data scenario. This initialization should be done be calling the API services of the AUT or DB (using a DAO), but never initialize the data calling another pages of the AUT.
* src/test/java/,,,/__stepsdefs__: create a StepDef per Feature, and follow the same package structure as the related feature
  * For commonn steps to different stepDef, but within the same folder, create a CommonStepDef
  * Use _Serenity.getCurrentSession().put/get_ to share common data between related steps definitions classes
* src/test/java/,,,/__tests__: here you have the classes to run the tests
  
## Configuration
* This is a gradle project, you can import it from your favorite IDE as Gradle project.
* To verify that all is up to date with dependencies and test compilation, execute the gradle task: _testClasses_

```cmd
    gradlew.bat testClasses
```

### Compilation
* From IntelliJ: [See intelliJ config](docs/intelliJ.md).
* From Eclipse: [See eclipse config](docs/eclipse.md).

### Execution
* From gradle:
```cmd
gradle test -Dcucumber.options="--tags @something"
```
* From junit class: src/test/java/es/s2o/selenium/tests/reservations/ReservationTest.java
* __See the reports in target/site/serenity/index.html__

### CI

* [See Jenkinsfile](Jenkinsfile) 

```
$ zalenium/docker-compose up -d
```

* Selenium Grid Console http://localhost:4444/grid/console
* Zalenium http://localhost:4444/grid/admin/live

# Práctica
Utilizando los patrones aprendidos y frameworks Open Source. Implementar el siguiente escenario:
En la web de Vueling, verificar que existen vuelos para la siguiente búsqueda:
■ Origen ‘Madrid’
■ Destino ‘Barcelona’,
■ Fecha ‘1 Junio’
■ Solo ida
■ Un único pasajero-Se puede implementar en cualquier lenguaje.-El entregable debe contener:
■ Código fuente del proyecto (sin el compilado)
■ Un README con las instrucciones necesarias para la ejecución del test.
■ Un report con el resultado de la ejecución