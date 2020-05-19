# Spring-Security

Spring Authorization via JWT.

We can directly use for realtime.
(username and password are hardcode in service layer.Need to change by implememt JPA repositories)

UserName : admin
Password : login

Session are stateless. 
So JWT not stored in session.

step 1:
	 Authenticate userid and password.
	 Generate JWT token.

step 2:
	
	Extract JWT token from header.
	Validate JWT and set into security context path.
	
   
	For each request, we need to examine once if header contains jwt token.
	User filter to do that process.
	  extends OncePerRequestFilter
	  
	  
	 Decode JWT https://jwt.io/
	 
	API :
	
	Need to send authorization header on each request except authenticate endpoint(cofiguration written for not to do authentication for "/authenticate" post call).
	
	http://localhost:8080
	
	header :
	Authorization : Bearer <JWT>