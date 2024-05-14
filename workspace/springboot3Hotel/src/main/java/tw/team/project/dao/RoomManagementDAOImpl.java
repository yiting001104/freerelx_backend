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
import tw.team.project.model.RoomManagement;

@Repository
public class RoomManagementDAOImpl implements RoomManagementDAO {

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
	        String startRepairStatus = obj.isNull("startRepairStatus") ? null : obj.getString("startRepairStatus");
	        String endRepairStatus = obj.isNull("endRepairStatus") ? null : obj.getString("endRepairStatus");

	        CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
	        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

	        // from product
	        Root<RoomManagement> table = criteriaQuery.from(RoomManagement.class);

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
	        if (startRepairStatus != null && startRepairStatus.length() != 0) {
	        	rooms.add(criteriaBuilder.like(table.get("startRepairStatus"), "%" + startRepairStatus + "%"));
	        }
	        if (endRepairStatus != null && endRepairStatus.length() != 0) {
	        	rooms.add(criteriaBuilder.like(table.get("endRepairStatus"), "%" + endRepairStatus + "%"));
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
	public List<RoomManagement> find(JSONObject obj) {
	    try {
	    	Integer id = obj.isNull("id") ? null : obj.getInt("id");
	        Integer number = obj.isNull("number") ? null : obj.getInt("number");
	        String startRepairStatus = obj.isNull("startRepairStatus") ? null : obj.getString("startRepairStatus");
	        String endRepairStatus = obj.isNull("endRepairStatus") ? null : obj.getString("endRepairStatus");

	        int start = obj.isNull("start") ? 0 : obj.getInt("start");
	        int rows = obj.isNull("rows") ? 10 : obj.getInt("rows");
	        String order = obj.isNull("order") ? "id" : obj.getString("order");
	        boolean dir = obj.isNull("dir") ? false : obj.getBoolean("dir");

	        CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
	        CriteriaQuery<RoomManagement> criteriaQuery = criteriaBuilder.createQuery(RoomManagement.class);

	        // from product
	        Root<RoomManagement> table = criteriaQuery.from(RoomManagement.class);

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
	        if (startRepairStatus != null && startRepairStatus.length() != 0) {
	        	rooms.add(criteriaBuilder.like(table.get("startRepairStatus"), "%" + startRepairStatus + "%"));
	        }
	        if (endRepairStatus != null && endRepairStatus.length() != 0) {
	        	rooms.add(criteriaBuilder.like(table.get("endRepairStatus"), "%" + endRepairStatus + "%"));
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

	        TypedQuery<RoomManagement> typedQuery = this.getSession().createQuery(criteriaQuery).setFirstResult(start)
	                .setMaxResults(rows);

	        List<RoomManagement> result = typedQuery.getResultList();
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

