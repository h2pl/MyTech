package com.javase.Java8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 周杰伦 on 2018/5/6.
 */
public class 流 {
    @Test
    public void test() {
//        Stream
//        Stream可以分成串行流和并行流，并行流是基于Java7中提供的ForkJoinPool来进行任务的调度，达到并行的处理的目的。 集合是我们平时在进行Java编程时非常常用的API，使用Stream可以帮助更好的来操作集合。Stream提供了非常丰富的操作，包括筛选、切片、映射、查找、匹配、归约等等， 这些操作又可以分为中间操作和终端操作，中间操作会返回一个流，因此我们可以使用多个中间操作来作链式的调用，当使用了终端操作之后，那么这个流就被认为是被消费了， 每个流只能有一个终端操作。

//筛选后收集到一个List中
        List apples = new ArrayList();
        Object vegetarianMenu = apples.stream().filter(Apple::isRed).collect(Collectors.toList());

//筛选加去重
        List<Integer> numbers = Arrays.asList(1,2,1,3,3,2,4);
        numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);
    }


}
class Apple {
    public static boolean isRed() {
        return false;
    }

    public static boolean isRed(Object o) {
        return true;
    }
}

class Streams  {
    @Test
    public void test() {

        final Collection< Task > tasks = Arrays.asList(
                new Task( Status.OPEN, 5 ),
                new Task( Status.OPEN, 13 ),
                new Task( Status.CLOSED, 8 )
        );
        // Calculate total points of all active tasks using sum()
        final long totalPointsOfOpenTasks = tasks
                .stream()
                .filter( task -> task.getStatus() == Status.OPEN )
                .mapToInt( Task::getPoints )
                .sum();

        System.out.println( "Total points: " + totalPointsOfOpenTasks );
    }
    private enum Status {
        OPEN, CLOSED
    };

    private static final class Task {
        private final Status status;
        private final Integer points;

        Task( final Status status, final Integer points ) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return points;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return String.format( "[%s, %d]", status, points );
        }
    }
}