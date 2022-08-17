package bai3_4_5;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Controller
public class PrimeBai3_4_5 {
    private static Map<Integer, List<Integer>> database = new HashMap<>();
    private static LoadingCache<Integer, List<Integer>> primeCache = null;

    @RequestMapping(value = "/prime", method = RequestMethod.GET)
    @ResponseBody
    public List<Integer> createPrime(@RequestParam Integer n) throws ExecutionException {

        if (primeCache == null) {
            createCache();
        }

        for (Map.Entry<Integer, List<Integer>> d : database.entrySet()) {
            if (d.getKey().equals(n)) {
                return primeCache.get(n);
            }
        }

        List<Integer> list = Bai3.prime(n);
        database.put(n, list);
        return primeCache.get(n);
    }

    private static LoadingCache<Integer, List<Integer>> createCache() {
         primeCache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .expireAfterAccess(20, TimeUnit.MINUTES) // sau 30p khi ghi phần tử sẽ tự xóa
                .expireAfterWrite(10, TimeUnit.MINUTES) // sau 25p ko có request đọc phần tử sẽ tự xóa
                .build(new CacheLoader<Integer, List<Integer>>() {
                    @Override
                    public List<Integer> load(Integer empId) throws Exception {

                        return database.get(empId);
                    }
                });
        return primeCache;
    }

}
