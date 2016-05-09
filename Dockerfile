FROM java

MAINTAINER Alexandre Gama <alexandre.gama.lima@gmail.com> 

ADD build/libs/mailler-0.0.1.jar mailler.jar

RUN bash -c 'touch /mailler.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/mailler.jar"]