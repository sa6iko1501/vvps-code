package test;

import org.junit.Test;
import org.junit.Assert;

public class PriceCalculatorTest {
    @Test
    public void calculatePriceBasedOnOneWayOrRoundTripForOneWay(){
        Assert.assertEquals(4.50, main.PriceCalculator
                .calculatePriceBasedOnOneWayOrRoundTrip(true, 4.50), 0);
    }

    @Test
    public void calculatePriceBasedOnOneWayOrRoundTripForRoundTrip(){
        Assert.assertEquals(9.00, main.PriceCalculator
                .calculatePriceBasedOnOneWayOrRoundTrip(false, 4.50),0);
    }

    @Test
    public void calculatePriceApplyDiscountsTestNoDiscounts(){
        Assert.assertEquals(4.50, main.PriceCalculator
                .calculatePriceApplyDiscounts(4.50,
                        false, false, false), 0);
    }

    @Test
    public void calculatePriceApplyDiscountsTestOnlyFamilyCard(){
        Assert.assertEquals(10.00, main.PriceCalculator
                .calculatePriceApplyDiscounts(10,
                        false, false, true), 0);
    }

    @Test
    public void calculatePriceApplyDiscountsTestOnlySmallChild(){
        Assert.assertEquals(9.00, main.PriceCalculator
                .calculatePriceApplyDiscounts(10,
                        false, true, false), 0);
    }

    @Test
    public void calculatePriceApplyDiscountsTestSmallChildAndFamilyCard(){
        Assert.assertEquals(5.00, main.PriceCalculator
                .calculatePriceApplyDiscounts(10,
                        false, true, true), 0);
    }

    @Test
    public void calculatePriceApplyDiscountsTestOnlySenior(){
        Assert.assertEquals(6.6, main.PriceCalculator
                .calculatePriceApplyDiscounts(10.00,
                        true, false, false), 0);
    }
    @Test
    public void calculatePriceApplyDiscountsTestSeniorAndFamilyCard(){
        Assert.assertEquals(6.6, main.PriceCalculator
                .calculatePriceApplyDiscounts(10,
                        true, false, true), 0);
    }
    @Test
    public void calculatePriceApplyDiscountsTestSeniorAndSmallChild(){
        Assert.assertEquals(5.94, main.PriceCalculator
                .calculatePriceApplyDiscounts(10,
                        true, true, false), 0);
    }

    @Test
    public void calculatePriceApplyDiscountsTestAllDiscounts(){
        Assert.assertEquals(3.3, main.PriceCalculator
                .calculatePriceApplyDiscounts(10,
                        true, true, true), 0);
    }
}
