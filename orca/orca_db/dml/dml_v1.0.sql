
-- @BEGIN `ORCA_ROWING_LEVEL`
INSERT INTO `ORCA`.ORCA_ROWING_LEVEL VALUES( 'BEGINNER' ), ( 'HOBBYIST' ), ( 'OLYMPIC' );
-- @END `ORCA_ROWING_LEVEL`

-- @BEGIN `ORCA_BOAT_TYPE`
INSERT INTO `ORCA_BOAT_TYPE` VALUES ( '1x' ), ( '2x' ), ( '4x' );
-- @END `ORCA_BOAT_TYPE`

-- @BEGIN `ORCA_BOAT`
INSERT INTO `ORCA_BOAT` VALUES ( UUID(), 'boat_001', '1x', 1 ), 
                               ( UUID(), 'boat_002', '1x', 1 ), 
                               ( UUID(), 'boat_003', '1x', 1 ),
                               ( UUID(), 'boat_004', '1x', 1 ),
                               ( UUID(), 'boat_005', '2x', 1 ),
                               ( UUID(), 'boat_006', '4x', 1 );
-- @END `ORCA_BOAT`

-- @BEGIN `ORCA_ACCOUNT`
INSERT INTO `ORCA_ACCOUNT` VALUES( UUID(), ' Test Full Name #1', 'fname_1@test.com', 1234566, '123 44 55', '1990-01-01', 1, 1, 'BEGINNER' ), 
                                 ( UUID(), ' Test Full Name #2', 'fname_2@test.com', 1234566, '123 44 55', '1990-01-01', 1, 1, 'BEGINNER' ),
                                 ( UUID(), ' Test Full Name #3', 'fname_3@test.com', 1234566, '123 44 55', '1990-01-01', 1, 1, 'BEGINNER' );
-- @END `ORCA_ACCOUNT`

-- @BEGIN `ORCA_RESERVATION`
INSERT INTO `ORCA_RESERVATION` VALUES( UUID(), '2018-07-15', '09:00', '180a87a6-8f59-11e8-800d-b3fcd5399b5f', '180c8b1e-8f59-11e8-800d-b3fcd5399b5f' ), 
                                     ( UUID(), '2018-07-15', '09:00','180a9106-8f59-11e8-800d-b3fcd5399b5f' , '180c9762-8f59-11e8-800d-b3fcd5399b5f' ),
									 ( UUID(), '2018-07-16', '10:00', '180a95ac-8f59-11e8-800d-b3fcd5399b5f', '180c9a46-8f59-11e8-800d-b3fcd5399b5f'),                                                                          
                                     ( UUID(), '2018-07-15', '09:00', '180a92e6-8f59-11e8-800d-b3fcd5399b5f', '180c9a46-8f59-11e8-800d-b3fcd5399b5f');
-- @END `ORCA_RESERVATION`

-- @BEGIN `ORCA_OPERATING_HOUR`
INSERT INTO `ORCA_OPERATING_HOUR` VALUES( '07:00' ), ( '08:00' ), ( '09:00' ), ( '10:00' ), ( '11:00' ), ( '13:00' ),
                                        ( '14:00' ), ( '15:00' ), ( '16:00' ), ( '17:00' ), ( '18:00' ), ( '19:00' );
-- @END `ORCA_OPERATING_HOUR`
