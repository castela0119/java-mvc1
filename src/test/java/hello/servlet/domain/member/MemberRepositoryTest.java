package hello.servlet.domain.member;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    // 싱글톤이기 때문에 new 로 만들 수 없다.
    // MemberRepository memberRepository = new MemberRepository();
    MemberRepository memberRepository = MemberRepository.getInstance();

    // save(), findAll() 중 어떤게 먼저 실행될지 순서가 보장되지 않기 때문에 clearStore() 를 꼭 해준다.
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {

        // given
        Member member = new Member("hello", 20);

        // when
        Member savedMember = memberRepository.save(member);

        // then
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll() {

        // given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        // when
        List<Member> result = memberRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);

    }
}
