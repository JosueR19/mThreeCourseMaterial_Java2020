package dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private String productType;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;

    public Product(String productType, BigDecimal costPerSquareFoot, BigDecimal laborCostPerSquareFoot){
        this.productType = productType;
        this.costPerSquareFoot = costPerSquareFoot;
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    @Override
    public int hashCode(){
        int hash = 7;
        hash = 7 * hash + Objects.hashCode(this.productType);
        hash = 7 * hash + Objects.hashCode(this.costPerSquareFoot);
        hash = 7 * hash + Objects.hashCode(this.laborCostPerSquareFoot);

        return hash;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }

        if(obj == null || obj.getClass() != getClass()){
            return false;
        }

        final Product other = (Product) obj;
        if(this.productType != other.productType){
            return false;
        }

        if(this.costPerSquareFoot != other.costPerSquareFoot){
            return false;
        }

        if(this.laborCostPerSquareFoot != other.laborCostPerSquareFoot){
            return false;
        }

        return true;
    }
}
