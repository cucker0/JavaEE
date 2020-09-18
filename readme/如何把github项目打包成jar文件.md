如何把github项目打包成jar文件
==

## 为什么需要自己打包jar文件
```
有不少java项目是需要依赖第三方组件的，我们一般使用jar包，
但是网上很多开源项目的最新版本没有给出现在的jar包供下载，尤其是github上的开源项目。
只提供了源码，我们该如何打包成jar包呢
```

## 操作步骤

```
一般的项目中，根目录下包含有如下文件或目录
src         ... ... 程序代码
mvnw        ... ... maven wrapper，用于maven版本和期望的版本不一致。用于unix环境
mvnw.cmd    ... ... 功能同上，用于windows环境
pom.xml     ... ... Maven的相关配置文件，指定了maven坐标、依赖关系、版本等
```

1. 安装maven
    ``` bash
    wget https://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
    yum -y install apache-maven
    mvn -v
    ```

2. clone项目的稳定版或最新版
    ```bash
    cd 存放项目的目录
    git clone https://github.com/xx
    ## 如果是gz、zip包等，请先解压，然后cd 到解压后的目录中，一般包含有pom.xml文件
    # 执行下面的命令，开始打包，这里可能要等上很久，少则几分钟，多则半小时，甚至几个小时都有可能
    # 打包成功后，会在 当前目录下的target目录中生成jar包
    mvn package
    ```


* 让maven下载使用国内镜像
    ```
    # 查找已安装的maven版
    rpm -qa |grep maven
    # 查看已经安装的maven相关的文件，这样可以找到maven的配置文件settings.xml路径
    rpm -ql apache-maven-3.xx.xx.noarch
    ```
    
    maven配置文件/etc/maven/settings.xml中的<settings>标签中添加如下内容，即使用阿里云maven镜像
    ```
      <mirrors>
        <!--
         | Specifies a repository mirror site to use instead of a given repository. The repository that
         | this mirror serves has an ID that matches the mirrorOf element of this mirror. IDs are used
         | for inheritance and direct lookup purposes, and must be unique across the set of mirrors.
         | -->
        <mirror>

          <id>alimaven</id>
          <mirrorOf>central</mirrorOf>
          <name>aliyun maven</name>
          <url>http://maven.aliyun.com/nexus/content/repositories/central/</url>
          
        </mirror>
      </mirrors>
    ```