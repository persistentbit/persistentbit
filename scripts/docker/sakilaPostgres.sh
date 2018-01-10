#!/usr/bin/env bash
echo run sakila in postgres port 7001 username postgres no password
docker run -rm --name persistent_sakila_postgres -p 7001:5432 -d sakilapostgres
