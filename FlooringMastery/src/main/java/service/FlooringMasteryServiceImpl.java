package service;

import dao.FlooringMasteryDao;
import dto.Order;
import dto.Product;
import dto.StateTax;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class FlooringMasteryServiceImpl implements FlooringMasteryService{

    FlooringMasteryDao dao;

    public FlooringMasteryServiceImpl(FlooringMasteryDao dao){
        this.dao = dao;
    }

    @Override
    public List<Order> listOrdersByDate(LocalDate date) throws InvalidOrderDateException {
        return null;
    }

    @Override
    public Order addOrder(String customerName, String stateName, String product, BigDecimal area, LocalDate date) throws InvalidProductException, InvalidStateException, InvalidCustomerException, InvalidAreaException {
        Product productType = dao.getProduct(product);
        StateTax state = dao.getState(stateName);

        if(product == null){
            throw new InvalidProductException("Invalid product type.");
        }

        if(state == null){
            throw new InvalidStateException("Invalid State");
        }

        if(customerName == null || customerName.isEmpty()){
            throw new InvalidCustomerException("Invalid Customer Name.");
        }

        if(area == null || area.compareTo(new BigDecimal(0)) <= 0){
            throw new InvalidAreaException("Invalid area.");
        }

        Order newOrder = new Order(customerName, state, productType, area, date);
        return dao.addOrder(newOrder);
    }

    @Override
    public Order removeOrder(int orderNumber)throws OrderNotFoundException{
        Order removedOrder = dao.removeOrder(orderNumber);
        if(removedOrder == null){
            throw new OrderNotFoundException("Order does not exist.");
        }
        return removedOrder;
    }

    @Override
    public List<StateTax> getStateTaxList() {
        return null;
    }

    @Override
    public List<Product> getProductList() {
        return null;
    }

    @Override
    public Order listOrder(int orderNumber) {
        return null;
    }

    @Override
    public StateTax getState(String state) throws InvalidStateException {
        return null;
    }

    @Override
    public Product getProduct(String product) throws InvalidProductException {
        return null;
    }
}
