# Fraud Risk Analysis

Zopa is more than just another challenger bank - we're an established FinTech with a long history of innovation. Zopa disburses over a billion pounds of loans each year, we have a rapidly growing savings product with over billion pounds invested with us and a brand new credit card that's already in the top five in the UK  - we're a big potential target for fraudsters. Fraud prevention is a very real domain for us and whilst this exercise is very simplified perhaps it will give you some insight into opportunities and challenges faced by a company at our scale and level of success.

Fraud detection and analysis is a complex domain, so for the purposes of this exercise we assume that this has been outsourced to third parties. The exercise revolves around a fictional service called "FraudRisk" which is responsible for taking some personally identifying details as an input and returning a fraud risk score. It does this by calling two fictional fraud detection services - Fraudster and SteerClear - and returning a combined result.


| Details used to determine fraud risk | Resulting score | Meaning |
|---|---|---|
| email: getrichquick898@example.com<br>first name: Charrlie<br>last name: Zoplin<br>post code: ABC 123 | 95 | A score of 95 indicates a high risk of fraud. |
| email: charlie.zoplin@example.com<br>first name: Charlie<br>last name: Zoplin<br> post code: ZZ0 P4 | 5 | A score of 5 indicates a low risk of fraud. |

So, from a functional perspective a simple enough service. At our scale it also needs to:

- Be reliable as it forms an important part of our business processes
- Handle the sensitive nature of this service securely
- Be efficient with its resources - for example each call to a third party fraud service incurs a cost
- Be able to be used by a variety of clients with different use cases

The pairing exercise will focus on the implementation of a service which integrates with the two third parties that we use for these checks, and the design exercise will focus on how such a service can be used as part of a larger system.

##
## Implementation details

The service has been built using:
- Java 11
- Jersey and Jetty
- JUnit 5 with AssertJ and Mockito for testing
- Logback for logging
- Gradle 7

##
### Building and running

To build:

`./gradlew build`

To run locally:

`./gradlew run`

By default, the server listens on port 8080.

###
### User interface

The service has a REST api with the following endpoint for retrieving a person’s fraud risk score:
GET /api/scores

The scores endpoint accepts the following query parameters:
- email
- firstName
- lastName
- postCode

The endpoint returns a JSON containing a summary of personal details as well as a fraud risk score (as a number between 0 and 100).


#### Example request
``` 
GET /api/scores?email=charlie.zoplin@zopa.com&firstName=Charlie&lastName=Zoplin&postCode=ZZ0%20P4
```


#### Example response:
``` json
{
    "email"     : "charlie.zoplin@zopa.com",
    "firstName" : "Charlie",
    "lastName"  : "Zoplin",
    "postCode"  : "ZZ0 P4",
    "score"     : 56
}
```

On receiving a fraud risk score request, the service does the following:
1. Checks if there is a cached result matching the request.
2. If there is a cached result, it’s returned.
3. If there is no cached result, a new request is sent to a 3rd party service (Fraudster).
4. If the Fraudster call is successful, the new result is cached and returned.

For simplicity, 3rd parties are represented in the form of stubbed clients: FraudsterClientStub and SteerClearClientStub.


###
### Health check

The service also exposes two health check endpoints:
```
GET /health/liveness
GET /health/readiness
```

##
## Example task

As part of the pairing session you will be asked to make some changes or implement new features in FraudRisk codebase.
The first task you will be asked to complete is the following:

### Scenario: Failing readiness check

#### Problem:
We've run into an issue when trying to deploy our service - the readiness check always returns an error, even when the service seems to be running.

#### Task:
1. In HealthEndpointIntegrationTest add a missing test for the readiness endpoint.
2. Fix the bug in the HealthController.
