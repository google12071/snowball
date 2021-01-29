package csv;

import org.developer.learn.common.util.CSVUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CSVUtilTest
 * @Description:
 * @Author lfq
 * @Date 2021/1/29
 **/
public class CSVUtilTest {
    private static final String[] headers = {"userId", "userName"};

    private static final Map<String, String> dataMap = new HashMap<>();

    static {
        dataMap.put("Dan Simmons", "Hyperion");
        dataMap.put("Douglas Adams", "The Hitchhiker's Guide to the Galaxy");
    }


    @Test
    public void readFile() {
        String fileName = this.getClass().getResource("/test.csv").getPath();
        try {
            CSVUtil.readCSV(fileName, headers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void writeCSV() throws IOException {
        String fileName = this.getClass().getResource("/demo.csv").getPath();
        CSVUtil.writeCSV(fileName, new String[]{"name", "desc"}, dataMap);
    }
}
