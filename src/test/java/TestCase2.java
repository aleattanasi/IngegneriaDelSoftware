import static org.junit.Assert.*;

import org.junit.Test;

public class TestCase2 {
@Test
public void test() {
Client c = new Client(5,6,'+');
assertTrue(11==c.result);
}

}

