#!/bin/bash

source scripts/env.sh

lein do clean, ring uberjar
docker build -t $IMAGE .
