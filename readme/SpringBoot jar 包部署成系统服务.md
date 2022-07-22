# SpringBoot jar 包部署成系统服务


## 相关脚本
* hello-world.service
    ```bash
    [Unit]
    Description=hello-world SpringBoot web service
    After=network.target
    After=syslog.target
    
    [Service]
    Type=simple
    Environment="LOG_PATH=/data/log/hello-world"
    Environment="SERVICE_LOG_FOLDER=/data/log"
    
    ExecStartPre=/usr/bin/bash -c 'if [ ! -d ${LOG_PATH} ]; then mkdir -p ${LOG_PATH}; fi'
    ExecStart=/usr/local/java/jdk/bin/java -server -Xms2g -Xmx2g -XX:+UseG1GC -verbose:gc -Xloggc:/data/log/hello-world/hello-world-gc.log -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/data/log/hello-world -Duser.timezone=GMT+8 -jar /data/software/hello-world/spring-boot-01-helloworld-1.0.-SNAPSHOT.jar --spring.config.location=/data/software/hello-world/application.properties
    ExecReload=/usr/bin/kill -s HUP $MAINPID
    ExecStop=/usr/bin/kill -s QUIT $MAINPID
    Restart=always
    RestartSec=10
    
    [Install]
    WantedBy=multi-user.target
    ```

* application.properties
    ```properties
    server.port=8090
    spring.profiles.active=pro
    logging.level.root=INFO
    ```

* install.sh
    ```bash
    #!/bin/bash
    cp hello-world.service /etc/systemd/system/
    systemctl enable hello-world.service
    ```

## 安装服务并启动
```bash
// 安装服务
bash ./install.sh

// 启动服务
systemctl start hello-world

// 查看服务状态
systemctl status hello-world
```