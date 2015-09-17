package tr.edu.metu.ceng352.repository;

import javax.persistence.*;
import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import tr.edu.metu.ceng352.model.User;

import java.math.BigDecimal;

@Repository
@Transactional(readOnly = true)
public class UserRepository {

	private static final String FIND_BY_EMAIL = "select u from User u where u.email = :email";

	private static final String CURRENT_MONEY = "select u.money from User u where u.user_id = :id";

	private static final String WITHDRAW_MONEY = "Update user set money = money - ? where user_id = ?;";

	private static final String ADD_MONEY = "Update user set money = money + ? where user_id = ?;";

	private static final String BECOME_DEVELOPER = "Update user set role = 'ROLE_DEVELOPER' where user_id = ?;";

	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		entityManager.persist(user);
		return user;
	}
	
	public User findByEmail(String email) {
		try {
			return entityManager.createQuery(FIND_BY_EMAIL, User.class)
					.setParameter("email", email)
					.getSingleResult();
		} catch (PersistenceException e) {
			return null;
		}
	}

	public Double getCurrentMoney(Long userId) {
		Object o= entityManager.createNativeQuery(CURRENT_MONEY)
				.setParameter("id", userId)
				.getSingleResult();
		return ((BigDecimal) o).doubleValue();
	}

	@Transactional
	public Boolean withdrawMoney(Long userId, Double amount) {
		entityManager.createNativeQuery(WITHDRAW_MONEY)
				.setParameter(1, amount)
				.setParameter(2, userId)
				.executeUpdate();
		return true;
	}

	@Transactional
	public Boolean addMoney(Long userId, Double amount) {
		entityManager.createNativeQuery(ADD_MONEY)
				.setParameter(1, amount)
				.setParameter(2, userId)
				.executeUpdate();
		return true;
	}

	@Transactional
	public Boolean becomeDeveloper(Long userId) {
		entityManager.createNativeQuery(BECOME_DEVELOPER)
				.setParameter(1, userId)
				.executeUpdate();
		return true;
	}
}
