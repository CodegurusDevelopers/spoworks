package steamworks.codeManage.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import steamworks.codeManage.entity.Code;

import java.util.List;

@Repository
public interface CodeRepository extends CrudRepository<Code, Long> {
    List<Code> findByParents(Code parent);
}
