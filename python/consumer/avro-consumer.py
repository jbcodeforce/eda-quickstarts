
from confluent_kafka import Consumer, KafkaError
from confluent_kafka.schema_registry import SchemaRegistryClient
from confluent_kafka.schema_registry.avro import AvroDeserializer

from argparse import ArgumentParser, FileType
from configparser import ConfigParser

def process_message(key: str, event_dict: dict):
   print(f"Consumed message key = {key} value = {event_dict}")

def consume(topic, config, schema_registry_url, schema_id):

  schema_registry_client = SchemaRegistryClient({'url': schema_registry_url})
  schema = schema_registry_client.get_schema(schema_id)
  deserializer = AvroDeserializer(schema_registry_client, schema_str=schema.schema_str)

  # creates a new consumer instance
  consumer = Consumer(config)

  # subscribes to the specified topic
  consumer.subscribe([topic])
  print("\n------------- start listening ------\n")
  try:
    running = True
    while running:
      # consumer polls the topic and prints any incoming messages
      msg = consumer.poll(1.0)
      if msg is not None:
        if msg.error() is None:
          key = msg.key().decode("utf-8")
          value = msg.value()
          event_dict = deserializer(value, None)
          process_message( key,event_dict)
        elif msg.error().code() != KafkaError._PARTITION_EOF:
          print(msg.error())
          running = False
  except KeyboardInterrupt:
    pass
  finally:
    # closes the consumer connection
    consumer.close()

if __name__ == "__main__":
  parser = ArgumentParser()
  parser.add_argument("config_file", type=FileType("r"))
  args = parser.parse_args()

  config_parser= ConfigParser()
  config_parser.read_file(args.config_file)
  config = dict(config_parser["default"])
  config.update(config_parser["consumer"])
  print(config)
  topic_name=config_parser["common"]["topic_name"]
  schema_registry_url = 'http://localhost:8081'
  schema_id="2"
  consume(topic_name,config,schema_registry_url,schema_id)
