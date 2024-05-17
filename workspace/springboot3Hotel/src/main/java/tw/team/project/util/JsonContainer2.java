package tw.team.project.util;

import tw.team.project.dto.AdditionalChargesDTO;
import tw.team.project.dto.CheckOutInspectionDTO;
import tw.team.project.dto.HousingManagementDTO;
import tw.team.project.dto.RoomAssignmentDTO;
import tw.team.project.dto.RoomManagementDTO;
import tw.team.project.model.AdditionalCharges;
import tw.team.project.model.CheckOutInspection;
import tw.team.project.model.HousingManagement;
import tw.team.project.model.RoomAssignment;
import tw.team.project.model.RoomManagement;

public class JsonContainer2 {


	public AdditionalChargesDTO setAdditionalCharges(AdditionalCharges add){
		AdditionalChargesDTO addDTO = new AdditionalChargesDTO();
			if (add != null){
				addDTO.setAdditionalChargesId(add.getId());
				addDTO.setAmount(add.getAmount());
				addDTO.setQuantity(add.getQuantity());
//				addDTO.setHousingManagement(add.getHousingManagement());
//				addDTO.setMinibar(add.getMinibar());
				return addDTO;
			}
	
		return null;
	}
	
	public CheckOutInspectionDTO setCheckOutInspection(CheckOutInspection check){
		CheckOutInspectionDTO checkDTO = new CheckOutInspectionDTO();
			if (check != null){
				checkDTO.setId(check.getId());
				checkDTO.setCompensation(check.getCompensation());
				checkDTO.setFee(check.getFee());
				checkDTO.setHousingManagement(check.getHousingManagement());
				return checkDTO;
			}
	
		return null;
	}
	
	public HousingManagementDTO setHousingManagement(HousingManagement room) {
		HousingManagementDTO roomDTO = new HousingManagementDTO();
			if(room != null) {
				roomDTO.setId(room.getId());
				roomDTO.setRemarks(room.getRemarks());
				roomDTO.setCheckInTime(room.getCheckInTime());
				roomDTO.setCheckOutTime(room.getCheckOutTime());
				roomDTO.setTotalAdditional(room.getTotalAdditional());
				roomDTO.setTotalCompensation(room.getTotalCompensation());
				roomDTO.setOrderRoom(room.getOrderRoom());
				roomDTO.setRoomManagement(room.getRoomManagement());
				return roomDTO;
			}return null;
	}
	
	public RoomAssignmentDTO setRoomAssignment(RoomAssignment room) {
		RoomAssignmentDTO roomDTO = new RoomAssignmentDTO();
		if(room != null) {
			roomDTO.setId(room.getId());
			roomDTO.setLeft(room.getLeft());
			roomDTO.setDate(room.getDate());
			roomDTO.setRoomInformation(room.getRoomInformation());
		}
		return roomDTO;
	}
	
	public RoomManagementDTO setRoomManagement(RoomManagement room){
		RoomManagementDTO roomDTO = new RoomManagementDTO();
			if (room != null){
				roomDTO.setId(room.getId());
				roomDTO.setNumber(room.getNumber());
				roomDTO.setRepairStatus(room.getRepairStatus());
				roomDTO.setRoomInformation(room.getRoomInformation());
//				roomDTO.setRoomInfoDTO(room.getRoomInformation());
				roomDTO.setRoomstate(room.getRoomState());
				return roomDTO;
			}
	
		return null;
	}
	
//	public RoomManagementDTO setRoomManagement(RoomManagement room) {
//	    RoomManagementDTO roomDTO = new RoomManagementDTO();
//	    if (room != null) {
//	        roomDTO.setId(room.getId());
//	        roomDTO.setNumber(room.getNumber());
//	        roomDTO.setRepairStatus(room.getRepairStatus());
//	        
//	        // 创建一个新的 RoomInfoDTO 对象并设置其属性
//	        RoomInfoDTO roomInfoDTO = new RoomInfoDTO();
//	        roomInfoDTO.setId(room.getRoomInfoDTO().getId());
//	        roomInfoDTO.setTotal(room.getRoomInfoDTO().getTotal());
//	        
//	        // 将 RoomInfoDTO 对象设置到 RoomManagementDTO 中
//	        roomDTO.setRoomInfoDTO(roomInfoDTO);
//	        
//	        roomDTO.setRoomstate(room.getRoomState());
//	        return roomDTO;
//	    }
//	    return null;
//	}

}

