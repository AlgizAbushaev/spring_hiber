package hiber.dao;

import hiber.model.User;
import jakarta.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    //    @Autowired
    private final SessionFactory sessionFactory;

    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings({"unchecked", "unchecked"})
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public User getUserByCar(String model, int series) {

        TypedQuery<User> typedQuery = sessionFactory.getCurrentSession().createQuery("from User user where user.car.model =: model and  user.car.series =: series");
        typedQuery.setParameter("model", model).setParameter("series", series);

        return typedQuery.getSingleResult();
    }
}
