# Kafka Consumer

## Linux 打包部署

1.cd /opt/githubrepository/uv-kafka-consumer/

2.mvn package

3.cp /opt/githubrepository/uv-kafka-consumer/target/uv-kafka-consumer-0.0.1-SNAPSHOT.jar /opt/app/uv-kafka-consumer

4.cp /opt/githubrepository/uv-kafka-consumer/Dockerfile /opt/app/uv-kafka-consumer
  
5. cd /opt/app/uv-kafka-consumer
  
6. docker build -t uv-kafka-consumer:0.0.2 .

### docker 运行

```bash
docker run -p 8081:8080 -t --name uv-kafka-consumer -d uv-kafka-consumer:0.0.2
```

### docker-compose 运行

```bash
 cd /opt/app/uv-kafka-consumer
 vim docker-compose.yml
docker-compose up -d
#docker-compose down --volume
```


## docker-compose.yml 配置

编辑内容如下：
 
 ```yaml
version: '3'
services:
  uvscheduler:
    image: uv-kafka-consumer:0.0.2
    ports:
      - "8081:8080"
    logging:
      driver: gelf
      options:
        gelf-address: udp://xx.xx.xx.xx:12201
        tag: "uv-kafka-consumer"
```
