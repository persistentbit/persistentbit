#!/usr/bin/env bash
echo starting postgres 10.1 port 7000 username postgres password docker
docker run --rm --name=persistentbit_postgres_101 -d --env="POSTGRES_PASSWORD=docker" -p 7000:5432 postgres:10.1