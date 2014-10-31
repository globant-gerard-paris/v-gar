package com.searshc.mygarage.controllers.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.searshc.mygarage.entities.store.Store;
import com.searshc.mygarage.entities.store.StoreRepository;
import com.searshc.mygarage.services.store.StoreService;
import com.searshc.mygarage.util.VGUtils;

/**
 * 
 * The {@link StoreController} have the responsibility to manager the request about the
 * {@link Store}s in the system.
 * 
 * @author Jero
 *
 */
@RestController
public class StoreController {

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private StoreService storeService;

	@RequestMapping(value = "/stores", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Store>> getStores() {
		List<Store> stores =  VGUtils.toList(storeRepository.findAll());
		return new ResponseEntity<List<Store>>(stores, HttpStatus.OK);
	}

	@RequestMapping(value = "/store/{storeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Store> getStores(@PathVariable("storeId") String storeId) {
		return new ResponseEntity<Store>(storeRepository.findOne(Long.valueOf(storeId)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/stores/lt/{myLat}/lg/{myLong}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Object>> getCarTransactionsHistory(
			@PathVariable("myLat") String myLat, @PathVariable("myLong") String myLong) {
		List<Object> stores = this.storeService.getStoresNear(myLat, myLong, "5000");
		return new ResponseEntity<List<Object>>(stores, HttpStatus.OK);
	}
}
