package solution.resolver;

import net.solution.calculator.Calculator;
import net.solution.calculator.SimpleCalculator;
import net.solution.resolver.EquationResolverImpl;
import org.junit.Assert;
import org.junit.Test;

public class EquationResolverImplTest {

    @Test
    public void resolveWithSimpleCalculatorPositiveTest() {
        Calculator calculator = new SimpleCalculator();
        EquationResolverImpl resolver = new EquationResolverImpl(calculator);
        Assert.assertTrue(resolver.isSolution("2*x+5=17", 6));
        Assert.assertTrue(resolver.isSolution("2*x=10", 5));
        Assert.assertTrue(resolver.isSolution("2*x+5+x+5=10", 0));
        Assert.assertTrue(resolver.isSolution("17=2*x+5", 6));
        Assert.assertTrue(resolver.isSolution("198=3+x*4-10+x*20-4*x+x/2", 10));
        Assert.assertTrue(resolver.isSolution("17=2*x+5", 6));
        Assert.assertTrue(resolver.isSolution("-7=2*x+5", -6));
    }

    @Test
    public void resolveWithSimpleCalculatorNegativeTest() {
        Calculator calculator = new SimpleCalculator();
        EquationResolverImpl resolver = new EquationResolverImpl(calculator);
        Assert.assertFalse(resolver.isSolution("2*x+5=17", 1));
        Assert.assertFalse(resolver.isSolution("2*x=10", 50));
        Assert.assertFalse(resolver.isSolution("2*x+5+x+5=10", 10));
        Assert.assertFalse(resolver.isSolution("17=2*x+5", 70));
    }
}
