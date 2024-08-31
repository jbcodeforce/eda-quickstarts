package org.acme.eda.demo.ordermgr.infra.api;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.acme.eda.demo.ordermgr.domain.OrderEntity;
import org.acme.eda.demo.ordermgr.domain.OrderService;
import org.acme.eda.demo.ordermgr.infra.api.dto.OrderDTO;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("/api/v1/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class OrderResource {
    private static final Logger logger = Logger.getLogger(OrderResource.class.getName());

    @Inject
    public OrderService service;
    
    @GET
    public Multi<OrderDTO> getAllActiveOrders() {
        List<OrderDTO> l = new ArrayList<OrderDTO>();
        for (OrderEntity order : service.getAllOrders()) {
            l.add(OrderDTO.fromEntity(order));
        }
        return Multi.createFrom().items(l.stream());
    }

    @POST
    public Uni<OrderDTO> saveNewOrder(OrderDTO order) {
        logger.info("POST operation " + order.toString());
        OrderEntity entity = OrderDTO.toEntity(order);
        return Uni.createFrom().item(OrderDTO.fromEntity(service.createOrder(entity)));
    }

    @PUT
    public Uni<OrderDTO> updateExistingOrder(OrderDTO order) {
        logger.info("PUT operation " + order.toString());
        OrderEntity entity = OrderDTO.toEntity(order);
        return Uni.createFrom().item(OrderDTO.fromEntity(service.updateOrder(entity)));
    }
    
}