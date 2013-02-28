package shopping.cart.om;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Order {
       private String id;
       private String description;

       public Order() {
       }

       public Order(String id, String description) {
              this.id = id;
              this.description = description;
       }
       public String getId() {
              return id;
       }
       public void setId(String id) {
              this.id = id;
       }     
       public String getDescription() {
              return description;
       }
       public void setDescription(String description) {
              this.description = description;
       }
}