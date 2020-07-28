#!/bin/bash

SCRIPT_HOME="$( cd "$(dirname "$0")" ; pwd -P )"

AERGO_IMAGE_ID=$(docker images -q aergo/node:2.2.3)

function remove_aergo {
  AERGO_DATA=$SCRIPT_HOME/blockchain/data
  if [ -d $AERGO_DATA ]; then
    rm -rf $AERGO_DATA
  fi
  AERGO_AUTH=$SCRIPT_HOME/blockchain/auth
  if [ -d $AERGO_AUTH ]; then
    rm -rf $AERGO_AUTH
  fi
  AERGO_MEMPOOL_DUMP=$SCRIPT_HOME/blockchain/mempool.dump
  if [ -f $AERGO_MEMPOOL_DUMP ]; then
    rm -rf $AERGO_MEMPOOL_DUMP
  fi
  AERGO_CONTRACT=$SCRIPT_HOME/contract
  if [ -d $AERGO_CONTRACT ]; then
    rm -rf $AERGO_CONTRACT
  fi
}

# Aergo Image가 있을 경우
if [ ! -z $AERGO_IMAGE_ID ]; then
  AERGO_CONTAINER_ID=$(docker ps -aqf "name=aergo")

  # Aergo가 구동 중이지 않은 경우
  if [ -z $AERGO_CONTAINER_ID ]; then
    docker rmi $AERGO_IMAGE_ID
    remove_aergo
  # Aergo가 구동 중인 경우
  else
    docker rm -f $AERGO_CONTAINER_ID
    docker rmi $AERGO_IMAGE_ID
    remove_aergo
  fi
fi
