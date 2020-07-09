package view;

import dto.Order;
import dto.Product;
import dto.StateTax;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FlooringMasteryView {
    private UserIO io;

    public FlooringMasteryView(UserIO io){
        this.io = io;
    }

    public void displayWelcomeBanner(){
        io.print("*****************************");
        io.print("* <<Flooring Program>>");
    }

    public int printMenuAndGetSelection(){
        io.print("*1. Display Orders");
        io.print("*2. Add an Order");
        io.print("*3. Edit an Order");
        io.print("*4. Remove an Order");
        io.print("*5. Export All Data");
        io.print("*6. Quit");
        io.print("*");
        io.print("*****************************");

        return io.readInt("Please select from the choices above.", 1, 6);
    }

    public LocalDate getOrderDate(){
        String orderDate = io.readString("Please enter the order date. (MM/DD/YYYY)");
        LocalDate date = LocalDate.parse(orderDate, DateTimeFormatter.ofPattern("MM/DD/YYYY"));
        return date;
    }

    public String getNewOrderCustomerName(){
        return io.readString("Please enter the customer's name: ");
    }

    public String getNewOrderState(List<StateTax> statesList){
        for(StateTax state : statesList){
            io.print(state.getStateName() + " " + state.getTaxRate());
        }

        return io.readString("Please enter your State's name: ");
    }

    public String getNewOrderProduct(List<Product> products){
        for(Product product : products){
            io.print(product.getProductType());
        }
        return io.readString("Please enter the material you would like to use: ");
    }

    public BigDecimal getNewOrderArea(){
        return new BigDecimal(io.readString("Please enter the area size: "));
    }

    public int getNewOrderNumber(){
        return io.readInt("Please enter the order's corresponding order number you wish to remove: ");
    }

    public void displayOrder(Order order){
        if(order != null){
            io.print("Order Number #: " + order.getOrderNumber() + " | Customer Name: " + order.getCustomerName() + ", Material: " + order.getProductType().getProductType() + ", Area: " + order.getArea() + ", State: " + order.getStateTax().getStateName() + ", Total: " + order.getTotal());
        }
        else{
            io.print("No such order.");
        }
        io.print("Press enter to continue.");
    }

    public String confirmOrder(Order order){
        return io.readString("Are you sure you want to place this order? (yes/no)");
    }

    public void displayAddOrderSuccessBanner(Order order){
        io.readString("Thank you " + order.getCustomerName() + "! Your order has been successfully placed!");
    }

    public void displayCancelOrder(){
        io.readString("Your order has been cancelled.");
    }

    public void displayOrdersByDateBanner(LocalDate date){
        io.print("****** ALL ORDERS ON " + date + " ******");
    }

    public void displayAllOrders(List<Order> orders){
        if(orders != null){
            for(Order order : orders){
                io.print("Order Number #: " + order.getOrderNumber() + " | Customer Name: " + order.getCustomerName() + ", Material: " + order.getProductType().getProductType() + ", Area: " + order.getArea() + ", State: " + order.getStateTax().getStateName() + ", Total: " + order.getTotal());
            }
        }else{
            io.print("No orders were placed on this date.");
        }
        io.print("Press enter to continue.");
    }

    public void displayAddOrderBanner(){
        io.print("****** ADD NEW ORDER ******");
    }

    public void displayRemoveOrderBanner(){
        io.print("****** REMOVE AN ORDER ******");
    }

    public String removeOrderConfirmation(){
        return io.readString("Are you sure you want to remove this order? (yes/no)");
    }

    public void displayRemoveSuccessBanner(){
        io.print("The order was successfully removed.");
    }

    public void cancelRemovalBanner(){
        io.print("The order was NOT removed.");
    }


    public void displayExitMessage(){
        io.print("Good Bye!!!");
    }
    public void displayUnknownCommand(){
        io.print("Unknown Command!");
    }
    public void displayErrorMessage(String errorMsg){
        io.print("****** ERROR ******");
        io.print(errorMsg);
    }




}
