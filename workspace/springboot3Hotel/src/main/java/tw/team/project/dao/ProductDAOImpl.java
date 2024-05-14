package tw.team.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import tw.team.project.model.Product;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Repository
public class ProductDAOImpl implements ProductDAO {
	@PersistenceContext
	private Session session;
	public Session getSession() {
		return this.session;
	}
//	Integer id = obj.isNull("id") ? null : obj.getInt("id");
//	String productName = obj.isNull("productName") ? null : obj.getString("productName");
//	Integer productPrice = obj.isNull("productPrice") ? null : obj.getInt("productPrice");
//	Integer productStock = obj.isNull("productStock") ? null : obj.getInt("productStock");
//	Integer productSupplierId = obj.isNull("productSupplierId") ? null : obj.getInt("productSupplierId");
//	String productDescription = obj.isNull("productDescription") ? null : obj.getString("productDescription");
//	Integer productArrivalDay = obj.isNull("productArrivalDay") ? null : obj.getInt("productArrivalDay");
	@Override
	public long count(JSONObject obj) {
		Integer id = obj.isNull("id") ? null : obj.getInt("id");
		String productName = obj.isNull("productName") ? null : obj.getString("productName");
		Integer productPrice = obj.isNull("productPrice") ? null : obj.getInt("productPrice");
		CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

//		from product
		Root<Product> table = criteriaQuery.from(Product.class);

//		select count(*)
		criteriaQuery = criteriaQuery.select(criteriaBuilder.count(table));
		
//		where start
		List<Predicate> predicates = new ArrayList<>();
		if(id!=null) {
			Predicate p = criteriaBuilder.equal(table.get("id"), id);
			predicates.add(p);
		}
		if(productName!=null && productName.length()!=0) {
			predicates.add(criteriaBuilder.like(table.get("productName"), "%"+productName+"%"));
		}
		if(productPrice!=null) {
			predicates.add(criteriaBuilder.greaterThan(table.get("productPrice"), productPrice));
		}		
		if(predicates!=null && !predicates.isEmpty()) {
			Predicate[] array = predicates.toArray(new Predicate[0]);
			criteriaQuery = criteriaQuery.where(array);
		}
//		where end
		
		TypedQuery<Long> typedQuery = this.getSession().createQuery(criteriaQuery);
		Long result = typedQuery.getSingleResult();
		if(result!=null) {
			return result.longValue();
		} else {
			return 0;
		}
	}
	
	@Override
	public List<Product> find(JSONObject obj) {
		Integer id = obj.isNull("id") ? null : obj.getInt("id");
		String productName = obj.isNull("productName") ? null : obj.getString("productName");
		Integer productPrice = obj.isNull("productPrice") ? null : obj.getInt("productPrice");
		int start = obj.isNull("start") ? 0 : obj.getInt("start");
		int rows = obj.isNull("rows") ? 10 : obj.getInt("rows");
		String order = obj.isNull("order") ? "id" : obj.getString("order");
		boolean dir = obj.isNull("dir") ? false : obj.getBoolean("dir");

		CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
		CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

//		from product
		Root<Product> table = criteriaQuery.from(Product.class);
		
//		where start
		List<Predicate> predicates = new ArrayList<>();
		if(id!=null) {
			Predicate p = criteriaBuilder.equal(table.get("id"), id);
			predicates.add(p);
		}
		if(productName!=null && productName.length()!=0) {
			predicates.add(criteriaBuilder.like(table.get("productName"), "%"+productName+"%"));
		}
		if(productPrice!=null) {
			predicates.add(criteriaBuilder.greaterThan(table.get("productPrice"), productPrice));
		}
		if(predicates!=null && !predicates.isEmpty()) {
			Predicate[] array = predicates.toArray(new Predicate[0]);
			criteriaQuery = criteriaQuery.where(array);
		}
//		where end
		
//		Order By
		if(dir) {
			criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.desc(table.get(order)));
		} else {
			criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.asc(table.get(order)));
		}

		TypedQuery<Product> typedQuery = this.getSession().createQuery(criteriaQuery)
				.setFirstResult(start)
				.setMaxResults(rows);
		
		List<Product> result = typedQuery.getResultList();
		if(result!=null && !result.isEmpty()) {
			return result;
		} else {
			return null;
		}
	}

}
