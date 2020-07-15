#!/bin/bash

AERGO_IMAGE_ID=$(docker images -q aergo/node:2.2.3)

# Aergo Image가 있을 경우
if [ ! -z $AERGO_IMAGE_ID ]; then
  AERGO_CONTAINER_ID=$(docker ps -aqf "name=aergo")

  # Aergo가 구동 중이지 않은 경우
  if [ -z $AERGO_CONTAINER_ID ]; then
    docker rmi $AERGO_IMAGE_ID
  # Aergo가 구동 중인 경우
  else
    docker rm -f $AERGO_CONTAINER_ID
    docker rmi $AERGO_IMAGE_ID
  fi
fi
