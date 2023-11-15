package solution.calculator;

import net.solution.calculator.SimpleCalculator;
import org.junit.Assert;
import org.junit.Test;

public class SimpleCalculatorTest {

    @Test
    public void calculateTest() {
        SimpleCalculator simpleCalculator = new SimpleCalculator();
        Assert.assertTrue(-6.5 == simpleCalculator.calculate("-1.3*x/2", 10));
        Assert.assertTrue(3 == simpleCalculator.calculate("6/x+5", -3));
        Assert.assertTrue(-0.5 == simpleCalculator.calculate("11/x+5", -2));
        Assert.assertTrue(-7 == simpleCalculator.calculate("2*x+5", -6));
        Assert.assertTrue(1 == simpleCalculator.calculate("2*x+5", -2));
        Assert.assertTrue(9 == simpleCalculator.calculate("2*x+5", 2));
        Assert.assertTrue(23 == simpleCalculator.calculate("3+4*x", 5));
        Assert.assertTrue(6 == simpleCalculator.calculate("3+x*5-10*2+3", 4));
        Assert.assertTrue(-14 == simpleCalculator.calculate("3+x*5-10*x+3", 4));
        Assert.assertTrue(11 == simpleCalculator.calculate("3+40/x", 5));
        Assert.assertTrue(-17 == simpleCalculator.calculate("2*x-5", -6));
        Assert.assertTrue(-7 == simpleCalculator.calculate("12/x-5", -6));
        Assert.assertTrue(-2 == simpleCalculator.calculate("12/x", -6));
        Assert.assertTrue(198 == simpleCalculator.calculate("3+x*4-10+x*20-4*x+x/2", 10));
    }
}
