package org.example.mylearn.tradingengine.test;

import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class DosomeTest {
    public static void main(String[] args) {
        //basicMapTest();
        //advancedMapTest();
        //testThread();
        testCollection();
    }

    private static void advancedMapTest(){
        Map<String,HashMap<String, String>> map = new HashMap<>();
        HashMap<String,String> map1 = new HashMap<>();
        map1.put("map1-key1", "map1-value1");
        map1.put("map1-key2", "map1-value2");
        map1.put("map1-key3", "map1-value3");
        map1.put("map1-key4", "map1-value4");
        map.put("KEY1", map1);
        HashMap<String,String> map2 = new HashMap<>();
        map2.put("map2-key1", "map2-value1");
        map2.put("map2-key2", "map2-value2");
        map2.put("map2-key3", "map2-value3");
        map.put("KEY2", map2);

        System.out.println("====method1:");
        map.forEach((k,v)->{System.out.println(k);System.out.println(v);});

        System.out.println("====method2:");

        map.forEach((k,v)->{
                v.forEach((k1,v1)->{System.out.println(v1);}
                );
            }
        );

        System.out.println("====method3:");
        for(var v : map.values()){
            System.out.println(v.values());
            for(String v2 : v.values()){
                System.out.println(v2);
            }

        }

        System.out.println("====method4:");
        List<String> list = new ArrayList<>();
        for(var v : map.values()){
//            v.values().forEach(list::add);
//            v.values().forEach(v1->{list.add(v1);});
            list.addAll(v.values());

        }
        System.out.println(list);


    }

    private static void basicMapTest() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        System.out.println(map.values());
        System.out.println(map.entrySet());
        System.out.println(map.keySet());
        for(var entry : map.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("map.forEach():");
        map.forEach((k,v)->System.out.println(k + " : " + v));
    }

    private static void testThread(){

        System.out.println("====testThread:");
        System.out.println("Main thread: "+ Thread.currentThread().getName());
        Thread t1 = new Thread(()->{
            System.out.println("Runnable执行: " + Thread.currentThread().getName());
        }, "Thread-123");
       t1.start();

        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("Runnable执行: " + Thread.currentThread().getName());
            }
        }, "Runnable-124");
        t2.start();
    }

    private static void testCollection(){
        System.out.println("====testCollection:");

        var set1 = new TreeSet<Integer>();
        set1.add(30);
        set1.add(2);
        set1.add(10);
        set1.add(8);
        System.out.println("set1: " + set1);

        /*System.out.println("Test Itrator:");
        var iterator = set1.descendingIterator();
        while (iterator.hasNext()){
            var e =  iterator.next();
            if(e.equals(10)){
                System.out.println("remove: " + e);
                iterator.remove();
                iterator.forEachRemaining(el->{System.out.println(el);});
                continue;
            }
        }*/

        System.out.println("first= " + set1.first());
        System.out.println("last= " + set1.last());
        System.out.println("Test stream ");

        var s =  set1.stream().filter(e->!e.equals(8)).collect(Collectors.toList());
        System.out.println("s: " + s.getClass() + " " + s);

        System.out.println("======Test Map");
        var map = new HashMap<Integer,String >();
        var v = map.computeIfAbsent(1, k->String.valueOf(k));
        System.out.println("map: " + map + " v= " + v);

        var map2 = new TreeMap<Integer,List<Integer>>();
        var v2 = map2.computeIfAbsent(1, k -> new ArrayList<Integer>());
        v2.add(10);
        var v3 = map2.computeIfAbsent(1, k -> new ArrayList<Integer>());
        v3.add(20);
        System.out.println("map: " + map + " v2= " + v2 +  " v3= " + v3);

        System.out.println("Test Map order");

//        TreeMap<DosomeTest.person, String> personMap = new TreeMap<>(
//                (s1,s2)-> s1.age.compareTo(s2.age));

        TreeMap<person, String> personMap = new TreeMap<>(new Comparator<person>() {
            @Override
            public int compare(person o1, person o2) {
                int r = o1.age.compareTo(o2.age);
                if(r != 0) return r;
                return o1.name.compareTo(o2.name) ;
            }
        });
        personMap.put(new person("zhang", 24, BigDecimal.valueOf(34.56)), "zhang");
        personMap.put(new person("wang", 30, BigDecimal.valueOf(34.56)), "wang");
        personMap.put(new person("li", 15, BigDecimal.valueOf(34.56)), "li");
        personMap.put(new person("zhao", 30, BigDecimal.valueOf(34.56)), "zhao");

        var gson = new Gson();
        var it = personMap.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            System.out.println(gson.toJson(entry.getKey()) + " : " + entry.getValue());
        }


        person p1 = new person();
        person p2 = new person();
        System.out.println("Integer.toHexString(p1.hashCode()): 0x" + Integer.toHexString(p1.hashCode()));
        System.out.println("Integer.toHexString(p2.hashCode()): 0x" + Integer.toHexString(p2.hashCode()));
        System.out.println("p1.equals(p2): " + p1.equals(p2));
        System.out.println("p1.equals(p1): " + p1.equals(p1));
    }

    private static class person{
        public String name;
        public Integer age;
        public BigDecimal weight;

        public person() {
        }

        public person(String name, Integer age, BigDecimal weight) {
            this.name = name;
            this.age = age;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object obj) {
            System.out.println("call " + this.getClass().getSimpleName() + ".equals()");
            return super.equals(obj);
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public BigDecimal getWeight() {
            return weight;
        }

        public void setWeight(BigDecimal weight) {
            this.weight = weight;
        }
    }
}
