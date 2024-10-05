# A Simple Demo with Java Kafka base API

## Start the minimum environment

* One Kafka node with Kraft protocol
* One Schema Registry
* The Control Center [http://localhost:9021/clusters](http://localhost:9021/clusters)

[See this getting Started](https://docs.confluent.io/platform/current/get-started/platform-quickstart.html)


## Connect a Python consumer

Once the DataGen connector are created for the topic `pageviews` and `users`, starts one of the Avro Consumer. For example in the `python/consumer` folder use the avro consumer:

```sh
python avro-consumer.py users_client.properties

# or
python avro-consumer.py pageviews_client.properties
```

## Connect a Java Consumer