#MODERN WEB PROJECT

##Last update : 
2020.04.13


##적용 기술
* BackEnd : spring-boot 2.x , gradle 4.10, Jpa

* FrontEnd  : mustache


* 보안을 위해 인증 파일 삭제함
아래 파일을 추가해야 한다. 
파일명 : 
application-oauth.properties

내용 : 
spring.security.oauth2.client.registration.google.client-id=클라이언트 아이디
spring.security.oauth2.client.registration.google.client-secret=시크릿 아이디 
spring.security.oauth2.client.registration.google.scope=profile,email

