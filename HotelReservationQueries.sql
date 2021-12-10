USE HotelReservation;

-- QUERY 1
-- Write a query that returns a list of reservations that end in July 2023, 
-- including the name of the guest, the room number(s), and the reservation dates.
--------------------
SELECT 
	Guest.FirstName,
    Guest.LastName,
    Room.RoomNumber,
    Reservation.CheckInDate,
    Reservation.CheckOutDate
FROM Guest
INNER JOIN GuestReservation ON Guest.GuestId = GuestReservation.GuestId
INNER JOIN Reservation ON  GuestReservation.ReservationId = Reservation.ReservationId
INNER JOIN RoomReservation ON Reservation.ReservationId = RoomReservation.ReservationId
INNER JOIN Room ON RoomReservation.RoomNumber = Room.RoomNumber
WHERE CheckOutDate BETWEEN '2023-07-01' AND '2023-07-31';

-- QUERY 2
-- Write a query that returns a list of all reservations for rooms with a jacuzzi, 
-- displaying the guest's name, the room number, and the dates of the reservation.
--------------------
SELECT
	Guest.FirstName,
    Guest.LastName,
    Room.RoomNumber,
    Reservation.CheckInDate,
    Reservation.CheckOutDate
FROM Room
INNER JOIN RoomReservation ON Room.RoomNumber = RoomReservation.RoomNumber
INNER JOIN Reservation ON RoomReservation.ReservationId = Reservation.ReservationId
INNER JOIN GuestReservation ON Reservation.ReservationId = GuestReservation.ReservationId
INNER JOIN Guest ON GuestReservation.GuestId = Guest.GuestId
WHERE HasJacuzzi = 1;

-- QUERY 3
-- Write a query that returns all the rooms reserved for a specific guest, 
-- including the guest's name, the room(s) reserved, the starting date of the reservation, 
-- and how many people were included in the reservation. (Choose a guest's name from the existing data.)
--------------------
SELECT
	Guest.FirstName,
    Guest.LastName,
    Room.RoomNumber,
    Reservation.CheckInDate,
    Reservation.CheckOutDate,
    Reservation.Adults + Reservation.Children AS TotalPeople
FROM Guest
INNER JOIN GuestReservation ON Guest.GuestId = GuestReservation.GuestId
INNER JOIN Reservation ON  GuestReservation.ReservationId = Reservation.ReservationId
INNER JOIN RoomReservation ON Reservation.ReservationId = RoomReservation.ReservationId
INNER JOIN Room ON RoomReservation.RoomNumber = Room.RoomNumber
WHERE Guest.GuestId = 2;

-- QUERY 4
-- Write a query that returns a list of rooms, reservation ID, and per-room cost for each reservation. 
-- The results should include all rooms, whether or not there is a reservation associated with the room.
--------------------
SELECT
	Room.RoomNumber,
    Reservation.ReservationId,
    CASE
		WHEN (((Room.RoomNumber BETWEEN '201' AND '204') OR (Room.RoomNumber BETWEEN '301' AND '304')) AND Reservation.Adults <= 2)
			THEN ((Room.BasePrice) * DATEDIFF(Reservation.CheckOutDate, Reservation.CheckInDate))
		 WHEN (((Room.RoomNumber BETWEEN '201' AND '204') OR (Room.RoomNumber BETWEEN '301' AND '304')) AND Reservation.Adults > 2)
			 THEN ((Room.BasePrice + ((Reservation.Adults - Room.StandardOccupancy) * 10) * DATEDIFF(Reservation.CheckOutDate, Reservation.CheckInDate)))
		WHEN ((Room.RoomNumber BETWEEN '205' AND '208') OR (Room.RoomNumber BETWEEN '305' AND '308'))
			 THEN ((Room.BasePrice) * DATEDIFF(Reservation.CheckOutDate, Reservation.CheckInDate))
		WHEN (((Room.RoomNumber BETWEEN '401' AND '402')) AND Reservation.Adults <= 3)
			THEN ((Room.BasePrice) * DATEDIFF(Reservation.CheckOutDate, Reservation.CheckInDate))
		WHEN (((Room.RoomNumber BETWEEN '401' AND '402')) AND Reservation.Adults > 3)
			THEN ((Room.BasePrice + ((Reservation.Adults - Room.StandardOccupancy) * 20) * DATEDIFF(Reservation.CheckOutDate, Reservation.CheckInDate)))
	END AS Total
FROM Guest
RIGHT OUTER JOIN GuestReservation ON Guest.GuestId = GuestReservation.GuestId
RIGHT OUTER JOIN Reservation ON  GuestReservation.ReservationId = Reservation.ReservationId
RIGHT OUTER JOIN RoomReservation ON Reservation.ReservationId = RoomReservation.ReservationId
RIGHT OUTER JOIN Room ON RoomReservation.RoomNumber = Room.RoomNumber;

-- QUERY 5
-- Write a query that returns all the rooms accommodating at least 
-- three guests and that are reserved on any date in April 2023.
SELECT 
	Room.RoomNumber
FROM RESERVATION
INNER JOIN RoomReservation ON Reservation.ReservationId = RoomReservation.ReservationId
INNER JOIN Room ON RoomReservation.RoomNumber = Room.RoomNumber
WHERE 	(Reservation.Adults + Reservation.Children) > 2
		AND ((Reservation.CheckInDate BETWEEN '2023-04-01' AND '2023-04-30')
		OR (Reservation.CheckOutDate BETWEEN '2023-04-01' AND '2023-04-30'));
    

-- QUERY 6
-- Write a query that returns a list of all guest names and the number of reservations per guest, 
-- sorted starting with the guest with the most reservations and then by the guest's last name.
--------------------
SELECT 
	g.Firstname,
    g.Lastname,
    COUNT(gr.GuestId) AS TotalReservations
FROM Reservation r
INNER JOIN GuestReservation gr ON r.ReservationId = gr.ReservationId
INNER JOIN Guest g ON gr.GuestId = g.GuestId
GROUP BY g.FirstName
ORDER BY TotalReservations DESC, g.LastName;  


-- QUERY 7
-- Write a query that displays the name, address, and phone number of a guest 
-- based on their phone number. (Choose a phone number from the existing data.)
--------------------
SELECT 
	g.FirstName,
    g.LastName,
    g.Street,
    g.City,
    g.State,
    g.Zip,
    g.Phone
FROM GUEST g
WHERE g.Phone LIKE '%(291) 553-0508%';
