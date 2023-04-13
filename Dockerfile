# 基础镜像
FROM mirrors.tencent.com/tjdk/tencentkona8
#FROM openjdk:8-jdk-alpine
MAINTAINER saberqu <343103097@qq.com>

#定义时区参数
ENV TZ=Asia/Shanghai
# 设置时区
RUN rm -rf /etc/localtime
RUN apk  --no-cache add tzdata && \
    ln -sv /usr/share/zoneinfo/$TZ /etc/localtime &&\
    echo '$TZ' >/etc/timezone

VOLUME /data/app
EXPOSE 11059
# 定义参数
ARG JAR_FILE=target/*.jar
ENV JAVA_OPTS=""
# 复制jar
COPY ${JAR_FILE} /data/app/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/data/app/app.jar"]

#docker run -e "SPRING_PROFILES_ACTIVE=prod" -p 8080:8080 -t springio/gs-spring-boot-docker
