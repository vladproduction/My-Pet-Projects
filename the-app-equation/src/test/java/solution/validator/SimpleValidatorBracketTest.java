package solution.validator;

import net.solution.validator.SimpleValidatorBracket;
import org.junit.Assert;
import org.junit.Test;

public class SimpleValidatorBracketTest {
    @Test
    public void isValid() {
        SimpleValidatorBracket validator = new SimpleValidatorBracket();
        Assert.assertFalse(validator.isValid("(6/x)+5=3"));
        Assert.assertTrue(validator.isValid("6/x+5=3"));
    }
}
