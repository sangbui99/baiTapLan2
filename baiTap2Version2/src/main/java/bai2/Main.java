package bai2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<MobileItems> list = crawl();
        String formattedDate = formatTime();
//        String formattedDate2 = "E:/new_code/Documents/" + formattedDate + ".txt";
//        FileWriter(list, formattedDate2);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + "   ContentTarget: " + list.get(i).getContentTarget());
            System.out.println(new String("      Ảnh: ".getBytes(StandardCharsets.UTF_8)) + list.get(i).getImageUrl());
            System.out.println("      Title: " + list.get(i).getTitle());
            System.out.println();
        }
    }

    public static void FileWriter(List<MobileItems> list, String formattedDate) {
        try {
//          append = true để thêm dữ liệu mà ko ghi đè
            FileWriter fw = new FileWriter(formattedDate);
            BufferedWriter bw = new BufferedWriter(fw);
            for (MobileItems mobileItems : list) {
                bw.write(mobileItems.toString());
                bw.newLine();
            }

            bw.close();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void FileReader() {
        List<MobileItems> arr = new ArrayList<>();
        try {
//            FileReader fr = new FileReader("Obj.data.txt");
            FileReader fr = new FileReader("E:\\new_code\\Documents\\2022_08_01_18_01_30.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(";");
                MobileItems staff = new MobileItems(txt[0], txt[1], txt[2]);
                arr.add(staff);
//                System.out.println(line);
            }
            for (MobileItems staff : arr) {
                System.out.println(staff.toString());
            }

            br.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String formatTime() {
        LocalDateTime myObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");

        String formattedDate = myObj.format(myFormatObj);
        return formattedDate;
    }

    public static List<MobileItems> crawl() throws IOException {
        ArrayList<MobileItems> listMobiles = new ArrayList<>();

        Document doc = Jsoup.connect("https://dantri.com.vn/").timeout(5000).get();

        Elements elements = doc.getElementsByClass("article-item");

        for (Element e : elements) {
            MobileItems item = new MobileItems();
            item.setContentTarget(e.attr("data-content-target"));

            item.setImageUrl(e.child(0).child(0).child(0).attr("data-src"));
            if (item.getImageUrl() == "") {
                item.setImageUrl(e.child(0).child(0).child(0).attr("src"));
            }
            item.setTitle(e.child(0).child(0).child(0).attr("alt"));

            try {
                item.setImageUrl(e.child(0).child(1).child(0).child(0).attr("src"));
                item.setTitle(e.child(0).child(1).child(0).child(0).attr("alt"));
            } catch (Exception e1) {

            }
            listMobiles.add(item);
        }

        //Chuyển listMobiles thành JSON text
//        Gson gson = new Gson();
//        String jsonData = gson.toJson(listMobiles);
//
//        System.out.println(jsonData);
//        for (int i = 0; i < listMobiles.size(); i++) {
//            System.out.println(i + "   ContentTarget: " + listMobiles.get(i).getContentTarget());
//            System.out.println("      Ảnh: " + listMobiles.get(i).getImageUrl());
//            System.out.println("      Title: " + listMobiles.get(i).getTitle());
//            System.out.println();
//        }
        return listMobiles;
    }
}
