# vim Dockerfile
# 镜像构建命令
# docker build -t 镜像名称[:tag] .
# 容器启动命令
# docker run -d --restart always --name 容器名称 -p 8080:8000 -v /data/docker_data_volume_list/jar包文件夹:/jar 镜像名称[:tag]
FROM openjdk:11
MAINTAINER 295672923@qq.com
RUN mkdir -p /jar
WORKDIR /jar
ENV TZ Asia/Shanghai
VOLUME ["/jar"]
EXPOSE 8080
ENTRYPOINT ["java","-jar","后端代码.jar"]