package co.dingco.answer;
import org.springframework.data.domain.*; import org.springframework.data.jpa.repository.*; import org.springframework.data.jpa.domain.Specification;
interface TodoRepository extends JpaRepository<Todo,Long>, JpaSpecificationExecutor<Todo> {
  @Override @EntityGraph(attributePaths="user") Page<Todo> findAll(Specification<Todo> spec, Pageable pageable);
}
