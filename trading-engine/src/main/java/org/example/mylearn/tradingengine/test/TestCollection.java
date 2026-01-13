package org.example.mylearn.tradingengine.test;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.concurrent.ConcurrentSkipListSet;

public class TestCollection {

    private static class Fruit{
        public BigDecimal price;
        public String name;

        public Fruit(String name, BigDecimal price) {
            this.name = name;
            this.price = price;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public String getName() {
            return name;
        }
        public String toString() {
            return name +" : " + price;
        }
    }
    private static  void testArrayDeque(){
        // 初始化容量为4
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>(4);
        //添加元素
        System.out.println(arrayDeque.add(1));
        System.out.println(arrayDeque.add(2));
        System.out.println(arrayDeque.add(3));
        System.out.println(arrayDeque.add(4));
        System.out.println(arrayDeque.add(5));
        System.out.println(arrayDeque.add(6));

        arrayDeque.forEach(System.out::println);
    }
    private static  void testSet(){
        ConcurrentSkipListSet<TestCollection.Fruit> set = new ConcurrentSkipListSet<>(Comparator.comparing(TestCollection.Fruit::getPrice));
        var f1 = new TestCollection.Fruit("Apple", new BigDecimal("2.345"));
        set.add(f1);
        set.add(new TestCollection.Fruit("Orange", new BigDecimal("56")));
        set.add(new TestCollection.Fruit("Banana", new BigDecimal("4.56")));
        set.add(new TestCollection.Fruit("water", new BigDecimal("10")));
        set.forEach(i->{
            System.out.println("name=%s, price=%s".formatted(i.name, i.price));
        });
//        System.out.println(set.toString());
        var fruit2 = new Fruit("Apple", new BigDecimal("56.00")); // true
        System.out.println(set.contains(fruit2));
        System.out.println(set.equals(fruit2));
        System.out.println(set.equals(f1));


        System.out.println("test ceiling(), higher():");
        var fruit3 = new Fruit("mock", new BigDecimal("4.56")); // true
        System.out.println(set.ceiling(fruit3));
        System.out.println(set.higher(fruit3));

        System.out.println("test for first(), last(), poll");
        System.out.println(set.first()); // 不删除
        System.out.println(set.last() + " end");
        //System.out.println(set.pollFirst()); // poll()会删除元素
        System.out.println(set.getFirst()); // poll()会删除元素
        System.out.println("set ==");
        set.forEach(System.out::println);

        System.out.println("test for remove():");
        set.remove(fruit3);
        set.forEach(System.out::println);

        System.out.println("test for subSet():");
        var fruit4 = new Fruit("mock", new BigDecimal("11"));
        var subSet =  set.subSet(fruit4, true, fruit4, true);
        System.out.println(subSet);
        System.out.println(subSet.pollFirst());

        subSet.forEach(System.out::println);


        var fruit5 = new Fruit("mock", new BigDecimal("10"));
        System.out.println("before headset() set = ");
        set.forEach(System.out::println);
        System.out.println("subSet() return set = ");
        set.subSet(fruit5, true, fruit5, true).forEach(System.out::println);
        System.out.println("after headset() set = ");
        set.forEach(System.out::println);




    }
    public static void main(String[] args) {
                //testArrayDeque();
        testSet();
    }


}
