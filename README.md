# BilTrader API - CS102 Project
This repository contains the Spring Boot API service. This is meant to be used in conjunction with NextJS/React-based frontend.

## Installation
To be updated later once a database instance is added. For now, just git clone the directory and refer to Spring Boot's docs.

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
