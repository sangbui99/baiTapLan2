package bai3_4_5;

import java.util.ArrayList;
import java.util.List;

public class Bai3 {
    public static List<Integer> prime(Integer n){
        List<Integer> list = new ArrayList<>();
        int a = 0;
        list.add(1);
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    a = 1;
                    break;
                }
            }
            if (a == 0) list.add(i);
            else a = 0;
        }
        return list;
    }
}
