# account-transfer
A RESTful API for money transfers between accounts.

Developed using Java 8 and [javalin.io](https://javalin.io/).

## Features
* Account creation/list
* Transfer execution
* In-memory datastore
* Stand-alone jar (no need for a pre-installed container/server)

## Requirements
* Java 8
* Maven

## How to build the application
Checkout the project from this repository, then run
```
    mvn clean package
```
## How to run the application
Build the application then run
```
    java -jar target\account-transfer-1.0-SNAPSHOT-fat.jar
```
The application runs on port 7000

## How to use the application
### Accounts
#### Create an account
The following request creates an account and returns it:
```
    POST localhost:7000/accounts
    {
        "owner": "John Doe",
        "amount": "4325.65"
    }
```
Response:
```
    HTTP 201 Created
    {
      "status": "SUCCESS",
      "errorMessage": null,
      "returnedResult": {
        "owner": "John Doe",
        "amount": 4325.65,
        "id": 1
      }
    }
```
#### Retrieve all accounts
The next request retrieves all accounts in the datastore
```
    GET localhost:8080/accounts
```
Response:
```
    HTTP 200 OK
    [
      {
        "id": 1,
        "ownerName": "John Doe",
        "currentBalance": 1000000
      },
      {
        "id": 2,
        "ownerName": "Albert Kowalski",
        "currentBalance": 56600.75
      },
      {
        "id": 3,
        "ownerName": "Ursula Ogon",
        "currentBalance": 7823.56
      }
    ]
```
### Transfers
#### Make a transfer - w/o any errors
The following request make a transfer:
```
    POST localhost:7000/transfer
    {
        "fromAccountId": "1",
        "toAccountId": "3",
        "amount": "5200.67"
    }
```
Response:
```
    HTTP 200 OK
    {
      "status": "SUCCESS",
      "errorMessage": null,
      "returnedResult": null
    }
```
#### Make a transfer - w/ some errors
The following request make a transfer:
```
    POST localhost:7000/transfer
    {
        "fromAccountId": "1",
        "toAccountId": "11", <-- not existing account id;
        "amount": "5200.67"
    }
```
Response:
```
    HTTP 500 Server Error
    {
      "status": "FAILED",
      "errorMessage": "Transfer hasn't finished with success.",
      "returnedResult": "Couldn't find an Account entity with id=11"
    }
```
