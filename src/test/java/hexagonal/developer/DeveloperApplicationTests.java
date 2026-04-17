package hexagonal.developer;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeveloperApplicationTests {

	@Test
	@Disabled("Requires Oracle DB - skipped in CI")
	void contextLoads() {
	}

}