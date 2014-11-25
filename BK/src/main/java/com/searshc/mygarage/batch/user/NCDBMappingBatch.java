package com.searshc.mygarage.batch.user;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;

/**
 * The {@link NCDBMappingBatch} is the process batch that fill all the stores of the customer in the
 * system from file csv.
 * 
 * @author Jero
 *
 */
//@Component
//@Configuration
public class NCDBMappingBatch {

//	@Autowired
	private JobLauncher jobLauncher;

//	@Autowired
	private Job job;
	
	public void run() { 
		//TODO Here will execute Teradata daily import of mapping table of NCDB & SYW user.
		//The configuration is '/BK/src/main/resources/spring/job-configuration.xml' (not finish yet)
	}
	
}