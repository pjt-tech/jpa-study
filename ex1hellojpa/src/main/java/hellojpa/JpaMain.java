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

        try {
            /*em.flush(); //sql 버퍼 비우기
            em.clear(); //영속성 컨텍스트 1차 캐시 초기화 */
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity", "street", "1000000"));
            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new Address("old1", "street2", "20000"));
            member.getAddressHistory().add(new Address("old2", "street3", "30000"));

            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
