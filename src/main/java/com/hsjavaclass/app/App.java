package com.hsjavaclass.app;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            System.gc();
            Thread.sleep(10);
            long before = Runtime.getRuntime().freeMemory();
            int num = 1;
            long after = Runtime.getRuntime().freeMemory();
            System.out.println(before - after);

            System.gc();
            Thread.sleep(10);
            before = Runtime.getRuntime().freeMemory();
            int[] array = new int[5];
            after = Runtime.getRuntime().freeMemory();
            System.out.println(before - after);

            System.gc();
            Thread.sleep(10);
            before = Runtime.getRuntime().freeMemory();
            Object thing = new Object();
            after = Runtime.getRuntime().freeMemory();
            System.out.println(before - after);

            System.gc();
            Thread.sleep(10);
            before = Runtime.getRuntime().freeMemory();
            String word = "hello hgf";
            after = Runtime.getRuntime().freeMemory();
            System.out.println(before - after);
        } catch(Exception e) {

        }
    }
}
