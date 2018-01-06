#!/usr/bin/env bash
echo starting MySql 5.6 on port 6603 username root password docker
docker run --name=persistentbit_mysql_56 --env="MYSQL_ROOT_PASSWORD=docker" -d -p 6603:3306 mysql:56