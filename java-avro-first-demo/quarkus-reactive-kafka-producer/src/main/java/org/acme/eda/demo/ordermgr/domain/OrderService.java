package org.acme.eda.demo.ordermgr.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;


import org.acme.eda.demo.ordermgr.infra.events.OrderEventProducer;
import org.acme.eda.demo.ordermgr.infra.repo.OrderRepository;
import io.smallrye.reactive.messaging.kafka.KafkaRecord;


@ApplicationScoped
public class OrderService {
	private static final Logger logger = Logger.getLogger(OrderService.class.getName());

	@Inject
	public OrderRepository repository;
	@Inject
	public OrderEventProducer eventProducer;

	public OrderService(){}
	
	@Transactional
	public OrderEntity createOrder(OrderEntity order) {
		if (order.creationDate == null) {
			order.creationDate = LocalDate.now().toString();
		}
		order.updateDate= order.creationDate;
		repository.addOrder(order);
		eventProducer.emitCreateOrderEventFromOder(order);
		return order;
	}

	public List<OrderEntity> getAllOrders() {
		return repository.getAll();
	}

	@Transactional
    public OrderEntity updateOrder(OrderEntity order) {
		order.updateDate = LocalDate.now().toString();
		repository.updateOrder(order);
		eventProducer.emitUpdateOrderEventFromOder(order);
		return order;
    }

	
}
