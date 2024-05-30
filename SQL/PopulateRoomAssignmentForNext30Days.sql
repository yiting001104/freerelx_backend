CREATE PROCEDURE PopulateRoomAssignmentForNext30Days
AS
BEGIN
    DECLARE @currentDate DATE = CAST(GETDATE() AS DATE);
    DECLARE @endDate DATE = DATEADD(DAY, 30, @currentDate);
    DECLARE @roomInformationId INT;

    WHILE @currentDate <= @endDate
    BEGIN
        SET @roomInformationId = 1;

        WHILE @roomInformationId <= 9
        BEGIN
            INSERT INTO roomAssignment (rooms_left, assignment_date, room_information_id)
            VALUES (5, @currentDate, @roomInformationId);

            SET @roomInformationId = @roomInformationId + 1;
        END

        SET @currentDate = DATEADD(DAY, 1, @currentDate);
    END
END;


EXEC PopulateRoomAssignmentForNext30Days;
