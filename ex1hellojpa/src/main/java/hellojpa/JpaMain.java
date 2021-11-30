package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        //EntityManagerFactory 애플리케이션 동작 시 하나만 존재해야함
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //EntityManager 트랜잭션이 일어날때마다 만들어주는것
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team);
            em.persist(member);

            /*em.flush(); //sql 버퍼 비우기
            em.clear(); //영속성 컨텍스트 1차 캐시 초기화 */

            Member findMember = em.find(Member.class, member.getId());

            Team findTeam = findMember.getTeam();

            List<Member> members = findTeam.getMembers();

            for (Member member1 : members) {
                System.out.println("member1 = " + member1.getUsername());
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
