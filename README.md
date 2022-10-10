# SpringbootKafkaDemo
Springboot + kafka + docker
# Docker启动实例

## 实例一（使用容器连接） 

> 修改host  127.0.0.1 kafka.example.com

### Zookeeper

#### 启动

~~~shell
docker run -d -e TZ="Asia/Shanghai" -p 2181:2181 --name zookeeper-server -e ALLOW_ANONYMOUS_LOGIN=yes --restart always zookeeper
# -e TZ="Asia/Shanghai" # 指定上海时区 
# -d # 表示在一直在后台运行容器
# -p 2181:2181 # 对端口进行映射，将本地2181端口映射到容器内部的2181端口
# --name # 设置创建的容器名称
# -v $PWD/data:/data  
# -v 将本地目录(文件)挂载到容器指定目录；
# --restart always #始终重新启动zookeeper
~~~

###使用zkcli连接

~~~shell
docker run -it --rm --link zookeeper-server:zookeeper-server zookeeper zkCli.sh -server zookeeper
# 重新启动一个zookeeper容器来作为客户端连接zookeeper
# -server zookeeper是启动zkCli.sh的参数
~~~

#### Zookeeper命令

```text
创建节点 create
create [-s] [-e] [-c] [-t ttl] path [data] [acl]

-s 创建有序节点
如果在创建znode时，我们使用排序标志的话，ZooKeeper会在我们指定的 znode 名字后面增加一个数字。我们继续加入相同名字的znode时，这个数字会不断增加。这个序号的计数器是由这些排序znode的父节点来维护的。

-e 创建临时节点
znode有两种类型：ephemeral 和 persistent。在创建znode时，我们指定znode的类型，并且在之后不会再被修改。当创建znode的客户端的session结束后，ephemeral类型的znode将被删除。persistent类型的znode在创建以后，就与客户端没什么联系了，除非主动去删除它，否则他会一直存在。Ephemeral znode没有任何子节点。
```

### kafka

#### 启动

~~~shell
 docker run -d --name kafka --hostname kafka.example.com  -p 9092:9092 -p 9093:9093 --link zookeeper-server
 -e ALLOW_PLAINTEXT_LISTENER=yes 
 -e KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=CLIENT:PLAINTEXT,EXTERNAL:PLAINTEXT
 -e KAFKA_CFG_LISTENERS=CLIENT://:9092,EXTERNAL://:9093
 -e KAFKA_CFG_ADVERTISED_LISTENERS=CLIENT://kafka.example.com:9092,EXTERNAL://kafka.example.com:9093
 -e KAFKA_CFG_INTER_BROKER_LISTENER_NAME=CLIENT
 -e KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper-server:2181 bitnami/kafka

# --link 关联容器
~~~

#### 使用kafka客户端创建topic

~~~shell
docker run -it --rm  --link kafka  -e AFKA_CFG_ZOOKEEPER_CONNECT=zookeeper-server:2181   bitnami/kafka:latest kafka-topics.sh --create  --bootstrap-server kafka:9092 --replication-factor 1 --partitions 1 --topic mytest
~~~
