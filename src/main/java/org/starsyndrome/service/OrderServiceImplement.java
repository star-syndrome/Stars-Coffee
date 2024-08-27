package org.starsyndrome.service;

import org.starsyndrome.model.Order;

import java.util.List;
import java.util.Optional;

public class OrderServiceImplement implements OrderService {

    @Override
    public Integer totalPayment(List<Order> orders){
        return Optional.of(orders)
                .map(orders1 -> orders1.stream()
                        .reduce(0, (result, order) -> result + order.getTotalPrice(), Integer::sum))
                .orElse(0);
    }
    @Override
    public Integer totalQuantity(List<Order> orders){
        return Optional.of(orders)
                .map(orders1 -> orders1.stream()
                        .reduce(0, (result, order) -> result + order.getQty(), Integer::sum))
                .orElse(0);
    }
}