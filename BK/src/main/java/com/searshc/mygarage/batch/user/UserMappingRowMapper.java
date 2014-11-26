/**
 * 
 */
package com.searshc.mygarage.batch.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author Jero
 *
 */
public class UserMappingRowMapper implements RowMapper<UserMapping> {

	/*
	 * Map the {@link ResultSet} row to {@link UserMapping} class.
	 * 
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public UserMapping mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserMapping user = new UserMapping();
		user.setNcdbId(rs.getString("ncdbId"));
		user.setSywrId(rs.getString("sywrId"));
		return user;
	}

}
