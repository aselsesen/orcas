
/* [ REGISTRAR ]
 * 
 * project         : ORCA
 * engineered by   : Berk Ekim Pasmakoglu, berkekim@gmail.com
 * alias           : PROCEDURE_001
 * name            : GET_RESERVABLE_BOAT_INSTANCE_COUNT
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

-- @BEGIN `GET_RESERVABLE_BOAT_INSTANCE_COUNT`
/* PART-1: RE-CREATION OF THE PROCEDURE TO KEEP IT UP-TO-DATE */
SELECT 'Dropping procedure GET_RESERVABLE_BOAT_INSTANCE_COUNT' AS `dropping`;
DROP PROCEDURE IF EXISTS `GET_RESERVABLE_BOAT_INSTANCE_COUNT`;

-- INTERMEDIATE-PART: sets the delimiter as '||'
SET @theDelimiter='||';
DELIMITER @theDelimiter

/* PART-2: CREATION OF THE PROCEDURE 
 *
 * Invocation Examples:
 * --------------------
 * CALL GET_RESERVABLE_BOAT_INSTANCE_COUNT( '1X', @reservableBoatInstanceCount );
 *
*/
SELECT 'Creating procedure `GET_RESERVABLE_BOAT_INSTANCE_COUNT`' AS `creation` @theDelimiter
CREATE PROCEDURE `GET_RESERVABLE_BOAT_INSTANCE_COUNT`( IN boatType VARCHAR(255), OUT reservableBoatInstanceCount INT(11) )
COMMENT 'gets the reservable boat counts.' 
    BEGIN	
    
		DECLARE groupedBoatType VARCHAR(255);
    
        -- retrieves reservable boats grouped by boat type.
        SELECT ob.BOAT_TYPE AS `Type`, COUNT(*) AS `Count` INTO groupedBoatType, reservableBoatInstanceCount
        FROM ORCA_BOAT AS ob
        WHERE ob.RESERVABLE=1 AND ob.BOAT_TYPE=boatType
        GROUP BY ob.BOAT_TYPE;	            
        	
    END @theDelimiter
-- end of CREATE PROCEDURE `GET_RESERVABLE_BOAT_INSTANCE_COUNT`( IN boatType VARCHAR(255), OUT reservableBoatInstanceCount INT(11) )
