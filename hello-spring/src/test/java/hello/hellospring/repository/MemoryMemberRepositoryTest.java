package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearAll();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }


    @Test
    public void doubleColon(){
        List<String> list = Arrays.asList("first", "second", "third", "forth");
        list.forEach(item -> System.out.println(item));
        System.out.println("----------");
        list.forEach(System.out::println);

        Function<String, Food> function1 = (String a) -> new Food(a);
        Food food = function1.apply("pizza");
        System.out.println(food.getName());

        Function<String, Food> function2 = Food::new;
        food = function2.apply("pasta");
        System.out.println(food.getName());

    }

    public class Food{
        private String name;
        public Food(String name){
            this.name = name;
        }

        public String getName(){
            return this.name;
        }
    }


}
