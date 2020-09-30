FROM centos

RUN yum install -y java

VOLUME /tmp
ADD /pet-clinic-web/target/pet-clinic-web-0.0.3-SNAPSHOT myapp.jar
RUN sh -c 'touch /myapp.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/myapp.jar"]