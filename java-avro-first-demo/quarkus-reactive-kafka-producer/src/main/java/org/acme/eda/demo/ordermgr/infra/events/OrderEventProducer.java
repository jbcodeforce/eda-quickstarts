package org.acme.eda.demo.ordermgr.infra.events;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;

import io.smallrye.reactive.messaging.kafka.KafkaRecord;

import java.util.logging.Logger;

import org.acme.eda.demo.ordermgr.domain.OrderEntity;

import org.acme.eda.demo.ordermgr.infra.events.Address;
import org.acme.eda.demo.ordermgr.infra.events.OrderEvent;

public class OrderEventProducer {
    private static final Logger logger = Logger.getLogger(OrderEventProducer.class.getName());

    @Channel("orders")
	public Emitter<OrderEvent> eventProducer;

	
    public void emitCreateOrderEventFromOder(OrderEntity order){
		try {
            OrderEvent orderPayload = buildOrderEventFromOrder(order,"OrderCreatedEvent");
			Message<OrderEvent> record = KafkaRecord.of(order.getOrderID(),orderPayload);
			eventProducer.send(record);
			logger.info("order created event sent for " + order.getOrderID());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void emitUpdateOrderEventFromOder(OrderEntity order) {
        
			try {
                OrderEvent orderPayload = buildOrderEventFromOrder(order,"OrderUpdateEvent");
				logger.info("emit order updated event for " + order.getOrderID());
                Message<OrderEvent> record = KafkaRecord.of(order.getOrderID(),orderPayload);
				eventProducer.send(record);
			} catch (Exception e) {
				e.printStackTrace();
			}
    }

    private OrderEvent buildOrderEventFromOrder(OrderEntity order, String eventType) {
        Address deliveryAddress = null;
		if (order.getDeliveryAddress() !=null) {
			deliveryAddress = new Address(order.getDeliveryAddress().getStreet()
			,order.getDeliveryAddress().getCity()
			,order.getDeliveryAddress().getCountry()
			,order.getDeliveryAddress().getState(),
			order.getDeliveryAddress().getZipcode());
		}
		
		OrderEvent orderPayload =
		 new OrderEvent(order.getOrderID(),
				order.getProductID(),
				order.getCustomerID(),
				order.getQuantity(),
				order.getStatus(),
		        order.creationDate,
				order.updateDate,
				deliveryAddress,
                eventType
				);	
        return orderPayload;
    }
}


