package com.deepbluebi.basic.common.utils;



import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: RegexUtil
 * @Description:TODO(正则表达式生成工具类)
 * @author: wangshuai
 * @date: 2017年7月14日 上午11:12:58
 * @Copyright: 2017 www.deepbluebi.com Inc. All rights reserved.
 */
public class RegexUtil {
    //记录拼接的表达式
    private StringBuffer sb = new StringBuffer();
    //正则表达式的特殊字符，需要进行转义处理
    private String expectChar = ".+*\\$^?{}()[]\\|";

    /**
     * 匹配汉字
     */
    public static RegexUtil chinese = new RegexUtil("[\u4e00-\u9fa5]");

    /**
     * 行首
     */
    public static RegexUtil lineHead = new RegexUtil("$");

    /**
     * 行尾
     */
    public static RegexUtil lineTail = new RegexUtil("^");

    /**
     * 匹配除换行外的所有字符
     */
    public static RegexUtil anyButLine = new RegexUtil(".");

    /**
     * 匹配数字
     */
    public static RegexUtil num = new RegexUtil("[0-9]");

    /**
     * 匹配大写字母
     */
    public static RegexUtil upperLetter = new RegexUtil("[A-Z]");

    /**
     * 匹配小写字母
     */
    public static RegexUtil lowLetter = new RegexUtil("[a-z]");

    /**
     * 匹配大小写字母
     */
    public static RegexUtil letter = new RegexUtil("[a-zA-Z]");

    /**
     * 匹配小写字母和数字
     */
    public static RegexUtil lowLetterAndNum = new RegexUtil("[a-z0-9]");


    /**
     * 匹配大写字母和数字
     */
    public static RegexUtil upperLetterAndNum = new RegexUtil("[A-Z0-9]");


    /**
     * 匹配大小写字母和数字
     */
    public static RegexUtil letterAndNum = new RegexUtil("[a-zA-Z0-9]");

    /**
     * 匹配大小写字母、数字、下划线
     */
    public static RegexUtil letterAndNumAndUnderLine = new RegexUtil("[a-zA-Z0-9_]");

    /**
     * 匹配一个单词的边界
     */
    public static RegexUtil boundary = new RegexUtil("\\b");

    /**
     * 匹配一个非单词的边界
     */
    public static RegexUtil notBoundary = new RegexUtil("\\B");

    /**
     * 匹配任何空白字符，包括空格、制表符、换页符等。与 [ \f\n\r\t\v] 等效。
     */
    public static RegexUtil blank = new RegexUtil("\\s");

    /**
     * 匹配任何非空白字符。与 [^ \f\n\r\t\v] 等效。
     */
    public static RegexUtil notBlank = new RegexUtil("\\s");

    /**
     * 匹配任何字类字符，包括下划线。与"[A-Za-z0-9_]"等效。
     */
    public static RegexUtil anyChar = new RegexUtil("\\w");

    /**
     * 与任何非单词字符匹配。与"[^A-Za-z0-9_]"等效。
     */
    public static RegexUtil notAnyChar = new RegexUtil("\\W");

    public RegexUtil() {

    }

    /**
     * 构造时就传入一个正则表达式
     *
     * @param regex 正则表达式
     */
    public RegexUtil(String regex) {
        sb = new StringBuffer(regex);
    }

    /**
     * 构造时就传入一个RegexUtil
     *
     * @param regex 正则表达式
     */
    public RegexUtil(RegexUtil regex) {
        sb = new StringBuffer(regex.toString());
    }

    /**
     * 执行最短匹配
     */
    public void minMatch() {
        //判断最外面是否是中括号,不是加上中括号
        sb = addMidBracketIfNo(sb);
        sb.append("?");
    }

    /**
     * 重复0-N次，等效于 {0,}。
     */
    public void repeatZeroOrMore() {
        //判断最外面是否是中括号,不是加上中括号
        sb = addMidBracketIfNo(sb);
        sb.append("*");
    }

    /**
     * 重复0或1次，等效于 {0,1}或?。
     */
    public void repeatZeroOrOne() {
        //判断最外面是否是中括号,不是加上中括号
        sb = addMidBracketIfNo(sb);
        sb.append("?");
    }

    /**
     * 重复1-N次，等效于 {1,}。
     */
    public void repeatOneOrMore() {
        //判断最外面是否是中括号,不是加上中括号
        sb = addMidBracketIfNo(sb);
        sb.append("+");
    }

    /**
     * 重复num次
     *
     * @param num 次数
     */
    public void repeat(int num) {
        //判断最外面是否是中括号,不是加上中括号
        sb = addMidBracketIfNo(sb);
        sb.append("{" + num + "}");
    }

    /**
     * 重复min-max次
     *
     * @param min 最小
     * @param max 最大
     */
    public void repeat(int min, int max) {
        //判断最外面是否是中括号,不是加上中括号
        sb = addMidBracketIfNo(sb);
        sb.append("{" + min + "," + max + "}");
    }

    /**
     * 至少重复num次
     *
     * @param num 次数
     */
    public void repeatMin(int num) {
        //判断最外面是否是中括号,不是加上中括号
        sb = addMidBracketIfNo(sb);
        sb.append("{" + num + ",}");
    }

    /**
     * 若字符串两边不是中括号增加上中括号
     *
     * @param sb 原StringBuffer
     * @return StringBuffer
     */
    private StringBuffer addMidBracketIfNo(StringBuffer sb) {
        if (!chkMidBracket(sb)) {
            return addMinBrackets(sb);
        } else {
            return sb;
        }
    }

    /**
     * 字符串两边加上()
     *
     * @param str 字符串
     * @return StringBuffer
     */
    private StringBuffer addMinBrackets(StringBuffer str) {
        return new StringBuffer("(" + str + ")");
    }

    /**
     * 字符串两边加上[]
     *
     * @param str 字符串
     * @return StringBuffer
     */
    private StringBuffer addMidBrackets(StringBuffer str) {
        return new StringBuffer("[" + str + "]");
    }

    /**
     * 去掉字符串两边的[]
     *
     * @param str 字符串
     * @return String
     */
    private String removeMidBrackets(StringBuffer str) {
        return str.toString().replaceAll("^\\[", "").replaceAll("\\]$", "");
    }

    /**
     * 对字符串里面的特殊字符进行处理
     *
     * @param str 源字符串
     * @return String
     */
    private String handleExpectChar(String str) {
        StringBuffer sbTemp = new StringBuffer();
        char[] arr = str.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (expectChar.indexOf(arr[i]) != -1) {
                sbTemp.append("\\" + arr[i]);
            } else {
                sbTemp.append(arr[i]);
            }
        }
        return sbTemp.toString();
    }

    /**
     * 判断字符串最外围是否为中括号
     *
     * @param sb
     * @return boolean 是 true，否则 false。
     */
    private boolean chkMidBracket(StringBuffer sb) {
        if ("[".equals(sb.substring(0, 1)) && "]".equals(sb.substring(sb.length() - 1))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 追加一个正则
     *
     * @param re 正则
     */
    public void append(RegexUtil re) {
        sb.append(re.toString());
    }

    /**
     * 追加一个正则表达式
     *
     * @param String 正则表达式
     */
    public void append(String re) {
        sb.append(handleExpectChar(re));
    }

    /**
     * 或一个正则
     *
     * @param re 正则
     */
    public void or(RegexUtil re) {
        or(re.toString());
    }

    /**
     * 或一个正则表达式
     *
     * @param String 正则表达式
     */
    public void or(String re) {
        //最外层为中括号
        if (chkMidBracket(sb)) {
            //首先去掉两边的中括号
            sb = new StringBuffer(removeMidBrackets(sb));
        }
        if (re.length() > 1) {
            //字符串用|
            sb.append("|" + handleExpectChar(re));
        } else {
            //非字符串直接追加
            sb.append(handleExpectChar(re));
        }
        //追加上中括号
        sb = new StringBuffer(addMidBrackets(sb));
    }

    /**
     * 对自己进行否处理
     */
    public void not() {
        sb = new StringBuffer("[^" + sb + "]");
    }

    /**
     * 返回正则表达式
     */
    public String toString() {
        return sb.toString();
    }

    /**
     * 工号
     * 组织架构也是这个（规则一样）
     */
    public static final String USER_NO_PATTERN = "^[_0-9a-zA-Z]+$";
    /**
     * 邮箱
     */
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    /**
     * 手机号
     */
    //public static final String MOBILE_PATTERN = "^((1[3-9]{1}[0-9]{1})+\\d{8})$";
    /**
     * 考虑到不全是中国手机号，所以用数字校验就行
     */
    public static final String MOBILE_PATTERN = "^\\d{10,20}$";
    /**
     * 身份证
     */
    public static final String ID_CARD_NO_PATTERN = "(^\\d{15}$)|(^\\d{17}([0-9]|X)$)";
    /**
     * 护照
     */
    public static final String PASSPORT_NO_PATTERN = "^1[45][0-9]{7}$|(^[P|p|S|s]\\d{7}$)|(^[S|s|G|g|E|e]\\d{8}$)|(^[Gg|Tt|Ss|Ll|Qq|Dd|Aa|Ff]\\d{8}$)|(^[H|h|M|m]\\d{8,10}$)";
    /**
     * 电话号码
     */
    public static final String TELEPHONE = "^(\\(\\d{3,4}\\)|\\d{3,4}-|\\s)?\\d{7,14}$";
    /**
     * 用户卡号
     */
    public static final String USER_CARD_NO_PATTERN = "^\\d{8,10}$";

    /**
     * 返回是否匹配对应的正则表达式
     */
    public static boolean commonMatch(final String str, final String pattern) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        return Pattern.compile(pattern).matcher(str).matches();
    }
    /**
     * 判断字符串是否为非负整数
     *
     * @param str
     * @return
     */
    public static boolean isNonnegativeInt(String str) {
        Pattern pattern = Pattern.compile("(^[0-9]*$)");
        Matcher isInt = pattern.matcher(str);
        if (!isInt.matches()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断字符串是否为非负数
     *
     * @param str
     * @return
     */
    public static boolean isNonnegativeDouble(String str) {
        Pattern pattern = Pattern.compile("(^[0-9]+([.]{1}[0-9]+){0,1}$)");
        Matcher isDouble = pattern.matcher(str);
        if (!isDouble.matches()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断字符串是否为金钱
     *
     * @param str
     * @return
     */
    public static boolean isMoney(String str) {
        if (str.charAt(str.length() - 1) == '.')
            str += "0";
        Pattern pattern = Pattern.compile("(^[1-9]([0-9]+)?(\\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\\.[0-9]([0-9])?$)");
        Matcher isMoney = pattern.matcher(str);
        if (!isMoney.matches()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断密码是否符合规范
     *
     * @param str
     * @return
     */
    public static boolean isPassword(String str) {
        Pattern pattern = Pattern.compile("(^[A-Za-z0-9]{6,16}$)");
        Matcher isPwd = pattern.matcher(str);
        if (!isPwd.matches()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 手机号是否合法(是否为11位的纯数字)
     *
     * @param phone
     * @return
     */
    public static boolean isValidPhone(String phone) {
//        String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
//        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
//        Matcher m = p.matcher(phone);
//        String regex1 = "\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}$";
//        Pattern p1 = Pattern.compile(regex1, Pattern.CASE_INSENSITIVE);
//        Matcher m1 = p1.matcher(phone);
//        return m.matches()||m1.matches();
        String regex = "[0-9]*";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(phone);
        return (m.matches() && (phone.length() == 11));
    }

    /**
     * 邮箱是否合法
     *
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 域名是否合法
     *
     * @param domain
     * @return
     */
    public static boolean isValidDomain(String domain) {
        String regex = "^(?=^.{3,255}$)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(domain);
        return m.matches();
    }
    /**
     * 域名是否合法
     *
     * @param ip
     * @return
     */
    public static boolean isValidIP(String ip) {
        String regex = "((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(ip);
        return m.matches();
    }

}