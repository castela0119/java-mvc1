package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

    private Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    // 무조건 얘로 조회해야 한다.
    public static MemberRepository getInstance() {
        return instance;
    }

    // 싱글톤으로 만든 경우에는 private 으로 생성자를 막아야한다.
    private MemberRepository() {
    }

    public Member save(Member member) {

        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        // store 내부 값을 변환시키지 않기 위해 ArrayList<>() 로 감싸서 반환
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
