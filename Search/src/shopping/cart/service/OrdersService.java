package shopping.cart.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import shopping.cart.dao.OrderDao;
import shopping.cart.om.Order;

//maps this resource to the URL orders
@Path("/orders")
public class OrdersService {

         // Allows to insert contextual objects into the class
         @Context
         UriInfo uriInfo;
         @Context
         Request request;
        
         // Return the list of orders for applications with json or xml formats
         @GET
         @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
         public List<Order> getOrders() {
           List<Order> orders = new ArrayList<Order>();
           orders.addAll(OrderDao.instance.getModel().values());
           return orders;
         }
}