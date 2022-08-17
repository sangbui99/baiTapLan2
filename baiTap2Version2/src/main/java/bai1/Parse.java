package bai1;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Parse implements Runnable {
    int a = 0;
    User usePre;
    WriteLog writeLog = new WriteLog();

    @Override
    public void run() {
        User user = resultUrl();
        if (usePre == null) {
// ghi log cho user INFO
            writeLog.visit(user, "INFO");
            usePre = user;
            a = 0;
        } else if (((float) (user.getUser() - usePre.getUser()) / (float) usePre.getUser()) > 0.05) {
// ghi log cho user INFO
            writeLog.visit(user, "INFO");
            usePre = user;
            a = 0;
        } else if (a == 5) {
//  ghi log cho user là DEBUG
            writeLog.visit(user, "DEBUG");
            usePre = user;
            a = 0;
        } else {
            a++;
        }
    }


    private static User resultUrl() {
        String json = null;
        try {
            json = readUrl("http://news.admicro.vn:10002/api/realtime?domain=kenh14.vn");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        return user;
    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

}
