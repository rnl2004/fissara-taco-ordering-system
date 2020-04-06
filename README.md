# Getting Started

## Reference Documentation
This application is intended for coding challenge test. The application is written in Java 11 using Spring boot, JPA Spring Data, H2 and please check the pom.xml configuration for more details.  

## Pre-requisites 
These application needs to have the following requirements:
* Java 11
* Maven 
* Git
* Postman

## Clone Project Application
At first clone the project from github.com with the following steps. Assuming you have git installed.
```
$ git clone =https://github.com/rnl2004/fissara-taco-ordering-system.git

```

## Compile and Run The Application
Assuming java 11 and maven has been installed
```
$ cd fissara-taco-ordering-system
$ mvn clean install
$ mvn clean test

Option 1:
- mvn spring-boot:run

Option 2: Run using profile
- java -jar target\fissara-taco-ordering-system-0.0.1-SNAPSHOT.jar --spring.profiles.active=application

NOTE: Since only one profile exist you can ommit the flag "--spring.profiles.active=application" just simply invoke this command "java -jar target\fissara-taco-ordering-system-0.0.1-SNAPSHOT.jar"

```

## Test the Rest API
Assuming Postman has been install or any REST API testing tool which you preferred.
```
NOTE: At first load of spring boot application the migration script executed to auto populate the data in database.

To verify if the data persisted or inserted successfully. Please invoke the API below.

Rest API 1: 
Name: /api/order/findAllOrdersByCustomer
Method: GET
Params: id - Customer ID
Complete URL: http://localhost:8080/api/order/findAllOrdersByCustomer?id=1
Description: This will fetch all orders per customer

Expected output:
{
    "customerOrders": [
        {
            "orderId": 1,
            "createdAt": "2020-04-03T16:00:00.000+0000",
            "tacos": [
                {
                    "id": 1,
                    "name": "Carne Asada Tacos",
                    "createdAt": "2020-04-03T16:00:00.000+0000",
                    "ingredients": [
                        {
                            "id": 1,
                            "name": "Strips of beef are marinated in lime and pepper, then quickly sautéed",
                            "createdAt": "2020-04-03T16:00:00.000+0000"
                        },
                        {
                            "id": 2,
                            "name": "Soft corn tortillas with tomatillo sauce",
                            "createdAt": "2020-04-03T16:00:00.000+0000"
                        }
                    ]
                }
            ]
        },
        {
            "orderId": 2,
            "createdAt": "2020-04-03T16:00:00.000+0000",
            "tacos": [
                {
                    "id": 2,
                    "name": "Dee's Roast Pork for Tacos",
                    "createdAt": "2020-04-03T16:00:00.000+0000",
                    "ingredients": [
                        {
                            "id": 3,
                            "name": "Pork shoulder roasts slowly with diced green chilies",
                            "createdAt": "2020-04-03T16:00:00.000+0000"
                        },
                        {
                            "id": 4,
                            "name": "Seasonings for the meat just falls apart",
                            "createdAt": "2020-04-03T16:00:00.000+0000"
                        }
                    ]
                },
                {
                    "id": 3,
                    "name": "Tacos in Pasta Shells",
                    "createdAt": "2020-04-03T16:00:00.000+0000",
                    "ingredients": [
                        {
                            "id": 5,
                            "name": "Jumbo pasta shells are stuffed with seasoned ground beef and cream cheese and baked with taco sauce",
                            "createdAt": "2020-04-03T16:00:00.000+0000"
                        }
                    ]
                }
            ]
        }
    ],
    "customerDetail": {
        "id": 1,
        "name": "Fissara Company",
        "createdAt": "2020-04-03T16:00:00.000+0000"
    }
}
   
Rest API 2:
Name: /api/order/placeOrder
Method: POST
Complete URL: http://localhost:8080/api/order/placeOrder
Description: This will place an order for each customer

RequestBody: 
{
	"order": {
		"customer": {
			"id": 1,
			"name": "test Customer"
		},
		"tacos": [
            {
                "name": "Test Taco 1",
                "ingredients": [
                    {
                        "name": "Test Ingredient 1"
                    },
                    {
                        "name": "Test Ingredient 2"
                    }
                ]
            }
        ]
	}
}

Expected Output:
{
    "orderId": 3,
    "createAt": "2020-04-04T13:42:49.443+0000",
    "message": "Transaction Completed"
}

NOTE: You can also testing the scenario base on the requirement in actual API calls.
Eg. 
- Using the place order input you can play around the by changing the name of taco less than 5 characters and see the response below.

Expected output:
{
    "timestamp": "2020-04-04T14:22:22.368+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Taco name is required at least 5 characters",
    "path": "/api/order/placeOrder"
}
 
- Also you remove all ingredient on a specific taco input so the ingredients have empty array or []. Please see the response below. 

Expected output:
{
    "timestamp": "2020-04-04T14:25:22.342+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Ingredient(s) is required at least one",
    "path": "/api/order/placeOrder"
}


Rest API 3:
Name: /api/customer/create
Method: POST
Complete URL: http://localhost:8080/api/customer/create
Description: This will place an order for each customer

RequestBody:
{
	"name": "Rico Nodalo Lugod"
} 

Expect output:
{
    "createdAt": "2020-04-04T11:30:08.119+0000",
    "updatedAt": null,
    "id": 2,
    "name": "Rico Nodalo Lugod"
}

NOTE: For this challenge i only put one element for simplicity just name :)


Thanks...!!! :)

```