package steamworks.commoncode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import steamworks.commoncode.entity.CommonCode;
import steamworks.commoncode.entity.MsgBundle;

@Repository
public interface MsgBundleRepository extends JpaRepository<MsgBundle,Long> {
    public MsgBundle findByMsgCd(String msgCd);
}
