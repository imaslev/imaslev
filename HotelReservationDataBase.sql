DROP DATABASE IF EXISTS HotelReservation;

CREATE DATABASE HotelReservation;

USE HotelReservation;

CREATE TABLE Room (
    RoomNumber INT PRIMARY KEY NOT NULL,
    RoomType VARCHAR(10) NOT NULL,
    IsADA TINYINT(1) NOT NULL,
    StandardOccupancy INT NOT NULL,
    MaximumOccupancy INT NOT NULL,
    BasePrice DECIMAL(5 , 2 ) NOT NULL,
    ExtraPerson DECIMAL(4 , 2 ) NOT NULL,
    HasJacuzzi TINYINT(1) NOT NULL
);

CREATE TABLE Amenity(
	AmenityId INT PRIMARY KEY AUTO_INCREMENT,
	AmenityType VARCHAR (30)
);

CREATE TABLE RoomAmenity(
		RoomNumber INT NOT NULL,
        AmenityId INT NOT NULL,
        PRIMARY KEY pk_RoomAmenity(RoomNumber, AmenityId),
			FOREIGN KEY fk_RoomAmenity_Room (RoomNumber)
				REFERENCES Room(RoomNumber),
            FOREIGN KEY fk_RoomAmenity_Amenity(AmenityId)
				REFERENCES Amenity(AmenityId)
);    

CREATE TABLE Reservation(
		ReservationId INT PRIMARY KEY AUTO_INCREMENT,
        Adults INT NOT NULL,
        Children INT NOT NULL,
        CheckInDate DATE,
        CheckOutDate DATE,
        Total DECIMAL(8, 2) NOT NULL
);

CREATE TABLE Guest(
		GuestId INT PRIMARY KEY AUTO_INCREMENT,
        FirstName VARCHAR (50) NOT NULL,
        LastName VARCHAR (50) NOT NULL,
        Street VARCHAR (100),
        City VARCHAR (50),
        State CHAR (2),
        Zip CHAR(5),
        Phone CHAR(14)
);

CREATE TABLE GuestReservation (
	GuestId INT,
    ReservationId INT,
    PRIMARY KEY pk_GuestReservation (GuestId, ReservationId),
    FOREIGN KEY fk_GuestReservation_Guest (GuestId)
		REFERENCES Guest (GuestId),
	FOREIGN KEY fk_GuestReservation_Reservation (ReservationId)
		REFERENCES Reservation (ReservationId)
);        
        

CREATE TABLE RoomReservation(
	RoomNumber INT,
    ReservationId INT,
    PRIMARY KEY pk_RoomReservation (RoomNumber, ReservationId),
    FOREIGN KEY fk_RoomReservation_Room (RoomNumber)
		REFERENCES Room (RoomNumber),
	FOREIGN KEY fk_RoomReservation_Reservation (ReservationId)
		REFERENCES Reservation (ReservationId)
);