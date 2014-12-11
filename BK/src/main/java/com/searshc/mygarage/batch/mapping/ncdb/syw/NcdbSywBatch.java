package com.searshc.mygarage.batch.mapping.ncdb.syw;

import java.util.Calendar;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.searshc.mygarage.batch.mapping.ncdb.syw.dto.SYWNCDBMappingDTO;

/**
 * 
 * The {@link NcdbSywBatch} is the Job that have to responsibility to fill the table relationship of
 * user NCDB & SYW.
 * 
 * @author Jero
 * 
 */
@Configuration
@EnableBatchProcessing
@EnableScheduling
public class NcdbSywBatch {

	private static final Log LOG = LogFactory.getLog(NcdbSywBatch.class);
	private static final int DAYS_TO_LOAD = 2;
	private static final int ROWS_BY_TX = 500;

	@Autowired
	private JobLauncher jobLauncher;

	private Job job;

	@Autowired
	@Qualifier("datasource-teradata")
	private DataSource dataSourceOrigin;

	@Autowired
	@Qualifier("datasource-application")
	private DataSource dataSourceApp;

	@Autowired
	@Qualifier("job-mappingtable-step-one")
	private Step stepOne;

	@Bean(name = "sywncdbMappingReader")
	public ItemReader<SYWNCDBMappingDTO> getReader() {
		
		JdbcCursorItemReader<SYWNCDBMappingDTO> reader = new JdbcCursorItemReader<SYWNCDBMappingDTO>();
		NcdbSywRowMapper rowMapper = new NcdbSywRowMapper();
		
		reader.setRowMapper(rowMapper);
		reader.setDataSource(dataSourceOrigin);

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT	T.UNV_LYL_CRD_NO AS SywrId, ");
		sql.append("		A.FAM_ID AS NcdbId ");
		sql.append("FROM	POS_DW_VIEWS.POSXTPX_POS_TRAN T ");
		sql.append("LEFT ");
		sql.append("OUTER ");
		sql.append("JOIN	POS_DW_VIEWS.POSXTAU_AUTO_ADDL_INFO A ");
		sql.append("ON		T.CUS_IAN_ID_NO = A.CUS_IAN_ID_NO ");
		sql.append("WHERE	1 = 1 ");
		sql.append("AND		T.TRS_DT > " + getCurrentToSearch());
		sql.append("AND		T.UNV_LYL_CRD_NO IS NOT NULL ");
		sql.append("AND		A.FAM_ID <> '000000000000' ");
		sql.append("GROUP ");
		sql.append("BY		1,2 ");
		reader.setSql(sql.toString());

		return reader;
	}

	private String getCurrentToSearch() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -1 * DAYS_TO_LOAD);
		return String.format("%03d%02d%02d", (100 + (cal.get(Calendar.YEAR) - 2000)),
				cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE));
	}

	@Bean
	public ItemWriter<SYWNCDBMappingDTO> getWriter() {
		JdbcBatchItemWriter<SYWNCDBMappingDTO> writer = new JdbcBatchItemWriter<SYWNCDBMappingDTO>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<SYWNCDBMappingDTO>());
		writer.setDataSource(dataSourceApp);
		writer.setSql("insert  into `sywrncdbidmapping`(`SywrId`,`NcdbId`) values (:SywrId, :NcdbId)");
		return writer;
	}

	@Bean
	public Job sywNcdbMappingJob(JobBuilderFactory jobs) {
		this.job = jobs.get("sywNcdbMappingJob").incrementer(new RunIdIncrementer()).flow(stepOne)
				.end().build();
		return job;
	}

	@Bean(name = "job-mappingtable-step-one")
	public Step stepOneMapping(StepBuilderFactory stepBuilderFactory,
			ItemReader<SYWNCDBMappingDTO> reader, NcdbSywCustomWriter writer) {
		return stepBuilderFactory.get("sywncdbMappingJob")
				.<SYWNCDBMappingDTO, SYWNCDBMappingDTO> chunk(ROWS_BY_TX).reader(reader).writer(writer)
				.build();
	}

	@Bean
	public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	// Every day of 4 AM.
	@Scheduled(cron = "0 0 4 * * ?")
	public void run() {
		try {
			LOG.info(" ****** Starting to update mapping table NCDB & SYW User Job ******* ");
			JobParameters jobParameters = new JobParametersBuilder().addLong("time",
					System.currentTimeMillis()).toJobParameters();
			jobLauncher.run(job, jobParameters);
			LOG.info(" ****** Finish the Job of mapping NCDB & SYW Users ******* ");
		} catch (Exception e) {
			LOG.error("An error ocurre when try to execute Job of mapping NCDB & SYW Users.", e);
		}
	}

}
