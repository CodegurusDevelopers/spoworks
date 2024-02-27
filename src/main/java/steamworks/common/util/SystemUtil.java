package steamworks.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class SystemUtil {

    private static final String ACTIVE_PROFILE = System.getProperty("spring.profiles.active");
    private static final String DEFAULT_PROFILE = System.getProperty("spring.profiles.default");

    public static String getProfiles() {
        return Optional.ofNullable(ACTIVE_PROFILE).orElse(DEFAULT_PROFILE);
    }

    /**
     * 현재 로컬환경(개발자PC)일 경우 true, 아니면 false
     *
     * @return
     */
    public static boolean isLocal() {
        return getProfiles().contains("local");
    }

    /**
     * 현재 개발서버일 경우 true, 아니면 false
     *
     * @return
     */
    public static boolean isDev() {
        return getProfiles().contains("dev");
    }

    /**
     * 현재 운영서버일 경우 true, 아니면 false
     *
     * @return
     */
    public static boolean isProd() {
        return getProfiles().contains("prod");
    }

    /**
     * db updated 카운트가 예상대로인지 체크하여, 예상과 다르면 exception throw
     *
     * @param updatedCnt 실제 updated count
     * @param updatedCntExpected 기대되는 updated count
     */


}
