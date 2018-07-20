package cn.org.jcdp.common.utils;

import java.util.regex.Pattern;

/**
 * Test
 *
 * @author venson
 * @version 20180705
 */
public class Test {
    private static final Pattern lowerRegex = Pattern.compile("[a-z *_]*");
    public static void main(String[] args){
        String str="select *,- from test_student where ";
        String newStr=str.toLowerCase();
        System.out.println(str.equals(str));
        System.out.println(lowerRegex.matcher(str).matches());
    }
}

