package steamworks.commoncode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import steamworks.commoncode.entity.MsgBundle;
import steamworks.commoncode.entity.SettingManage;

@Repository
public interface SettingManageRepository extends JpaRepository<SettingManage,Long> {
}
