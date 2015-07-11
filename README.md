# Tipspromenad.nu

## Info

Repository contains server-side code for project called Tipspromenad.nu whose main goal was to digitalize traditional Swedish game called "tipspromenad". The idea never really saw the light of day, however to give more meaning to the work that has been done, team decided to make server-side part of project open source.

Some insight into progress we made:

+ [Tipspromenad on Twitter](http://www.twitter.com/tipspromenad)
+ [Tipspromenad on Facebook](http://www.facebook.com/tipspromenad)

## Instructions

Tipspromenad.nu requires at least Oracle JDK 7, Tomcat 7 and MySQL 5 server during run-time and Maven 3 for building the source code. However, in order to avoid all steps involved into deployment process, [Docker image] (https://registry.hub.docker.com/u/guligo/tipspromenad) for Tipspromenad.nu has been created. To get Tipspromenad.nu running, introduce yourself to [Docker] (https://www.docker.com) platform and use commands below to start up container:

```
docker pull guligo/tipspromenad:latest
docker run -t -p 8080:8080 guligo/tipspromenad:latest sh /tmp/start.sh \
    <reCAPTCHA private key> \
    <Facebook application ID> \
    <Facebook application secret>
```

Once container is up and running, use following URL:

```
http://<your host>:8080/tipspromenad
```

In case you're interested in building and deploying the project outside of Docker image, then follow [Dockerfile] (https://github.com/guligo/docker-images/blob/master/tipspromenad/Dockerfile) and make conclusions on how to do it yourself.

## Credits

+ [guligo] (https://se.linkedin.com/pub/igors-gulbinskis/17/128/b41)
+ [Pavel Efimov] (https://se.linkedin.com/in/meetpavelefimov)
