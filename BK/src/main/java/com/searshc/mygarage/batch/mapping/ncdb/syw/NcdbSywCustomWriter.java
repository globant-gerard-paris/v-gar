package com.searshc.mygarage.batch.mapping.ncdb.syw;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DuplicateKeyException;

import com.searshc.mygarage.batch.mapping.ncdb.syw.dto.SYWNCDBMappingDTO;

/**
 * 
 * The {@link NcdbSywCustomWriter} is the custom item writer of spring batch that fill 
 * the mapping table user of SYW & NCDB.
 * 
 * @author Jero
 *
 */
@Configuration
public class NcdbSywCustomWriter implements ItemWriter<SYWNCDBMappingDTO> {
	
	Log log = LogFactory.getLog(NcdbSywCustomWriter.class);
	
	@Autowired
	private ItemWriter<SYWNCDBMappingDTO> writer;

	@Override
	public void write(List<? extends SYWNCDBMappingDTO> usersMapping) throws Exception {
		for (SYWNCDBMappingDTO userMapping : usersMapping) {
			try {
				writer.write(usersMapping);
			} catch (DuplicateKeyException e) {
				log.debug("The user with NCDB_ID: "+userMapping.getNcdbId()+" & SWI_ID:"+userMapping.getSywrId()+" already exist.");
			}
		}
	}
}