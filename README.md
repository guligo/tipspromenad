# tipspromenad

## Info

This repository contains server-side code for project called Tipspromenad.nu whose main goal was to digitalize traditional Swedish game called tipspromenad. The idea never really saw the light of day, however to give bit of meaning to the work that has been done, team decided to make server-side code open source.



+ [Tipspromenad on Twitter](http://www.twitter.com/tipspromenad)
+ [Tipspromenad on Facebook](http://www.facebook.com/tipspromenad)

## Instructions

Tipspromenad requires at least Oracle JDK 7, Tomcat 7, MySQL server 5 during run-time and Maven 3 for build-time activities. Clone this repository, set-up technologies mentioned above and execute command below.

```
mvn clean package cargo:run \
    -Denv.serverHome=<path to Tomcat> \
    -Dcaptcha.privateKey=<reCAPTCHA private key> \
    -Dfb.appId=<Facebook application ID> \
    -Dfb.appSecret=<Facebook application secret>
```

# Authors

+ [Igors Gulbinskis] (https://se.linkedin.com/pub/igors-gulbinskis/17/128/b41)
+ [Pavel Efimov] (https://se.linkedin.com/in/meetpavelefimov)
+ Askar Abdurazakov
+ Gustav Salomonsson
+ Ksenia Sigakova
