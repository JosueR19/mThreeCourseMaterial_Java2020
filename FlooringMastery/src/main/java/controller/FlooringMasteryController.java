package controller;

import dao.FlooringMasteryDaoPersistenceException;
import dto.Order;
import dto.Product;
import dto.StateTax;
import service.*;
import view.FlooringMasteryView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class FlooringMasteryController {
    private FlooringMasteryView view;
    private FlooringMasteryService service;

    public FlooringMasteryController(FlooringMasteryService service, FlooringMasteryView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        view.displayWelcomeBanner();

        try {
            boolean keepGoing = true;
            int menuSelection = 0;

            while (keepGoing) {
                try {
                    menuSelection = getMenuSelection();

                    switch (menuSelection) {
                        case 1:
                            listOrdersByDate();
                            break;
                        case 2:
                            addOrder();
                            break;
                        case 3:
                            //editOrder();
                            break;
                        case 4:
                            removeOrder();
                            break;
                        case 5:
                            //exportData();
                            break;
                        case 6:
                            keepGoing = false;
                            break;
                        default:
                            unknownCommand();
                    }
                } catch (InvalidOrderDateException ex) {
                    view.displayErrorMessage(ex.getMessage());
                }
            }
            exitMessage();
        } catch (FlooringMasteryDaoPersistenceException ex) {
            view.displayErrorMessage(ex.getMessage());
        }
    }

    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }

    private void listOrdersByDate() throws InvalidOrderDateException {
        try{
            LocalDate date = view.getOrderDate();
            view.displayOrdersByDateBanner(date);
            List<Order> orders = service.listOrdersByDate(date);
            view.displayAllOrders(orders);
        }catch(InvalidOrderDateException ex){
            view.displayErrorMessage(ex.getMessage());
        }
    }

    private void addOrder(){
        view.displayAddOrderBanner();

        List<StateTax> statesList = service.getStateTaxList();
        List<Product> products = service.getProductList();

        String customerName = view.getNewOrderCustomerName();
        String state = view.getNewOrderState(statesList);
        String product = view.getNewOrderProduct(products);
        BigDecimal area = view.getNewOrderArea();
        LocalDate date = LocalDate.now();

        boolean hasErrors = false;
        while(!hasErrors)
            try{
                Order currentOrder = new Order(customerName, service.getState(state), service.getProduct(product), area, date);
                view.displayOrder(currentOrder);
                String confirmation = view.confirmOrder(currentOrder);

                if(confirmation.equalsIgnoreCase("yes")){
                    Order newOrder = service.addOrder(customerName, state, product, area, date);
                    view.displayAddOrderSuccessBanner(newOrder);
                }else{
                    view.displayCancelOrder();
                }
                hasErrors = true;

            }catch(InvalidCustomerException ex){
                view.displayErrorMessage(ex.getMessage());
                customerName = view.getNewOrderCustomerName();
            }catch(InvalidStateException ex){
                view.displayErrorMessage(ex.getMessage());
                state = view.getNewOrderState(statesList);
            }catch(InvalidProductException ex){
                view.displayErrorMessage(ex.getMessage());
                product = view.getNewOrderProduct(products);
            }catch(InvalidAreaException ex){
                view.displayErrorMessage(ex.getMessage());
                area = view.getNewOrderArea();
            }
    }

    private void removeOrder() throws InvalidOrderDateException{

        listOrdersByDate();

        view.displayRemoveOrderBanner();
        int orderNumber = view.getNewOrderNumber();
        Order currentOrder = service.listOrder(orderNumber);
        view.displayOrder(currentOrder);
        String confirmation = view.removeOrderConfirmation();

        if(confirmation.equalsIgnoreCase("yes")){
            try{
                service.removeOrder(orderNumber);
                view.displayRemoveSuccessBanner();
            }catch(OrderNotFoundException ex){
                view.displayErrorMessage(ex.getMessage());
            }
        }else{
            view.cancelRemovalBanner();
        }
    }

    private void unknownCommand(){
        view.displayUnknownCommand();
    }

    private void exitMessage(){
        view.displayExitMessage();
    }
}
