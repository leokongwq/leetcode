package com.leokongwq.jdk8;

import sun.misc.Unsafe;

import java.time.Clock;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.*;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/12/24
 * Time: 上午9:33
 * Email:leokongwq@gmail.com
 */
public class StreamTest {


    private static void testStream(){
        final Collection<Integer> integers = Arrays.asList(3, 1, 4, 8, 5);
        Collection<Integer> result = integers.stream().filter(num -> num >= 5).sorted().collect(Collectors.toList());
        System.out.println(result);
    }

    private static void testStreamParallel(){
        final Collection<Person> persons = Arrays.asList(new Person(23), new Person(28), new Person(30));
        //int sum = persons.stream().parallel().map(p -> p.age).reduce(0, Integer::sum);
        int sum = persons.stream().parallel().map(p -> p.age).reduce(0, (a, b) -> a + b);
        System.out.printf("sumed age is %d \n", sum);
    }

    private static void testStreamGroup(){
        final Collection<Person> persons = Arrays.asList(new Person(23), new Person(28), new Person(30));
        System.out.println(persons.stream().collect(Collectors.groupingBy(p -> p.age)));
    }

    /**
     * 3-9-1 problem 1
     * @param stream
     * @return
     */
    private static int addUp(Stream<Integer> stream){
        Objects.requireNonNull(stream, "parameter stream can not be null");
        return stream.reduce(0, (result, num) -> result + num);
    }

    /**
     * 3-9-2 problem 2
     * @param artists
     * @return
     */
    private static List<String> getArtistNameAndNations(List<Artist> artists){
        Objects.requireNonNull(artists, "artists can not be null");
        return artists.stream().map(artist -> "[" + artist.name + " from " +artist.origin + "]").collect(Collectors.toList());
    }

    /**
     * 3-9-1-3 problem 3
     * @param albums
     */
    private static List<Album> getLessThan3TracksAlbums(List<Album> albums){
        Objects.requireNonNull(albums, "albums can not be null");
        return albums.stream().filter(album -> album.getTrackers().size() <= 3).collect(Collectors.toList());
    }

    /**
     * 3-9-2-1
     */
    private static void convertOuterIteratorIntoInner(List<Artist> artists){
//        int totalMembers = 0;
//        for (Artist artist : artists){
//            Stream<String> members = artist.getMembers();
//            totalMembers += members.count();
//        }
        int totalMembers = artists.stream().flatMap(artist -> artist.getMembers()).collect(Collectors.toList()).size();
        System.out.printf("totalMembers = %d \n", totalMembers);
    }

    private static long countLowerCaseCharsInString(String string){
        return string.chars().filter(Character::isLowerCase).count();
    }

    private static void test(){
        List<String> strings = Arrays.asList("hello", "world");
        AtomicInteger integer = new AtomicInteger(0);
        strings.stream().forEach(s -> {
            System.out.println(s);
            integer.incrementAndGet();
        });
    }

    /**
     * 查找含有最多小写字母的字符串
     * @param strings
     * @return
     */
    private static Optional<String> findHasMostLowerCaseCharacterString(List<String> strings){
        if (strings == null || strings.isEmpty()){
            return Optional.empty();
        }
        return strings.stream().max(Comparator.comparing(StreamTest::countLowerCaseCharsInString));
    }

    public static void main(String[] args) {
        testStream();
        testStreamParallel();
        testStreamGroup();

        //3-9-1
        System.out.println(addUp(Arrays.asList(1, 2, 3).stream()));

        //3-9-2
        Artist lennon = new Artist("John Lennon", Arrays.asList("java", "c++"), "America");
        Artist eagles = new Artist("eagles", Arrays.asList("Don Henley", "Glen Frey"), "America");

        System.out.println(getArtistNameAndNations(Arrays.asList(lennon, eagles)));

        convertOuterIteratorIntoInner(Arrays.asList(lennon, eagles));

        System.out.println(countLowerCaseCharsInString("Hello"));

        System.out.println(findHasMostLowerCaseCharacterString(Arrays.asList("Hello", "java")));

        System.out.println(Stream.of("1", "2").map(str -> Integer.parseInt(str)).collect(Collectors.toList()));

        System.out.println(Stream.of("1", "2").mapToInt(str -> Integer.parseInt(str)).sum());

        System.out.println(Stream.of("123", "456").flatMap(str -> str.chars().boxed()).count());

        int sum = Stream.of("123", "456").flatMap(str -> str.chars().boxed()).reduce((init, num) -> init + num).get();

        System.out.println(sum);

        System.out.println(Stream.of(1, 2, 3).reduce(0, (res, num) -> res + num));

        System.out.println(Stream.of(1, 2, 3, 4).parallel().reduce(0, Integer::sum, Integer::sum));

        long start = Clock.systemUTC().millis();
        long intSum = LongStream.range(0, 10000000).reduce(Long::sum).getAsLong();
        long end = Clock.systemUTC().millis();
        System.out.printf("serial time cost %d, \n", (end - start));

        intSum = LongStream.range(0, 10000000).parallel().reduce(0, Long::sum);
        long end1 = Clock.systemUTC().millis();
        System.out.printf("parallel time cost %d \n", (end1 - end));

        System.out.println(Stream.of(1, 2, 3, 2).distinct().count());

        System.out.println(Stream.of(1,4,2,3).sorted().collect(Collectors.toList()));

        System.out.println(Stream.of("aaa", "be").sorted((a, b) -> a.length() - b.length()).collect(Collectors.toList()));


        Stream.of(1, 2, 3).peek(num -> System.out.println(num)).count();

        int[] arr = new int[10000];
        Random random = new Random(System.currentTimeMillis());
        Arrays.parallelSetAll(arr, i -> random.nextInt(10000));

        Arrays.stream(arr).sorted().skip(10).limit(5);

        IntStream.range(1, 10).forEach(num -> {
            System.out.print(Thread.currentThread().getName() + "-->");
            System.out.println(num);
        });

        System.out.println(Stream.of(1, 2, 3).toArray(Integer[]::new));

        System.out.println(Stream.of(1, 2, 3).collect((Supplier<ArrayList<Integer>>)ArrayList::new, (left, integer) -> left.add(integer), (left, right) -> left.addAll(right)));


        //System.out.println(Stream.of(1, 2, 3).collect(new MyListCollector()));

        Optional.of("hello").ifPresent(value -> System.out.println(value));

        testAtomicLong();
    }

    static class MyListCollector implements Collector<Object, ArrayList, List> {

        @Override
        public Supplier<ArrayList> supplier() {
            return ArrayList::new;
        }

        @Override
        public BiConsumer<ArrayList, Object> accumulator() {
            return (container, element) -> {container.add(element);};
        }

        @Override
        public BinaryOperator<ArrayList> combiner() {
             return (left, right) -> {
                 left.addAll(right); return left;
             };
        }

        @Override
        public Function<ArrayList, List> finisher() {
            return (arrayList) -> new ArrayList(arrayList);
        }

        @Override
        public Set<Characteristics> characteristics() {
            return null;
        }
    }

    static class Person {
        private int age;

        public Person(int age) {
            this.age = age;
        }
    }

    static class Tracker {
        private String name;

        public Tracker(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static class Album {
        private List<Tracker> trackers;

        public Album(List<Tracker> trackers) {
            this.trackers = trackers;
        }

        public List<Tracker> getTrackers() {
            return trackers;
        }
    }

    static class Artist {

        private String name;

        private List<String> members;

        private String origin;

        public Artist(String name, List<String> members, String origin) {
            this.name = name;
            this.members = members;
            this.origin = origin;
        }

        public Stream<String> getMembers() {
            return members.stream();
        }
    }

    public static void testAtomicLong(){
        AtomicLong seqNum = new AtomicLong(1);
        long id = seqNum.getAndIncrement();

        Unsafe unsafe = Unsafe.getUnsafe();
        try {
            final long valueOffset = unsafe.objectFieldOffset(AtomicLong.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
