import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class NewsReport {


    public void readNews(String pathFileNews) {

        Path p = Paths.get(pathFileNews);
        String fileNameReport="raport"+System.currentTimeMillis()+".txt";
        Path pout = Paths.get(fileNameReport);
        String newsWholeContent="";
        try {
            newsWholeContent = new String(Files.readAllBytes(p));
        } catch (IOException e) {
            e.printStackTrace();
        }


        StringTokenizer st = new StringTokenizer(newsWholeContent, "~");
        String currentNews="";
        int nrNews=0;
        while (st.hasMoreTokens()) {
            currentNews=st.nextToken();
            currentNews=currentNews.toLowerCase().trim();
            nrNews++;


            Map<String, Integer> resultMap = processCurrentNews(currentNews);

            String fileContent="";
            for (Map.Entry<String,Integer> entry : resultMap.entrySet()) {
                System.out.println(entry.getKey() +
                        ":" + entry.getValue());

                fileContent = fileContent + entry.getKey() +
                        ":" + entry.getValue()+"\n";
            }
                byte[] strToBytes = fileContent.getBytes();

                try {
                    Files.write(pout, strToBytes, StandardOpenOption.CREATE);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                SendMail sm = new SendMail();
                sm.setToEmail("ionel.condor@gmail.com");
                sm.setFileName(fileNameReport);
                sm.sendEmail(); // sincron , blocant

            System.out.println("-------------------------------- DONE CURRENT NEWS");

        }

    }


    /*

     ana are mere si ana are pere are

     are 3
     are 2
     mere 1
     pere 1
     si 1



     */
    private Map processCurrentNews(String currentNews) {
        // System.out.println("current news"+currentNews);
        Map<String, Integer> myMap = new HashMap<>();
        StringTokenizer stCurrentNews= new StringTokenizer(currentNews);
        int nrWordsPerNews=0;
        while (stCurrentNews.hasMoreTokens()) {
            String tokenNews=stCurrentNews.nextToken();

            int index = tokenNews.lastIndexOf(',');
            if (index != -1)
                tokenNews = tokenNews.substring(0, index);

            index = tokenNews.lastIndexOf('.');
            if (index != -1)
                tokenNews = tokenNews.substring(0, index);


            nrWordsPerNews++;
            if(!myMap.containsKey(tokenNews)) {
                myMap.put(tokenNews, 1);
            }
            else {
                myMap.put(tokenNews, myMap.get(tokenNews)+1);
            }
        }

        Map<String,Integer> sortedMap= sortByValueDesc(myMap);

        return sortedMap;
    }


    // code adapted from https://www.geeksforgeeks.org/java-program-to-sort-a-hashmap-by-keys-and-values/?ref=rp
    private static HashMap<String, Integer> sortByValueDesc(Map<String, Integer> hm) {
        // Creating a list from elements of HashMap
        List<Map.Entry<String, Integer>> list
                = new LinkedList<Map.Entry<String, Integer>>(
                hm.entrySet());

        // Sorting the list using Collections.sort() method
        // using Comparator
        Collections.sort(
                list,
                new Comparator<Map.Entry<String, Integer>>() {
                    public int compare(
                            Map.Entry<String, Integer> object1,
                            Map.Entry<String, Integer> object2) {
                        return (object2.getValue())
                                .compareTo(object1.getValue());
                    }
                });

        // putting the  data from sorted list back to hashmap
        HashMap<String, Integer> result
                = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> me : list) {
            result.put(me.getKey(), me.getValue());
        }

        // returning the sorted HashMap
        return result;
    }


    public static void main(String[] args) {




    }
}
