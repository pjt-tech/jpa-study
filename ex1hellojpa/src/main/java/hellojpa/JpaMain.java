package hellojpa;

import javax.persistence.*;

public class JpaMain {

    public static void main(String[] args) {
        //EntityManagerFactory 애플리케이션 동작 시 하나만 존재해야함
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //EntityManager 트랜잭션이 일어날때마다 만들어주는것
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();


    }
}
