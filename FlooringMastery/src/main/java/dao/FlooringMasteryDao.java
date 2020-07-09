package dao;

import dto.Order;
import dto.Product;
import dto.StateTax;

import java.time.LocalDate;
import java.util.List;

public interface FlooringMasteryDao {

    List<Order> getAllOrdersByDate (LocalDate date);
    Order addOrder(Order order);
    Order getOrder(int orderNumber) throws FlooringMasteryDaoPersistenceException;
    Order editOrder(int orderNumber, Order order) throws FlooringMasteryDaoPersistenceException;
    Order removeOrder(int orderNumber);

    List<Product> getProductList();
    Product getProduct(String material);

    List<StateTax> getStateTaxList();
    StateTax getState(String stateName);

    int getNextOrderNumber();


}
