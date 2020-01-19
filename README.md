# Lunch voting system
Is is a sofisticated voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users. It is also assumed that it is a *closed community* of 'lunch-goers'. So a user 
 must be registered in the system by an admin before he/she can vote. 
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

# Build and start

To build,test and run - type the following command: 

```mvn clean package cargo:run```

- *the system deploys into the ROOT application context*
- *The system is using an in-memory database so you loose the data every 
time you shut down the application*
- *it is a prototype so the system is pre-populated with some data*

# Authentication

To get the access to the functions you need to user Basic Authentication in your requests.
There are two levels of access: Admin and User  

Initial Admin User credentials: *admin@gmail.com/admin*
Initial User credentials: *user@yandex.ru/admin*

# REST API

## User Management Operations

The following capabilities are indented to be used by the system administrators only, and they are blocked from  the
regular users. 

Function | Action | URL
---|---|---
Full user list  |GET    |/rest/admin/user
Get user        |GET    |/rest/admin/user/{id}
Add user        |POST   |/rest/admin/user
Remove user     |DELETE |/rest/admin/user/{id}
Edit user       |PUT    |/rest/admin/user/{id}  

### Full user list
```
GET /rest/admin/user
```
The call returns the full list of the users registered in the system. 
The list is sorted alphabetically by the first name and then by the email.
The result is returned in JSON format in the body of the response .   

*Example:*

```
curl -i --basic -u admin@gmail.com:admin localhost:8080/rest/admin/user
```

### Get a user
```
GET /rest/admin/user/{id}
```
The call returns information about a user.   

*Example:*
```
curl -i --basic -u admin@gmail.com:admin localhost:8080/rest/admin/user/100001
```

### Add user 
```
POST /rest/admin/user
```
The call creates a user in the system. The system checks for duplicate emails and doesn't allow to create a user with 
the email already registered in the system. It also sets the user role to User by default, if needed it can be changed 
through edit request at a later time.  

*Example:*

```
curl --basic -u admin@gmail.com:admin -i -H 'Content-Type:application/json' \
 -d '{"name":"Sam","email":"sam@gmail.com","password":"password"}' \
localhost:8080/rest/admin/user
```


### Remove user 
```
DELETE /rest/admin/user/{id}
```
The call removes a user from the system. The call takes the system user id as the argument. 
Please use this function with great caution, as the removal is final, and there is no way to restore the user.

*Example:*
```
curl --basic -u admin@gmail.com:admin -i -X DELETE localhost:8080/rest/admin/user/100017
```


### Edit user 
```
PUT /rest/admin/user/{id}
```
The call update all the parameters of a user. It takes the system user id and the user parameters in JSON format in the 
body of the request. Standard format validations and duplicate checks are made. This call merges parameters sent in the 
request with the existing parameters of the user, for example the user name "Sam" before the call and the email address is 
"adams@hotmail.com", the update request contains only the new user name "Sam Adams", so the resulting user will have the 
name set to "Sam Adams" and the email address will stay the same - "adams@hotmail.com".

*Example:*
```
curl --basic -u admin@gmail.com:admin -i -H 'Content-Type:application/json' -X PUT \ 
-d '{"id":100000,"name":"User","email":"new@email.com","password":"password","roles":["ROLE_USER"]}'\
 localhost:8080/rest/admin/user/100000
```

## Restaurant Management Operations

The restaurant management operations can be grouped into the three categories:
 - operations to manipulate restaurant entities
 - operations related with getting information about the restaurant menu 
 - operation to manipulate individual menu items. 
 
 There are two levels of access for those operations: 
- **Admin access** that gives the ability to edit and delete entities. 
- **User access** that primarily gives the ability to view entities    

So please find below the summary of the operations: 

**Restaurant entity operations**

Method | Access | Action | URL
---|---|---|---
List all restaurants | User | GET | /rest/restaurant
Get restaurant | User | GET | /rest/restaurant/{id}
Add a new restaurant | Admin | POST | /rest/admin/restaurant
Delete a restaurant | Admin | DELETE | /rest/admin/restaurant/{id}
Edit restaurant parameters | Admin | PUT | /rest/admin/restaurant/{id}

**Menu entity operations**

Method | Access | Action | URL
---|---|---|---
Get restaurant menu (today)| User | GET | /rest/restaurant/{id}/menu
Get history of menu changes | User | GET | /rest/restaurant/menu_history?start={iso_date}&restaurantId={id}
Add an item to the menu | Admin | POST | /rest/admin/restaurant/{id}/menu
Delete menu (all items for today) | Admin | DELETE | /rest/admin/restaurant/{id}/menu


**Menu items operations**

Method | Access | Action | URL
---|---|---|---
Get menu item description| User | GET | /rest/menuItems/{id}
Delete a menuitem| Admin | DELETE |/rest/admin/menuItems/{id}
Edit menu item parameters | Admin | PUT | /rest/admin/menuItems/{id}


### List all restaurants
```
GET /rest/restaurant
```
The call returns the full list of the restaurants registered in the system. 
The list is sorted alphabetically by restaurant name.
The result is returned in JSON format in the body of the response.
Access level: User

*Example:*

```
curl -i --basic -u user@yandex.ru:password localhost:8080/rest/restaurant
```

### Get restaurant
```
GET /rest/restaurant/{id}
```
The call return description of a restaurant by its ID.
Access level: User

*Example:*
```
curl -i --basic -u user@yandex.ru:password localhost:8080/rest/restaurant/100003
```

### Add a new restaurant 
```
POST /rest/admin/restaurant
```
The call creates a restaurant. Tre restaurant description should be 
provided in the body of the request in JSON format. 
Access Level: Admin

*Example:*
```
curl --basic -u admin@gmail.com:admin -i -H 'Content-Type:application/json' \
-d '{"name":"Sams Kitchen","description":"just simple food","address":"behind the yellow building"}' \
localhost:8080/rest/admin/restaurant
```

### Delete a restaurant 
```
DELETE /rest/admin/restaurant/{id}
```
Does pretty much what it says - deletes a restaurant by its id. 
Access level: Admin

*Example:*
```
curl --basic -u admin@gmail.com:admin -i -X DELETE localhost:8080/rest/admin/restaurant/100015
```

### Edit restaurant parameters 
```
PUT /rest/admin/restaurant/{id}
```
It is used to update parameters of a restaurant by its id.
As a general rule: it updates only parameters that are not null in the request.
Though you must specify Id and Name in the request. 
Access level: Admin

*Example:*
```
curl --basic -u admin@gmail.com:admin -i -H 'Content-Type:application/json' -X PUT -d \
'{"id":100002,"name":"newname"}' localhost:8080/rest/admin/restaurant/100002
```

### Get restaurant menu (current date)
```
GET /rest/restaurant/{id}/menu
```
The call returns menu(list of menu items) of the restaurant by its Id for the current date.
If admins haven't put items for the current day it returns nothing. 
Access level: User

*Example:*
```
curl -i --basic -u user@yandex.ru:password localhost:8080/rest/restaurant/100003/menu
```

### Get history of menu changes
```
GET /rest/restaurant/menu_history?start={iso_date}&restaurantId={id}
```
The call returns history of menu changes - menu items grouped by date and restaurant and also ordered alphabetically.
It takes two optional parameters: 
- start - the date since what you want to get the information, if null it returns history for the last 14 dates. 
- restaurantId - if you are interested in a specific restaurant, if null it returns for all restaurants.
 
Access level: User

*Example:*
```
curl -i --basic -u user@yandex.ru:password localhost:8080/rest/restaurant/menu_history?start=2020-01-15&restaurantId=100002
```

### Add an item to the menu (current date)
```
POST /rest/admin/restaurant/{id}/menu
```
The call creates a menu item for a restaurant with the specified id for **the current date menu**.
The menu item description should be provided in the body of the request in JSON format. 
Access Level: Admin

*Example:*
```
curl --basic -u admin@gmail.com:admin -i -H 'Content-Type:application/json' \
-d '{"name":"Something new","price":17.25}' \
localhost:8080/rest/admin/restaurant/100002/menu
```
### Delete menu (all items) (current date)
```
DELETE /rest/admin/restaurant/{id}/menu
```
This operation allows you to remove all the menu items for **the current date**. It is assumed to be used if an admin 
put wrong menu items and wants to remove it all for the day. But it doesn't affect the items for the previous days (history).  
Access level: Admin

*Example:*
```
curl --basic -u admin@gmail.com:admin -i -X DELETE localhost:8080/rest/admin/restaurant/100015/menu
```

### Get menu item description
```
GET /rest/menuItems/{id}
```
The call returns description of an individual menu item by its id. 
Access level: User

*Example:*
```
curl -i --basic -u user@yandex.ru:password localhost:8080/rest/menuItems/100010
```
### Delete a menu item 
```
DELETE /rest/admin/menuItems/{id}
```
It deletes an menu item by its id. 
Access level: Admin

*Example:*
```
curl --basic -u admin@gmail.com:admin -i -X DELETE localhost:8080/rest/admin/menuItems/100011
```

### Edit menu item parameters 
```
PUT /rest/admin/menuItems/{id}
```
It is used to update parameters of a menu item by its id.
Here you must specify all the parameters in the request. 
Access level: Admin

*Example:*
```
curl --basic -u admin@gmail.com:admin -i -H 'Content-Type:application/json' -X PUT -d \
'{"id":100010,"name":"Cactus Burger","price":23.0,"menuDate":"2020-01-15"}' localhost:8080/rest/admin/menuItems/100010
```

## Voting capabilities

Method | Access | Action | URL
---|---|---|---
Vote for the restaurant | User | POST | /rest/vote?restaurantId={id}
Get voting history | User | GET | /rest/vote/history?start={iso_date}
Get your last vote | User | GET | /rest/vote/last
Get voting results | User | GET | /rest/vote/result?date={iso_date}
Get your particular vote info | User | GET | /rest/vote/{id}

### Vote for the restaurant
```
POST /rest/vote?restaurantId={id}
```
The call allows a user to vote for a restaurant by its it. Only one vote counted per user, but
if the user votes again in the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed 
Access level: User

*Example:*
```
curl --basic -u user@yandex.ru:password -X POST localhost:8080/rest/vote?restaurantId=100004
```

### Get voting history
```
GET /rest/vote/history?start={iso_date}
```
The call returns history of your votes. It restricts you with your history only and shows votes based on the authenticated user.
The result is order by date/time.
It takes one optional parameter: 
- start - the date since what you want to get the history information, if null it returns history for last 14 days. 
 
Access level: User

*Example:*
```
curl -i --basic -u user@yandex.ru:password localhost:8080/rest/vote/history?start=2020-01-15
```

### Get your last vote
```
GET /rest/vote/last
```
It is a convenience operation. It returns your last vote. That means it takes the authenticated user and returns its last vote.
 
Access level: User

*Example:*
```
curl -i --basic -u user@yandex.ru:password localhost:8080/rest/vote/last
```

### Get voting results
```
GET /rest/vote/result?date={iso_date}
```
The call returns voting results summary - restaurants and number of votes against them.
It takes date as an optional parameter, if specified it will return voting results for the date, if not it returns results
for the current date.  
Access level: User

*Example:*
```
curl -i --basic -u user@yandex.ru:password localhost:8080/rest/vote/result?date=2020-01-15
```
### Get your particular vote info
```
GET /rest/vote/{id}
```
Returns vote description but only if the vote belongs to the authorized user.
 
Access level: User

*Example:*
```
curl -i --basic -u user@yandex.ru:password localhost:8080/rest/vote/100005
```

