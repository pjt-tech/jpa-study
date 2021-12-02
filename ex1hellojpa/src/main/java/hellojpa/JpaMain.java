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
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            //findParent.getChildList().remove(0);

            em.remove(findParent);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);
        Team team = member.getTeam();
        System.out.println("team = " + team.getName());
    }
}
