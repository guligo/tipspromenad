# tipspromenad

## Info

Repository contains server-side code for project called tipspromenad.nu whose main goal was to digitalize traditional Swedish game called tipspromenad. The idea never really saw the light of day, however to give more meaning to the work that has been done, team decided to make server-side part of project open source.

+ [Tipspromenad on Twitter](http://www.twitter.com/tipspromenad)
+ [Tipspromenad on Facebook](http://www.facebook.com/tipspromenad)

## Instructions

Tipspromenad requires at least Oracle JDK 7, Tomcat 7, MySQL server 5 during run-time and Maven 3 for building the source code. Clone this repository, set-up technologies mentioned above and execute command below.

```
mvn clean package cargo:run \
    -Denv.serverHome=<path to Tomcat> \
    -Dcaptcha.privateKey=<reCAPTCHA private key> \
    -Dfb.appId=<Facebook application ID> \
    -Dfb.appSecret=<Facebook application secret>
```

For lazy people there is docker image available.

## Credits

+ [Igors Gulbinskis] (https://se.linkedin.com/pub/igors-gulbinskis/17/128/b41)
+ [Pavel Efimov] (https://se.linkedin.com/in/meetpavelefimov)
+ Askar Abdurazakov
+ Gustav Salomonsson
+ Ksenia Sigakova
