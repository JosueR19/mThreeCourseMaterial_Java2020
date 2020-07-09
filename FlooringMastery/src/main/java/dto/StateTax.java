package dto;

import java.math.BigDecimal;
import java.util.Objects;

public class StateTax {
    private String stateAbbr;
    private String stateName;
    private BigDecimal taxRate;

    public StateTax(String stateAbbr, String stateName, BigDecimal taxRate){
        this.stateAbbr = stateAbbr;
        this.stateName = stateName;
        this.taxRate = taxRate;
    }

    public String getStateAbbr() {
        return stateAbbr;
    }

    public void setStateAbbr(String stateAbbr) {
        this.stateAbbr = stateAbbr;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public int hashCode(){
        int hash = 7;
        hash = 7 * hash + Objects.hashCode(this.stateAbbr);
        hash = 7 * hash + Objects.hashCode(this.stateName);
        hash = 7 * hash + Objects.hashCode(this.taxRate);

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

        final StateTax other = (StateTax) obj;
        if(this.stateAbbr != other.stateAbbr){
            return false;
        }

        if(this.stateName != other.stateName){
            return false;
        }

        if(this.taxRate != other.taxRate){
            return false;
        }

        return true;
    }
}
