package org.acme.eda.demo.ordermgr.infra.repo;

import java.util.List;

import org.acme.eda.demo.ordermgr.domain.OrderEntity;

public interface OrderRepository {
    public List<OrderEntity> getAll();
    public void addOrder(OrderEntity entity);
    public void updateOrder(OrderEntity entity);
}
