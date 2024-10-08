package tw.team.project.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tw.team.project.model.RoomAssignment;
import tw.team.project.repository.RoomAssignmentRepository;

@Service
public class RoomAssignmentService {
	
    @Autowired
    private RoomAssignmentRepository roomAssignmentRepo;
    
    public Page<RoomAssignment> findAll(Integer id) {
        Pageable pgb = PageRequest.of(id-1, 15, Sort.Direction.ASC, "id");
        Page<RoomAssignment> page = roomAssignmentRepo.findAll(pgb);
        return page;
    }
    
//    public List<RoomAssignment> find(String json) {
//        try {
//            JSONObject obj = new JSONObject(json);
//            return roomAssignmentRepo.find(obj);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    
    public boolean existById(Integer id) {
        if (id != null) {
            return roomAssignmentRepo.existsById(id);
        }
        return false;
    }
    
    public boolean delete(Integer id) {
        if (id != null) {
            Optional<RoomAssignment> optional = roomAssignmentRepo.findById(id);
            if (optional.isPresent()) {
                roomAssignmentRepo.deleteById(id);
                return true;
            }
        }
        return false;
    }
    
    // 新增
    public RoomAssignment insert(RoomAssignment bean) {
        if (bean != null && bean.getId() != null) {
            Optional<RoomAssignment> optional = roomAssignmentRepo.findById(bean.getId());
            if (optional.isEmpty()) {
                return roomAssignmentRepo.save(bean);
            }
        }
        return null;
    }
    
    public RoomAssignment findById(Integer id) {
        Optional<RoomAssignment> optional = roomAssignmentRepo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }
    
    public RoomAssignment update(RoomAssignment bean) {
        if (bean != null && bean.getId() != null) {
            Optional<RoomAssignment> optional = roomAssignmentRepo.findById(bean.getId());
            if (optional.isPresent()) {
                return roomAssignmentRepo.save(bean);
            }
        }
        return null;
    }

    // 更新資料
    public RoomAssignment updateData(Integer id, String json) {
        try {
            JSONObject obj = new JSONObject(json);

            Integer left = obj.isNull("left") ? null : obj.getInt("left");
            String dateStr = obj.isNull("date") ? null : obj.getString("date");
            Date newDate = null;
            if (dateStr != null) {
                newDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(dateStr).getTime());
            }

            Optional<RoomAssignment> optional = roomAssignmentRepo.findById(id);
            if (optional.isPresent()) {
                RoomAssignment existingAssignment = optional.get();
                if (left != null) {
                    existingAssignment.setLeft(left);
                }
                if (newDate != null) {
                    existingAssignment.setDate(newDate);
                }
                return roomAssignmentRepo.save(existingAssignment);
            }
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<RoomAssignment> findAvailableAssignmentsInRange(Date startDate, Date endDate, Integer roomId) {
        return roomAssignmentRepo.findAvailableAssignmentsInRange(startDate, endDate, roomId);
    }

    public Integer findMinLeftInRange(Date startDate, Date endDate, Integer roomId) {
        return roomAssignmentRepo.findMinLeftInRange(startDate, endDate, roomId);
    }

    public boolean isFullyAvailableInRange(Date startDate, Date endDate, Integer roomId) {
        long availableDaysCount = roomAssignmentRepo.countAvailableDaysInRange(startDate, endDate, roomId);
        long totalDays = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24) + 1;
        return availableDaysCount == totalDays;
    }


	// 依日期查詢修改房間數量
	@Transactional
	public RoomAssignment findByDate(Date date, Integer id, String json) {
	    if (date != null) {
	       Optional<RoomAssignment> optional = roomAssignmentRepo.findByDateOrd(date, id);
	       if (optional.isPresent()) {
	    	   try {
				JSONObject obj = new JSONObject(json);
				   
				   RoomAssignment roomAss= optional.get();
				   Integer rooms = obj.isNull("rooms") ? null : obj.getInt("rooms");
				   boolean booking = obj.isNull("booking") ? false : obj.getBoolean("booking");
				   if (rooms!=null) {
					   if (booking) {
						   roomAss.setLeft(roomAss.getLeft()-rooms);
					   }else {
						   roomAss.setLeft(roomAss.getLeft()+rooms); 
					   }
					   
					   return roomAss;
				   }
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       }
	    }
	    return null;
	}

}