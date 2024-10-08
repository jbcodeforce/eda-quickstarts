# EDA Quickstarts

UNDER CONSTRUCTION - Created 07/2022  Updated 09/2024

This project includes a set of getting started code to demonstrate some of the main EDA key concepts from this [book](https://jbcodeforce.github.io/eda-studies/).

The approach is to use one of the different starting code folder to help implementing your own event driven solution. 

While event storming and domain driven design help to start your project on good track, the code presented in this repository tries to leverage the DDD application structure for microservice implementation, using analysis and design elements such as aggregates, commands, events and bounded contexts. 

The DDD code structure practice organizes code with resources (support RESTful resource and APIs), domain model and business services, infrastructure layer with repository and messaging.

## Knowledge 


* [Kustomize is used in this project](https://kustomize.io/) for Kubernetes native configuration management.
* [OpenAPI ]()

## A Java demo with Avro, Kafka Producer and Consumer

This is a simple demonstration of deploying Kafka Strimzi operator and Kafka cluster on minikube and one Quarkus reactive messaging producer, and one quarkus reactive messaging consumer. The business domain is order managment.

[See readme for installation and demonstration script](./java-avro-first-demo/README.md

Each project includes:

* Code template based on the same OrderEntity management
* Github action workflow to build with maven, build jvm docker image and push the image to an image registry
* Kustomize folder for yamls to deploy to OpenShift cluster which could be included in a GitOps project.

A template of GitOps project is in [the eda-quickdtart-gitops repository](https://github.com/ibm-cloud-architecture/eda-quickdtart-gitops.git) with the 2 reactive messaging services
configured and deployed.

## Java templates


Still under construction:

* [Quarkus app with Kafka Producer API](https://github.com/ibm-cloud-architecture/eda-quickstarts/tree/main/quarkus-producer-kafka-api): OpenAPI, metrics, health, RESTeasy, Kafka API
* [Quarkus app with Kafka Consumer API](https://github.com/ibm-cloud-architecture/eda-quickstarts/tree/main/quarkus-consumer-kafka-api): OpenAPI, metrics, health, RESTeasy, Kafka API, to consume 'product' events. Deployable on OpenShift with source to image, with configMap to tune the EventStreams or Kafka client settings.


* [Quarkus app with Kafka Producer API and Avro schema](https://github.com/ibm-cloud-architecture/eda-quickstarts/tree/main/)
* [Quarkus Kafka producer - command part of CQRS](https://github.com/ibm-cloud-architecture/eda-quickstarts/tree/main/quarkus-kafka-producer) is to use Microprofile reactive messaging outbound channel which produces `create events` when data is pushed via REST POST end point. The basic Order entity is here as a placeholder to support REST operation, event creation and persistence. The repository is a Postgresql access layer using Quarkus Panache and hibernate ORM. The code generates to `orders` topic.
* [Spring Cloud Stream](https://github.com/ibm-cloud-architecture/eda-quickstarts/tree/main/spring-cloud-stream)

Other projects that could be used as template or source of inspiration:

* [Order CQRS with Open Liberty and Kafka API](https://github.com/jbcodeforce/refarch-kc-order-ms)
* [Outbox pattern with Postgresql, Debezium Outbox Quarkus plugin and Debezium change data capture with Kafka Conncet](https://github.com/jbcodeforce/vaccine-order-mgr-pg)
