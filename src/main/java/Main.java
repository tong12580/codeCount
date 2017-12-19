import util.FileUtil;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author yutong
 * @version 1.0
 * @description
 * @since 2017/12/19 21:19
 */
public class Main {
    private static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        if (0 == args.length) {
            logger.warning("无数据");
        } else {
            List<String> argList = Arrays.asList(args);
            argList.parallelStream().forEach(str -> {
                try {
                    List<String> file = FileUtil.read(str, null);
                    String path = Paths.get(str).getFileName().toString();
                } catch (IOException e) {
                    logger.severe(e.getLocalizedMessage());
                }
            });
        }
    }
}
