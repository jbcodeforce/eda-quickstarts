package org.acme.eda.app;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Channel;
import io.smallrye.mutiny.Multi;
import org.jboss.resteasy.reactive.RestStreamElementType;
import  org.acme.demo.ordermgr.infra.events.*;

@ApplicationScoped
@Path("/consumed-orders")
public class Consumer {

/**
 * Here we are using MicroProfile Reactive Messaging to interact with Apache Kafka. 
 * We have setup all configuration required to read data from a topic in application.properties file 
 * all configuration starting with mp.messaging.incoming.my-data-stream is for this incoming connection
 * all configuration starting with mp.messaging.connector.smallrye-kafka is also for this connection 
 * because we have configured `mp.messaging.incoming.my-data-stream.connector=smallrye-kafka`
 * so in this function we provide connector to use same as above. `my-data-stream` and it is incoming because of `incoming`
 * @param priceInUsd data sent to topic. data sent to topic should be of type double otherwise deserialization will fail
 * @return 
 */
    @Channel("orders-from-kafka")
    Multi<OrderEvent> orders;


    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @RestStreamElementType(MediaType.TEXT_PLAIN)
    public Multi<String> stream() {
        return orders.map(order -> String.format("'%s' for %s", order.getOrderID(), order.getProductID()));
    }
    
}
