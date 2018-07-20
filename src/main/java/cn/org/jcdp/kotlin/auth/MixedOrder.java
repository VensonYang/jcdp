package cn.org.jcdp.kotlin.auth;

public class MixedOrder{
    int a = 0;
    boolean flag = false;
    public void writer(){
        a++;
        flag = true;
    }

    public void read(){
        if(flag){
            System.out.println(a);
        }
    }

    public static void main(String[] args){
        MixedOrder mixedOrder=new MixedOrder();
        mixedOrder.read();
        mixedOrder.writer();
        mixedOrder.read();
    }
}