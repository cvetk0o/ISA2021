INSERT INTO `isa20`.`authority` (`id`, `name`) VALUES ( 1, "ROLE_PATIENT");
INSERT INTO `isa20`.`authority` (`id`, `name`) VALUES ( 2, "ROLE_PHARMACY_ADMIN");
INSERT INTO `isa20`.`authority` (`id`, `name`) VALUES ( 3, "ROLE_SUPPLIER");
INSERT INTO `isa20`.`authority` (`id`, `name`) VALUES ( 4, "ROLE_PHARMACIST");
INSERT INTO `isa20`.`authority` (`id`, `name`) VALUES ( 5, "ROLE_DERMATOLOGIST");
INSERT INTO `isa20`.`authority` (`id`, `name`) VALUES ( 6, "ROLE_SYSTEM_ADMIN");


--lekovi 


INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type` ,`avg_rate`)
VALUE
( 1 , "sifra1" , "bromazepam" , "specifikacija 1" , "antibiotik",1);

INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`,`avg_rate`)
VALUE
( 2 , "sifra2" , "ibuprofen" , "specifikacija 1" , "antidot",2);

INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`,`avg_rate`)
VALUE
( 3 , "sifra3" , "karvedilol" , "specifikacija 1" , "analgetik",3);

INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`,`avg_rate`)
VALUE
( 4 , "sifra4" , "ranitidin" , "specifikacija 1" , "analgetik",4);

INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`,`avg_rate`)
VALUE
( 5 , "sifra5" , "pavulon" , "specifikacija 1" , "antibiotik",5);


INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`,`avg_rate`)
VALUE
( 6, "sifra5" , "pregabalin" , "specifikacija 6" , "antidot",2);

INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`,`avg_rate`)
VALUE
( 7 , "sifra5" , "gabalin" , "specifikacija 7" , "antibiotik",4);


INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`,`avg_rate`)
VALUE
( 8 , "sifra5" , "riociguat" , "specifikacija 8" , "antidot",3);


INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`,`avg_rate`)
VALUE
( 9 , "sifra5" , "oguricit" , "specifikacija 9" , "analgetik",2);


INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`,`avg_rate`)
VALUE
( 10 , "sifra5" , "tricigolaren" , "specifikacija 10" , "antibiotik",4);


INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`,`avg_rate`)
VALUE
( 11 , "sifra5" , "brigatinib" , "specifikacija 11" , "antibiotik",3);


INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`,`avg_rate`)
VALUE
( 12 , "sifra5" , "triganitib" , "specifikacija 12" , "antidot",2);


INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`,`avg_rate`)
VALUE
( 13 , "sifra5" , "valuron" , "specifikacija 13" , "analgetik",5);


INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`,`avg_rate`)
VALUE
( 14 , "sifra5" , "deksametazon" , "specifikacija 14" , "antidot",1);


INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`,`avg_rate`)
VALUE
( 15 , "sifra5" , "heksametazon" , "specifikacija 15" , "antibiotik",5);


INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`,`avg_rate`)
VALUE
( 16 , "sifra5" , "tetametazon" , "specifikacija 16" , "antibiotik",1);


INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`,`avg_rate`)
VALUE
( 17 , "sifra5" , "timolol" , "specifikacija 17" , "antibiotik",5);


INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`,`avg_rate`)
VALUE
( 18 , "sifra5" , "lomotil" , "specifikacija 18" , "analgetik",2);


INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`,`avg_rate`)
VALUE
( 19 , "sifra5" , "erlotinib" , "specifikacija 19" , "antibiotik",3);


INSERT INTO `isa20`.`drug` 
(`id`, `code` , `name`,`specification`,`type`,`avg_rate`)
VALUE
( 20 , "sifra5" , "ibuprofen" , "specifikacija 20" , "antibiotik",4);





--adrese

INSERT INTO `isa20`.`address`
(`id`,`city`,`country`,`number`,`street`)
VALUES
(1, "novi sad" , "srbija" , "4" ,"bulevar oslobodjenja");


INSERT INTO `isa20`.`address`
(`id`,`city`,`country`,`number`,`street`)
VALUES
(2, "cacak" , "srbija" , "8" ,"hadzi ruvimova");

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






INSERT INTO `isa20`.`pharmacy`
(`id`,`avg_rate`,`consulting_price`,`description`,`name`,`address_id`)
values
(1,3,200,"opise neke apoteke","jankovic" , 1) ;


INSERT INTO `isa20`.`pharmacy`
(`id`,`avg_rate`,`consulting_price`,`description`,`name`,`address_id`)
values
(2,2,320,"opise neke apoteke 2","milosevic" , 2) ;


INSERT INTO `isa20`.`pharmacy`
(`id`,`avg_rate`,`consulting_price`,`description`,`name`,`address_id`)
values
(3,4,400,"opise neke apoteke","Benu" , 3) ;


INSERT INTO `isa20`.`pharmacy`
(`id`,`avg_rate`,`consulting_price`,`description`,`name`,`address_id`)
values
(4,5,880,"opise neke apoteke 2","Cvejic" , 4) ;



INSERT INTO `isa20`.`pharmacy`
(`id`,`avg_rate`,`consulting_price`,`description`,`name`,`address_id`)
values
(5,4,720,"opise neke apoteke","irisFarm" , 5) ;


INSERT INTO `isa20`.`pharmacy`
(`id`,`avg_rate`,`consulting_price`,`description`,`name`,`address_id`)
values
(6,3,640,"opise neke apoteke 2","Laurus" , 4) ;



INSERT INTO `isa20`.`pharmacy`
(`id`,`avg_rate`,`consulting_price`,`description`,`name`,`address_id`)
values
(7,2,200,"opise neke apoteke","Biofarm" , 3) ;


INSERT INTO `isa20`.`pharmacy`
(`id`,`avg_rate`,`consulting_price`,`description`,`name`,`address_id`)
values
(8,1,510,"opise neke apoteke 2","Centar" , 2) ;










-- INSERT INTO `isa20`.`user` ( )

INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number` ,`penal`, `address_id`)
VALUES
("Patient",1,1, "petar.ponjevic7@gmail.com", "ponjevic" ,"petar","$2a$10$VcOWM4BngtrhYi0lNkIYKuv71BUQgqMBbJoTBOzFRK3NFWOH05eN.","06412345",0,2);

INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number`, `avg_rate`, `address_id`)
VALUES
("Dermatologist",2,1,"petar.ponjevic6@gmail.com", "milosevic" ,"metar","$2a$10$VcOWM4BngtrhYi0lNkIYKuv71BUQgqMBbJoTBOzFRK3NFWOH05eN." ,"06412345",2,3);

INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number` , `avg_rate`,`working_hours_from`,`working_hours_to`, `address_id` ,`pharmacy_id`)
VALUES
("Pharmacist",3,1, "petar.ponjevic5@gmail.com", "jankovic" ,"dzoni","$2a$10$VcOWM4BngtrhYi0lNkIYKuv71BUQgqMBbJoTBOzFRK3NFWOH05eN.","06412345",4,"08:00","16:00",4 ,1);

INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number`, `avg_rate`,`working_hours_from`,`working_hours_to`, `address_id`,`pharmacy_id`)
VALUES
("Pharmacist",4,1, "petar.ponjevic4@gmail.com", "jankovic" ,"poni","$2a$10$VcOWM4BngtrhYi0lNkIYKuv71BUQgqMBbJoTBOzFRK3NFWOH05eN.","06412345",2,"12:00","20:00",5 ,2);


INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number`, `penal`,`address_id`)
VALUES
("Patient",5,1, "mokocev403@botfed.com", "Vladimir" ,"mokocev","$2a$10$VcOWM4BngtrhYi0lNkIYKuv71BUQgqMBbJoTBOzFRK3NFWOH05eN.","06412345 ",0,5 );


INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number`, `penal`,`address_id`)
VALUES
("Patient",6,1, "user6@gmail.com", "nikola" ,"popovic","$2a$10$VcOWM4BngtrhYi0lNkIYKuv71BUQgqMBbJoTBOzFRK3NFWOH05eN.","06412345 ",0,4 );


INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number`, `penal`,`address_id`)
VALUES
("Patient",7,1, "user7@gmail.com", "aleksandar" ,"jankovic","$2a$10$VcOWM4BngtrhYi0lNkIYKuv71BUQgqMBbJoTBOzFRK3NFWOH05eN.","06412345 ",0,1 );


INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number`, `penal`,`address_id`)
VALUES
("Patient",8,1, "user8@gmail.com", "aleksa" ,"vucic","$2a$10$VcOWM4BngtrhYi0lNkIYKuv71BUQgqMBbJoTBOzFRK3NFWOH05eN.","06412345",0,3 );



INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number`, `avg_rate`, `address_id`)
VALUES
("Dermatologist",9,1,"petar.dorevic@gmail.com", "milosevic" ,"nikola","$2a$10$VcOWM4BngtrhYi0lNkIYKuv71BUQgqMBbJoTBOzFRK3NFWOH05eN." ,"06412345",2,3);

INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number`, `avg_rate`, `address_id`)
VALUES
("Dermatologist",10,1,"petar.djordjevic@gmail.com", "djordjevic" ,"metar","$2a$10$VcOWM4BngtrhYi0lNkIYKuv71BUQgqMBbJoTBOzFRK3NFWOH05eN." ,"06412345",2,3);


INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number` , `avg_rate`,`working_hours_from`,`working_hours_to`, `address_id` ,`pharmacy_id`)
VALUES
("Pharmacist",11,1, "petar.ponjevic11@gmail.com", "jankovic" ,"Nemanja","$2a$10$VcOWM4BngtrhYi0lNkIYKuv71BUQgqMBbJoTBOzFRK3NFWOH05eN.","06412345",4,"08:00","16:00",4 ,3);

INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number`, `avg_rate`,`working_hours_from`,`working_hours_to`, `address_id`,`pharmacy_id`)
VALUES
("Pharmacist",12,1, "petar.ponjevic12@gmail.com", "felps" ,"Branislav","$2a$10$VcOWM4BngtrhYi0lNkIYKuv71BUQgqMBbJoTBOzFRK3NFWOH05eN.","06412345",2,"12:00","20:00",5 ,4);

INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number` , `avg_rate`,`working_hours_from`,`working_hours_to`, `address_id` ,`pharmacy_id`)
VALUES
("Pharmacist",13,1, "petar.ponjevic13@gmail.com", "cavic" ,"Stevan","$2a$10$VcOWM4BngtrhYi0lNkIYKuv71BUQgqMBbJoTBOzFRK3NFWOH05eN.","06412345",4,"08:00","16:00",4 ,5);

INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number`, `avg_rate`,`working_hours_from`,`working_hours_to`, `address_id`,`pharmacy_id`)
VALUES
("Pharmacist",14,1, "petar.ponjevic14@gmail.com", "Nevolja" ,"Damjan","$2a$10$VcOWM4BngtrhYi0lNkIYKuv71BUQgqMBbJoTBOzFRK3NFWOH05eN.","06412345",2,"12:00","20:00",5 ,6);

INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number` , `avg_rate`,`working_hours_from`,`working_hours_to`, `address_id` ,`pharmacy_id`)
VALUES
("Pharmacist",15,1, "petar.ponjevic15@gmail.com", "Milic" ,"Rade","$2a$10$VcOWM4BngtrhYi0lNkIYKuv71BUQgqMBbJoTBOzFRK3NFWOH05eN.","06412345",4,"08:00","16:00",4 ,7);

INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number`, `avg_rate`,`working_hours_from`,`working_hours_to`, `address_id`,`pharmacy_id`)
VALUES
("Pharmacist",16,1, "petar.ponjevic16@gmail.com", "Vukasinovic" ,"ljubisa","$2a$10$VcOWM4BngtrhYi0lNkIYKuv71BUQgqMBbJoTBOzFRK3NFWOH05eN.","06412345",2,"12:00","20:00",5 ,8);

INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number` , `avg_rate`,`working_hours_from`,`working_hours_to`, `address_id` ,`pharmacy_id`)
VALUES
("Pharmacist",17,1, "petar.ponjevic17@gmail.com", "Dobric" ,"dzoni","$2a$10$VcOWM4BngtrhYi0lNkIYKuv71BUQgqMBbJoTBOzFRK3NFWOH05eN.","06412345",4,"08:00","16:00",4 ,7);

INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number`, `avg_rate`,`working_hours_from`,`working_hours_to`, `address_id`,`pharmacy_id`)
VALUES
("Pharmacist",18,1, "petar.ponjevic18@gmail.com", "Pokrajac" ,"poni","$2a$10$VcOWM4BngtrhYi0lNkIYKuv71BUQgqMBbJoTBOzFRK3NFWOH05eN.","06412345",2,"12:00","20:00",5 ,3);



INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number`, `avg_rate`, `address_id`)
VALUES
("Dermatologist",19,1,"petar.djordjevic1@gmail.com", "djordjevic" ,"Danilo","$2a$10$VcOWM4BngtrhYi0lNkIYKuv71BUQgqMBbJoTBOzFRK3NFWOH05eN." ,"06412345",2,3);

INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number`, `avg_rate`, `address_id`)
VALUES
("Dermatologist",20,1,"petar.djordjevic2@gmail.com", "djordjevic" ,"metar","$2a$10$VcOWM4BngtrhYi0lNkIYKuv71BUQgqMBbJoTBOzFRK3NFWOH05eN." ,"06412345",2,3);

INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number`, `avg_rate`, `address_id`)
VALUES
("Dermatologist",21,1,"petar.djordjevic3@gmail.com", "djordjevic" ,"metar","$2a$10$VcOWM4BngtrhYi0lNkIYKuv71BUQgqMBbJoTBOzFRK3NFWOH05eN." ,"06412345",2,3);

INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number`, `avg_rate`, `address_id`)
VALUES
("Dermatologist",22,1,"petar.djordjevic4@gmail.com", "djordjevic" ,"metar","$2a$10$VcOWM4BngtrhYi0lNkIYKuv71BUQgqMBbJoTBOzFRK3NFWOH05eN." ,"06412345",2,3);

INSERT INTO `isa20`.`user`
( `dType`,`id`,`activated`,`email`,`lastname`,`name`,`password`,`phone_number`, `avg_rate`, `address_id`)
VALUES
("Dermatologist",23,1,"petar.djordjevic5@gmail.com", "djordjevic" ,"metar","$2a$10$VcOWM4BngtrhYi0lNkIYKuv71BUQgqMBbJoTBOzFRK3NFWOH05eN." ,"06412345",2,3);






INSERT INTO `isa20`.`user_authorities` (`user_id`,`authorities_id`) VALUES ( 5,1 );
INSERT INTO `isa20`.`user_authorities` (`user_id`,`authorities_id`) VALUES ( 1,1 );
INSERT INTO `isa20`.`user_authorities` (`user_id`,`authorities_id`) VALUES ( 6,1 );
INSERT INTO `isa20`.`user_authorities` (`user_id`,`authorities_id`) VALUES ( 7,1 );
INSERT INTO `isa20`.`user_authorities` (`user_id`,`authorities_id`) VALUES ( 8,1 );



INSERT INTO `isa20`.`pharmacy_dermatologists`(`pharmacy_id`,`dermatologists_id`) VALUES ( 1, 2);
INSERT INTO `isa20`.`pharmacy_dermatologists`(`pharmacy_id`,`dermatologists_id`) VALUES ( 2, 10);
INSERT INTO `isa20`.`pharmacy_dermatologists`(`pharmacy_id`,`dermatologists_id`) VALUES ( 3, 9);
INSERT INTO `isa20`.`pharmacy_dermatologists`(`pharmacy_id`,`dermatologists_id`) VALUES ( 4, 19);
INSERT INTO `isa20`.`pharmacy_dermatologists`(`pharmacy_id`,`dermatologists_id`) VALUES ( 5, 20);
INSERT INTO `isa20`.`pharmacy_dermatologists`(`pharmacy_id`,`dermatologists_id`) VALUES ( 6, 21);
INSERT INTO `isa20`.`pharmacy_dermatologists`(`pharmacy_id`,`dermatologists_id`) VALUES ( 7, 22);
INSERT INTO `isa20`.`pharmacy_dermatologists`(`pharmacy_id`,`dermatologists_id`) VALUES ( 8, 23);






 INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 1 , 120 , 10 , 1, 1 );
 
 INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 2 , 130 , 9 , 2, 2 );
  
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 3 , 420 , 8 , 1, 3 );
   
 INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 4 , 520 , 7 , 3, 4 );
    
 INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 5 , 550 , 6 , 4, 5 );
     
 INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 6 , 240 , 5 , 5, 6 );
 
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 7 , 240 , 5 , 6, 7 );
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 8 , 240 , 5 , 7, 8 );
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 9 , 240 , 5 , 8, 7 );
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 10 , 240 , 5 , 9, 6 );
 INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 11 , 240 , 5 , 10, 5 );
 INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 12 , 240 , 5 , 11, 4 );
 INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 13 , 240 , 5 , 12, 3 );
 INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 14 , 240 , 5 , 13, 2 );
 INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 15, 240 , 5 , 14, 1 );
 INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 16, 240 , 5 , 15, 2 );
  

  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 17 , 240 , 5 , 16, 3 );
 INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 18 , 240 , 5 , 17, 4 );
 INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 19 , 240 , 5 , 18, 5 );
 INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 20 , 240 , 5 , 19, 6 );
 INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 21 , 240 , 5 , 20, 7 );
 INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 22 , 240 , 5 , 19, 8 );
 INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES (23 , 240 , 5 , 18, 7 );
 INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 24 , 240 , 5 , 17, 6 );
 INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 25 , 240 , 5 , 16, 5 );
 INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 26 , 240 , 5 , 15, 4 );

  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 27 , 240 , 5 , 14, 3 );
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 28 , 240 , 5 , 13, 8 );
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 29 , 240 , 5 , 12, 1 );
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 30 , 240 , 5 , 11, 2 );
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 32 , 240 , 5 , 10, 3 );
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 31 , 240 , 5 , 9, 4 );
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 33 , 240 , 5 , 8, 5 );
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 34 , 240 , 5 , 7, 6 );
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 35 , 240 , 5 , 6, 6 );
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 36 , 240 , 5 , 5, 8 );

  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 37 , 240 , 5 , 4, 7 );
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 38 , 240 , 5 , 3, 6 );
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 39 , 240 , 5 , 2, 5 );
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 40 , 240 , 5 , 1, 4 );
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 41 , 240 , 5 , 20, 3 );
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 42 , 240 , 5 , 15, 3 );
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 44 , 240 , 5 , 18, 1 );
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 43 , 240 , 5 , 7, 2 );
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 46 , 240 , 5 , 13, 3 );
  INSERT INTO `isa20`.`item`(`id`,`price`,`quantity`, `drug_id` , `pharmacy_id`) VALUES ( 45 , 240 , 5 , 4, 4 );
           
     
     

 --vacations
 
 
 insert into `isa20`.`vacation`(`id`,`vacation_from`,`vacation_to`,`pharmacist_id`) values ( 1 , "2021-01-30" ,"2021-03-30" , 4 );

 insert into `isa20`.`vacation`(`id`,`vacation_from`,`vacation_to`,`pharmacist_id`) values ( 2 , "2021-02-20" ,"2021-03-10" , 3 );

 insert into `isa20`.`vacation`(`id`,`vacation_from`,`vacation_to`,`pharmacist_id`) values ( 3 , "2021-07-10" ,"2021-07-15" , 17 );

 insert into `isa20`.`vacation`(`id`,`vacation_from`,`vacation_to`,`pharmacist_id`) values ( 4 , "2021-09-30" ,"2021-10-30" , 15 );

 insert into `isa20`.`vacation`(`id`,`vacation_from`,`vacation_to`,`pharmacist_id`) values ( 5 , "2021-04-30" ,"2021-05-20" , 13 );

 insert into `isa20`.`vacation`(`id`,`vacation_from`,`vacation_to`,`pharmacist_id`) values ( 6 , "2021-05-30" ,"2021-06-17" , 11 );

 insert into `isa20`.`vacation`(`id`,`vacation_from`,`vacation_to`,`pharmacist_id`) values ( 7 , "2021-06-30" ,"2021-07-08" , 16 );

 insert into `isa20`.`vacation`(`id`,`vacation_from`,`vacation_to`,`pharmacist_id`) values ( 8 , "2021-07-30" ,"2021-08-07" , 14 );

 insert into `isa20`.`vacation`(`id`,`vacation_from`,`vacation_to`,`pharmacist_id`) values ( 9 , "2021-08-30" ,"2021-09-03" , 12 );

 


-- consultings

INSERT INTO `isa20`.`consulting`(`id`,`end_time`,`start_time`,`status`,`pharmacist_id` , `patient_id`) values (1,"2020-06-15 15:30" , "2020-06-15 15:00" ,1, 3 ,1);
INSERT INTO `isa20`.`consulting`(`id`,`end_time`,`start_time`,`status`,`pharmacist_id` , `patient_id`) values (2,"2020-06-15 15:30" , "2020-06-15 15:00" ,1, 4 ,5);
INSERT INTO `isa20`.`consulting`(`id`,`end_time`,`start_time`,`status`,`pharmacist_id` , `patient_id`) values (3,"2020-06-15 15:30" , "2020-06-15 15:00" ,1, 11 ,6);
INSERT INTO `isa20`.`consulting`(`id`,`end_time`,`start_time`,`status`,`pharmacist_id` , `patient_id`) values (4,"2020-06-15 15:30" , "2020-06-15 15:00" ,1, 13 ,7);
INSERT INTO `isa20`.`consulting`(`id`,`end_time`,`start_time`,`status`,`pharmacist_id` , `patient_id`) values (5,"2020-06-15 15:30" , "2020-06-15 15:00" ,1, 15 ,8);

INSERT INTO `isa20`.`consulting`(`id`,`end_time`,`start_time`,`status`,`pharmacist_id` , `patient_id`) values (6,"2021-06-15 15:30" , "2021-06-15 15:00" ,0, 17,1 );
INSERT INTO `isa20`.`consulting`(`id`,`end_time`,`start_time`,`status`,`pharmacist_id` , `patient_id`) values (7,"2021-07-15 15:30" , "2021-07-15 15:00" ,0, 18 ,5);
INSERT INTO `isa20`.`consulting`(`id`,`end_time`,`start_time`,`status`,`pharmacist_id` , `patient_id`) values (8,"2021-08-15 15:30" , "2021-08-15 15:00" ,0, 16 ,6);
INSERT INTO `isa20`.`consulting`(`id`,`end_time`,`start_time`,`status`,`pharmacist_id` , `patient_id`) values (9,"2021-09-15 15:30" , "2021-09-15 15:00" ,0, 14 ,7);
INSERT INTO `isa20`.`consulting`(`id`,`end_time`,`start_time`,`status`,`pharmacist_id` , `patient_id`) values (10,"2021-03-15 15:30" , "2021-03-15 15:00" ,0, 12 ,8);
INSERT INTO `isa20`.`consulting`(`id`,`end_time`,`start_time`,`status`,`pharmacist_id` , `patient_id`) values (11,"2021-04-15 15:30" , "2021-04-15 15:00" ,0, 15 ,6);



--INSERT INTO `isa20`.`consulting_reservation`(`id`,`consulting_id`,`patient_id`) values ( 1 , 1 , 2);

--examinations

--predefinisani
INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 1 , "2021-06-15 15:30" , 500 , "2021-06-15 15:00" , 0 ,2  ,null);
INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 2 , "2021-03-15 10:30" , 600 , "2021-06-15 09:45" , 0 ,9  ,null);
INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 3 , "2021-05-15 15:30" , 700 , "2021-06-15 15:00" , 0 ,19  ,null);
INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 4 , "2021-08-15 15:30" , 400 , "2021-06-15 15:00" , 0 ,10  ,null);
INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 5 , "2021-08-15 15:30" , 400 , "2021-06-15 15:00" , 0 ,20  ,null);
INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 6 , "2021-05-15 15:30" , 700 , "2021-06-15 15:00" , 0 ,21  ,null);
INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 7 , "2021-08-15 15:30" , 400 , "2021-06-15 15:00" , 0 ,22  ,null);
INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 8 , "2021-08-15 15:30" , 400 , "2021-06-15 15:00" , 0 ,23  ,null);


--rezervisani
INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 9 , "2021-06-13 15:30" , 500 , "2021-06-13 15:00" , 1 ,2  ,1);
INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 10 , "2021-03-13 10:30" , 600 , "2021-06-13 09:45" , 1 ,9  ,5);
INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 11 , "2021-05-13 15:30" , 700 , "2021-06-13 15:00" , 1 ,19  ,6);
INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 12 , "2021-08-13 15:30" , 400 , "2021-06-13 15:00" , 1 ,10  ,7);
INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 13 , "2021-08-13 15:30" , 400 , "2021-06-13 15:00" , 1 ,20  ,8);
INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 14 , "2021-05-18 15:30" , 700 , "2021-06-18 15:00" , 1 ,21  ,7);
INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 15 , "2021-08-14 15:30" , 400 , "2021-06-14 15:00" , 1 ,22  ,6);
INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 16 , "2021-08-23 15:30" , 400 , "2021-06-23 15:00" , 1 ,23  ,5);



--odradjeni

INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 17 , "2011-06-15 15:30" , 500 , "2011-06-15 15:00" , 2 ,2  ,1);
INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 18 , "2011-03-15 10:30" , 600 , "2011-06-15 09:45" , 2 ,9  ,5);
INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 19 , "2011-05-15 15:30" , 700 , "2011-06-15 15:00" , 2 ,19  ,6);
INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 20 , "2011-08-15 15:30" , 400 , "2011-06-15 15:00" , 2 ,10  ,7);
INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 21 , "2011-08-15 15:30" , 400 , "2011-06-15 15:00" , 2 ,20  ,8);
INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 22 , "2011-05-15 15:30" , 700 , "2011-06-15 15:00" , 2 ,21  ,7);
INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 23 , "2011-08-15 15:30" , 400 , "2011-06-15 15:00" , 2 ,22  ,6);
INSERT INTO `isa20`.`examination`(`id`,`end_time`,`price`,`start_time`,`status`,`dermatologist_id` , `patient_id`) values( 24 , "2011-08-15 15:30" , 400 , "2011-06-15 15:00" , 2 ,23  ,5);




--drug reservations 




