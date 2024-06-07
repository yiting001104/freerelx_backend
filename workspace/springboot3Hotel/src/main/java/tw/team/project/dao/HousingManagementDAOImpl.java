package tw.team.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import tw.team.project.model.HousingManagement;
import tw.team.project.util.DatetimeConverter;

@Repository
public class HousingManagementDAOImpl implements HousingManagementDAO{

	
	@PersistenceContext
	private Session session;

	public Session getSession() {
		return this.session;
	}
	
	@Override
	public long count(JSONObject obj) {
	    try {
	        Integer id = obj.isNull("id") ? null : obj.getInt("id");
	        Integer number = obj.isNull("number") ? null : obj.getInt("number");
	        String startRemarks = obj.isNull("startRemarks") ? null : obj.getString("startRemarks");
	        String endRemarks = obj.isNull("endRemarks") ? null : obj.getString("endRemarks");
	        String startCheckInTime = obj.isNull("startCheckInTime") ? null : obj.getString("startCheckInTime");
	        String endCheckInTime = obj.isNull("endCheckInTime") ? null : obj.getString("endCheckInTime");
	        String startCheckOutTime = obj.isNull("startCheckOutTime") ? null : obj.getString("startCheckOutTime");
	        String endCheckOutTime = obj.isNull("endCheckOutTime") ? null : obj.getString("endCheckOutTime");
	        Double startTotalAdditional = obj.isNull("startTotalAdditional") ? null : obj.getDouble("startTotalAdditional");
	        Double endTotalAdditional = obj.isNull("endTotalAdditional") ? null : obj.getDouble("endTotalAdditional");
	        Double startTotalCompensation = obj.isNull("startTotalCompensation") ? null : obj.getDouble("startTotalCompensation");
	        Double endTotalCompensation = obj.isNull("endTotalCompensation") ? null : obj.getDouble("endTotalCompensation");


	        CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
	        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

	        // from product
	        Root<HousingManagement> table = criteriaQuery.from(HousingManagement.class);

	        // select count(*)
	        criteriaQuery = criteriaQuery.select(criteriaBuilder.count(table));

	        // where start
	        List<Predicate> rooms = new ArrayList<>();
	        if (id != null) {
	            Predicate p = criteriaBuilder.equal(table.get("id"), id);
	            rooms.add(p);
	        }
	        if (number != null) {
	            Predicate p = criteriaBuilder.equal(table.get("number"), number);
	            rooms.add(p);
	        }
	        if (startRemarks != null && startRemarks.length() != 0) {
	        	rooms.add(criteriaBuilder.like(table.get("startRemarks"), "%" + startRemarks + "%"));
	        }
	        if (endRemarks != null && endRemarks.length() != 0) {
	        	rooms.add(criteriaBuilder.like(table.get("endRemarks"), "%" + endRemarks + "%"));
	        }
	        if (startCheckInTime != null && startCheckInTime.length() != 0) {
	            java.util.Date temp = DatetimeConverter.parse(startCheckInTime, "yyyy-MM-dd HH:mm:ss");
	            rooms.add(criteriaBuilder.greaterThan(table.get("make"), temp));
	        }
	        if (endCheckInTime != null && endCheckInTime.length() != 0) {
	            java.util.Date temp = DatetimeConverter.parse(endCheckInTime, "yyyy-MM-dd HH:mm:ss");
	            rooms.add(criteriaBuilder.lessThan(table.get("make"), temp));
	        }	        
	        if (startCheckOutTime != null && startCheckOutTime.length() != 0) {
	            java.util.Date temp = DatetimeConverter.parse(startCheckOutTime, "yyyy-MM-dd HH:mm:ss");
	            rooms.add(criteriaBuilder.greaterThan(table.get("make"), temp));
	        }
	        if (endCheckOutTime != null && endCheckOutTime.length() != 0) {
	            java.util.Date temp = DatetimeConverter.parse(endCheckOutTime, "yyyy-MM-dd HH:mm:ss");
	            rooms.add(criteriaBuilder.lessThan(table.get("make"), temp));
	        }
	        if (startTotalAdditional != null) {
	        	rooms.add(criteriaBuilder.greaterThan(table.get("startTotalAdditional"), startTotalAdditional));
	        }
	        if (endTotalAdditional != null) {
	        	rooms.add(criteriaBuilder.greaterThan(table.get("endTotalAdditional"), endTotalAdditional));
	        }
	        if (startTotalCompensation != null) {
	        	rooms.add(criteriaBuilder.greaterThan(table.get("startTotalCompensation"), startTotalCompensation));
	        }
	        if (endTotalCompensation != null) {
	        	rooms.add(criteriaBuilder.greaterThan(table.get("endTotalCompensation"), endTotalCompensation));
	        }


	        if (rooms != null && !rooms.isEmpty()) {
	            Predicate[] array = rooms.toArray(new Predicate[0]);
	            criteriaQuery = criteriaQuery.where(array);
	        }
	        // where end

	        TypedQuery<Long> typedQuery = this.getSession().createQuery(criteriaQuery);
	        Long result = typedQuery.getSingleResult();
	        if (result != null) {
	            return result.longValue();
	        } else {
	            return 0;
	        }
	    } catch (JSONException e) {

	        e.printStackTrace();
	        return 0; 
	    }
	}


	@Override
	public List<HousingManagement> find(JSONObject obj) {
	    try {
	    	Integer id = obj.isNull("id") ? null : obj.getInt("id");
	        Integer number = obj.isNull("number") ? null : obj.getInt("number");
	        String startRemarks = obj.isNull("startRemarks") ? null : obj.getString("startRemarks");
	        String endRemarks = obj.isNull("endRemarks") ? null : obj.getString("endRemarks");
	        String startCheckInTime = obj.isNull("startCheckInTime") ? null : obj.getString("startCheckInTime");
	        String endCheckInTime = obj.isNull("endCheckInTime") ? null : obj.getString("endCheckInTime");
	        String startCheckOutTime = obj.isNull("startCheckOutTime") ? null : obj.getString("startCheckOutTime");
	        String endCheckOutTime = obj.isNull("endCheckOutTime") ? null : obj.getString("endCheckOutTime");
	        Double startTotalAdditional = obj.isNull("startTotalAdditional") ? null : obj.getDouble("startTotalAdditional");
	        Double endTotalAdditional = obj.isNull("endTotalAdditional") ? null : obj.getDouble("endTotalAdditional");
	        Double startTotalCompensation = obj.isNull("startTotalCompensation") ? null : obj.getDouble("startTotalCompensation");
	        Double endTotalCompensation = obj.isNull("endTotalCompensation") ? null : obj.getDouble("endTotalCompensation");

	        int start = obj.isNull("start") ? 0 : obj.getInt("start");
	        int rows = obj.isNull("rows") ? 10 : obj.getInt("rows");
	        String order = obj.isNull("order") ? "id" : obj.getString("order");
	        boolean dir = obj.isNull("dir") ? false : obj.getBoolean("dir");

	        CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
	        CriteriaQuery<HousingManagement> criteriaQuery = criteriaBuilder.createQuery(HousingManagement.class);

	        // from product
	        Root<HousingManagement> table = criteriaQuery.from(HousingManagement.class);

	        // where start
	        List<Predicate> rooms = new ArrayList<>();
	        if (id != null) {
	            Predicate p = criteriaBuilder.equal(table.get("id"), id);
	            rooms.add(p);
	        }
	        if (number != null) {
	            Predicate p = criteriaBuilder.equal(table.get("number"), number);
	            rooms.add(p);
	        }
	        if (startRemarks != null && startRemarks.length() != 0) {
	        	rooms.add(criteriaBuilder.like(table.get("startRemarks"), "%" + startRemarks + "%"));
	        }
	        if (endRemarks != null && endRemarks.length() != 0) {
	        	rooms.add(criteriaBuilder.like(table.get("endRemarks"), "%" + endRemarks + "%"));
	        }
	        if (startCheckInTime != null && startCheckInTime.length() != 0) {
	            java.util.Date temp = DatetimeConverter.parse(startCheckInTime, "yyyy-MM-dd HH:mm:ss");
	            rooms.add(criteriaBuilder.greaterThan(table.get("make"), temp));
	        }
	        if (endCheckInTime != null && endCheckInTime.length() != 0) {
	            java.util.Date temp = DatetimeConverter.parse(endCheckInTime, "yyyy-MM-dd HH:mm:ss");
	            rooms.add(criteriaBuilder.lessThan(table.get("make"), temp));
	        }	        
	        if (startCheckOutTime != null && startCheckOutTime.length() != 0) {
	            java.util.Date temp = DatetimeConverter.parse(startCheckOutTime, "yyyy-MM-dd HH:mm:ss");
	            rooms.add(criteriaBuilder.greaterThan(table.get("make"), temp));
	        }
	        if (endCheckOutTime != null && endCheckOutTime.length() != 0) {
	            java.util.Date temp = DatetimeConverter.parse(endCheckOutTime, "yyyy-MM-dd HH:mm:ss");
	            rooms.add(criteriaBuilder.lessThan(table.get("make"), temp));
	        }
	        if (startTotalAdditional != null) {
	        	rooms.add(criteriaBuilder.greaterThan(table.get("startTotalAdditional"), startTotalAdditional));
	        }
	        if (endTotalAdditional != null) {
	        	rooms.add(criteriaBuilder.greaterThan(table.get("endTotalAdditional"), endTotalAdditional));
	        }
	        if (startTotalCompensation != null) {
	        	rooms.add(criteriaBuilder.greaterThan(table.get("startTotalCompensation"), startTotalCompensation));
	        }
	        if (endTotalCompensation != null) {
	        	rooms.add(criteriaBuilder.greaterThan(table.get("endTotalCompensation"), endTotalCompensation));
	        }

	        // where end

	        // Order By
	        if (dir) {
	            criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.desc(table.get(order)));
	        } else {
	            criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.asc(table.get(order)));
	        }

	        TypedQuery<HousingManagement> typedQuery = this.getSession().createQuery(criteriaQuery).setFirstResult(start)
	                .setMaxResults(rows);

	        List<HousingManagement> result = typedQuery.getResultList();
	        if (result != null && !result.isEmpty()) {
	            return result;
	        } else {
	            return null;
	        }
	    } catch (JSONException e) {

	        e.printStackTrace();
	        return null; 
	    }
	}
	
}
