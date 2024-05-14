package tw.team.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import tw.team.project.model.RoomAssignment;
import tw.team.project.model.RoomManagement;

@Repository
public class RoomAssignmentDAOImpl implements RoomAssignmentDAO{

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return this.session;
	}
	
	@Override
	public long count(JSONObject obj) {
	    try {
	        Integer id = obj.isNull("id") ? null : obj.getInt("id");
	        Integer left = obj.isNull("left") ? null : obj.getInt("left");
	        String startDate = obj.isNull("startDate") ? null : obj.getString("startDate");
	        String endDate = obj.isNull("endDate") ? null : obj.getString("endDate");

	        CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
	        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

	        // from product
	        Root<RoomAssignment> table = criteriaQuery.from(RoomAssignment.class);

	        // select count(*)
	        criteriaQuery = criteriaQuery.select(criteriaBuilder.count(table));

	        // where start
	        List<Predicate> rooms = new ArrayList<>();
	        if (id != null) {
	            Predicate p = criteriaBuilder.equal(table.get("id"), id);
	            rooms.add(p);
	        }
	        if (left != null) {
	            Predicate p = criteriaBuilder.equal(table.get("left"), left);
	            rooms.add(p);
	        }
	        if (startDate != null && startDate.length() != 0) {
	        	rooms.add(criteriaBuilder.like(table.get("startDate"), "%" + startDate + "%"));
	        }
	        if (endDate != null && endDate.length() != 0) {
	        	rooms.add(criteriaBuilder.like(table.get("endDate"), "%" + endDate + "%"));
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
	public List<RoomAssignment> find(JSONObject obj) {
	    try {
	    	Integer id = obj.isNull("id") ? null : obj.getInt("id");
	        Integer left = obj.isNull("left") ? null : obj.getInt("left");
	        String startDate = obj.isNull("startDate") ? null : obj.getString("startDate");
	        String endDate = obj.isNull("endDate") ? null : obj.getString("endDate");

	        int start = obj.isNull("start") ? 0 : obj.getInt("start");
	        int rows = obj.isNull("rows") ? 10 : obj.getInt("rows");
	        String order = obj.isNull("order") ? "id" : obj.getString("order");
	        boolean dir = obj.isNull("dir") ? false : obj.getBoolean("dir");

	        CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
	        CriteriaQuery<RoomAssignment> criteriaQuery = criteriaBuilder.createQuery(RoomAssignment.class);

	        // from product
	        Root<RoomAssignment> table = criteriaQuery.from(RoomAssignment.class);

	        // where start
	        List<Predicate> rooms = new ArrayList<>();
	        if (id != null) {
	            Predicate p = criteriaBuilder.equal(table.get("id"), id);
	            rooms.add(p);
	        }
	        if (left != null) {
	            Predicate p = criteriaBuilder.equal(table.get("left"), left);
	            rooms.add(p);
	        }
	        if (startDate != null && startDate.length() != 0) {
	        	rooms.add(criteriaBuilder.like(table.get("startDate"), "%" + startDate + "%"));
	        }
	        if (endDate != null && endDate.length() != 0) {
	        	rooms.add(criteriaBuilder.like(table.get("endDate"), "%" + endDate + "%"));
	        }


	        if (rooms != null && !rooms.isEmpty()) {
	            Predicate[] array = rooms.toArray(new Predicate[0]);
	            criteriaQuery = criteriaQuery.where(array);
	        }
	        // where end

	        // Order By
	        if (dir) {
	            criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.desc(table.get(order)));
	        } else {
	            criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.asc(table.get(order)));
	        }

	        TypedQuery<RoomAssignment> typedQuery = this.getSession().createQuery(criteriaQuery).setFirstResult(start)
	                .setMaxResults(rows);

	        List<RoomAssignment> result = typedQuery.getResultList();
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
