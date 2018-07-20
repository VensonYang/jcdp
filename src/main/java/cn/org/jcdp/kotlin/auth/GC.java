package cn.org.jcdp.kotlin.auth;

public class GC {
  
    public static GC SAVE_HOOK = null;  
  
    public static void main(String[] args) throws InterruptedException {  
        SAVE_HOOK = new GC();
        System.out.println(SAVE_HOOK);
        SAVE_HOOK = null;
        System.out.println(SAVE_HOOK);
        System.gc();
        Thread.sleep(500);
        //此时对象应该处于(reachable, finalized)状态
        if (null != SAVE_HOOK) {
            System.out.println(SAVE_HOOK);
            System.out.println("Yes , I am still alive");
        } else {
            System.out.println("No , I am dead");
        }
        System.out.println(SAVE_HOOK);
        SAVE_HOOK = null;
        System.out.println(SAVE_HOOK);
        System.gc();
        System.out.println(SAVE_HOOK);
        Thread.sleep(500);
        if (null != SAVE_HOOK) {  
            System.out.println("Yes , I am still alive");  
        } else {  
            System.out.println("No , I am dead");  
        }  
    }  
  
    @Override  
    protected void finalize() throws Throwable {  
        super.finalize();  
        System.out.println("execute method finalize()"+this);
        SAVE_HOOK = this;  
    }  
} 