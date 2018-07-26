
/* [ REGISTRAR ]
 * 
 * project         : ORCA
 * engineered by   : Berk Ekim Pasmakoglu, berkekim@gmail.com
 * alias           : PROCEDURE_002
 * name            : GET_AVAILABLE_HOURS
 * type            : Retrieval
 * direct use      : Allowed
 *
 *
 * [ IMMEDIATE WARNING ]
 *
 * While creating a stored procedure, the 'COMMENT' section's length 
 * can be 65 characters along at most. Otherwise, it can not create 
 * the stored procedure.
*/

-- @BEGIN `GET_AVAILABLE_HOURS`
/* PART-1: RE-CREATION OF THE PROCEDURE TO KEEP IT UP-TO-DATE */
SELECT 'Dropping procedure GET_AVAILABLE_HOURS' AS `dropping`;
DROP PROCEDURE IF EXISTS `GET_AVAILABLE_HOURS`;

-- INTERMEDIATE-PART: sets the delimiter as '||'
SET @theDelimiter='||';
DELIMITER @theDelimiter

/* PART-2: CREATION OF THE PROCEDURE 
 *
 * Invocation Examples:
 * --------------------
 * CALL GET_AVAILABLE_HOURS( '2018-07-15', '1X' );
 *
*/
SELECT 'Creating procedure `GET_AVAILABLE_HOURS`' AS `creation` @theDelimiter
CREATE PROCEDURE `GET_AVAILABLE_HOURS`( IN reservationDate DATE, IN boatType VARCHAR(255) )
COMMENT 'gets the available hours.' 
    BEGIN	
    
		-- declare the variables we will use.
		DECLARE reservableBoatInstanceCount INT(11);
    
		-- retrieves available, usable and reservable boat instance number
		-- for the given boat type and output parameter, 'reservableBoatInstanceCount'.
		CALL GET_RESERVABLE_BOAT_INSTANCE_COUNT( boatType, @reservableBoatInstanceCount );
    
        -- retrieves the available hours for which one can make a reservation.
        -- traverses every operating hour and seeks a number of reservation made for
        -- the given date, time and boat type. If the number of current reservations
        -- is less than the reservable boat instance count, the reservation can happen.
        -- If no reservation returns in the result set, this means no reservation 
        -- is ever done.
		SELECT *
		FROM ORCA_OPERATING_HOUR AS ooh
		WHERE @reservableBoatInstanceCount > 
		(
			SELECT COUNT( ob.BOAT_TYPE )
			FROM ORCA_RESERVATION AS ore, ORCA_BOAT AS ob, ORCA_ACCOUNT AS oa
			WHERE ore.BOAT_RESERVED=ob.BOAT_ID AND ore.MEMBER_BOOKED=oa.ACCOUNT_ID AND ore.RESERVATION_DATE=reservationDate AND ore.RESERVATION_TIME=ooh.WORKING_HOUR
			GROUP BY ob.BOAT_TYPE
			HAVING ob.BOAT_TYPE=boatType
	
		) OR NOT EXISTS
		(
			SELECT COUNT( ob.BOAT_TYPE )
			FROM ORCA_RESERVATION AS ore, ORCA_BOAT AS ob, ORCA_ACCOUNT AS oa
			WHERE ore.BOAT_RESERVED=ob.BOAT_ID AND ore.MEMBER_BOOKED=oa.ACCOUNT_ID AND ore.RESERVATION_DATE=reservationDate AND ore.RESERVATION_TIME=ooh.WORKING_HOUR
			GROUP BY ob.BOAT_TYPE
			HAVING ob.BOAT_TYPE=boatType
		);       	
        	
    END @theDelimiter
-- end of CREATE PROCEDURE `GET_AVAILABLE_HOURS`( IN reservationDate DATE, IN boatType VARCHAR(255) )
