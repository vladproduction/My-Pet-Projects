package solution.validator;

import net.solution.validator.VariablesPresentOnlyOnOneSideOfEqualValidator;
import org.junit.Assert;
import org.junit.Test;

public class VariablesPresentOnlyOnOneSideOfEqualValidatorTest {

    @Test
    public void positiveValidation() {
        VariablesPresentOnlyOnOneSideOfEqualValidator validator = new VariablesPresentOnlyOnOneSideOfEqualValidator();
        Assert.assertTrue(validator.isValid("2*x+5=17"));
        Assert.assertTrue(validator.isValid("-1.3*5/x=1.2"));
        Assert.assertTrue(validator.isValid("2*x=10"));
        Assert.assertTrue(validator.isValid("2*x+5+х+5=10"));
        Assert.assertTrue(validator.isValid("17=2*x+5"));
    }

    @Test
    public void negativeValidation() {
        VariablesPresentOnlyOnOneSideOfEqualValidator validator = new VariablesPresentOnlyOnOneSideOfEqualValidator();
        Assert.assertFalse(validator.isValid("2*x+5=17+x"));
        Assert.assertFalse(validator.isValid("2+8=10"));
    }

}
