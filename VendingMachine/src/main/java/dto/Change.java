package dto;

import java.math.BigDecimal;

public class Change {

    private int numQuarters;
    private int numDimes;
    private int numNickels;
    private int numPennies;

    public int getNumQuarters() {
        return numQuarters;
    }

    public int getNumDimes() {
        return numDimes;
    }

    public int getNumNickels() {
        return numNickels;
    }

    public int getNumPennies() {
        return numPennies;
    }

    public void calculateChange(Item item, BigDecimal funds){
        BigDecimal cost = item.getItemCost();
        BigDecimal userChange = funds.subtract(cost);

        userChange = userChange.multiply(new BigDecimal("100"));

        numQuarters = 0;
        numDimes = 0;
        numNickels = 0;
        numPennies = 0;

        while(userChange.floatValue() > 0){
            {
                while(userChange.floatValue() >= 25){
                    userChange = userChange.subtract(new BigDecimal("25"));
                    numQuarters++;
                }
                while(userChange.floatValue() >= 10){
                    userChange = userChange.subtract(new BigDecimal("10"));
                    numDimes++;
                }
                while(userChange.floatValue() >= 5){
                    userChange = userChange.subtract(new BigDecimal("5"));
                    numNickels++;
                }
                while(userChange.floatValue() >= 1){
                    userChange = userChange.subtract(new BigDecimal("1"));
                    numPennies++;
                }
            }
        }
    }
}
