package cn.org.awcp.kotlin


/**
 * HelloWorld
 *
 * @author venson
 * @version 20180707
 */
fun main(args: Array<String>) {
    var a = 1
    // 使用变量名作为模板:
    val s1 = "a is $a"

    a = 2
    // 使用表达式作为模板:
    val s2 = "${s1.replace("is", "was")}, but now is $a"
    println(s2)
    println("hello world");
}