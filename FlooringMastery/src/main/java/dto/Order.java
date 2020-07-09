package dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

public class Order {
    private int orderNumber;
    private String customerName;
    private StateTax stateTax;
    private Product product;
    private LocalDate date;
    private BigDecimal area, materialCost, laborCost, taxTotal, total;

    public Order(String customerName, StateTax stateTax, Product product, BigDecimal area, LocalDate date){
        this.customerName = customerName;
        this.stateTax = stateTax;
        this.product = product;
        this.area = area;
        this.date = date;

        this.materialCost = area.multiply(product.getCostPerSquareFoot()).setScale(2, RoundingMode.HALF_UP);
        this.laborCost = area.multiply(product.getLaborCostPerSquareFoot()).setScale(2, RoundingMode.HALF_UP);
        this.taxTotal = ((materialCost.add(laborCost)).multiply(stateTax.getTaxRate().divide(new BigDecimal("100")))).setScale(2, RoundingMode.HALF_UP);
        this.total = materialCost.add(laborCost).add(taxTotal);
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public StateTax getStateTax() {
        return stateTax;
    }

    public void setStateTax(StateTax stateTax) {
        this.stateTax = stateTax;
    }

    public Product getProductType() {
        return product;
    }

    public void setProductType(Product product) {
        this.product = product;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public LocalDate getDate(){
        return date;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public BigDecimal getMaterialCost() {
        return materialCost = this.product.getCostPerSquareFoot().multiply(area).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getLaborCost() {
        return laborCost = this.product.getLaborCostPerSquareFoot().multiply(area).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTaxTotal() {
        return taxTotal = ((this.stateTax.getTaxRate().divide(new BigDecimal("100"))).multiply(this.materialCost.add(this.laborCost))).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotal() {
        return total = this.materialCost.add(this.laborCost).add(this.taxTotal);
    }

    @Override
    public int hashCode(){
        int hash = 7;
        hash = 7 * hash + Objects.hashCode(this.orderNumber);
        hash = 7 * hash + Objects.hashCode(this.customerName);
        hash = 7 * hash + Objects.hashCode(this.stateTax);
        hash = 7 * hash + Objects.hashCode(this.product);
        hash = 7 * hash + Objects.hashCode(this.area);
        hash = 7 * hash + Objects.hashCode(this.materialCost);
        hash = 7 * hash + Objects.hashCode(this.laborCost);
        hash = 7 * hash + Objects.hashCode(this.taxTotal);
        hash =  7 * hash + Objects.hashCode(this.total);

        return hash;
    }

    @Override
    public boolean equals (Object obj){
        if(this == obj){
            return true;
        }

        if(obj == null || obj.getClass() != getClass()){
            return false;
        }

        final Order other = (Order) obj;
        if(this.orderNumber != other.orderNumber){
            return false;
        }

        if(this.customerName != other.customerName){
            return false;
        }

        if(this.stateTax != other.stateTax){
            return false;
        }

        if(this.product != other.product){
            return false;
        }

        if(this.area != other.area){
            return false;
        }

        if(this.materialCost != other.materialCost){
            return false;
        }

        if(this.laborCost   != other.laborCost){
            return false;
        }

        if(this.taxTotal != other.taxTotal){
            return false;
        }

        if(this.total != other.total){
            return false;
        }

        return true;
    }
}
