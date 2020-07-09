package controller;

import dao.VendingMachineDaoPersistenceException;
import dto.Item;
import service.InsufficientFundsException;
import service.NoItemInventoryException;
import service.VendingMachineServiceLayer;
import view.VendingMachineView;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineController {
    private VendingMachineView view;
    private VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view){
        this.service = service;
        this.view = view;
    }

    public void run(){
        boolean keepPurchasing = true;
        int menuSelection = 0;
            view.displayWelcomeBanner();

            try{
                listVendingMachineItems();

                while(keepPurchasing){

                    menuSelection = getMenuSelection();

                    switch(menuSelection){
                        case 1:
                            purchaseItem();
                            if(anotherTransaction())
                                keepPurchasing = true;
                            else
                                keepPurchasing = false;
                            break;
                        case 2:
                            keepPurchasing = false;
                            break;
                        default:
                            unknownCommand();
                    }
                }
                exitMessage();
            }catch(VendingMachineDaoPersistenceException ex){
                view.displayErrorMessage(ex.getMessage());
            }
    }

    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }

    private void listVendingMachineItems() throws VendingMachineDaoPersistenceException{
        List<Item> itemList = service.getVendingMachineItems();
        view.displayVendingMachineItemsBanner(itemList);
    }

    private void purchaseItem() throws VendingMachineDaoPersistenceException{
        BigDecimal funds = requestFunds();

        boolean hasErrors = false;
        do{
            String itemName = view.getItemName();
            try{
                String purchasedItem = service.purchaseItem(itemName, funds);
                view.displayChange(purchasedItem);
                view.displaySuccessfulVendBanner(itemName);
                hasErrors = false;
            }catch(NoItemInventoryException ex){
                hasErrors = true;
                view.displayErrorMessage(ex.getMessage());
            } catch(InsufficientFundsException ex){
                hasErrors = true;
                view.displayErrorMessage(ex.getMessage());
                view.displayFunds(funds);
                funds = funds.add(requestFunds());
            }
        }while(hasErrors);
    }

    private BigDecimal requestFunds(){
        BigDecimal funds = view.displayRequestFunds();
        return funds;
    }

    private boolean anotherTransaction(){
        if(view.displayAnotherTransaction().equals("yes")){
            return true;
        }
        return false;
    }

    private void unknownCommand(){
        view.displayUnknownCommandBanner();
    }

    private void exitMessage(){
        view.displayExitBanner();
    }

}
