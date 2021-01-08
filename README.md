[![Codacy Badge](https://app.codacy.com/project/badge/Grade/d4009428931a461ebbb4e3d75ae360f5)](https://www.codacy.com/gh/zaakki/restVote/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=zaakki/restVote&amp;utm_campaign=Badge_Grade)

#Voting system

<h2>Цель проекта:</h2>
Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

The task is:

Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
   - If it is before 11:00 we assume that he changed his mind.
   - If it is after 11:00 then it is too late, vote can't be changed
Each restaurant provides a new menu each day.

## REST API

## The REST API to example app is describe below

## cURLs for users
<br>

  * New user registration 

 Request
 ```sh
 curl -H "Content-Type: application/json" -X POST http://localhost:8080/restVote/rest/admin/users -d "{\"name\":\"NewUser\",\"email\": \"new@yandex.ru\",\"password\":\"password\",\"roles\":[\"USER\"]}" ^ -v -u admin@email.com:admin_password
 ```
 Response 
 
 ```sh
  HTTP/1.1 201
   Location: http://localhost:8080/restVote/rest/admin/users/100019
   Cache-Control: no-cache, no-store, max-age=0, must-revalidate
   Pragma: no-cache
   Expires: 0
   X-XSS-Protection: 1; mode=block
   X-Frame-Options: DENY
   X-Content-Type-Options: nosniff
   Content-Type: application/json;charset=UTF-8
   Transfer-Encoding: chunked
   Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
  {"id":100019,"name":"NewUser","email":"new@yandex.ru","password":"{bcrypt}$2a$10$CXQgS1VyIvrkJ.1FyYJmVONcVzTiDm6D3quL9UG5f69qp0q43kNyK","enabled":true,"registered":"2021-01-08T06:33:02.514+0000","roles":["USER"],"votes":[]}
```
<br>

 * Get all users

 Request
 ```sh
 curl -XGET "http://localhost:8080/restVote/rest/admin/users" ^ -v -u admin@email.com:admin_password
 ```
 Response
 ```sh
 HTTP/1.1 200
 Cache-Control: no-cache, no-store, max-age=0, must-revalidate
 Pragma: no-cache
 Expires: 0
 X-XSS-Protection: 1; mode=block
 X-Frame-Options: DENY
 X-Content-Type-Options: nosniff
 Content-Type: application/json;charset=UTF-8
 Transfer-Encoding: chunked
 Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
  [{"id":100001,"name":"Admin","email":"admin@email.com","password":"{noop}admin_password","enabled":true,"registered":"2019-04-23T09:00:00.000+0000","roles":["ADMIN","USER"],"votes":null},{"id":100000,"name":"User","email":"user@email.com","password":"{noop}user_password","enabled":true,"registered":"2019-04-23T07:00:00.000+0000","roles":["USER"],"votes":null}]
 ```
<br>

 * Get user id 100000 (for user)

 Request
 ```sh
 curl -XGET -H "Content-type: application/json" "http://localhost:8080/restVote/rest/profile" ^ -v -u user@email.com:user_password
 ```
 Response
 ```
 HTTP/1.1 200
 Cache-Control: no-cache, no-store, max-age=0, must-revalidate
 Pragma: no-cache
 Expires: 0
 X-XSS-Protection: 1; mode=block
 X-Frame-Options: DENY
 X-Content-Type-Options: nosniff
 Content-Type: application/json;charset=UTF-8
 Transfer-Encoding: chunked
 Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"

{"id":100000,"name":"User","email":"user@email.com","password":"{noop}user_password","enabled":true,"registered":"2019-04-23T07:00:00.000+0000","roles":["USER"],"votes":null}

```

<br>

 * Get user id 100000 (for the admin)

 Request
 ```
 curl -XGET -H "Content-type: application/json" "http://localhost:8080/restVote/rest/admin/users/100000" ^ -v -u admin@email.com:admin_password
 ```
 Response
 ```
     HTTP/1.1 200
     Cache-Control: no-cache, no-store, max-age=0, must-revalidate
     Pragma: no-cache
     Expires: 0
     X-XSS-Protection: 1; mode=block
     X-Frame-Options: DENY
     X-Content-Type-Options: nosniff
     Content-Type: application/json;charset=UTF-8
     Transfer-Encoding: chunked
     Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
     {"id":100000,"name":"User","email":"user@email.com","password":"{noop}user_password","enabled":true,"registered":"2019-04-23T07:00:00.000+0000","roles":["USER"],"votes":null}
 
 ```
<br>

 * Update user id 100000 (for users)

 Request
 ```
 curl -H "Content-Type: application/json" -X PUT http://localhost:8080/restVote/rest/profile -d "{\"name\":\"New777\",\"email\": \"new777@yandex.ru\",\"password\":\"passwordNew\"}" ^ -v -u user@email.com:user_password
 ```
 Response
 ``` 
     HTTP/1.1 204
     Cache-Control: no-cache, no-store, max-age=0, must-revalidate
     Pragma: no-cache
     Expires: 0
     X-XSS-Protection: 1; mode=block
     X-Frame-Options: DENY
     X-Content-Type-Options: nosniff
     Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
```
<br>

 * Update user id 100000 (for the admin)

 Request
 ```
curl -H "Content-Type: application/json" -X PUT http://localhost:8080/restVote/rest/admin/users/100000 -d "{\"name\":\"New777\",\"email\": \"new777@yandex.ru\",\"password\":\"passwordNew\"}" ^ -v -u admin@email.com:admin_password
 ```
 Response
 ``` 
     HTTP/1.1 204
     Cache-Control: no-cache, no-store, max-age=0, must-revalidate
     Pragma: no-cache
     Expires: 0
     X-XSS-Protection: 1; mode=block
     X-Frame-Options: DENY
     X-Content-Type-Options: nosniff
     Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"

```
<br>

 * Delete user id 100000 (for users)

 Request
 ```
curl -XDELETE -H "Content-type: application/json" "http://localhost:8080/restVote/rest/profile" ^ -v -u user@email.com:user_password
```
 Response
 ```
     HTTP/1.1 204
     Cache-Control: no-cache, no-store, max-age=0, must-revalidate
     Pragma: no-cache
     Expires: 0
     X-XSS-Protection: 1; mode=block
     X-Frame-Options: DENY
     X-Content-Type-Options: nosniff
     Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
```

<br>

 * Delete user id 100000 (for the admin)

 Request
 ```
curl -XDELETE -H "Content-type: application/json" "http://localhost:8080/restVote/rest/admin/users/100000" ^ -v -u admin@email.com:admin_password
```
 Response
 ```
     HTTP/1.1 204
     Cache-Control: no-cache, no-store, max-age=0, must-revalidate
     Pragma: no-cache
     Expires: 0
     X-XSS-Protection: 1; mode=block
     X-Frame-Options: DENY
     X-Content-Type-Options: nosniff
     Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
```
 
# cURLs for dishes
<br>

 * Get all dishes

 Request
 ```
curl -XGET "http://localhost:8080/restVote/rest/dishes"  ^ -v -u admin@email.com:admin_password
```
 Response
 ```
     HTTP/1.1 200
     Cache-Control: no-cache, no-store, max-age=0, must-revalidate
     Pragma: no-cache
     Expires: 0
     X-XSS-Protection: 1; mode=block
     X-Frame-Options: DENY
     X-Content-Type-Options: nosniff
     Content-Type: application/json;charset=UTF-8
     Transfer-Encoding: chunked
     Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
     [{"id":100012,"name":"Bugs","price":20000},{"id":100011,"name":"Hamburger","price":10000},{"id":100013,"name":"McSteak","price":11100},{"id":100014,"name":"McVine","price":22200},{"id":100010,"name":"Steak","price":100000},{"id":100015,"name":"Takoburger","price":33300}]
```
 
<br>

 * Get dish id 100010 for menu id 100005

 Request
 ```
curl -XGET -H "Content-type: application/json" "http://localhost:8080/restVote/rest/dishes/100010/menus/100005" ^ -v -u admin@email.com:admin_password
```
 Response
 ```
     HTTP/1.1 200
     Cache-Control: no-cache, no-store, max-age=0, must-revalidate
     Pragma: no-cache
     Expires: 0
     X-XSS-Protection: 1; mode=block
     X-Frame-Options: DENY
     X-Content-Type-Options: nosniff
     Content-Type: application/json;charset=UTF-8
     Transfer-Encoding: chunked
     Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
     {"id":100010,"name":"Steak","price":100000}
```
<br>

  * Get dish id 100007
 
  Request
  ```
  curl -XGET -H "Content-type: application/json" "http://localhost:8080/restVote/rest/dishes/menus/100007" ^ -v -u admin@email.com:admin_password
```
  Response
  ``` 
      HTTP/1.1 200
      Cache-Control: no-cache, no-store, max-age=0, must-revalidate
      Pragma: no-cache
      Expires: 0
      X-XSS-Protection: 1; mode=block
      X-Frame-Options: DENY
      X-Content-Type-Options: nosniff
      Content-Type: application/json;charset=UTF-8
      Transfer-Encoding: chunked
      Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
      [{"id":100012,"name":"Bugs","price":20000},{"id":100013,"name":"McSteak","price":11100},{"id":100014,"name":"McVine","price":22200}]

```
<br>

   * Get dish by date : 2020-11-19  
  
   Request
   ```
curl -XGET -H "Content-type: application/json" "http://localhost:8080/restVote/rest/dishes/by?date=2020-11-19" ^ -v -u admin@email.com:admin_password
```
   Response
   ``` 
       HTTP/1.1 200
       Cache-Control: no-cache, no-store, max-age=0, must-revalidate
       Pragma: no-cache
       Expires: 0
       X-XSS-Protection: 1; mode=block
       X-Frame-Options: DENY
       X-Content-Type-Options: nosniff
       Content-Type: application/json;charset=UTF-8
       Transfer-Encoding: chunked
      Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
       [{"id":100010,"name":"Steak","price":100000}]

```
   
<br> 

  * Create new dishes for menu id 100005
 
  Request
  ```
curl -XPOST -H "Content-type: application/json" -d "{\"name\":\"Created\",\"price\":100000}" "http://localhost:8080/restVote/rest/dishes/menus/100005" -v -u admin@email.com:admin_password
```
  Response
  ```
      HTTP/1.1 201
      Location: http://localhost:8080/restVote/rest/dishes/100005/menus/100019
      Cache-Control: no-cache, no-store, max-age=0, must-revalidate
      Pragma: no-cache
      Expires: 0
      X-XSS-Protection: 1; mode=block
      X-Frame-Options: DENY
      X-Content-Type-Options: nosniff
      Content-Type: application/json;charset=UTF-8
      Transfer-Encoding: chunked
      Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
      {"id":100019,"name":"Created","price":100000}

```
<br>

 * Update dish id 100010 for menu id 100005

 Request
 ```
curl -XPUT -H "Content-type: application/json" -d "{\"id\":100010,\"name\":\"Update\",\"price\":100500}" "http://localhost:8080/restVote/rest/dishes/100010/menus/100005" -v -u admin@email.com:admin_password
```
 Response
```
     HTTP/1.1 204
    Cache-Control: no-cache, no-store, max-age=0, must-revalidate
    Pragma: no-cache
    Expires: 0
    X-XSS-Protection: 1; mode=block
    X-Frame-Options: DENY
    X-Content-Type-Options: nosniff
    Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
```
<br> 

 * Delete dish id 100010 for menu 100005

 Request
 ```
curl -XDELETE "http://localhost:8080/restVote/rest/dishes/100010/menus/100005"  ^ -v -u admin@email.com:admin_password
```
 Response
 ```
     HTTP/1.1 204
     Cache-Control: no-cache, no-store, max-age=0, must-revalidate
     Pragma: no-cache
     Expires: 0
     X-XSS-Protection: 1; mode=block
     X-Frame-Options: DENY
     X-Content-Type-Options: nosniff
      Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
```
# cURLs for restaurant 
<br>

 * Get all restaurant 

 Request
 ```
curl -XGET "http://localhost:8080/restVote/rest/restaurants"  ^ -v -u admin@email.com:admin_password
```
 Response
 ```
     HTTP/1.1 200
     Cache-Control: no-cache, no-store, max-age=0, must-revalidate
     Pragma: no-cache
     Expires: 0
     X-XSS-Protection: 1; mode=block
     X-Frame-Options: DENY
     X-Content-Type-Options: nosniff
     Content-Type: application/json;charset=UTF-8
     Transfer-Encoding: chunked
      Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
     [{"id":100002,"name":"BurgerKing"},{"id":100003,"name":"KFC"},{"id":100004,"name":"McDonalds"}]
```
 
<br>

 * Get restaurant id 100002

 Request
 ```
curl -XGET -H "Content-type: application/json" "http://localhost:8080/restVote/rest/restaurants/100002" ^ -v -u admin@email.com:admin_password
```
 Response
 ```
     HTTP/1.1 200
     Cache-Control: no-cache, no-store, max-age=0, must-revalidate
     Pragma: no-cache
     Expires: 0
     X-XSS-Protection: 1; mode=block
     X-Frame-Options: DENY
     X-Content-Type-Options: nosniff
     Content-Type: application/json;charset=UTF-8
     Transfer-Encoding: chunked
      Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
     {"id":100002,"name":"BurgerKing"}
```
 
<br>

  * Get dish by restaurant name "KFC"
 
  Request
  ```
curl -XGET -H "Content-type: application/json" "http://localhost:8080/restVote/rest/restaurants/by?name=KFC" ^ -v -u admin@email.com:admin_password
```
  Response
  ```
      HTTP/1.1 200
      Cache-Control: no-cache, no-store, max-age=0, must-revalidate
      Pragma: no-cache
      Expires: 0
      X-XSS-Protection: 1; mode=block
      X-Frame-Options: DENY
      X-Content-Type-Options: nosniff
      Content-Type: application/json;charset=UTF-8
      Transfer-Encoding: chunked
      Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
      {"id":100003,"name":"KFC"}
```
   
<br> 

  * Create new restaurant
 
  Request
  ```
curl -XPOST -H "Content-type: application/json" -d "{\"name\":\"Created\"}" "http://localhost:8080/restVote/rest/restaurants" -v -u admin@email.com:admin_password
```
  Response
  ```   
      HTTP/1.1 201
      Location: http://localhost:8080/restVote/rest/restaurants/100019
      Cache-Control: no-cache, no-store, max-age=0, must-revalidate
      Pragma: no-cache
      Expires: 0
      X-XSS-Protection: 1; mode=block
      X-Frame-Options: DENY
      X-Content-Type-Options: nosniff
      Content-Type: application/json;charset=UTF-8
      Transfer-Encoding: chunked
      Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
      {"id":100019,"name":"Created"}
```
<br>

 * Update restaurant id 100002

 Request
 ```
curl -XPUT -H "Content-type: application/json" -d "{\"id\":100002,\"name\":\"Update\"}" "http://localhost:8080/restVote/rest/restaurants/100002" -v -u admin@email.com:admin_password
```
 Response
 ```    
     HTTP/1.1 204
     Cache-Control: no-cache, no-store, max-age=0, must-revalidate
     Pragma: no-cache
     Expires: 0
     X-XSS-Protection: 1; mode=block
     X-Frame-Options: DENY
     X-Content-Type-Options: nosniff
     Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
```

<br> 

 * Delete restaurant id 100002

 Request
 ```
curl -XDELETE "http://localhost:8080/restVote/rest/restaurants/100002"  ^ -v -u admin@email.com:admin_password
```
 Response
 ```
     HTTP/1.1 204
     Cache-Control: no-cache, no-store, max-age=0, must-revalidate
     Pragma: no-cache
     Expires: 0
     X-XSS-Protection: 1; mode=block
     X-Frame-Options: DENY
     X-Content-Type-Options: nosniff
     Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
```

# cURLs for menus  
<br>

 * Get all menus

 Request
 ```
 curl -XGET "http://localhost:8080/restVote/rest/menus"  ^ -v -u admin@email.com:admin_password
```
 Response
 ```
     HTTP/1.1 200
     Cache-Control: no-cache, no-store, max-age=0, must-revalidate
     Pragma: no-cache
     Expires: 0
     X-XSS-Protection: 1; mode=block
     X-Frame-Options: DENY
     X-Content-Type-Options: nosniff
     Content-Type: application/json;charset=UTF-8
     Transfer-Encoding: chunked
     Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
     [{"id":100009,"date":"2020-06-12","dishes":null,"restaurant":null},{"id":100005,"date":"2020-11-19","dishes":null,"restaurant":null},{"id":100006,"date":"2020-11-20","dishes":null,"restaurant":null},{"id":100007,"date":"2021-01-08","dishes":null,"restaurant":null},{"id":100008,"date":"2021-01-08","dishes":null,"restaurant":null}]
```
<br>

 * Get menu id 100005 for restaurant id 100002

 Request
 ```
curl -XGET -H "Content-type: application/json" "http://localhost:8080/restVote/rest/menus/100005/restaurants/100002" ^ -v -u admin@email.com:admin_password
```
 Response
 ```
     HTTP/1.1 200
     Cache-Control: no-cache, no-store, max-age=0, must-revalidate
     Pragma: no-cache
     Expires: 0
     X-XSS-Protection: 1; mode=block
     X-Frame-Options: DENY
     X-Content-Type-Options: nosniff
     Content-Type: application/json;charset=UTF-8
     Transfer-Encoding: chunked
     Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
     {"id":100005,"date":"2020-11-19","dishes":null,"restaurant":null}
```
<br>

  * Get menu with dishes by date 2020-11-19 
 
  Request
  ```
curl -XGET -H "Content-type: application/json" "http://localhost:8080/restVote/rest/menus/byDate?date=2020-11-19" ^ -v -u admin@email.com:admin_password
```
  Response
  ```
      HTTP/1.1 200
      Cache-Control: no-cache, no-store, max-age=0, must-revalidate
      Pragma: no-cache
      Expires: 0
      X-XSS-Protection: 1; mode=block
      X-Frame-Options: DENY
      X-Content-Type-Options: nosniff
      Content-Type: application/json;charset=UTF-8
      Transfer-Encoding: chunked
      Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
      [{id":100005,"date":"2020-11-19","dishes":[{"id":100010,"name":"Steak","price":100000}],"restaurant":null}]
```
  
<br>

   * Get menus with dishes by current date
  
   Request
   ```
curl -XGET -H "Content-type: application/json" "http://localhost:8080/restVote/rest/menus/byDate?date" ^ -v -u admin@email.com:admin_password
```
   Response
   ```
       HTTP/1.1 200
       Cache-Control: no-cache, no-store, max-age=0, must-revalidate
       Pragma: no-cache
       Expires: 0
       X-XSS-Protection: 1; mode=block
       X-Frame-Options: DENY
       X-Content-Type-Options: nosniff
       Content-Type: application/json;charset=UTF-8
       Transfer-Encoding: chunked
       Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
       [{"id":100007,"date":"2021-01-08","dishes":[{"id":100012,"name":"Bugs","price":20000},{"id":100013,"name":"McSteak","price":11100},{"id":100014,"name":"McVine","price":22200}],"restaurant":null},{"id":100008,"date":"2021-01-08","dishes":[{"id":100015,"name":"Takoburger","price":33300}],"restaurant":null}]
```
   
<br>

   * Get all menus by restaurant with name: "KFC"
  
   Request
   ```
curl -XGET -H "Content-type: application/json" "http://localhost:8080/restVote/rest/menus/byRestaurant?name=KFC" ^ -v -u admin@email.com:admin_password
```
   Response
   ```
       HTTP/1.1 200
       Cache-Control: no-cache, no-store, max-age=0, must-revalidate
       Pragma: no-cache
       Expires: 0
       X-XSS-Protection: 1; mode=block
       X-Frame-Options: DENY
       X-Content-Type-Options: nosniff
       Content-Type: application/json;charset=UTF-8
       Transfer-Encoding: chunked
       Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
       [{"id":100006,"date":"2020-11-20","dishes":null,"restaurant":null},{"id":100007,"date":"2021-01-08","dishes":null,"restaurant":null}]

```

<br>

   * Get menu by restaurant with name: KFC and date: 2020-11-20
  
   Request
   ```
curl -XGET -H "Content-type: application/json" "http://localhost:8080/restVote/rest/menus/byRestaurantAndDate?name=KFC&date=2020-11-20" ^ -v -u admin@email.com:admin_password
```
   Response
   ```
       HTTP/1.1 200
       Cache-Control: no-cache, no-store, max-age=0, must-revalidate
       Pragma: no-cache
       Expires: 0
       X-XSS-Protection: 1; mode=block
       X-Frame-Options: DENY
       X-Content-Type-Options: nosniff
       Content-Type: application/json;charset=UTF-8
       Transfer-Encoding: chunked
       Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
       {"id":100006,"date":"2020-11-20","dishes":null,"restaurant":null}
   
```
   
<br>

   * Get menu by id: 100006
  
   Request
   ```
curl -XGET -H "Content-type: application/json" "http://localhost:8080/restVote/rest/menus/byId?id=100006" ^ -v -u admin@email.com:admin_password
```
   Response
   ```
       HTTP/1.1 200
       Cache-Control: no-cache, no-store, max-age=0, must-revalidate
       Pragma: no-cache
       Expires: 0
       X-XSS-Protection: 1; mode=block
       X-Frame-Options: DENY
       X-Content-Type-Options: nosniff
       Content-Type: application/json;charset=UTF-8
       Transfer-Encoding: chunked
       Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
       {"id":100006,"date":"2020-11-20","dishes":null,"restaurant":null}
```
   
<br> 

  * Create new menu for restaurant id : 100003
 
  Request
  ```
curl -XPOST -H "Content-type: application/json" -d "{\"date\":\"2021-01-01\"}" "http://localhost:8080/restVote/rest/menus/restaurants/100003" -v -u admin@email.com:admin_password
```
  Response
  ```
      HTTP/1.1 201
      Location: http://localhost:8080/restVote/rest/menus
      Cache-Control: no-cache, no-store, max-age=0, must-revalidate
      Pragma: no-cache
      Expires: 0
      X-XSS-Protection: 1; mode=block
      X-Frame-Options: DENY
      X-Content-Type-Options: nosniff
      Content-Type: application/json;charset=UTF-8
      Transfer-Encoding: chunked
      Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
      {"id":100019,"date":"2019-01-01","dishes":[],"restaurant":null}
```
  
<br>

 * Update  menu with id 100006 for restaurant id 100003

 Request
 ```
curl -XPUT -H "Content-type: application/json" -d "{\"date\":\"2019-01-02\"}" "http://localhost:8080/restVote/rest/menus/100006/restaurants/100003" -v -u admin@email.com:admin_password
```
 Response
 ```
     HTTP/1.1 204
     Cache-Control: no-cache, no-store, max-age=0, must-revalidate
     Pragma: no-cache
     Expires: 0
     X-XSS-Protection: 1; mode=block
     X-Frame-Options: DENY
     X-Content-Type-Options: nosniff
     Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
```

<br> 

 * Delete menu with id 100005 for restaurant id 100002:

 Request
 ```
curl -XDELETE "Content-type: application/json" "http://localhost:8080/restVote/rest/menus/100005/restaurants/100002" ^ -v -u admin@email.com:admin_password
```
  Response
   ```
       HTTP/1.1 204
       Cache-Control: no-cache, no-store, max-age=0, must-revalidate
       Pragma: no-cache
       Expires: 0
       X-XSS-Protection: 1; mode=block
       X-Frame-Options: DENY
       X-Content-Type-Options: nosniff
       Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
  ```

# cURLs for votes

<br> 

  * Get all votes by date: 2020-11-20
 
  Request
  ```
curl -XGET -H "Content-type: application/json" "http://localhost:8080/restVote/rest/votes/byDate?date=2020-11-20" ^ -v -u admin@email.com:admin_password
```
  Response
  ```
      HTTP/1.1 200
      Cache-Control: no-cache, no-store, max-age=0, must-revalidate
      Pragma: no-cache
      Expires: 0
      X-XSS-Protection: 1; mode=block
      X-Frame-Options: DENY
      X-Content-Type-Options: nosniff
      Content-Type: application/json;charset=UTF-8
      Transfer-Encoding: chunked
      Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
      [{"id":100016,"user":null,"menu":null,"date":"2020-11-20"},{"id":100017,"user":null,"menu":null,"date":"2020-11-20"}]

```
  
<br>

 * Create vote for menu with id:100007

 Request
 ```
curl -XPOST -H "Content-type: application/json" "http://localhost:8080/restVote/rest/votes/menus/100007" ^ -v -u admin@email.com:admin_password
```
 Response
 ```
     HTTP/1.1 200
     Cache-Control: no-cache, no-store, max-age=0, must-revalidate
     Pragma: no-cache
     Expires: 0
     X-XSS-Protection: 1; mode=block
     X-Frame-Options: DENY
     X-Content-Type-Options: nosniff
     Content-Length: 0
     Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
```

<br> 

 * Update vote for menu with id:100008

 Request
 ```
curl -XPUT -H "Content-type: application/json" "http://localhost:8080/restVote/rest/votes/menus/100008" ^ -v -u admin@email.com:admin_password
```
  Response
 ```
     HTTP/1.1 422
     Cache-Control: no-cache, no-store, max-age=0, must-revalidate
     Pragma: no-cache
     Expires: 0
     X-XSS-Protection: 1; mode=block
     X-Frame-Options: DENY
     X-Content-Type-Options: nosniff
     Content-Type: application/json;charset=UTF-8
     Transfer-Encoding: chunked
     Date: Current date with format "Day of the week, day month year hours:minutes:seconds timezone"
```