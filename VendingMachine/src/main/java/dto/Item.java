package dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {
    private String itemName;
    private BigDecimal itemCost;
    private int numItems;

    public Item(String itemName){
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public BigDecimal getItemCost() {
        return itemCost;
    }

    public void setItemCost(BigDecimal itemCost) {
        this.itemCost = itemCost;
    }

    public int getNumItems() {
        return numItems;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    public int purchaseItem(){
        return numItems--;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }

        final Item other = (Item) obj;

        if(!Objects.equals(this.itemName, other.itemName)){
            return false;
        }

        if(!Objects.equals(this.itemCost, other.itemCost)){
            return false;
        }

        if(!Objects.equals(this.numItems,other.numItems)){
            return false;
        }
        return true;
    }

    // revisit hashCode method afterwards
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 7 * hash + Objects.hashCode(this.itemName);
        hash = 7 * hash * Objects.hashCode(this.itemCost);
        hash = 7 * hash * Objects.hashCode(this.numItems);

        return hash;
    }
}
