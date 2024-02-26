package steamworks.commoncode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import steamworks.commoncode.entity.CommonCode;
@Repository
public interface CommonCodeRepository extends JpaRepository<CommonCode,Long> {
}
