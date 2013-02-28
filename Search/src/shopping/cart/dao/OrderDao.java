package shopping.cart.dao;

import java.util.HashMap;
import java.util.Map;
import shopping.cart.om.Order;

public enum OrderDao {
       instance;
       private Map<String, Order> contentProvider = new HashMap<String, Order>();

       private OrderDao() {
              Order order = new Order("1", "1st order");
              order.setDescription("This is the 1st order");
              contentProvider.put("1", order);
              order = new Order("2", "2nd order");
              order.setDescription("This is the 2nd order");
              contentProvider.put("2", order);
       }
      
       public Map<String, Order> getModel(){
              return contentProvider;
       }
}