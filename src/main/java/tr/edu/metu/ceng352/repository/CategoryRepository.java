package tr.edu.metu.ceng352.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.metu.ceng352.model.Category;
import tr.edu.metu.ceng352.model.dbModel.SidebarCategory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional(readOnly = true)
public class CategoryRepository {

    private static final String FIND_ALL = "select c from Category c";
    private static final String FIND_BY_ID = "select c from Category c where c.id = :id";

    private static final String SIDEBAR_CATEGORIES = "Select a.category_id, c.title, c.description, count(*) as app_number " +
            "from category c, app a " +
            "where c.category_id = a.category_id " +
            "group by a.category_id " +
            "order by app_number desc;";

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Category save(Category category) {
        entityManager.persist(category);
        return category;
    }

    public Category findById(Long id) {
        try {
            return entityManager.createQuery(FIND_BY_ID, Category.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (PersistenceException e) {
            return null;
        }
    }

    public List<Category> findAll() {
        return entityManager.createQuery(FIND_ALL).getResultList();
    }

    public List<SidebarCategory> findSidebarCategories() {
        Query query = entityManager.createNativeQuery(SIDEBAR_CATEGORIES);
        List<Object[]> resultList=query.getResultList();
        List<SidebarCategory> categories = resultList.stream().map(this::mapToHomeApp).collect(Collectors.toList());
        return categories;
    }

    private SidebarCategory mapToHomeApp(Object[] o) {
        SidebarCategory category = new SidebarCategory();
        category.setId((int)o[0]);
        category.setTitle((String) o[1]);
        category.setDescription((String) o[2]);
        BigInteger count = (BigInteger) o[3];
        category.setCount(count.intValue());
        return category;
    }

}
