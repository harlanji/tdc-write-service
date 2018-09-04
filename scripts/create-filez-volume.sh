#!/bin/bash

source scripts/env.sh


docker-machine ssh default \
  "sudo mkdir -p $FILEZ_HOST_PATH \
    && sudo chown $MEDIA_UID:$MEDIA_GID $FILEZ_HOST_PATH \
    && sudo chmod 770 $FILEZ_HOST_PATH"

docker volume create \
  --name filez \
  -o type=none \
  -o o=bind \
  -o device=$FILEZ_HOST_PATH
