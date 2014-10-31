package com.searshc.mygarage;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base class of SpringJUnitTest in avoid DRY configurations about JUnit text environment.
 * 
 * @author Jero
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringApplicationConfiguration(classes = Application.class)
public abstract class AbstractIntegrationTest {

}