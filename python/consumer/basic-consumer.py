from confluent_kafka import Consumer, KafkaError
from argparse import ArgumentParser, FileType
from configparser import ConfigParser

def process_message(key: str, event: str):
   print(f"Consumed message key = {key} value = {event}")


def consume(topic, config):

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
          process_message( key,value)
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
  consume(topic_name,config)
