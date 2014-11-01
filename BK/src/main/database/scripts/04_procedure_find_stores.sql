DELIMITER $$
CREATE PROCEDURE `mygarage`.`GEODIST`( mylat float, mylong float, dist int )
BEGIN
    DECLARE lon1 float; 
    DECLARE lon2 float;
    DECLARE lat1 float; 
    DECLARE lat2 float;

set lon1 = mylong - dist/abs(cos(radians(mylat))*69);
set lon2 = mylong + dist/abs(cos(radians(mylat))*69);
set lat1 = mylat - (dist/69);
set lat2 = mylat + (dist/69);

 SELECT s.*, 3956 * 2 * ASIN(SQRT( POWER(SIN((myLat - abs(s.latitude)) * pi() /180 / 2), 2) +COS(myLat  * pi()/180) * COS(abs(s.latitude) * pi()/180) *POWER(SIN((myLong - s.longitude) * pi()/180 / 2), 2) )) as distance 
 FROM store s
 WHERE s.longitude between lon1 and lon2 and s.latitude between lat1 and lat2 
 having distance < dist ORDER BY Distance limit 10;

END $$