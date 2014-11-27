package com.searshc.mygarage.batch.store;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import com.searshc.mygarage.entities.Store;
import com.searshc.mygarage.services.store.StoreService;

/**
 * 
 * The {@link StoreDisabledCustomWriter} is the custom item writer of spring batch, that disable all
 * stores from the application in order to active in the Step-2 only with the stores that will exist in the flat-file.
 * 
 * @author Jero
 *
 */
@Configuration
public class StoreDisabledCustomWriter implements ItemWriter<Store> {

	@Autowired
	@Qualifier("storeUpdater")
	private ItemWriter<Store> storeUpdate;

	@Autowired
	private StoreService storeService;

	@Override
	public void write(List<? extends Store> items) throws Exception {
		List<Store> stores = storeService.getList();
		for (Store store : stores) {
			store.setIsActive(false);
		}
		if (!CollectionUtils.isEmpty(stores)) {
			storeUpdate.write(stores);
		}
	}

}
