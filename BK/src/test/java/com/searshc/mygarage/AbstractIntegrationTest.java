package com.searshc.mygarage;

import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Base class of SpringJUnitTest to avoid DRY about JUnit configurations
 * environment.
 *
 * @author Jero
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest
public abstract class AbstractIntegrationTest {

}
