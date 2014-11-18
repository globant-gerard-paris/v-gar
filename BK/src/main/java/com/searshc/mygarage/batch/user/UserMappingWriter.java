package com.searshc.mygarage.batch.user;

import java.util.List;
import org.springframework.batch.item.ItemWriter;

/**
 * @author Jero
 *
 */
public class UserMappingWriter implements ItemWriter<UserMapping> {

	@Override
	public void write(List<? extends UserMapping> items) throws Exception {

		//TODO: this will be persist to Mysql.
		System.out.println("writer..." + items.size());
		for (UserMapping item : items) {
			System.out.println(item);
		}

	}
}