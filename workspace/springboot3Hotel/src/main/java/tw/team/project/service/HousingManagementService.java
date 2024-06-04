package tw.team.project.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.team.project.model.HousingManagement;
import tw.team.project.model.OrderRoom;
import tw.team.project.model.RoomManagement;
import tw.team.project.model.RoomState;
import tw.team.project.repository.HousingManagementRepository;

@Service
@Transactional
public class HousingManagementService {

	@Autowired
	private HousingManagementRepository housingManagementRepo;

//	@Autowired
//	private RoomAssignmentService roomAssignmentService;

	@Autowired
	private RoomManagementService roomManagementService;

	@Autowired
	private OrderRoomService orderRoomService;
	
	@Autowired
	private RoomStateService roomStateService;
	
	
    public Page<HousingManagement> findAll(Integer id){
        Pageable pgb = PageRequest.of(id-1, 10, Sort.Direction.DESC,"id");
        Page<HousingManagement> page = housingManagementRepo.findAll(pgb);
        return page;
    }

	public boolean existsById(Integer id) {
		if (id != null) {
			long result = housingManagementRepo.countById(id);
			if (result != 0) {
				return true;
			}
		}
		return false;
	}

	public long count(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return housingManagementRepo.count(obj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean existById(Integer id) {
		if (id != null) {
			return housingManagementRepo.existsById(id);
		}
		return false;
	}

	public List<HousingManagement> find(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return housingManagementRepo.find(obj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public HousingManagement findById(Integer id) {
		if (id != null) {
			Optional<HousingManagement> optional = housingManagementRepo.findById(id);
			if (optional.isPresent()) {
				return optional.get();
			}
		}
		return null;
	}

	public List<HousingManagement> select(HousingManagement bean) {
		List<HousingManagement> result = null;
		if (bean != null && bean.getId() != null && !bean.getId().equals(0)) {
			Optional<HousingManagement> optional = housingManagementRepo.findById(bean.getId());
			if (optional.isPresent()) {
				result = new ArrayList<HousingManagement>();
				result.add(optional.get());
			}
		} else {
			result = housingManagementRepo.findAll();
		}
		return result;
	}

//	public HousingManagement create(String json, Integer id) {
//		try {
//			JSONObject obj = new JSONObject(json);
//			RoomManagement roomManagement = null;
//			OrderRoom orderRoom = null;
//
//			roomManagement = roomManagemnantService.findById(id);
//			orderRoom = orderRoomService.findById(id);
//
//			String remarks = obj.isNull("remarks") ? null : obj.getString("remarks");
//			Date checkInTime = new Date();
//			String check_Out_Time = obj.isNull("check_Out_Time") ? null : obj.getString("check_Out_Time");
//			Date checkOutTime;
//			if (check_Out_Time != null && check_Out_Time.length() != 0) {
//				checkOutTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(check_Out_Time);
//			} else {
//				checkOutTime = null;
//			}
//			String totalAdditional = obj.isNull("totalAdditional") ? null : obj.getString("totalAdditional");
//			BigDecimal totalAdditionalfee = new BigDecimal(totalAdditional);
//			String totalCompensation = obj.isNull("totalCompensation") ? null : obj.getString("totalCompensation");
//			BigDecimal totalCompensationfee = new BigDecimal(totalCompensation);
//
//			if (checkInTime != null) {
//				Optional<HousingManagement> optional = housingManagementRepo.findById(id);
//				if (optional.isEmpty()) {
//					HousingManagement insert = new HousingManagement();
//					insert.setRemarks(remarks);
//					insert.setCheckInTime(checkInTime);
//					insert.setCheckOutTime(checkOutTime);
//					insert.setTotalAdditional(totalAdditionalfee);
//					insert.setTotalCompensation(totalCompensationfee);
//					insert.setOrderRoom(orderRoom);
//					insert.setRoomManagement(roomManagement);
//
//					// 檢查是否為當日訂房 全部訂房皆不為當日訂房 所以只會做對 RoomManagement 的 roomState 狀態改變
//					OrderRoom orderdate = orderRoomService.findById(id);
//					if (!orderdate.getOrderDate().equals(checkInTime)) {
//						// 當日有其他訂房，進行庫存減少修改
//						RoomAssignment orderdate2 = roomAssignmentService.findById(id);
//						if (orderdate2 != null && orderdate2.getLeft() != 0) {
//							orderdate2.setLeft(orderdate2.getLeft() - 1);
//						} else {
//							// 當日沒有其他訂房，新增 RoomAssignment
//							RoomAssignment rooomAssignment = new RoomAssignment();
//							rooomAssignment.setDate(checkInTime);
//							rooomAssignment.setLeft(orderdate2.getLeft() - 1);
//							roomAssignmentService.insert(rooomAssignment);
//						}
//					} else {
//						// 當日已有其他訂房
//						return null;
//					}
//
//					// 更新 RoomManagement 的 roomState
//
//		            if (checkOutTime != null) {
//		                RoomManagement management = roomManagemnantService.findById(id);
//		                if (management != null) {
//		                	RoomState state  = roomStateService.findById(id);
//		                	management.setRoomState(state); // 假設1表示特定的房間狀態
//		                    roomManagemnantService.modify(json); // 更新 RoomManagement
//		                }
//		            }
//
//					HousingManagement newHousing = housingManagementRepo.save(insert);
//					return newHousing;
//				}
//			}
//		} catch (JSONException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	
	//new Create
	public HousingManagement create(String json, Integer orderid, Integer roomid) {
	    try {
	        JSONObject obj = new JSONObject(json);
	        RoomManagement roomManagement = null;
			OrderRoom orderRoom = null;
	        
	        orderRoom = orderRoomService.findById(orderid);
	        roomManagement = roomManagementService.findById(roomid);

	        String remarks = obj.isNull("remarks") ? null : obj.getString("remarks");
	        Date checkInTime = new Date();
	        String check_Out_Time = obj.isNull("check_Out_Time") ? null : obj.getString("check_Out_Time");
	        Date checkOutTime;
	        if (check_Out_Time != null && check_Out_Time.length() != 0) {
	            checkOutTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(check_Out_Time);
	        } else {
	            checkOutTime = null;
	        }
	        String totalAdditional = obj.isNull("totalAdditional") ? null : obj.getString("totalAdditional");
	        BigDecimal totalAdditionalfee = totalAdditional == null ? null : new BigDecimal(totalAdditional);
	        String totalCompensation = obj.isNull("totalCompensation") ? null : obj.getString("totalCompensation");
	        BigDecimal totalCompensationfee = totalCompensation == null ? null : new BigDecimal(totalCompensation);

	        if (checkInTime != null) {
	                HousingManagement insert = new HousingManagement();
	                insert.setRemarks(remarks);
	                insert.setCheckInTime(checkInTime);
	                insert.setCheckOutTime(checkOutTime);
	                insert.setTotalAdditional(totalAdditionalfee);
	                insert.setTotalCompensation(totalCompensationfee);
	                insert.setOrderRoom(orderRoom);
	                insert.setRoomManagement(roomManagement);

	                // 保存 HousingManagement
	                HousingManagement newHousing = housingManagementRepo.save(insert);

	                // 更新 RoomManagement 的 roomState
	                if (newHousing != null) {
	                    RoomState newState = roomStateService.findById(2); // 查找 id 為 2 的 RoomState
	                    if (newState != null) {
	                        roomManagement.setRoomState(newState);
	                        roomManagementService.modify1(roomManagement); // 更新 RoomManagement
	                    }
	                }return newHousing;       
	        }
	    } catch (JSONException | ParseException e) {
	        e.printStackTrace();
	    }
	    return null;
	}





	@Transactional
	public HousingManagement modify(Integer id, String json) {
		try {
			JSONObject obj = new JSONObject(json);

			String remarks = obj.isNull("remarks") ? null : obj.getString("remarks");
			String check_Out_Time = obj.isNull("check_Out_Time") ? null : obj.getString("check_Out_Time");
			Date checkOutTime;
			if (check_Out_Time != null && check_Out_Time.length() != 0) {
				checkOutTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(check_Out_Time);
			} else {
				checkOutTime = null;
			}
			String totalAdditional = obj.isNull("totalAdditional") ? null : obj.getString("totalAdditional");
			BigDecimal totalAdditionalfee = new BigDecimal(totalAdditional);
			String totalCompensation = obj.isNull("totalCompensation") ? null : obj.getString("totalCompensation");
			BigDecimal totalCompensationfee = new BigDecimal(totalCompensation);

			Optional<HousingManagement> optional = housingManagementRepo.findById(id);
			if (optional.isEmpty()) {
				HousingManagement update = optional.get();
				update.setRemarks(remarks);
				update.setCheckOutTime(checkOutTime);
				update.setTotalAdditional(totalAdditionalfee);
				update.setTotalCompensation(totalCompensationfee);
				
	            if (checkOutTime != null) {
	                RoomManagement management = roomManagementService.findById(id);
	                if (management != null) {
	                	RoomState state  = roomStateService.findById(id);
	                    management.setRoomState(state); // 假設1表示特定的房間狀態
	                    roomManagementService.modify(json); // 更新 RoomManagement
	                }
	            }

	            return update;
	        }
		} catch (JSONException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}