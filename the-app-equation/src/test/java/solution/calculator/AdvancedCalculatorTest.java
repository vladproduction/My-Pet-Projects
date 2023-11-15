package solution.calculator;

import net.solution.calculator.AdvancedCalculator;
import org.junit.Assert;
import org.junit.Test;

public class AdvancedCalculatorTest {


    @Test
    public void calculateSimpleTest() {
        AdvancedCalculator advancedCalculator = new AdvancedCalculator();
        Assert.assertTrue(-6.5 == advancedCalculator.calculate("-1.3*x/2", 10));
        Assert.assertTrue(3 == advancedCalculator.calculate("6/x+5", -3));
        Assert.assertTrue(-0.5 == advancedCalculator.calculate("11/x+5", -2));
        Assert.assertTrue(-7 == advancedCalculator.calculate("2*x+5", -6));
        Assert.assertTrue(1 == advancedCalculator.calculate("2*x+5", -2));
        Assert.assertTrue(9 == advancedCalculator.calculate("2*x+5", 2));
        Assert.assertTrue(23 == advancedCalculator.calculate("3+4*x", 5));
        Assert.assertTrue(6 == advancedCalculator.calculate("3+x*5-10*2+3", 4));
        Assert.assertTrue(-14 == advancedCalculator.calculate("3+x*5-10*x+3", 4));
        Assert.assertTrue(11 == advancedCalculator.calculate("3+40/x", 5));
        Assert.assertTrue(-17 == advancedCalculator.calculate("2*x-5", -6));
        Assert.assertTrue(-7 == advancedCalculator.calculate("12/x-5", -6));
        Assert.assertTrue(-2 == advancedCalculator.calculate("12/x", -6));
        Assert.assertTrue(198 == advancedCalculator.calculate("3+x*4-10+x*20-4*x+x/2", 10));
    }

    @Test
    public void calculateAdvancedTest() {
        AdvancedCalculator advancedCalculator = new AdvancedCalculator();
        Assert.assertTrue(10 == advancedCalculator.calculate("10*(3-x)", 2));
        Assert.assertTrue(50 == advancedCalculator.calculate("10*(3-x)", -2));
        Assert.assertTrue(11 == advancedCalculator.calculate("x-1+10*(3-x)", 2));
        Assert.assertTrue(47 == advancedCalculator.calculate("x-1+10*(3-x)", -2));
        Assert.assertTrue(7.5 == advancedCalculator.calculate("2+4*(x-1+10*(3-x))/8", 2));
        Assert.assertTrue(25.5 == advancedCalculator.calculate("2+4*(x-1+10*(3-x))/8", -2));
    }

}
