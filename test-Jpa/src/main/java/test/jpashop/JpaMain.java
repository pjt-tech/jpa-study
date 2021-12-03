package test.jpashop;

import test.jpashop.domain.Member;
import test.jpashop.domain.Order;
import test.jpashop.domain.OrderStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setName("A");
            em.persist(member);

            Order order = new Order();
            order.setStatus(OrderStatus.ORDER);
            order.setMember(member);
            em.persist(order);

            member.addOrder(order);

            Member findMember = em.find(Member.class, member.getId());
            List<Order> orders = findMember.getOrders();
            for (Order order1 : orders) {
                System.out.println(order1.getStatus());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
