-- DROP SCHEMA chegg;

CREATE SCHEMA chegg AUTHORIZATION postgres;

-- Drop table

-- DROP TABLE chegg.aquarium;

CREATE TABLE chegg.aquarium (
	id int4 NOT NULL,
	glass_type varchar(255) NULL,
	shape varchar(255) NULL,
	volume float8 NOT NULL,
	volume_unit varchar(255) NULL,
	CONSTRAINT aquarium_pkey PRIMARY KEY (id)
);

-- Drop table

-- DROP TABLE chegg.aquarium_fishes;

CREATE TABLE chegg.aquarium_fishes (
	id int4 NOT NULL,
	fish_count int4 NOT NULL,
	aquarium_id int4 NOT NULL,
	fish_id int4 NOT NULL,
	CONSTRAINT aquarium_fishes_pkey PRIMARY KEY (id),
	CONSTRAINT fk283ull2qsfge33cbxil4pwgvj FOREIGN KEY (fish_id) REFERENCES chegg.fish(id),
	CONSTRAINT fkfi68u0beebuo498fpf1wltl14 FOREIGN KEY (aquarium_id) REFERENCES chegg.aquarium(id)
);

-- Drop table

-- DROP TABLE chegg.fish;

CREATE TABLE chegg.fish (
	id int4 NOT NULL,
	color varchar(255) NULL,
	number_fins int4 NOT NULL,
	species varchar(255) NULL,
	CONSTRAINT fish_pkey PRIMARY KEY (id)
);

-- Drop table

-- DROP TABLE chegg.fish_fight;

CREATE TABLE chegg.fish_fight (
	fish_id_1 int4 NOT NULL,
	fish_id_2 int4 NOT NULL,
	CONSTRAINT fish_fight_pkey PRIMARY KEY (fish_id_1, fish_id_2),
	CONSTRAINT fkcsrdr5ia024lf4s7q6wk61xd5 FOREIGN KEY (fish_id_2) REFERENCES chegg.fish(id),
	CONSTRAINT fki170a9bn7ba29vft7s1jxres FOREIGN KEY (fish_id_1) REFERENCES chegg.fish(id)
);

-- Drop table

-- DROP TABLE chegg.fish_inventory;

CREATE TABLE chegg.fish_inventory (
	id int4 NOT NULL,
	available int8 NOT NULL,
	fish_id int4 NULL,
	CONSTRAINT fish_inventory_pkey PRIMARY KEY (id),
	CONSTRAINT fk22wpt3mt0ml6ppxtwr03d9jp4 FOREIGN KEY (fish_id) REFERENCES chegg.fish(id)
);
