package com.searshc.mygarage.controllers.mock;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.searshc.mygarage.apis.ncdb.NCDBApi;
import com.searshc.mygarage.apis.ncdb.response.vintovehicle.VehiclesListByLicensePlateOrVinNumberResponse;

@RestController
@RequestMapping("ncdbmock")
public class NCDBVinNumberSearchControllerMock {

	private static final Log log = LogFactory.getLog(NCDBVinNumberSearchControllerMock.class);
	
	@Inject
	NCDBApi ncdbApi;
	
	@RequestMapping(value = "/vinnumber",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
	public ResponseEntity<String> getVehiclesByVINNumberMock(@RequestBody final String request) {
		log.info("Request with data: " + request);
		String response = "<VehiclesResponse xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\"><TrackingEnvelope><ClientTrackingID></ClientTrackingID><ServiceTrackingID>7fc122c9-cee1-46aa-8054-c12436551991</ServiceTrackingID><GeneratedBy>SearsAutomotiveRESTServices</GeneratedBy></TrackingEnvelope><VehicleCount>20</VehicleCount><Vehicles><Vehicle><ID>25433</ID><Year>2002</Year><Make>GMC TRUCK</Make><Model>SIERRA 3500 PICKUP</Model><Engine>V8-364  6.0L</Engine><VIN>1GDJC34U52E205180</VIN><LicensePlate>000001</LicensePlate><LicensePlateState>RI</LicensePlateState></Vehicle><Vehicle><ID>27117</ID><Year>2005</Year><Make>LEXUS TRUCK</Make><Model>RX 330</Model><Engine>V6-3311 3.3L DOHC</Engine><VIN>2T2HA31U25C040754</VIN><LicensePlate>000001</LicensePlate><LicensePlateState>RI</LicensePlateState></Vehicle><Vehicle><ID>90109</ID><Year>2008</Year><Make>CHEVROLET TRUCK</Make><Model>TAHOE HYBRID</Model><Engine>V8-364  6.0L</Engine><VIN>1GNFK13528R241168</VIN><LicensePlate>000001</LicensePlate><LicensePlateState>RI</LicensePlateState></Vehicle><Vehicle><ID>76494</ID><Year>2010</Year><Make>FORD-MEDIUM DUTY</Make><Model>E450 VAN</Model><Engine>V8-363  6.0L Dsl</Engine><VIN>1FDXE4FP1ADA27998</VIN><LicensePlate>000001</LicensePlate><LicensePlateState>RI</LicensePlateState></Vehicle><Vehicle><ID>25348</ID><Year>2002</Year><Make>CHEVROLET TRUCK</Make><Model>AVALANCHE 1500</Model><Engine>V8-325  5.3L</Engine><VIN>3GNEK13T32G290123</VIN><LicensePlate>000001</LicensePlate><LicensePlateState>RI</LicensePlateState></Vehicle><Vehicle><ID>87823</ID><Year>2014</Year><Make>GMC TRUCK</Make><Model>SIERRA 1500 PICKUP</Model><Engine>V8-325  5.3L</Engine><VIN>3GTU2VEC4EG430631</VIN><LicensePlate>000001</LicensePlate><LicensePlateState>RI</LicensePlateState></Vehicle><Vehicle><ID>79922</ID><Year>2011</Year><Make>BUICK</Make><Model>LACROSSE</Model><Engine>V6-3564 3.6L DOHC</Engine><VIN>1G4GA5ED0BF355556</VIN><LicensePlate>000001</LicensePlate><LicensePlateState>RI</LicensePlateState></Vehicle><Vehicle><ID>90138</ID><Year>2007</Year><Make>TOYOTA TRUCK</Make><Model>HIGHLANDER HYBRID</Model><Engine>V6-3311 3.3L DOHC</Engine><VIN>JTEGW21A770021112</VIN><LicensePlate>000001</LicensePlate><LicensePlateState>RI</LicensePlateState></Vehicle><Vehicle><ID>18502</ID><Year>1999</Year><Make>LINCOLN</Make><Model>CONTINENTAL</Model><Engine>V8-281  4.6L DOHC</Engine><VIN>1LNHM97VXXY719437</VIN><LicensePlate>000001</LicensePlate><LicensePlateState>RI</LicensePlateState></Vehicle><Vehicle><ID>0</ID><Year>2008</Year><Make>HARLEY DAVIDSON</Make><Model>FLHT ELECTRA GLIDE</Model><Engine> </Engine><VIN>1HD1FMM118Y615438</VIN><LicensePlate>000001</LicensePlate><LicensePlateState>RI</LicensePlateState></Vehicle><Vehicle><ID>0</ID><Year>2012</Year><Make>HARLEY DAVIDSON</Make><Model>FLHP ROAD KNG POLICE</Model><Engine> </Engine><VIN>1HD1FHM11CB686416</VIN><LicensePlate>000001</LicensePlate><LicensePlateState>RI</LicensePlateState></Vehicle><Vehicle><ID>91154</ID><Year>2014</Year><Make>HONDA</Make><Model>CIVIC</Model><Engine> 4-1799 1.8L SOHC</Engine><VIN>19XFB2F83EE224116</VIN><LicensePlate>000001</LicensePlate><LicensePlateState>RI</LicensePlateState></Vehicle><Vehicle><ID>31304</ID><Year>2007</Year><Make>CHEVROLET</Make><Model>IMPALA</Model><Engine>V6-213  3.5L</Engine><VIN>2G1WB58K979345379</VIN><LicensePlate>000001</LicensePlate><LicensePlateState>RI</LicensePlateState></Vehicle><Vehicle><ID>27586</ID><Year>2006</Year><Make>FORD TRUCK</Make><Model>E350 VAN</Model><Engine>V8-363  6.0L Dsl</Engine><VIN>1FDSS34PX6HA52009</VIN><LicensePlate>000001</LicensePlate><LicensePlateState>RI</LicensePlateState></Vehicle><Vehicle><ID>76494</ID><Year>2010</Year><Make>FORD-MEDIUM DUTY</Make><Model>E450 VAN</Model><Engine>V8-363  6.0L Dsl</Engine><VIN>1FDXE4FP5ADA16034</VIN><LicensePlate>000001</LicensePlate><LicensePlateState>RI</LicensePlateState></Vehicle><Vehicle><ID>82443</ID><Year>2012</Year><Make>AUDI</Make><Model>A7</Model><Engine>V6-2995 3.0L DOHC</Engine><VIN>WAUSGAFC1CN023495</VIN><LicensePlate>000001</LicensePlate><LicensePlateState>RI</LicensePlateState></Vehicle><Vehicle><ID>96095</ID><Year>2015</Year><Make>MERCEDES BENZ TRUCK</Make><Model>GLK350</Model><Engine>V6-3498 3.5L DOHC</Engine><VIN>WDCGG8JB2FG346585</VIN><LicensePlate>000001</LicensePlate><LicensePlateState>RI</LicensePlateState></Vehicle><Vehicle><ID>34089</ID><Year>2009</Year><Make>TOYOTA TRUCK</Make><Model>RAV4</Model><Engine>V6-3456 3.5L DOHC</Engine><VIN>2T3BK31V29W009399</VIN><LicensePlate>000001</LicensePlate><LicensePlateState>RI</LicensePlateState></Vehicle><Vehicle><ID>31291</ID><Year>2007</Year><Make>CADILLAC</Make><Model>DTS</Model><Engine>V8-279  4.6L DOHC</Engine><VIN>1G6KD57Y37U227226</VIN><LicensePlate>000001</LicensePlate><LicensePlateState>RI</LicensePlateState></Vehicle><Vehicle><ID>85370</ID><Year>2013</Year><Make>CHEVROLET</Make><Model>VOLT</Model><Engine> 4-1398 1.4L DOHC</Engine><VIN>1G1RA6E40DU147560</VIN><LicensePlate>000001</LicensePlate><LicensePlateState>RI</LicensePlateState></Vehicle></Vehicles></VehiclesResponse>";
		return new ResponseEntity<String>(response, null, HttpStatus.OK);
	}
}
