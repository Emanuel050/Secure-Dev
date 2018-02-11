package db_repositories;

import javax.inject.Inject;
import javax.inject.Singleton;

import play.db.jpa.JPA;
import play.db.jpa.JPAApi;

@Singleton
public class JPAController {
	private static JPAApi jpaApi;
	
	@Inject
	public JPAController(JPAApi api) {
		jpaApi = api;
	}

	public static JPAApi getJpaApi() {
		return jpaApi;
	}

}
