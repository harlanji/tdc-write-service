# tdc-write-service

A service to upload files to a docker volume named filez. Uses non-root user privileges.

## Usage

1. ONCE: create volume. assumes docker-machine default.

`scripts/create-filez-volume.sh`

2. UPON CHANGE: rebuild docker image

`scripts/build.sh`

3. EACH RUN: start a container with the built image, mapped to the volume

`scripts/run.sh`

4. TEST: upload a file to the container

`scripts/upload-demo.sh`


