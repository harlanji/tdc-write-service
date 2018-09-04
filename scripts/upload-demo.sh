#!/bin/bash

source scripts/env.sh

curl -v -XPOST http://192.168.99.100:$PORT/upload -F file=@project.clj
