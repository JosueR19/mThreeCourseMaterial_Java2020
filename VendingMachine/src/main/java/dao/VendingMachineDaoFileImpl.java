package dao;

import dto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachineDaoFileImpl implements VendingMachineDao{
    private Map<String, Item> items = new HashMap<>();
    private final String VENDING_MACHINE;
    public static final String DELIMITER = "::";

    public VendingMachineDaoFileImpl(){
        VENDING_MACHINE = "VendingMachine.txt";
    }

    public VendingMachineDaoFileImpl(String vendingMachineTextFile){
        VENDING_MACHINE = vendingMachineTextFile;
    }


    @Override
    public List<Item> getVendingMachineItems() throws VendingMachineDaoPersistenceException {
        loadItems();
        return new ArrayList<Item>(items.values());
    }

    @Override
    public Item getItem(String itemName) throws VendingMachineDaoPersistenceException {
        loadItems();
        return items.get(itemName);
    }

    @Override
    public void purchaseItem(String itemName) throws VendingMachineDaoPersistenceException {
        loadItems();
        Item item = getItem(itemName);
        item.setNumItems(item.getNumItems() -1);
        writeItems();

    }

    private Item unmarshallItem(String itemAsText){
        String[] itemTokens = itemAsText.split(DELIMITER);
        String itemName = itemTokens[0];
        Item itemFromFile = new Item(itemName);

        itemFromFile.setItemCost(new BigDecimal(itemTokens[1]));
        itemFromFile.setNumItems(Integer.parseInt(itemTokens[2]));

        return itemFromFile;
    }

    private void loadItems() throws VendingMachineDaoPersistenceException{
        Scanner sc;

        try{
            sc = new Scanner(new BufferedReader(new FileReader(VENDING_MACHINE)));
        } catch(FileNotFoundException ex){
            throw new VendingMachineDaoPersistenceException("Could not load the item inventory into memory.", ex);
        }

        String currentLine;
        Item currentItem;

        while(sc.hasNextLine()){
            currentLine = sc.nextLine();
            currentItem = unmarshallItem(currentLine);
            items.put(currentItem.getItemName(), currentItem);
        }
        sc.close();
    }

    private String marshallItem(Item anItem){
        String itemAsText = anItem.getItemName() + DELIMITER + anItem.getItemCost() + DELIMITER + anItem.getNumItems() + DELIMITER;
        return itemAsText;
    }

    private void writeItems() throws VendingMachineDaoPersistenceException {
        PrintWriter out;
        try{
            out = new PrintWriter(new FileWriter(VENDING_MACHINE));
        } catch(IOException ex){
            throw new VendingMachineDaoPersistenceException("Could not save the item inventory.");
        }

        String itemAsText;

        List<Item> itemList = new ArrayList(items.values());
        for(Item currentItem : itemList){
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }
}
