package service;

import dao.VendingMachineDaoPersistenceException;
import dto.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineServiceLayer {

    List<Item> getVendingMachineItems() throws VendingMachineDaoPersistenceException;

    Item getItem(String itemName) throws VendingMachineDaoPersistenceException;

    String purchaseItem(String itemName, BigDecimal funds) throws VendingMachineDaoPersistenceException, InsufficientFundsException, NoItemInventoryException;



}
