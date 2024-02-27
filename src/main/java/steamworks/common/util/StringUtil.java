package steamworks.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import steamworks.Constants;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 문자열 관련 유틸 클래스
 *
 * @author 이프로
 * @version 2021.06
 */
@Slf4j
public class StringUtil {

    public static final String EMPTY = "";
    public static final String DELIM_01 = "@"; // 문자열 split,join시 임의로 사용하는 구분자

	/*
		UI에서 필수값이 아니라 선택값인 항목의 유효성검사를 할 경우가 있으므로 유효성검사 관련 코드들은 모두 빈 값("") 허용 처리 를 하였음.
			- @Pattern 의 경우 REGEX에 '|(\s*)' 추가
			- @DateCheck 의 경우 속성에 required=false 기본값 추가
		필수값인 경우 @NotBlank 를 추가적으로 변수에 붙이는 것으로 제어할 것
	 */
//	public static final String REGEX_CELLPHONE 	= "^(01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4}))|(\\s*)$"; // 정규식 패턴 (핸드폰번호)
//	public static final String REGEX_YN 		= "^([YN])|(\\s*)$"; 	// 정규식 패턴 (Y/N)
//	public static final String REGEX_ZIPCODE 	= "^(\\d{5})|(\\s*)$"; 	// 정규식 패턴 (우편번호)
//	public static final String REGEX_DB_PK 		= "^(\\d{1,11})|(\\s*)$"; 	// 정규식 패턴 (DB에서 일반적으로 PK로 많이 쓰는 unsigned int(11)를 기준으로)
//	public static final String REGEX_USER_ID 	= "^(?=.*[a-zA-z])(?=.*[0-9])(?!.*[^a-zA-z0-9]).{5,20}$"; // 정규식 패턴 (사용자 ID - 영문자/숫자 포함 5~20자) - 어차피 필수값일 것이므로 빈값 허용처리 하지 않음 (PW도 마찬가지)
//	public static final String REGEX_USER_PW 	= "^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[`~!@$!%*#^?&\\(\\)\\-_=+])(?!.*[^a-zA-z0-9`~!@$!%*#^?&\\(\\)\\-_=+]).{8,16}$"; // 정규식 패턴 (사용자 PW - 영문자/숫자/특수문자 포함 8~16자)

    /**
     * Error stacktrace 의 첫 행에서 Exception 명 이후에 나오는 상세메세지만 추출하여 리턴한다.
     *
     * @param stackTraceFirstRow
     * @return
     */
    public static String getExceptionSummaryFromStackTrace(String stackTraceFirstRow){

        String ret = StringUtils.EMPTY;
        String delim = "Exception:";
        if(stackTraceFirstRow.contains(delim)){
            ret = stackTraceFirstRow.substring((stackTraceFirstRow.indexOf(delim) + delim.length() +1), stackTraceFirstRow.length());
//			ret = ret.replaceAll("\\\"", "\""); //json 내부의 역슬래시 제거 (안됨 ㅠㅠ)
        }
        return ret;
    }

    /**
     * StringUtils.defaultString()이 Object도 받을 수 있도록 개량한 메서드
     *
     * @param src
     * @param defaultStr
     * @return
     */
    public static String defaultString(Object src, String defaultStr) {

        String ret = EMPTY;
        if(src == null) { return EMPTY; }
        if(src instanceof String) {
            ret = (String) src;
        }else{
            ret = src.toString();
        }
        return ret;
    }

    /**
     * null 문자열 처리 후 trim
     *
     * @param src
     * @return
     */
    public static String trim(Object src) {

        return StringUtils.trim(defaultString(src));
    }

    /**
     * StringUtils.defaultString()이 Object도 받을 수 있도록 개량한 메서드
     *
     * @param src
     * @return
     */
    public static String defaultString(Object src) {
        return StringUtil.defaultString(src, null);
    }

    /**
     * 간략한 Error stacktrace 정보를 문자열로 출력한다.
     *
     * @param e
     * @param hideStacktrace true이면 stacktrace를 노출하지 않는다.
     * @return
     */
    public static String getSimpleErrorStacktrace(Throwable e, boolean hideStacktrace) {

        log.debug("## hideStacktrace:[{}]", hideStacktrace);

        String rspMsg = StringUtils.EMPTY;
        if(hideStacktrace){
            rspMsg = Constants.UNDEFIEND_EXCEPTION;
        }else{
            rspMsg = StringUtil.substringByBytes(ExceptionUtils.getRootCauseMessage(e), 200);
        }
        return rspMsg;
    }

    /**
     * 매개변수로 주어진 문자열이 주어진 바이트길이를 초과하면 substring 하여 리턴한다.
     *
     * @param src
     * @param byteLimit
     * @return
     */
    public static String substringByBytes(String src, final int byteLimit){

        String ret = src;
        byte[] srcBytes = StringUtil.getBytes(src);
        if(srcBytes.length > byteLimit){
            byte[] retBytes = ArrayUtils.subarray(srcBytes, 0, byteLimit);
            ret = StringUtil.newString(retBytes);
            if (log.isDebugEnabled()) {
                log.debug("substringByBytes() > substring 전 문자열:[{}], 바이트길이:[{}]", src, srcBytes.length);
                log.debug("substringByBytes() > substring 후 문자열:[{}], 바이트길이:[{}]", ret, retBytes.length);
            }
        }
        return ret;
    }

    /**
     * 문자열을 바이트배열로 변환하여 리턴한다. (기본 캐릭터셋 사용)
     *
     * @param src
     * @return
     */
    public static byte[] getBytes(String src) {

        if(src == null){
            if (log.isErrorEnabled()) {
                log.error("## StringUtil.getBytes() 호출시 매개변수로 null을 사용하였습니다. 문자열 => 바이트 변환이 불가능합니다.");
            }
            return new byte[0]; //sparrow NULL_RETURN
        }
        try {
            return src.getBytes(Constants.DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            if (log.isErrorEnabled()) {
                log.error("## 문자열(" + src + ")의 바이트 변환 중  오류 발생", e);
            }
        }
        return new byte[0]; //sparrow NULL_RETURN
    }

    /**
     * 문자열의 바이트 길이를 리턴한다. (기본 캐릭터셋 사용)
     *
     * @param src
     * @return
     */
    public static int getByteLength(String src) {

        int ret = 0;
        try {
            ret = src.getBytes(Constants.DEFAULT_ENCODING).length;
        } catch (UnsupportedEncodingException e) {
            if (log.isErrorEnabled()) {
                log.error("## 문자열(" + src + ")의 바이트 길이 계산 중  오류 발생", e);
            }
        }
        return ret;
    }

    /**
     * 바이트배열을 받아서 문자열을 생성하여 리턴한다. (기본 캐릭터셋 사용)
     *
     * @param bytes
     * @return
     */
    public static String newString(byte[] bytes){

        if(ArrayUtils.isEmpty(bytes)){
            if (log.isWarnEnabled()) {
                log.warn("## StringUtil.newString() > 매개변수로 받은 바이트배열이 빈 값입니다.");
            }
            return StringUtils.EMPTY;
        }
        try {
            return new String(bytes, Constants.DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            if (log.isErrorEnabled()) {
                log.error("## StringUtil.newString() > 바이트배열("+bytes+") 을 받아서 문자열을 생성하던 중 오류 발생", e);
            }
        }
        return StringUtils.EMPTY;
    }

    public static boolean isBlank(Object src) {

        if(src == null) { return true; }
        if(src instanceof String) {
            return isBlank((String)src);
        }else {
            throw new RuntimeException("isblank() > 변환할 수 있는 형식이 아닙니다. => "+ src.getClass().getName());
        }
    }

    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(Object src) {
        return !isBlank(src);
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

//    /**
//     * Object.toString() 을 예쁘게 찍어보자
//     *
//     * @param o
//     * @return
//     */
//    public static String genToString(Object o){
//
//        String ret = JsonUtil.toJson(o);
//
//        // JSON 변환이 실패했을 경우
//        if (StringUtil.isBlank(ret)) {
//            ret = ToStringBuilder.reflectionToString(o, Constants.DEFAULT_TOSTRING_STYLE);
//        }
//
//        return ret;
//    }

    /**
     * 한글 조사 연결 (을/를,이/가,은/는,로/으로)
     * 1. 종성에 받침이 있는 경우 '을/이/은/으로/과'
     * 2. 종성에 받침이 없는 경우 '를/가/는/로/와'
     * 3. '로/으로'의 경우 종성의 받침이 'ㄹ' 인경우 '로'
     * 참고 1 : http://gun0912.tistory.com/65 (소스 참고)
     * 참고 2 : http://www.klgoodnews.org/board/bbs/board.php?bo_table=korean&wr_id=247 (조사 원리 참고)
     *
     * @param str
     * @param firstVal
     * @param secondVal
     * @return
     */
    public static String getPostWord(String str, String firstVal, String secondVal) {

        try {
            char laststr = str.charAt(str.length() - 1);
            // 한글의 제일 처음과 끝의 범위밖일 경우는 오류
            if (laststr < 0xAC00 || laststr > 0xD7A3) {
//				return str;
                return str + firstVal; // 오류로 하지 말고 그냥 firstVal 붙여주자 (이프로)
            }

            int lastCharIndex = (laststr - 0xAC00) % 28;

            // 종성인덱스가 0이상일 경우는 받침이 있는경우이며 그렇지 않은경우는 받침이 없는 경우
            if (lastCharIndex > 0) {
                // 받침이 있는경우
                // 조사가 '로'인경우 'ㄹ'받침으로 끝나는 경우는 '로' 나머지 경우는 '으로'
                if (firstVal.equals("으로") && lastCharIndex == 8) {
                    str += secondVal;
                } else {
                    str += firstVal;
                }
            } else {
                // 받침이 없는 경우
                str += secondVal;
            }
        } catch (Exception e) {
            log.error("", e);
        }

        return str;
    }

    /**
     * 매개변수로 받은 문자열 list에 중복이 존재할 경우 true, 아니면 false 리턴
     *
     * @param list
     * @return
     */
    public static boolean hasDuplicated(List<String> list) {

        if(list == null){ return false; }
        Set<String> set = new HashSet<>(list);
        if(set.size() < list.size()){
            return true;
        }
        return false;
    }

//    /**
//     * 문자열 마스킹 함수 (개인정보 보호용)
//     *
//     * @param src
//     * @param maskCntLeft 좌측부터 몇 글자 마스킹
//     * @param maskCntRight 우측부터 몇 글자 마스킹
//     * @return
//     */
//    public static String mask(String src, int maskCntLeft, int maskCntRight) {
//
//        if(StringUtil.isBlank(src)) { return ""; }
//        if (src.length() < (maskCntLeft + maskCntRight)) {
//            throw new CustomException(ResCodeEnum.ERROR_1000.name(), "마스킹 대상 문자열 ["+ src +"] 의 길이가 마스킹 길이 (좌:["+ maskCntLeft +"] + 우:["+ maskCntRight +"]) 보다 짧습니다.");
//        }
//        return src
//                .replaceAll("(^.{"+ maskCntLeft +"})", StringUtils.repeat('*', maskCntLeft))
//                .replaceAll("(.{"+ maskCntRight +"}$)", StringUtils.repeat('*', maskCntRight));
//    }

    /**
     * MyBatis 에서 사용할 목적으로 생성한 메서드
     *
     * 	- 최초 두 번째 매개변수가 String일 것이라 예상했는데 Map이어서, 꾸역꾸역 형태를 맞춰 작업하였음.
     */
    public static boolean containsList(List<Object> list, Map<String, Object> map){

        if (list == null || list.size() == 0) {
            return false;
        }
//		log.debug("## map:{}", JsonUtil.toJson(map));
        if(map == null || map.size() == 0){
            return false;
        }
        Object[] arr = map.keySet().toArray();
        if(arr == null || arr.length == 0){
            return false;
        }
        String val = StringUtil.trim(arr[0]); // 왜 이런 구조가 되는지는 모르겠지만, 일단 값을 얻어온다.
//		log.debug("## val:[{}]", val);
        boolean ret = list.contains(val);
//		log.debug("## containsList() 결과:[{}]", ret);
//        return list.contains(elem);
        return ret;
    }

}
