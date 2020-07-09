package dao;

import dto.Order;
import dto.Product;
import dto.StateTax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao{

    private final String ORDER_FILE;
    private final String PRODUCTS_FILE;
    private final String STATETAX_FILE;
    public static final String DELIMITER = "::";

    private Map<Integer, Order> orders = new HashMap<>();
    private Map<String, StateTax> taxRates = new HashMap<>();
    private Map<String, Product> productTypes = new HashMap<>();

    public FlooringMasteryDaoFileImpl(){
        ORDER_FILE = "orders.txt";
        PRODUCTS_FILE = "products.txt";
        STATETAX_FILE = "stateTax.txt";
    }

    public FlooringMasteryDaoFileImpl(String ordersTextFile, String productsTextFile, String stateTaxTextFile){
        ORDER_FILE = ordersTextFile;
        PRODUCTS_FILE = productsTextFile;
        STATETAX_FILE = stateTaxTextFile;
    }
    @Override
    public List<Order> getAllOrdersByDate(LocalDate date) {
        return orders.values()
                .stream()
                .filter(o -> o.getDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public Order addOrder(Order order) {
        order.setOrderNumber(getNextOrderNumber());
        orders.put(order.getOrderNumber(), order);
        return order;
    }

    @Override
    public Order getOrder(int orderNumber) throws FlooringMasteryDaoPersistenceException {
        return null;
    }

    @Override
    public Order editOrder(int orderNumber, Order order) throws FlooringMasteryDaoPersistenceException {
        return null;
    }

    @Override
    public Order removeOrder(int orderNumber){
        return orders.remove(orderNumber);
    }

    @Override
    public List<Product> getProductList() {
        return null;
    }

    @Override
    public Product getProduct(String material) {
        return null;
    }

    @Override
    public List<StateTax> getStateTaxList() {
        return null;
    }

    @Override
    public StateTax getState(String stateName) {
        return null;
    }

    @Override
    public int getNextOrderNumber(){
        int nextOrderNumber = 0;

        for(int n : orders.keySet()){
            if(n > nextOrderNumber){
                nextOrderNumber = n;
            }
        }
        nextOrderNumber++;
        return nextOrderNumber;
    }

    private Order unmarshallOrder(String orderAsText){
        String[] orderTokens = orderAsText.split(DELIMITER);
        Order orderFromFile = new Order(orderTokens[1], taxRates.get(orderTokens[2]), productTypes.get(orderTokens[3]), new BigDecimal(orderTokens[4]), LocalDate.parse(orderTokens[5]));
        orderFromFile.setOrderNumber(Integer.parseInt(orderTokens[0]));
        return orderFromFile;
    }

    private void loadOrders() throws FlooringMasteryDaoPersistenceException{
        Scanner sc;

        try{
            sc = new Scanner(new BufferedReader(new FileReader(ORDER_FILE)));
        }catch(FileNotFoundException ex){
            throw new FlooringMasteryDaoPersistenceException("Could not load the order file into memory.", ex);
        }

        String currentLine;
        Order currentOrder;

        while(sc.hasNextLine()){
            currentLine = sc.nextLine();
            currentOrder = unmarshallOrder(currentLine);
            orders.put(currentOrder.getOrderNumber(), currentOrder);
        }
        sc.close();
    }

    private Product unmarshallProduct(String productAsText){
        String[] productTokens = productAsText.split(DELIMITER);
        Product productFromFile = new Product(productTokens[0], new BigDecimal(productTokens[1]), new BigDecimal(productTokens[2]));
        return productFromFile;
    }

    private void loadProducts() throws FlooringMasteryDaoPersistenceException{
        Scanner sc;

        try{
            sc = new Scanner(new BufferedReader(new FileReader(PRODUCTS_FILE)));
        } catch(FileNotFoundException ex){
            throw new FlooringMasteryDaoPersistenceException("Could not load the product file into memory.", ex);
        }

        String currentLine;
        Product currentProduct;

        while(sc.hasNextLine()){
            currentLine = sc.nextLine();
            currentProduct = unmarshallProduct(currentLine);
            productTypes.put(currentProduct.getProductType(), currentProduct);
        }
        sc.close();
    }

    private StateTax unmarshallStateTax(String stateTaxAsText){
        String[] stateTaxTokens = stateTaxAsText.split(DELIMITER);
        StateTax stateTaxFromFile = new StateTax(stateTaxTokens[0], stateTaxTokens[1], new BigDecimal(stateTaxTokens[2]));

        return stateTaxFromFile;
    }

    private void loadStateTax() throws FlooringMasteryDaoPersistenceException{
        Scanner sc;

        try{
            sc = new Scanner(new BufferedReader(new FileReader(STATETAX_FILE)));
        }catch(FileNotFoundException ex){
            throw new FlooringMasteryDaoPersistenceException("Could not load the state tax file into memory.", ex);
        }

        String currentLine;
        StateTax currentStateTax;

        while(sc.hasNextLine()){
            currentLine = sc.nextLine();
            currentStateTax = unmarshallStateTax(currentLine);
            taxRates.put(currentStateTax.getStateName(), currentStateTax);
        }
        sc.close();
    }
}
