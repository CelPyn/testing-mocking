# Mocking and testing

This repository contains slides and exercises for the mocking and testing session of the Axxes traineeship.

## Building the project
Make sure you have configured JDK 17 as the JDK for this project.

Then run `mvn clean verify -DskipTests` or click the handy `Run` arrow next to this line when opening the README in Intellij.

If you want to run all the tests in this project, run `mvn clean verify`. This requires docker to be running for the integration-testing module.

## Modules

The project is divided into three modules:

- unit-testing
- test-driven-development
- integration-testing

### Unit Testing
This module contains exercises that focus on unit testing.

Refer to the [README](./unit-testing/README.md) of this module for more info.

### Test Driven Development
For this module, the consultant that's teaching the session will guide you. You're not getting any hints here ;) 

###
This module contains exercises that focus on integration testing.

Refer to the [README](./integration-testing/README.md) of this module for more info.
