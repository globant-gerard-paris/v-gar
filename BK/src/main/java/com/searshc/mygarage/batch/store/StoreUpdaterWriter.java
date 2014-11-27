package com.searshc.mygarage.batch.store;

import java.util.ArrayList;
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
 * The {@link StoreUpdaterWriter} is the custom item writer of spring batch, that validate if
 * necessary update or insert the store in the application.
 * 
 * @author Jero
 *
 */
@Configuration
public class StoreUpdaterWriter implements ItemWriter<Store> {

	@Autowired
	@Qualifier("storeUpdater")
	private ItemWriter<Store> storeUpdate;

	@Autowired
	@Qualifier("storeWriter")
	private ItemWriter<Store> storeWriter;

	@Autowired
	private StoreService storeService;

	@Override
	public void write(List<? extends Store> items) throws Exception {
		List<Store> storesToWrite = new ArrayList<Store>();
		List<Store> storesToUpdate = new ArrayList<Store>();

		for (Store store : items) {
			Store storeFound = storeService.findBySacStore(store.getSacStore());
			store.setIsActive(true);
			if (storeFound != null) {
				storesToUpdate.add(store);
			} else {
				storesToWrite.add(store);
			}
		}

		if (!CollectionUtils.isEmpty(storesToWrite)) {
			storeWriter.write(storesToWrite);
		}

		if (!CollectionUtils.isEmpty(storesToUpdate)) {
			storeUpdate.write(storesToUpdate);
		}
	}

}
