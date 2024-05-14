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
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.team.project.model.HousingManagement;
import tw.team.project.model.OrderRoom;
import tw.team.project.model.RoomAssignment;
import tw.team.project.model.RoomManagement;
import tw.team.project.model.RoomState;
import tw.team.project.repository.HousingManagementRepository;

@Service
@Transactional
public class HousingManagementService {

	@Autowired
	private HousingManagementRepository HosingManagementRepo;

	@Autowired
	private RoomAssignmentService roomAssignmentService;

	@Autowired
	private RoomManagementService roomManagemnantService;

	@Autowired
	private OrderRoomService orderRoomService;
	
	@Autowired
	private RoomStateService roomStateService;

	public boolean existsById(Integer id) {
		if (id != null) {
			long result = HosingManagementRepo.countById(id);
			if (result != 0) {
				return true;
			}
		}
		return false;
	}

	public long count(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return HosingManagementRepo.count(obj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean existById(Integer id) {
		if (id != null) {
			return HosingManagementRepo.existsById(id);
		}
		return false;
	}

	public List<HousingManagement> find(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return HosingManagementRepo.find(obj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public HousingManagement findById(Integer id) {
		if (id != null) {
			Optional<HousingManagement> optional = HosingManagementRepo.findById(id);
			if (optional.isPresent()) {
				return optional.get();
			}
		}
		return null;
	}

	public List<HousingManagement> select(HousingManagement bean) {
		List<HousingManagement> result = null;
		if (bean != null && bean.getId() != null && !bean.getId().equals(0)) {
			Optional<HousingManagement> optional = HosingManagementRepo.findById(bean.getId());
			if (optional.isPresent()) {
				result = new ArrayList<HousingManagement>();
				result.add(optional.get());
			}
		} else {
			result = HosingManagementRepo.findAll();
		}
		return result;
	}

	public HousingManagement create(String json, Integer id) {
		try {
			JSONObject obj = new JSONObject(json);
			RoomManagement roomManagement = null;
			OrderRoom orderRoom = null;

			roomManagement = roomManagemnantService.findById(id);
			orderRoom = orderRoomService.findById(id);

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
			BigDecimal totalAdditionalfee = new BigDecimal(totalAdditional);
			String totalCompensation = obj.isNull("totalCompensation") ? null : obj.getString("totalCompensation");
			BigDecimal totalCompensationfee = new BigDecimal(totalCompensation);

			if (checkInTime != null) {
				Optional<HousingManagement> optional = HosingManagementRepo.findById(id);
				if (optional.isEmpty()) {
					HousingManagement insert = new HousingManagement();
					insert.setRemarks(remarks);
					insert.setCheckInTime(checkInTime);
					insert.setCheckOutTime(checkOutTime);
					insert.setTotalAdditional(totalAdditionalfee);
					insert.setTotalCompensation(totalCompensationfee);
					insert.setOrderRoom(orderRoom);
					insert.setRoomManagement(roomManagement);

					// 檢查是否當日已有訂房
					OrderRoom orderdate = orderRoomService.findById(id);
					if (!orderdate.getOrderDate().equals(checkInTime)) {
						// 當日沒有其他訂房，進行庫存減少
						RoomAssignment orderdate2 = roomAssignmentService.findById(id);
						if (orderdate2 != null && orderdate2.getLeft() != 0) {
							orderdate2.setLeft(orderdate2.getLeft() - 1);
						} else {
							// 當日沒有其他訂房，新增 RoomAssignment
							RoomAssignment rooomAssignment = new RoomAssignment();
							rooomAssignment.setDate(checkInTime);
							rooomAssignment.setLeft(orderdate2.getLeft() - 1);
							roomAssignmentService.insert(rooomAssignment);
						}
					} else {
						// 當日已有其他訂房
						return null;
					}

					// 更新 RoomManagement 的 roomState

		            if (checkOutTime != null) {
		                RoomManagement management = roomManagemnantService.findById(id);
		                if (management != null) {
		                	RoomState state  = roomStateService.findById(id);
		                	management.setRoomState(state); // 假設1表示特定的房間狀態
		                    roomManagemnantService.modify(json); // 更新 RoomManagement
		                }
		            }

					HousingManagement newHousing = HosingManagementRepo.save(insert);
					return newHousing;
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
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

			Optional<HousingManagement> optional = HosingManagementRepo.findById(id);
			if (optional.isEmpty()) {
				HousingManagement update = optional.get();
				update.setRemarks(remarks);
				update.setCheckOutTime(checkOutTime);
				update.setTotalAdditional(totalAdditionalfee);
				update.setTotalCompensation(totalCompensationfee);
				
	            if (checkOutTime != null) {
	                RoomManagement management = roomManagemnantService.findById(id);
	                if (management != null) {
	                	RoomState state  = roomStateService.findById(id);
	                    management.setRoomState(state); // 假設1表示特定的房間狀態
	                    roomManagemnantService.modify(json); // 更新 RoomManagement
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
