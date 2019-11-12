# Lunch voting system - REST API desing
### Restaurant Operations

Method | Action | URL
---|---|---
Create Restaurant:  |POST       |/rest/api/1/restaurant  
Get Restaurant: 	|GET        |/rest/api/1/restaurant/{restaurantId}  
Delete Restaurant	|DELETE     |/rest/api/1/restaurant/{restaurantId}  
Edit Restaurant		|PUT        |/rest/api/1/restaurant/{restaurantId}  
Get Menu			|GET	    |/rest/api/1/restaurant/{restaurantId}/menu  
Add Menu            |POST       |/rest/api/1/restaurant/{restaurantId}/menu  
Edit Menu           |PUT        |/rest/api/1/restaurant/{restaurantId}/menu  
Delete Menu         |DELETE     |/rest/api/1/restaurant/{restaurantId}/menu  

### Vote related actions
Method | Action | URL
---|---|---
Vote                |POST       |/rest/api/1/restaurant/{restaurantId}/vote  
Get Results         |GET        |/rest/api/1/votes  

### Menu related operations
Method              | Action    | URL
---                 |---        |---
Get Menu Items		|GET	    |/rest/api/1/menu/{menuId}/menuItem  
Add Menu Item       |POST       |/rest/api/1/menu/{menuId}/menuItem  
Edit Menu Item      |PUT        |/rest/api/1/menu/{menuId}/menuItem/{itemId}  
Delete Menu Item    |DELETE     |/rest/api/1/menu/{menuId}/menuItem/{itemId}  
