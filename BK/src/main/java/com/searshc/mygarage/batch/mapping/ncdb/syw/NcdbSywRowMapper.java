package com.searshc.mygarage.batch.mapping.ncdb.syw;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.searshc.mygarage.batch.mapping.ncdb.syw.dto.SYWNCDBMappingDTO;

public class NcdbSywRowMapper implements RowMapper<SYWNCDBMappingDTO>{

	public static final String NCDB_FIELD_NAME = "NcdbId";
	public static final String SYW_FIELD_NAME = "SywrId";
	
	@Override
	public SYWNCDBMappingDTO mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		SYWNCDBMappingDTO row = new SYWNCDBMappingDTO();
		row.setNcdbId(rs.getLong(NCDB_FIELD_NAME));
		row.setSywrId(rs.getLong(SYW_FIELD_NAME));
		return row;
	}

}
