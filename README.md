# Linux 배포 방법

## 개요 

> Spring Boot의 Jar이나 War같은 경우 개발 환경에 따라 사용을 하며
> AWS사용시 Jenkins나 Filezilla를 사용 하여 Cloud Server에 배포 합니다.
> 이후 putty(Window), ShellCraft(Mac)을 사용 하여 명령어를 실행합니다.
> Jenkins경우 파일 배포 및 명령어를 사용하여 자동 배포가 되지만 Filezilla는 파일만 업로드 가능 하므로 명령어 작업을 따로 해야 합니다.

## linux 사용 전

> apt update 필요
```Bash
    sudo apt update
```

## DB 설정

> 현재 프로젝트는 h2를 사용하였지만 h2는 대부분 개발용 으로 사용하므로 Mysql 기준으로 작성 하겠습니다.

### 설치

1. RDS
    * RDS를 사용하면 Application.yml 에 주소 및 계정만 작성하면 바로 사용 가능합니다.
    * RDS 생성 하면서 외부 사용자 계정을 따로 만들어 줄수 있습니다.
    * 단. 비용이 많이 발생합니다.

#### EC2 내부

* 설치
    1. sudo apt install mysql-server-'사용 버전'
        * apt-get를 업그레이드를 해야 할수도 있습니다.
* 사용자 생성
    1. 외부 접근 사용자 추가
        * create user 'test'@'%' identified by '0000';
    2. 권한 추가
        * grant all privileges on 'DB명'.'테이블 이름' to 'test'@'%';
        * 모든 DB, table권한은 *.*을 사용하면된다.

## WAR, JAR

### WAR

> WAR은 Spring Boot 프로젝트 파일(서버 제외)만 존재 하므로 Linux에 Tomcat을 사용해야 합니다.

1. 톰캣 설치

```bash
    sudo apt install tomcat9 tomcat9-admin
```

2. 톰캣 실행

```bash
    sudo systemctl start tomcat
```

3. war 파일 이동

```bash
    sudo mv war파일.war /var/lib/tomcat9/webapps
```

4. 톰캑 재 시동

```bash
    sudo systemctl restart tomcat
```

### JAR

> JAR은 자체에 서버도 포함 되어 있으므로 바로 실행 하면된다.
> java로만 실행하면 백그라운드로 실행이 되지 않아 접속을 끊으면 서버도 멈추 므로 nohup을 사용한다.

```bash
    nohup java -jar jar파일.jar&
```

#### 실행시 환경 설정

> -Dspring.profiles.active=prod 등 설정하여 원하는 properties 파일을 사용할수 있다.

## Nginx

> nginx를 사용하여 포트 접근을 제어 한다.

1. nginx 설치

```bash
    sudo apt install nginx
```

1. 포트 접근 파일 생성
> nginx /etc/nginx/sites-available/파일 명

```
server {
        listen 80; // 80 포트로 받고
        listen [::]:80;


        server_name 도메인 주소;

        location / {
                proxy_pass http://localhost:8080; // 8080포트로 접근 시킨다.
                proxy_set_header Host $host;
                proxy_set_header X-Real-IP $remote_addr;
                proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
                proxy_set_header X-Forwarded-Proto $scheme;
                proxy_set_header Upgrade $http_upgrade;
                proxy_set_header Connection "upgrade";
        }
}

```

2. sites-enabled link 설정

```bash
    ln -s /etc/nginx/sites-available/파일 명 /etc/nginx/sites-enabled/파일 명
```

3. nginx 재시동

```bash
    sudo systemctl start nginx
```