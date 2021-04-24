# BilTrader API - CS102 Project
This repository contains the Spring Boot API service. This is meant to be used in conjunction with NextJS/React-based frontend.

## Requirements
Java 11 SDK
Maven

## Installation
1. Clone the GitHub repository
2. Browse to the local cloned repository's directory using Terminal on macOS or Powershell on Windows and run the following command: `./mvnw spring-boot:run`
3. Next, using your web browser, navigate to `http://localhost:8080`. If you see an HTML error page, your installation is successful.

## Routes
Initially, the API methods (mostly GET and POST) are going to be documented here. Please document each of the API methods in the following format:

### Login:

Used to collect a Token for a registered User.

**URL** : `/login/`

**Method** : `POST`

**Auth required** : NO

**Data constraints**

```json
{
    "username": "[valid email address]",
    "password": "[password in plain text]"
}
```

**Data example**

```json
{
    "username": "iloveauth@example.com",
    "password": "abcd1234"
}
```

#### Success Response

**Code** : `200 OK`

**Content example**

```json
{
    "token": "93144b288eb1fdccbe46d6fc0f241a51766ecd3d"
}
```

#### Error Response

**Condition** : If 'username' and 'password' combination is wrong.

**Code** : `400 BAD REQUEST`

**Content** :

```json
{
    "non_field_errors": [
        "Unable to login with provided credentials."
    ]
}
```
