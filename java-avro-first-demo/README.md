# Order management with reactive messaging and Avro

Created January 2019 -  
Move to pure open source 08/2024, and align to Kafka 3.9, Quarkus 3.2.12.Final, JDK 11, jakarta packaging.

The demonstration illustrates the following concepts:

* Java applications with Quarkus framework and Microprofile 3.0 reactive messaging to produce and consumer order event messages
* Avro schema definition, own by the order manager component producer of events
* App uses health, metrics and open API extensions
* Use Apicurio schema registry and the maven plugin to upload new definition to the connected registry.
* Support Domain driven design practices

## Audiance

Developers and solution architects.

## Source of information

The following tutorials and guides are important to study to get a better understanding of the code and configuration of this repository:

* [See the schema registry avro quarkus guide](https://quarkus.io/guides/kafka-schema-registry-avro)
* [See Dev Services for Kafka](https://quarkus.io/guides/kafka-dev-services)
* [and Dev Services for Apicurio Registry](https://quarkus.io/guides/apicurio-registry-dev-services)


## Pre-requisites

1. Start minikube

    ```sh
    minikube start --memory 4096
    # Add ingress addons
    minikube addons enable ingress
    ```

1. Bootstrap Day0 operations

    * Install Strimzi Operator

    ```sh
    cd gitops/bootstraps-day0/k8s/strimzi
    ./install_strimzi.sh
    ```

    * Install Apicurio Operator

    ```sh
    cd gitops/bootstraps-day0/k8s/apicurio
    ./install_operator.sh
    ```

## Build the microservices

* Build the two images in one command: `buildAll.sh`
* Build the image individually:

    ```sh
    # 
    cd quarkus-reactive-kafka-producer
    ./build/buildImage.sh
    #
    cd quarkus-reactive-kafka-consumer
    ./build/buildImage.sh
    ```

## Gitops and Kustomize structure

Under gitops/environments/java-avro-first-demo:

* Create namespace for the demo: ` kubectl apply -k env/`
* Create Kafka Cluster: `kubectl apply -k services/strimzi-kafka/` under the `kafka` namespace
* Start producer app: `kubectl apply -k apps/order-producer/base/`
* Start consumerc app: `kubectl apply -k apps/order-consumer/base/`
