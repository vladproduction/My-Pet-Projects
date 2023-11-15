package solution.validator;

import net.solution.validator.HasUniqueEqualValidator;
import org.junit.Assert;
import org.junit.Test;

public class HasUniqueEqualValidatorTest {

    @Test
    public void positiveTest() {
        HasUniqueEqualValidator validator = new HasUniqueEqualValidator();
        Assert.assertTrue(validator.isValid("2*x+5=17"));
        Assert.assertTrue(validator.isValid("-1.3*5/x=1.2"));
        Assert.assertTrue(validator.isValid("2*x=10"));
        Assert.assertTrue(validator.isValid("2*x+5+х+5=10"));
        Assert.assertTrue(validator.isValid("17=2*x+5"));
    }

    @Test
    public void negativeTest() {
        HasUniqueEqualValidator validator = new HasUniqueEqualValidator();
        Assert.assertFalse(validator.isValid("2*x+5+17"));
        Assert.assertFalse(validator.isValid("-1.3*5/x1.2"));
        Assert.assertFalse(validator.isValid("2*x+5+х+5==10"));
        Assert.assertFalse(validator.isValid("17=2*x+5="));
        Assert.assertFalse(validator.isValid("=17=2*x+5"));
        Assert.assertFalse(validator.isValid("2*x==10"));
    }

}
