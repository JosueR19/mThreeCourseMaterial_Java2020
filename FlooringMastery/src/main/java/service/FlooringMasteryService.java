package service;

import dto.Order;
import dto.Product;
import dto.StateTax;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface FlooringMasteryService {

    List<StateTax> getStateTaxList();
    List<Product> getProductList();


    Order addOrder(String customerName, String stateName, String product, BigDecimal area, LocalDate date) throws InvalidProductException, InvalidStateException, InvalidCustomerException, InvalidAreaException;
    List<Order> listOrdersByDate(LocalDate date) throws InvalidOrderDateException;
    Order removeOrder(int orderNumber) throws OrderNotFoundException;

    Order listOrder(int orderNumber);
    StateTax getState(String state) throws InvalidStateException;
    Product getProduct(String product) throws InvalidProductException;

}
