#MODERN WEB PROJECT

#Last update : 
2020.04.13


#적용 기술
* BackEnd : spring-boot 2.x , gradle 4.10, Jpa, oauth2 login (google, naver)

* FrontEnd  : mustache


##application-oauth.properties
* 아래 파일을 추가

spring.security.oauth2.client.registration.google.client-id=구글클라이언트ID
spring.security.oauth2.client.registration.google.client-secret=구글클라이언트시크릿
spring.security.oauth2.client.registration.google.scope=profile,email

spring.security.oauth2.client.registration.naver.client-id=네이버클라이언트ID
spring.security.oauth2.client.registration.naver.client-secret=네이버클라이언트시크릿
spring.security.oauth2.client.registration.naver.redirect-uri={baseUrl}/{action}/oauth2/code/{registrationId}
spring.security.oauth2.client.registration.naver.authorization-grant-type=authorization_code
spring.security.oauth2.client.registration.naver.scope=name,email,profile_image
spring.security.oauth2.client.registration.naver.client-name=Naver

spring.security.oauth2.client.provider.naver.authorization-uri=https://nid.naver.com/oauth2.0/authorize
spring.security.oauth2.client.provider.naver.token-uri=https://nid.naver.com/oauth2.0/token
spring.security.oauth2.client.provider.naver.user-info-uri=https://openapi.naver.com/v1/nid/me
spring.security.oauth2.client.provider.naver.user-name-attribute=response
