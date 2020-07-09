package dao;

import dto.Item;

import java.util.List;

public interface VendingMachineDao {

    List<Item> getVendingMachineItems() throws VendingMachineDaoPersistenceException;

    Item getItem(String itemName) throws VendingMachineDaoPersistenceException;

    void purchaseItem(String itemNumber) throws VendingMachineDaoPersistenceException;
}
