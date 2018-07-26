CALL GET_RESERVABLE_BOAT_INSTANCE_COUNT( '1x' );

-- Query #01
-- retrieve all reservations by the given data and time.
SELECT *
FROM ORCA_RESERVATION AS ore
WHERE ore.RESERVATION_DATE='2018-07-15' AND ore.RESERVATION_TIME='09:00';

-- Query #02
-- retrieve reservation id, date, time accompanied with the reserved boat id and member info for all reservations by the given data and time.
SELECT ore.RESERVATION_ID, ore.RESERVATION_DATE, ore.RESERVATION_TIME, ore.BOAT_RESERVED, ore.MEMBER_BOOKED, ob.BOAT_TYPE, oa.FULL_NAME, oa.EMAIL
FROM ORCA_RESERVATION AS ore, ORCA_BOAT AS ob, ORCA_ACCOUNT AS oa
WHERE ore.BOAT_RESERVED=ob.BOAT_ID AND ore.MEMBER_BOOKED=oa.ACCOUNT_ID AND ore.RESERVATION_DATE='2018-07-15' AND ore.RESERVATION_TIME='09:00';

-- Query #03
-- retrieve all reservations group for all boat types and the reservation count for these boat types by the given data and time.
SELECT ob.BOAT_TYPE AS `Type`, COUNT( ob.BOAT_TYPE ) AS `Count`
FROM ORCA_RESERVATION AS ore, ORCA_BOAT AS ob, ORCA_ACCOUNT AS oa
WHERE ore.BOAT_RESERVED=ob.BOAT_ID AND ore.MEMBER_BOOKED=oa.ACCOUNT_ID AND ore.RESERVATION_DATE='2018-07-15' AND ore.RESERVATION_TIME='09:00'
GROUP BY ob.BOAT_TYPE;

-- Query #04
-- retrieve all reservations group for the given boat type and the reservation count for this boat type by the given data and time.
SET @targetBoatType=CONVERT('1x' USING latin5) COLLATE latin5_turkish_ci;

SELECT ob.BOAT_TYPE AS `Type`, COUNT( ob.BOAT_TYPE ) AS `Count`
FROM ORCA_RESERVATION AS ore, ORCA_BOAT AS ob, ORCA_ACCOUNT AS oa
WHERE ore.BOAT_RESERVED=ob.BOAT_ID AND ore.MEMBER_BOOKED=oa.ACCOUNT_ID AND ore.RESERVATION_DATE='2018-07-15' AND ore.RESERVATION_TIME='09:00'
GROUP BY ob.BOAT_TYPE
HAVING ob.BOAT_TYPE=@targetBoatType;

-- Query #05
-- retrieve all available hours.
SET @targetBoatType=CONVERT( '2x' USING latin5 ) COLLATE latin5_turkish_ci;
SET @targetReservationDate=CONVERT( '2018-07-15' USING latin5 ) COLLATE latin5_turkish_ci;
CALL GET_RESERVABLE_BOAT_INSTANCE_COUNT( @targetBoatType, @retrievaleBoatInstanceCount );
SELECT @retrievaleBoatInstanceCount;

SELECT *
FROM ORCA_OPERATING_HOUR AS ooh
WHERE @retrievaleBoatInstanceCount > 
(
	SELECT COUNT( ob.BOAT_TYPE )
	FROM ORCA_RESERVATION AS ore, ORCA_BOAT AS ob, ORCA_ACCOUNT AS oa
	WHERE ore.BOAT_RESERVED=ob.BOAT_ID AND ore.MEMBER_BOOKED=oa.ACCOUNT_ID AND ore.RESERVATION_DATE=@targetReservationDate AND ore.RESERVATION_TIME=ooh.WORKING_HOUR
	GROUP BY ob.BOAT_TYPE
	HAVING ob.BOAT_TYPE=@targetBoatType
	
) OR NOT EXISTS
(
	SELECT COUNT( ob.BOAT_TYPE )
	FROM ORCA_RESERVATION AS ore, ORCA_BOAT AS ob, ORCA_ACCOUNT AS oa
	WHERE ore.BOAT_RESERVED=ob.BOAT_ID AND ore.MEMBER_BOOKED=oa.ACCOUNT_ID AND ore.RESERVATION_DATE=@targetReservationDate AND ore.RESERVATION_TIME=ooh.WORKING_HOUR
	GROUP BY ob.BOAT_TYPE
	HAVING ob.BOAT_TYPE=@targetBoatType
);

-- control query
SELECT COUNT( ob.BOAT_TYPE )
FROM ORCA_RESERVATION AS ore, ORCA_BOAT AS ob, ORCA_ACCOUNT AS oa
WHERE ore.BOAT_RESERVED=ob.BOAT_ID AND ore.MEMBER_BOOKED=oa.ACCOUNT_ID AND ore.RESERVATION_DATE=@targetReservationDate AND ore.RESERVATION_TIME='09:00'
GROUP BY ob.BOAT_TYPE
HAVING ob.BOAT_TYPE=@targetBoatType;