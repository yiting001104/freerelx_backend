package tw.team.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.json.JSONException;
import org.json.JSONObject;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import tw.team.project.model.Minibar;
import tw.team.project.util.DatetimeConverter;

public class MinibarDAOImpl implements MinibarDAO {

	
	@PersistenceContext
	private Session session;

	public Session getSession() {
		return this.session;
	}
	
	@Override
	public long count(JSONObject obj) {
	    try {
	        Integer id = obj.isNull("id") ? null : obj.getInt("id");
	        String item = obj.isNull("item") ? null : obj.getString("item");
	        Double startPrice = obj.isNull("startPrice") ? null : obj.getDouble("startPrice");
	        Double endPrice = obj.isNull("endPrice") ? null : obj.getDouble("endPrice");
	        String startMake = obj.isNull("startMake") ? null : obj.getString("startMake");
	        String endMake = obj.isNull("endMake") ? null : obj.getString("endMake");
	        Integer startExpire = obj.isNull("startExpire") ? null : obj.getInt("startExpire");
	        Integer endExpire = obj.isNull("endExpire") ? null : obj.getInt("endExpire");

	        CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
	        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

	        // from product
	        Root<Minibar> table = criteriaQuery.from(Minibar.class);

	        // select count(*)
	        criteriaQuery = criteriaQuery.select(criteriaBuilder.count(table));

	        // where start
	        List<Predicate> predicates = new ArrayList<>();
	        if (id != null) {
	            Predicate p = criteriaBuilder.equal(table.get("id"), id);
	            predicates.add(p);
	        }
	        if (item != null && item.length() != 0) {
	            predicates.add(criteriaBuilder.like(table.get("item"), "%" + item + "%"));
	        }
	        if (startPrice != null) {
	            predicates.add(criteriaBuilder.greaterThan(table.get("price"), startPrice));
	        }
	        if (endPrice != null) {
	            predicates.add(criteriaBuilder.lessThan(table.get("price"), endPrice));
	        }
	        if (startMake != null && startMake.length() != 0) {
	            java.util.Date temp = DatetimeConverter.parse(startMake, "yyyy-MM-dd");
	            predicates.add(criteriaBuilder.greaterThan(table.get("make"), temp));
	        }
	        if (endMake != null && endMake.length() != 0) {
	            java.util.Date temp = DatetimeConverter.parse(endMake, "yyyy-MM-dd");
	            predicates.add(criteriaBuilder.lessThan(table.get("make"), temp));
	        }
	        if (startExpire != null) {
	            predicates.add(criteriaBuilder.greaterThan(table.get("expire"), startExpire));
	        }
	        if (endExpire != null) {
	            predicates.add(criteriaBuilder.lessThan(table.get("expire"), endExpire));
	        }

	        if (predicates != null && !predicates.isEmpty()) {
	            Predicate[] array = predicates.toArray(new Predicate[0]);
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
	public List<Minibar> find(JSONObject obj) {
	    try {
	        Integer id = obj.isNull("id") ? null : obj.getInt("id");
	        String item = obj.isNull("item") ? null : obj.getString("item");
	        Double startPrice = obj.isNull("startPrice") ? null : obj.getDouble("startPrice");
	        Double endPrice = obj.isNull("endPrice") ? null : obj.getDouble("endPrice");
	        String startMake = obj.isNull("startMake") ? null : obj.getString("startMake");
	        String endMake = obj.isNull("endMake") ? null : obj.getString("endMake");
	        Integer startExpire = obj.isNull("startExpire") ? null : obj.getInt("startExpire");
	        Integer endExpire = obj.isNull("endExpire") ? null : obj.getInt("endExpire");

	        int start = obj.isNull("start") ? 0 : obj.getInt("start");
	        int rows = obj.isNull("rows") ? 10 : obj.getInt("rows");
	        String order = obj.isNull("order") ? "id" : obj.getString("order");
	        boolean dir = obj.isNull("dir") ? false : obj.getBoolean("dir");

	        CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
	        CriteriaQuery<Minibar> criteriaQuery = criteriaBuilder.createQuery(Minibar.class);

	        // from product
	        Root<Minibar> table = criteriaQuery.from(Minibar.class);

	        // where start
	        List<Predicate> predicates = new ArrayList<>();
	        if (id != null) {
	            Predicate p = criteriaBuilder.equal(table.get("id"), id);
	            predicates.add(p);
	        }
	        if (item != null && item.length() != 0) {
	            predicates.add(criteriaBuilder.like(table.get("item"), "%" + item + "%"));
	        }
	        if (startPrice != null) {
	            predicates.add(criteriaBuilder.greaterThan(table.get("price"), startPrice));
	        }
	        if (endPrice != null) {
	            predicates.add(criteriaBuilder.lessThan(table.get("price"), endPrice));
	        }
	        if (startMake != null && startMake.length() != 0) {
	            java.util.Date temp = DatetimeConverter.parse(startMake, "yyyy-MM-dd");
	            predicates.add(criteriaBuilder.greaterThan(table.get("make"), temp));
	        }
	        if (endMake != null && endMake.length() != 0) {
	            java.util.Date temp = DatetimeConverter.parse(endMake, "yyyy-MM-dd");
	            predicates.add(criteriaBuilder.lessThan(table.get("make"), temp));
	        }
	        if (startExpire != null) {
	            predicates.add(criteriaBuilder.greaterThan(table.get("expire"), startExpire));
	        }
	        if (endExpire != null) {
	            predicates.add(criteriaBuilder.lessThan(table.get("expire"), endExpire));
	        }

	        if (predicates != null && !predicates.isEmpty()) {
	            Predicate[] array = predicates.toArray(new Predicate[0]);
	            criteriaQuery = criteriaQuery.where(array);
	        }
	        // where end

	        // Order By
	        if (dir) {
	            criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.desc(table.get(order)));
	        } else {
	            criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.asc(table.get(order)));
	        }

	        TypedQuery<Minibar> typedQuery = this.getSession().createQuery(criteriaQuery).setFirstResult(start)
	                .setMaxResults(rows);

	        List<Minibar> result = typedQuery.getResultList();
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