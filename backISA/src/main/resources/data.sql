INSERT INTO `isa20`.`authority` (`id`, `name`) VALUES ( 1, "ROLE_PATIENT");
INSERT INTO `isa20`.`authority` (`id`, `name`) VALUES ( 2, "ROLE_PHARMACY_ADMIN");
INSERT INTO `isa20`.`authority` (`id`, `name`) VALUES ( 3, "ROLE_SUPPLIER");
INSERT INTO `isa20`.`authority` (`id`, `name`) VALUES ( 4, "ROLE_PHARMACIST");
INSERT INTO `isa20`.`authority` (`id`, `name`) VALUES ( 5, "ROLE_DERMATOLOGIST");
INSERT INTO `isa20`.`authority` (`id`, `name`) VALUES ( 6, "ROLE_SYSTEM_ADMIN");


INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`)
VALUE
( 1 , "sifra1" , "bromazepam" , "specifikacija 1" , "antibiotik");

INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`)
VALUE
( 2 , "sifra2" , "ibuprofen" , "specifikacija 1" , "antibiotik");

INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`)
VALUE
( 3 , "sifra3" , "karvedilol" , "specifikacija 1" , "antibiotik");

INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`)
VALUE
( 4 , "sifra4" , "ranitidin" , "specifikacija 1" , "antibiotik");

INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`)
VALUE
( 5 , "sifra5" , "pavulon" , "specifikacija 1" , "antibiotik");



INSERT INTO `isa20`.`address`
(`id`,`city`,`country`,`number`,`street`)
VALUES
(1, "novi sad" , "srbija" , "4" ,"bulevar oslobodjenja");


INSERT INTO `isa20`.`address`
(`id`,`city`,`country`,`number`,`street`)
VALUES
(2, "novi sad" , "srbija" , "8" ,"hadzi ruvimova");

INSERT INTO `isa20`.`address`
(`id`,`city`,`country`,`number`,`street`)
VALUES
(3, "beograd" , "srbija" , "7" ,"doza djerdja");

INSERT INTO `isa20`.`address`
(`id`,`city`,`country`,`number`,`street`)
VALUES
(4, "kraljevo" , "srbija" , "6" ,"titova");

INSERT INTO `isa20`.`address`
(`id`,`city`,`country`,`number`,`street`)
VALUES
(5, "valjevo" , "srbija" , "5" ,"marksova");


-- INSERT INTO `isa20`.`user` ( )

INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number` , `address_id`)
VALUES
("Patient",1,1, "petar.ponjevic7@gmail.com", "ponjevic" ,"petar","'$2a$10$FByRnmrWkzf.8i3rYzaVQuUQuB1hFwmbX8a/iavMkIe1rMET3emni'","06412345",2);

INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number`, `address_id`)
VALUES
("Patient",2,1,"petar.ponjevic6@gmail.com", "milosevic" ,"metar","'$2a$10$kphiKvaevoKnWf1b259PAOKZqapf5jR0zmcopR0jpiwB8zixT3kTS'","06412345",3);

INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number`, `address_id`)
VALUES
("Patient",3,0, "petar.ponjevic5@gmail.com", "jankovic" ,"dzoni","'$2a$10$kphiKvaevoKnWf1b259PAOKZqapf5jR0zmcopR0jpiwB8zixT3kTS'","06412345",4);

INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number`, `address_id`)
VALUES
("Patient",4,0, "petar.ponjevic4@gmail.com", "jankovic" ,"poni","'$2a$10$kphiKvaevoKnWf1b259PAOKZqapf5jR0zmcopR0jpiwB8zixT3kTS'","06412345",5);







INSERT INTO `isa20`.`pharmacy`
(`id`,`description`,`name`,`address_id`)
values
(1,"opise neke apoteke","jankovic" , 1) ;


INSERT INTO `isa20`.`pharmacy`
(`id`,`description`,`name`,`address_id`)
values
(2,"opise neke apoteke 2","milosevic" , 2) ;





 INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 1 , 120 , 10 , 1, 2 );
 
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 2 , 130 , 9 , 2, 2 );
  
   INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 3 , 420 , 8 , 1, 1 );
   
    INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 4 , 520 , 7 , 3, 1 );
    
     INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 5 , 550 , 6 , 4, 2 );
     
     INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 6 , 240 , 5 , 5, 1 );
     
     





