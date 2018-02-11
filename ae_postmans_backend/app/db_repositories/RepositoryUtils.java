package db_repositories;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class RepositoryUtils {

	public static <T> void save(T object) {
		JPAController.getJpaApi().em().persist(object);
		JPAController.getJpaApi().em().refresh(object);
	}
	
	public static <T> void update(T object) {
		JPAController.getJpaApi().em().merge(object);
	}
	
	public static <T> void remove(T object) {
		JPAController.getJpaApi().em().remove(object);
	}

	public static <T> T getEntityById(Long id, Class<T> clazz) {
		try {
			return JPAController.getJpaApi().em().find(clazz, id);
		} catch (NoResultException e) {
			return null;
		}
	}

	public static <T> T getEntityByUniqueFieldValue(Class<T> clazz, String fieldName, String fieldValue) {
		try {
			return JPAController.getJpaApi().em().createQuery(
					String.format("SELECT e FROM %s e WHERE %s = '%s'", clazz.getSimpleName(), fieldName, fieldValue),
					clazz).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public static <T> List<T> getEntityListByFieldValue(Class<T> clazz, String fieldName, Object fieldValue) {
		try {
			return JPAController.getJpaApi().em().createQuery(
					String.format("SELECT e FROM %s e WHERE %s = :value", clazz.getSimpleName(), fieldName),
					clazz).setParameter("value", fieldValue).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public static <T> List<T> getAllEntities(Class<T> clazz) {
		try {
			return JPAController.getJpaApi().em().createQuery(
					String.format("SELECT e FROM %s e", clazz.getSimpleName()),
					clazz).getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public static <T> List<T> getEntityListByNamedQuery(Class<T> clazz, String queryStr, Object ... args) {
		try {
			TypedQuery<T> query = JPAController.getJpaApi().em().createNamedQuery(queryStr, clazz);
			
			int i = 1;
			for (Object arg: args) {
				query.setParameter(i, arg);
				i++;
			}
			
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
