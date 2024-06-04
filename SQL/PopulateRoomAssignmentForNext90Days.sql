-- 刪除現有的存儲過程
DROP PROCEDURE IF EXISTS PopulateRoomAssignmentForNext90Days;

-- 創建新的存儲過程來處理接下來的 90 天的任務
CREATE PROCEDURE PopulateRoomAssignmentForNext90Days
AS
BEGIN
    DECLARE @currentDate DATE = CAST(GETDATE() AS DATE);
    DECLARE @endDate DATE = DATEADD(DAY, 90, @currentDate);
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

-- 執行新的存儲過程
EXEC PopulateRoomAssignmentForNext90Days;
