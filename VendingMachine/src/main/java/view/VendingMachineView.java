package view;

import dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineView {
    private UserIO io;

    public VendingMachineView(UserIO io){
        this.io = io;
    }

    public void displayWelcomeBanner(){
        io.print("=== WELCOME! ===");
        io.print("");
    }

    public int printMenuAndGetSelection(){
        io.print("Main Menu");
        io.print("1. Purchase Item");
        io.print("2. Exit ");

        return io.readInt("Please select from the choices above.", 1, 2);
    }

    public void displayVendingMachineItemsBanner(List<Item> itemList){
        io.print("=== BEVERAGES ===");
        for(Item currentItem : itemList){
            String itemInfo = (currentItem.getItemName() + " $" + currentItem.getItemCost());
            io.print(itemInfo);
        }
        io.print("");
    }

    public String getItemName(){
        return io.readString("Please select a beverage.");
    }

    public BigDecimal displayRequestFunds(){
        BigDecimal bd = new BigDecimal(String.valueOf(io.readString("Please enter funds.")));
        return bd;
    }

    public void displayFunds(BigDecimal funds){
        io.print(String.valueOf("You only deposited $" + funds));
    }

    public void displaySuccessfulVendBanner(String itemName){
        io.print("Enjoy your " + itemName + "!");
    }

    public void displayChange(String change){
        io.print(change);
    }

    public String displayAnotherTransaction(){
        return io.readString("Do you want to make another purchase? (yes/no)");
    }

    public void displayUnknownCommandBanner(){
        io.print("Unknown Command!");
    }

    public void displayExitBanner(){
        io.print("Thank you! Have a great day!");
    }

    public void displayErrorMessage(String errorMsg){
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
