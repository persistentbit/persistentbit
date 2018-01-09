#!/usr/bin/env bash
docker stop persistbit_oracle_12201
docker rm persistbit_oracle_12201
#echo start oracle on port 1521 where  sys user has password Oradoc_db1 and SID is ORCLCDB
echo hostname: localhost
echo port: 1521
echo sid: xe
echo username: system
echo password: oracle
docker run -d --shm-size=2g -p 1521:1521 -p 8080:8080 --name persistbit_oracle_12201  alexeiled/docker-oracle-xe-11g
#docker run -d -it -p 1521:1521 --name persistbit_oracle_12201 store/oracle/database-enterprise:12.2.0.1-slim