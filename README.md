# tipspromenad

This repository contains server-side code for project called Tipspromenad.nu whose main goal was to digitalize traditional Swedish game called tipspromenad.

[Tipspromenad on Twitter](http://www.twitter.com/tipspromenad)
[Tipspromenad on Facebook](http://www.facebook.com/tipspromenad)

# Build instructions

Tipspromenad requires at least Oracle JDK 7, Tomcat 7 and MySQL server 5.

```
mvn clean package cargo:run -Denv.serverHome=<path to Tomcat> -Dcaptcha.privateKey=<reCAPTCHA private key> -Dfb.appId=<Facebook application ID> -Dfb.appSecret=<Facebook application secret>
```

# Authors

TODO.

