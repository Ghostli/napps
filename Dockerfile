FROM java:8
MAINTAINER Humblehound <dejtabejz@gmail.com>
VOLUME /tmp
ADD build/libs/nappserver-0.1.0.jar app.jar
ADD keystore.p12 /keystore.p12
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
