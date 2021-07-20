package com.example.study;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class IntellijDebugTest {

    @Test
    public void test() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
