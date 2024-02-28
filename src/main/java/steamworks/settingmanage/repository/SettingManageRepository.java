package steamworks.settingmanage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import steamworks.settingmanage.entity.MsgManage;
import steamworks.settingmanage.entity.SettingManage;

@Repository
public interface SettingManageRepository extends JpaRepository<SettingManage,Long> {
}
