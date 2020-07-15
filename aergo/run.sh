#!/bin/bash

SCRIPT_HOME="$( cd "$(dirname "$0")" ; pwd -P )"
  
AERGO_IMAGE_ID=$(docker images -q aergo/node:2.2.3)

# Aergo Image가 없을 경우
if [ -z $AERGO_IMAGE_ID ]; then
  docker create --name aergo -p 7845:7845 -v $SCRIPT_HOME/config.toml:/aergo/config.toml aergo/node:2.2.3 aergosvr --config /aergo/config.toml
  docker start aergo

# Aergo Image가 있을 경우
else
  AERGO_CONTAINER_ID=$(docker ps -aqf "name=aergo")
   
  # Aergo가 구동 중이지 않은 경우
  if [ -z $AERGO_CONTAINER_ID ]; then
    docker create --name aergo -p 7845:7845 -v $SCRIPT_HOME/config.toml:/aergo/config.toml aergo/node:2.2.3 aergosvr --config /aergo/config.toml
    docker start aergo

  # Aergo가 구동 중인 경우
  else
    docker restart aergo
  fi
fi

