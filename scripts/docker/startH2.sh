#!/usr/bin/env bash
echo starting h2 database from 2014 on port 9092 username sa password none
docker run --name=persistentbit_h2 -d -p 9092:9092  datagrip/h2