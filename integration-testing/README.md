# Integration Testing

In this module, you'll find an example of a simple TODO application.
The application is written in Spring, and exposes a simple CRUD REST API that talks to a MariaDB database.

The developer that came before you seems to have implemented all the business logic already. 
All that's left to do is write some tests that everything behaves like it should.

We've prepared this exercise in such a way that the application will not boot when you start it up locally.
If you do try to run it, you'll see Spring will complain about a missing datasource. 

However, when you run the integration test that's already there as an example, the datasource is configured for the test profile.

Your task is to test all the endpoints exposed in the `RestController`, without ever manually sending a request to your local dev environment.

Please write tests for the following usecases:

- Requesting all the TODOs should give you a list of TODOs, that are sorted alphabetically by their `Summary`
- Requesting one specific TODO should return the requested TODO
- Requesting one specific TODO that doesn't exist should return a 404
- Creating a TODO should be reflected in the `collection` endpoint or the `request a single TODO` endpoint
- Marking a TODO as done should be reflected in the `collection` endpoint or the `request a single TODO` endpoint
- Deleting a TODO should be reflected in the `collection` endpoint or the `request a single TODO` endpoint
