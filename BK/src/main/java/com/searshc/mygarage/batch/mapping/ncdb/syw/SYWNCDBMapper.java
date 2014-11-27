package com.searshc.mygarage.batch.mapping.ncdb.syw;

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
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.searshc.mygarage.batch.mapping.ncdb.syw.dto.SYWNCDBMappingDTO;


@Configuration
@EnableBatchProcessing
public class SYWNCDBMapper {

	@Bean
	public ItemReader<SYWNCDBMappingDTO> getReader(DataSource dataSource) {
		JdbcCursorItemReader<SYWNCDBMappingDTO> reader = new JdbcCursorItemReader<SYWNCDBMappingDTO>();
		reader.setSql("Select * from Record");
		NCDBSYWRowMapper rowMapper = new NCDBSYWRowMapper();
		reader.setRowMapper(rowMapper);
		reader.setDataSource(dataSource);
		reader.setSql("Select * from source");
		return reader;
	}
	
	@Bean
	public ItemWriter<SYWNCDBMappingDTO> getWriter(DataSource dataSource) {
		JdbcBatchItemWriter<SYWNCDBMappingDTO> writer = new JdbcBatchItemWriter<SYWNCDBMappingDTO>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<SYWNCDBMappingDTO>());
		writer.setSql("insert  into `sywrncdbidmapping`(`SywrId`,`NcdbId`) values (:SywrId, :NcdbId)");
		writer.setDataSource(dataSource);
		return writer;
	}
	
	@Bean
	public Job sywNcdbMappingJob(JobBuilderFactory jobs, Step s1) {
		return jobs.get("sywNcdbMappingJob").incrementer(new RunIdIncrementer()).flow(s1).end().build();
	}
	
	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<SYWNCDBMappingDTO> reader,
			ItemWriter<SYWNCDBMappingDTO> writer) {
		return stepBuilderFactory.get("step1").<SYWNCDBMappingDTO, SYWNCDBMappingDTO>chunk(10)
				.reader(reader)
				.writer(writer).build();
	}
	
	@Bean
	public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
