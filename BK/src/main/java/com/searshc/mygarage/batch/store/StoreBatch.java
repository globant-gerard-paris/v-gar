package com.searshc.mygarage.batch.store;

import java.net.MalformedURLException;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.searshc.mygarage.batch.CustomRecordSeparatorPolicy;
import com.searshc.mygarage.entities.store.Store;

/**
 * The {@link StoreBatch} is the process batch that fill all the stores of the customer in the
 * system from file csv.
 * 
 * @author Jero
 *
 */
@Configuration
@EnableBatchProcessing
public class StoreBatch {

	/**
	 * Convenient constant for the common case of a comma delimiter.
	 */
	public static final String DELIMITER_SEMI_COMMA = ";";

	// tag::readerwriterprocessor[]
	@Bean
	public ItemReader<Store> reader() {

		FlatFileItemReader<Store> reader = new FlatFileItemReader<Store>();

		// Skip the Header column of the file
		reader.setLinesToSkip(3);
		reader.setRecordSeparatorPolicy(new CustomRecordSeparatorPolicy());

		// TODO is commented the following line because is still TBD the way that will take the file stores.
		// reader = setURLResource(reader);
		reader = setClassPathResource(reader);

		reader = setLineMapper(reader);

		return reader;
	}

	private FlatFileItemReader<Store> setClassPathResource(FlatFileItemReader<Store> reader) {
		reader.setResource(new ClassPathResource("store-list.csv"));
		return reader;
	}

	private FlatFileItemReader<Store> setURLResource(FlatFileItemReader<Store> reader) {
		try {
			reader.setResource(new UrlResource(
					"http://virtual-garage-file-system.paperplane.io/store-list.csv"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return reader;
	}

	private FlatFileItemReader<Store> setLineMapper(FlatFileItemReader<Store> reader) {
		reader.setLineMapper(new DefaultLineMapper<Store>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer(DELIMITER_SEMI_COMMA) {

					{
						setNames(new String[] { "sacStore", "standing", "storeType",
								"regionNumber", "regionName", "districtNumber", "districtName",
								"address", "city", "state", "zipCode", "phone", "wifi",
								"carRental", "oneStop", "latitude", "longitude", "storeManager",
								"mondayOpen", "mondayClose", "tuesdayOpen", "tuesdayClose",
								"wednesdayOpen", "wednesdayClose", "thursdayOpen", "thursdayClose",
								"fridayOpen", "fridayClose", "saturdayOpen", "saturdayClose",
								"sundayOpen", "sundayClose" });
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

	@Bean
	public ItemWriter<Store> writer(DataSource dataSource) {
		JdbcBatchItemWriter<Store> writer = new JdbcBatchItemWriter<Store>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Store>());
		writer.setSql("INSERT INTO store (sac_store, " + "standing, " + "store_type, "
				+ "region_number, " + "region_name, " + "district_number, " + "district_name, "
				+ "address, " + "city, " + "state, " + "zip_code, " + "phone,  " + "wifi, "
				+ "car_rental, " + "one_stop, " + "latitude, " + "longitude, " + "store_manager, "
				+ "monday_open," + "monday_close," + "tuesday_open," + "tuesday_close,"
				+ "wednesday_open," + "wednesday_close," + "thursday_open," + "thursday_close,"
				+ "friday_open," + "friday_close," + "saturday_open," + "saturday_close,"
				+ "sunday_open," + "sunday_close" + ") "

				+ "VALUES "

				+ "(" + ":sacStore, " + ":standing, " + ":storeType, " + ":regionNumber, "
				+ ":regionName, " + ":districtNumber, " + ":districtName, " + ":address, "
				+ ":city, " + ":state, " + ":zipCode, " + ":phone, " + ":wifi, " + ":carRental, "
				+ ":oneStop, " + ":latitude, " + ":longitude, " + ":storeManager,"
				+ ":mondayOpen, " + ":mondayClose, " + ":tuesdayOpen, " + ":tuesdayClose, "
				+ ":wednesdayOpen, " + ":wednesdayClose, " + ":thursdayOpen, " + ":thursdayClose, "
				+ ":fridayOpen, " + ":fridayClose, " + ":saturdayOpen, " + ":saturdayClose, "
				+ ":sundayOpen, " + ":sundayClose " + ")");
		writer.setDataSource(dataSource);
		
		return writer;
	}

	// end::readerwriterprocessor[]

	// tag::jobstep[]
	@Bean
	public Job importerStores(JobBuilderFactory jobs, Step s1) {
		return jobs.get("importerStores").incrementer(new RunIdIncrementer()).flow(s1).end().build();
	}

	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Store> reader,
			ItemWriter<Store> writer) {

		return stepBuilderFactory.get("step1").<Store, Store> chunk(10).reader(reader)
				.writer(writer).build();
	}

	// end::jobstep[]

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}