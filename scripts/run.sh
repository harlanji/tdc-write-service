#!/bin/bash

source scripts/env.sh

docker run --rm \
  -p $PORT:$PORT \
  --user=$MEDIA_UID:$MEDIA_GID \
  --volume filez:/filez \
  $IMAGE
