package util;

import java.util.Collection;

/**
 * 公共校验类
 *
 * @author yuton
 * @version 1.0
 * @description util
 * @since 下午2:06 2017/12/21
 */
public class CommonUtil {

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

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}