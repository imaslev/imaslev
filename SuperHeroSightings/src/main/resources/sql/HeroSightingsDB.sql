DROP SCHEMA  IF EXISTS HeroSightingsDB;

CREATE SCHEMA HeroSightingsDB;

USE HeroSightingsDB;


CREATE TABLE Superpower(
id INT PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR (50) NOT NULL,
DESCRIPTION VARCHAR (275)

);


CREATE TABLE Hero(
id INT PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(50) NOT NULL,
-- superpowerId INT NOT NULL,
superpowerId INT,
DESCRIPTION VARCHAR(275),
FOREIGN KEY fk_HeroPowerId_SuperpowerId(superpowerId)
   REFERENCES Superpower(id)

);

CREATE TABLE Organization(
id INT PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR (50) NOT NULL,
IsHero BOOLEAN NOT NULL DEFAULT false,
DESCRIPTION VARCHAR (275),
Address VARCHAR (275),
Contact VARCHAR(275)

);


CREATE TABLE OrganizationMember(
HeroId INT NOT NULL,
OrganizationId	INT NOT NULL,
PRIMARY KEY pk_OrganizationMember (HeroId, OrganizationId),
FOREIGN KEY fk_OrganizationMember_HeroId (HeroId)
     REFERENCES Hero(id),
FOREIGN KEY fk_OrganizationMember_OrganizationId(OrganizationId)
		REFERENCES Organization(id)

);   
 

CREATE TABLE Location(
id INT PRIMARY KEY AUTO_INCREMENT,
-- NAME VARCHAR (50) NOT NULL,Superpower
NAME VARCHAR (50),
Latitude VARCHAR(100),
Longitude VARCHAR(100),
DESCRIPTION VARCHAR (275),
Address VARCHAR (275)

);

CREATE TABLE Sightings(
id INT PRIMARY KEY AUTO_INCREMENT,
HeroId INT NOT NULL,
LocationId INT NOT NULL,
DATE DATETIME NOT NULL,
FOREIGN KEY fk_Sightings_HeroId (HeroId)
    REFERENCES Hero(id),
FOREIGN KEY fk_Sightings_LocationId (LocationId)
	REFERENCES Location(id)
    
);

-- if hero tosuperpower needs to be N to N relationship 
-- CREATE TABLE HeroSuperpower(
-- HeroId INT NOT NULL,
-- SuperpowerId INT NOT NULL,
-- PRIMARY KEY pk_HeroSuperpower (HeroId, SuperpowerId),
-- FOREIGN KEY fk_HeroSuperpower_HeroId (HeroId)
--      REFERENCES Hero(HeroId),
-- FOREIGN KEY fk_HeroSuperpower_SuperpowerId(SuperpowerId)
--      REFERENCES Superpower(SuperpowerId)
-- ); 