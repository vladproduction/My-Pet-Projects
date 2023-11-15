package solution.validator;

import net.solution.validator.ArithmeticOperationsValidator;
import org.junit.Assert;
import org.junit.Test;

public class ArithmeticOperationsValidatorTest {

    @Test
    public void positiveValidation() {
        ArithmeticOperationsValidator validator = new ArithmeticOperationsValidator();
        Assert.assertTrue(validator.isValid("2*x+5=17"));
        Assert.assertTrue(validator.isValid("-1.3*5/x=1.2"));
        Assert.assertTrue(validator.isValid("2*x=10"));
        Assert.assertTrue(validator.isValid("2*x+5+х+5=10"));
        Assert.assertTrue(validator.isValid("17=2*x+5"));
    }

    @Test
    public void negativeValidation() {
        ArithmeticOperationsValidator validator = new ArithmeticOperationsValidator();
        Assert.assertFalse(validator.isValid("2**x+5=17"));
        Assert.assertFalse(validator.isValid("2-*x+5=17"));
        Assert.assertFalse(validator.isValid("2+*x+5=17"));
        Assert.assertFalse(validator.isValid("2//x+5=17"));
    }

}
