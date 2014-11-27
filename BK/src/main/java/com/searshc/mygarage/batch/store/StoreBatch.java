package com.searshc.mygarage.batch.store;

import java.net.MalformedURLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.searshc.mygarage.batch.CustomRecordSeparatorPolicy;
import com.searshc.mygarage.entities.Store;

/**
 * The {@link StoreBatch} is the process batch that fill all the stores of the
 * customer in the system from file csv.
 *
 * @author Jero
 *
 */
@Configuration
@EnableBatchProcessing()
@EnableScheduling
@PropertySource("classpath:spring/batch.properties")
public class StoreBatch {
	
	private Log log = LogFactory.getLog(StoreBatch.class);

    /**
     * Convenient constant for the common case of a comma delimiter.
     */
    public static final String DELIMITER_SEMI_COMMA = ";";
    
    private Job job;
    
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("step-1-stores-importer")
    private Step stepOne;

    @Autowired
    @Qualifier("step-2-stores-importer")
    private Step stepTwo;
    
	@Bean(name = "storeWriter")
	public ItemWriter<Store> writer(DataSource dataSource) {
		JdbcBatchItemWriter<Store> writer = new JdbcBatchItemWriter<Store>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Store>());
		writer.setSql("INSERT INTO store (sac_store,  standing,  store_type, region_number,  region_name,  district_number,  district_name, address,  city,  state,  zip_code,  phone,   wifi, car_rental,  one_stop,  latitude,  longitude,  store_manager, monday_open, monday_close, tuesday_open, tuesday_close, wednesday_open, wednesday_close, thursday_open, thursday_close, friday_open, friday_close, saturday_open, saturday_close, sunday_open, sunday_close, is_active ) VALUES (:sacStore, :standing, :storeType, :regionNumber, :regionName, :districtNumber, :districtName, :address, :city, :state, :zipCode, :phone, :wifi, :carRental, :oneStop, :latitude, :longitude, :storeManager, :mondayOpen, :mondayClose, :tuesdayOpen, :tuesdayClose, :wednesdayOpen, :wednesdayClose, :thursdayOpen, :thursdayClose, :fridayOpen, :fridayClose, :saturdayOpen, :saturdayClose, :sundayOpen, :sundayClose, :isActive)");
		writer.setDataSource(dataSource);
		return writer;
	}

	@Bean(name = "storeUpdater")
	public ItemWriter<Store> update(DataSource dataSource) {
		JdbcBatchItemWriter<Store> writer = new JdbcBatchItemWriter<Store>();
		BeanPropertyItemSqlParameterSourceProvider<Store> provider = new BeanPropertyItemSqlParameterSourceProvider<Store>();
		writer.setItemSqlParameterSourceProvider(provider);
		writer.setSql("UPDATE store SET sac_store = :sacStore, standing = :standing,  store_type = :storeType, region_number = :regionNumber ,  region_name = :regionName,  district_number = :districtNumber,  district_name = :districtName, address = :address,  city = :city,  state = :state,  zip_code = :zipCode,  phone = :phone,   wifi = :wifi, car_rental = :carRental,  one_stop = :oneStop,  latitude = :latitude,  longitude = :longitude,  store_manager = :storeManager, monday_open = :mondayOpen, monday_close = :mondayClose, tuesday_open = :tuesdayOpen, tuesday_close = :tuesdayClose, wednesday_open = :wednesdayOpen, wednesday_close = :wednesdayClose, thursday_open = :thursdayOpen, thursday_close = :thursdayClose, friday_open = :fridayOpen, friday_close = :fridayClose, saturday_open = :saturdayOpen, saturday_close = :saturdayClose, sunday_open = :sundayOpen, sunday_close = :sundayClose,  is_active = :isActive WHERE sac_store = :sacStore");
		writer.setDataSource(dataSource);
		return writer;
	}
    
    // tag::readerwriterprocessor[]
    @Bean
    public ItemReader<Store> reader() {

        FlatFileItemReader<Store> reader = new FlatFileItemReader<Store>();

        // Skip the Header column of the file
        reader.setLinesToSkip(3);
        reader.setRecordSeparatorPolicy(new CustomRecordSeparatorPolicy());

        // reader = setURLResource(reader);
        reader = setClassPathResource(reader);
        reader = setLineMapper(reader);

        return reader;
    }

    private FlatFileItemReader<Store> setClassPathResource(final FlatFileItemReader<Store> reader) {
        reader.setResource(new ClassPathResource("store-list.csv"));
        return reader;
    }

    private FlatFileItemReader<Store> setURLResource(final FlatFileItemReader<Store> reader) {
        try {
            reader.setResource(new UrlResource(
                    "http://virtual-garage-file-system.paperplane.io/store-list.csv"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return reader;
    }

    private FlatFileItemReader<Store> setLineMapper(final FlatFileItemReader<Store> reader) {
        reader.setLineMapper(new DefaultLineMapper<Store>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer(DELIMITER_SEMI_COMMA) {

                    {
                        setNames(new String[]{"sacStore", "standing", "storeType",
                            "regionNumber", "regionName", "districtNumber", "districtName",
                            "address", "city", "state", "zipCode", "phone", "wifi",
                            "carRental", "oneStop", "latitude", "longitude", "storeManager",
                            "mondayOpen", "mondayClose", "tuesdayOpen", "tuesdayClose",
                            "wednesdayOpen", "wednesdayClose", "thursdayOpen", "thursdayClose",
                            "fridayOpen", "fridayClose", "saturdayOpen", "saturdayClose",
                            "sundayOpen", "sundayClose"});
                    }
                });

                setFieldSetMapper(new BeanWrapperFieldSetMapper<Store>() {
                    {
                        setTargetType(Store.class);
                    }
                });
            }
        });
        return reader;
    }

    // end::readerwriterprocessor[]
    // tag::jobstep[]
    
    @Bean
    public Job importerStores(JobBuilderFactory jobs) {
    	job = jobs.get("importerStores")
		.incrementer(new RunIdIncrementer())
		.flow(stepOne)
		.next(stepTwo)
		.end()
        .build();
    	return job;
    }
    //Execute each 20 at 4am evey month
    @Scheduled(cron="0 0 4 20 * ?")
    public void run (){
    	try {
    		log.info(" ****** Starting to update stores Job ******* ");
    		JobParameters jobParameters = 
    				  new JobParametersBuilder()
    				  .addLong("time",System.currentTimeMillis()).toJobParameters();
    		
			jobLauncher.run(job, jobParameters);
			log.info(" ****** Finish update stores ******* ");
    	} catch (Exception e) {
			log.error("An error ocurre when try to execute Importer Store Job. ", e);
		}
    }
    
    @Bean(name="step-1-stores-importer")
    public Step stepOne(StepBuilderFactory stepBuilderFactory, ItemReader<Store> reader,
    		StoreDisabledCustomWriter writer) {

        return stepBuilderFactory.get("stepOne").<Store, Store>chunk(2000).reader(reader)
                .writer(writer).build();
    }
    
    @Bean(name="step-2-stores-importer")
    public Step stepTwo(StepBuilderFactory stepBuilderFactory, ItemReader<Store> reader,
    		StoreUpdaterWriter writer) {
    	
    	return stepBuilderFactory.get("stepTwo").<Store, Store>chunk(100).reader(reader)
    			.writer(writer).build();
    }

    // end::jobstep[]
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
