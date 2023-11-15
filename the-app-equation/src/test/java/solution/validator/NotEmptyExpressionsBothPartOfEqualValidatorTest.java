package solution.validator;

import net.solution.validator.NotEmptyExpressionsBothPartOfEqualValidator;
import org.junit.Assert;
import org.junit.Test;

public class NotEmptyExpressionsBothPartOfEqualValidatorTest {

    @Test
    public void positiveTest() {
        NotEmptyExpressionsBothPartOfEqualValidator validator = new NotEmptyExpressionsBothPartOfEqualValidator();
        Assert.assertTrue(validator.isValid("2*x+5=17"));
        Assert.assertTrue(validator.isValid("-1.3*5/x=1.2"));
        Assert.assertTrue(validator.isValid("2*x=10"));
        Assert.assertTrue(validator.isValid("2*x+5+х+5=10"));
        Assert.assertTrue(validator.isValid("17=2*x+5"));
    }

    @Test
    public void negativeTest() {
        NotEmptyExpressionsBothPartOfEqualValidator validator = new NotEmptyExpressionsBothPartOfEqualValidator();
        Assert.assertFalse(validator.isValid("2*x+5+17="));
        Assert.assertFalse(validator.isValid("=-1.3*5/x1.2"));
        Assert.assertFalse(validator.isValid("="));
    }

}
