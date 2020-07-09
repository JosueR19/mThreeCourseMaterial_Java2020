package service;

import dao.VendingMachineDao;
import dao.VendingMachineDaoPersistenceException;
import dto.Change;
import dto.Item;

import java.math.BigDecimal;
import java.util.List;


public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{
    VendingMachineDao dao;
    Change change;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, Change change){
        this.dao = dao;
        this.change = change;
    }

    @Override
    public List<Item> getVendingMachineItems() throws VendingMachineDaoPersistenceException {
        return dao.getVendingMachineItems();
    }

    @Override
    public Item getItem(String itemName) throws VendingMachineDaoPersistenceException{
        return dao.getItem(itemName);
    }


    @Override
    public String purchaseItem(String itemName, BigDecimal funds) throws VendingMachineDaoPersistenceException, InsufficientFundsException, NoItemInventoryException {
        Item item = getItem(itemName);

        if(!getVendingMachineItems().contains(item)){
            throw new NoItemInventoryException("ERROR: This item does not exist!");
        }

        if(item.getNumItems() <= 0){
            throw new NoItemInventoryException("ERROR: " + item.getItemName() + "is SOLD OUT!");
        }

        if(funds.compareTo(item.getItemCost()) < 0){
            throw new InsufficientFundsException("INSUFFICIENT FUNDS! \n Please add more funds.");
        }

        change.calculateChange(item, funds);

        dao.purchaseItem(itemName);

        String stringChange = "Your change is \n" + change.getNumQuarters() + " Quarters \n"
                + change.getNumDimes() + " Dimes \n"
                + change.getNumNickels() + " Nickels \n"
                + change.getNumPennies() + " Pennies \n";

        return stringChange;
    }
}
