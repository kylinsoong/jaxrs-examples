package org.jboss.resteasy.examples.petstore.data;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.jboss.resteasy.examples.petstore.model.Order;

public class StoreData {
    
  static List<Order> orders = new ArrayList<Order>();

  static {
    orders.add(createOrder(1, 1, 2, new Date(), "placed"));
    orders.add(createOrder(2, 1, 2, new Date(), "delivered"));
    orders.add(createOrder(3, 2, 2, new Date(), "placed"));
    orders.add(createOrder(4, 2, 2, new Date(), "delivered"));
    orders.add(createOrder(5, 3, 2, new Date(), "placed"));
    orders.add(createOrder(11, 3, 2, new Date(), "placed"));
    orders.add(createOrder(12, 3, 2, new Date(), "placed"));
    orders.add(createOrder(13, 3, 2, new Date(), "placed"));
    orders.add(createOrder(14, 3, 2, new Date(), "placed"));
    orders.add(createOrder(15, 3, 2, new Date(), "placed"));
  }

  public Order findOrderById(long orderId) {
    for (Order order : orders) {
      if (order.getId() == orderId) {
        return order;
      }
    }
    return null;
  }

  public void placeOrder(Order order) {
    if (orders.size() > 0) {
      for (int i = orders.size() - 1; i >= 0; i--) {
        if (orders.get(i).getId() == order.getId()) {
          orders.remove(i);
        }
      }
    }
    orders.add(order);
  }

  public void deleteOrder(long orderId) {
    if (orders.size() > 0) {
      for (int i = orders.size() - 1; i >= 0; i--) {
        if (orders.get(i).getId() == orderId) {
          orders.remove(i);
        }
      }
    }
  }

  private static Order createOrder(long id, long petId, int quantity,
      Date shipDate, String status) {
    Order order = new Order();
    order.setId(id);
    order.setPetId(petId);
    order.setQuantity(quantity);
    order.setShipDate(shipDate);
    order.setStatus(status);
    return order;
  }
}