package com.dev.spring.dao;

import com.dev.spring.dto.UserResponseDto;
import com.dev.spring.model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User add(User user) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<UserResponseDto> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "SELECT new com.dev.spring.dto.UserResponseDto(u.name, u.email) "
                            + "FROM User u", UserResponseDto.class)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserResponseDto get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "SELECT new com.dev.spring.dto.UserResponseDto(u.name, u.email) "
                            + "FROM User u WHERE u.id = :id", UserResponseDto.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
