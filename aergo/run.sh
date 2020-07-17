#!/bin/bash

SCRIPT_HOME="$( cd "$(dirname "$0")" ; pwd -P )"
  
AERGO_IMAGE_ID=$(docker images -q aergo/node:2.2.3)

function remove_aergo_data {
  AERGO_DATA=$SCRIPT_HOME/blockchain/data
  if [ -d $AERGO_DATA ]; then
    rm -rf $AERGO_DATA
  fi
  AERGO_MEMPOOL_DUMP=$SCRIPT_HOME/blockchain/mempool.dump
  if [ -f $AERGO_MEMPOOL_DUMP ]; then
    rm -rf $AERGO_MEMPOOL_DUMP
  fi
}

function init_aergo {
  docker run --rm -v $SCRIPT_HOME:/aergo aergo/node:2.2.3 aergosvr init --genesis /aergo/blockchain/genesis.json --home /aergo
  docker create --name aergo -p 7845:7845 -v $SCRIPT_HOME:/aergo aergo/node:2.2.3 aergosvr --home /aergo --config /aergo/config.toml
  docker start aergo
}

# Aergo Image가 없을 경우
if [ -z $AERGO_IMAGE_ID ]; then
  remove_aergo_data
  init_aergo

# Aergo Image가 있을 경우
else
  AERGO_CONTAINER_ID=$(docker ps -aqf "name=aergo")
   
  # Aergo가 구동 중이지 않은 경우
  if [ -z $AERGO_CONTAINER_ID ]; then
    remove_aergo_data
    init_aergo
  # Aergo가 구동 중인 경우
  else
    AERGO_CONTAINER_EXIT_CODE=$(docker inspect $AERGO_CONTAINER_ID --format='{{.State.ExitCode}}')
    # Aergo Container에 오류가 발생했을 경우
    if [ $AERGO_CONTAINER_EXIT_CODE -ne 0 ]; then
      remove_aergo_data
      init_aergo
    # Aergo Container에 오류가 발생하지 않았을 경우
    else
      docker restart aergo
    fi
  fi
fi

