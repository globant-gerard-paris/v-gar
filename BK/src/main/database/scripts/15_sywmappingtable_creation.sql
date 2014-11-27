CREATE TABLE `sywrncdbidmapping` (
  `SywrId` decimal(18,0) NOT NULL,
  `NcdbId` varchar(20) NOT NULL,
  PRIMARY KEY (`SywrId`,`NcdbId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# USER syw_ncdb
insert  into `sywrncdbidmapping`(`SywrId`,`NcdbId`) values ('7081410000211605','000035925195');

# USER syw_ncdb_linked
insert  into `sywrncdbidmapping`(`SywrId`,`NcdbId`) values ('7081640000268599','000073311110');