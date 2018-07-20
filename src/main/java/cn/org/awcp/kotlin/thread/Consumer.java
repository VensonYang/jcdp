package cn.org.awcp.kotlin.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * Consumer
 *
 * @author venson
 * @version 20180709
 */
public class Consumer {

    public static void main(String[] args){
        ProductPool productPool=new ProductPool();
        ExecutorService executors = Executors.newFixedThreadPool(5);
        for(int j=0;j<5;j++){
            executors.execute(()->{
                for(int i=0;i<10;i++){
                    Product product = productPool.get();
                    System.out.println(product.getId());
                }
            });
        }
        executors.shutdown();


    }
}
