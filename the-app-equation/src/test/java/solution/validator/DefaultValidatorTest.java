package solution.validator;

import net.solution.validator.DefaultValidator;
import org.junit.Assert;
import org.junit.Test;

public class DefaultValidatorTest {

    @Test
    public void validationTest() {
        DefaultValidator defaultValidator = new DefaultValidator();
        Assert.assertTrue(defaultValidator.isValid("2*X+10"));
        Assert.assertFalse(defaultValidator.isValid(null));
        Assert.assertFalse(defaultValidator.isValid(""));
        Assert.assertFalse(defaultValidator.isValid("      "));
        Assert.assertFalse(defaultValidator.isValid("2*xx+10"));
        Assert.assertFalse(defaultValidator.isValid("2*xxxx+10"));
        Assert.assertFalse(defaultValidator.isValid("2*x+10+y"));
        Assert.assertFalse(defaultValidator.isValid("y+2*x+10"));
        Assert.assertFalse(defaultValidator.isValid("2*x+y+10"));
    }

}
