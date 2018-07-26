
SET @boatType='2x';
SET @reservationDate='2018-07-16';

CALL GET_RESERVABLE_BOAT_INSTANCE_COUNT( @boatType, @retrievaleBoatInstanceCount );
SELECT @retrievaleBoatInstanceCount;

CALL GET_AVAILABLE_HOURS( @reservationDate, @boatType );