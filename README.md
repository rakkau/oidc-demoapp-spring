# oidc-demoapp-spring
This demo app was developed using the OIDC implementation of Spring Security
 
Sources:
 - [Spring Security](https://projects.spring.io/spring-security-oauth/docs/oauth2.html)
 - [Baeldung](https://www.baeldung.com/spring-security-openid-connect)

## Configure
In order to deploy the demo application, you need to configure a number of parameters in the [configuration file](src/main/resources/application.yml).
Some of those parameters are endpoint URLs of your IdP.
Additionally, you need to create your client application in your IdP and configure the client_id and client_secret. 
The redirect_uri sent by the application is:

    <protocol>://<server-name>:8080/login/oauth2/code/<provider-name> 

The application.yml configuration file requires a name for the OIDC provider. This name is used in the redirect_uri:
```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          PROVIDER_NAME:
            client-id: client-id
            client-secret: client-secret
            client-authentication-method: basic
            authorization-grant-type: authorization_code
            scope:
              - openid
              - email
              - profile
        provider:
          PROVIDER_NAME:
            issuer-uri: provider-uri
            user-name-attribute: name
```

For Gluu Access Manager the issuer-uri is the server root path (without oxauth)

## Build
The build process to compile the source code is based in Apache Maven.
To create the war file, go to the folder where you cloned the repository and run:

    mvn clean package

## Run

### WAR
To execute in a web container like Tomcat, simply copy the file to the webapps folder or deploy to your application server following standard procedures.

### Spring Boot
To build and run the code, in the folder where you cloned the repository, run:

    mvn spring-boot:run

### Docker (pending)
The demo app can run a as Docker container.
Dockerfile and instructions documented [here](docker/)