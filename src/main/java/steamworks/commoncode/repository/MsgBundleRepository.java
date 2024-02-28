package steamworks.commoncode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import steamworks.commoncode.entity.CommonCode;
import steamworks.commoncode.entity.MsgManage;

@Repository
public interface MsgManageRepository extends JpaRepository<MsgManage,Long> {
    public MsgManage findByMsgCd(String msgCd);
}
