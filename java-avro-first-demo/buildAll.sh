#!/bin/bash
set -x 
scriptDir=$(dirname $0)
cd $scriptDir/quarkus-reactive-kafka-producer
./build/buildImage.sh
cd ..
cd quarkus-reactive-kafka-consumer
./build/buildImage.sh