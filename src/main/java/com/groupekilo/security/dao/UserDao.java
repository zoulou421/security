package com.groupekilo.security.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Query;
import org.hibernate.Session;

import com.groupekilo.security.config.HibernateUtil;
import com.groupekilo.security.entities.UserEntity;

public class UserDao extends RepositoryImpl<UserEntity> implements IUserDao {
	private Session session = HibernateUtil.getSessionFactory().openSession();

	@Override
	public Optional<UserEntity> login(String email, String password) {
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<UserEntity> cr = cb.createQuery(UserEntity.class);
		Root<UserEntity> user = cr.from(UserEntity.class);
		// Predicate pour la clause where
		Predicate predicateEmail = cb.equal(user.get("email"), email);
		Predicate predicatePwd = cb.equal(user.get("password"), password);
		Predicate finalPredicate = cb.and(predicateEmail, predicatePwd);

		cr.select(user);
		cr.where(finalPredicate);
		try {
			// return Optional.ofNullable(session.createQuery(cr).getSingleResult());
			return Optional.of(session.createQuery(cr).getSingleResult());
		} catch (Exception e) {
			return Optional.empty();
		}
		// return Optional.ofNullable(session.createQuery(cr).getSingleResult());
	}

	// Method to search users by name or email
	public List<UserEntity> searchByCriteria(String searchTerm) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
		Root<UserEntity> root = cq.from(UserEntity.class);

		Predicate firstNamePredicate = cb.like(root.get("firstName"), "%" + searchTerm + "%");
		Predicate lastNamePredicate = cb.like(root.get("lastName"), "%" + searchTerm + "%");
		Predicate emailPredicate = cb.like(root.get("email"), "%" + searchTerm + "%");
		cq.where(cb.or(firstNamePredicate, lastNamePredicate, emailPredicate));

		return session.createQuery(cq).getResultList();
	}

	@Override
	public List<UserEntity> myListFilter(UserEntity entity, String filterColumn, String filterValue, String sortColumn,
			String sortOrder) {
		StringBuilder hql = new StringBuilder("FROM UserEntity WHERE 1=1");// UserEntity is not renamed

		// Apply filtering
		if (filterColumn != null && filterValue != null) {
			hql.append(" AND ").append(filterColumn).append(" LIKE :filterValue");
		}
		// Apply sorting
		if (sortColumn != null && sortOrder != null) {
			hql.append(" ORDER BY ").append(sortColumn).append(" ").append(sortOrder);
		}
		Query<UserEntity> query = session.createQuery(hql.toString(), UserEntity.class);

		// Set filter value if applicable
		if (filterValue != null) {
			query.setParameter("filterValue", "%" + filterValue + "%");
		}

		return query.list();
	}

	@Override
	public List<UserEntity> getPaginatedUsers(int page, int pageSize) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        Query<UserEntity> query = session.createQuery("FROM UserEntity", UserEntity.class);
        query.setFirstResult((page - 1) * pageSize);
        query.setMaxResults(pageSize);
        List<UserEntity> result = query.getResultList();
        session.close();
        return result;
	}

	@Override
	public int countAllUsers() {
		 Session session = HibernateUtil.getSessionFactory().openSession();
	        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM UserEntity", Long.class);
	        Long count = query.getSingleResult();
	        session.close();
	        return count.intValue();
	}

}
