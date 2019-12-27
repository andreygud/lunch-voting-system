# Lunch voting system - REST API desing
### Restaurant Operations

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
