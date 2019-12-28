# Lunch voting system - REST API desing

## User Management Operations

The following capabilities are indented to be used by the system administrators only, and they are blocked from  the
regular users. 

Function | Action | URL
---|---|---
Full user list  |GET    |/rest/admin/users
Get user        |GET    |/rest/admin/user/{id}
Add user        |POST   |/rest/admin/user
Remove user     |DELETE |/rest/admin/user/{id}
Edit user       |PUT    |/rest/admin/user/{id}  

### Full user list
```
GET /rest/admin/users
```
The call returns the full list of the users registered in the system. 
The list is sorted alphabetically by the first name and then by the email.
The result is returned in JSON format in the body of the response .   

*Example:*

```
curl -i localhost:8080/rest/admin/users
```

### Get a user
```
GET /rest/admin/user/{id}
```
The call returns information about a user.   

*Example:*

```
curl -i localhost:8080/rest/admin/user/{id}
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
curl -i -H 'Content-Type:application/json' -d '{**TBD**}' localhost:8080/rest/admin/user
```


### Remove user 
```
DELETE /rest/admin/user/{id}
```
The call removes a user from the system. The call takes the system user id as the argument. 
Please use this function with great caution, as the removal is final, and there is no way to restore the user.

*Example:*
```
curl -i -X DELETE localhost:8080/rest/admin/user/100001
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
curl -i -H 'Content-Type:application/json' -X PUT -d '{**TBD**}' localhost:8080/rest/admin/user/100000
```


___________________

## Restaurant Operations

Method | Action | URL
---|---|---
Create Restaurant:          |POST       |/rest/api/1/restaurant  
Get Restaurant: 	        |GET        |/rest/api/1/restaurant/{restaurantId}  
Delete Restaurant	        |DELETE     |/rest/api/1/restaurant/{restaurantId}  
Edit Restaurant		        |PUT        |/rest/api/1/restaurant/{restaurantId}  
Get Menu With all items     |GET	    |/rest/api/1/restaurant/{restaurantId}/menu  
Add Menu With all items     |POST       |/rest/api/1/restaurant/{restaurantId}/menu  
Edit Menu With all items    |PUT        |/rest/api/1/restaurant/{restaurantId}/menu  
Delete Menu                 |DELETE     |/rest/api/1/restaurant/{restaurantId}/menu  

### Vote related actions
Method | Action | URL
---|---|---
Vote                        |POST       |/rest/api/1/restaurant/{restaurantId}/vote  
Get Vote Results            |GET        |/rest/api/1/votes  
