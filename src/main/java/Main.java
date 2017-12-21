import util.CommonUtil;
import util.FileUtil;
import util.Result;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    private static void handleFile(final String str) {
        Result result = new Result();
        List<String> file = null;
        try {
            file = FileUtil.read(str, null);
        } catch (IOException e) {
            logger.severe(e.getLocalizedMessage());
        }
        int empty = 0;
        int comment = 0;
        int effective = 0;
        int temp = 0;
        int index = 0;
        if (null == file) {
            logger.info(result.toString());
            return;
        }
        for (String content : file) {
            if (CommonUtil.isBlank(content)) {
                empty++;
                if (temp != 0) {
                    temp++;
                }
            }
            if (content.contains("//")) {
                comment++;
                if (!CommonUtil.isBlank(content.substring(0, content.indexOf("//")))) {
                    effective++;
                }
            }
            if (content.contains("/*")) {
                comment++;
                temp = index;
            }
            if (content.contains("*/")) {
                int end = index - temp;
                temp = 0;
                comment = comment + end;
            }
            index++;
        }
        effective = effective + file.size() - empty - comment;
        result.setFile(Paths.get(str).getFileName().toString())
                .setTotal(file.size())
                .setComment(comment)
                .setEffective(effective)
                .setEmpty(empty);
        logger.info(result.toString());
    }

    public static void main(String[] args) {
        if (0 == args.length) {
            logger.warning("Not for the system to any path; 没有为系统传入任何路径或文件");
        } else {
            List<String> argList = Arrays.asList(args);
            argList.parallelStream().forEach(str -> {
                if (Paths.get(str).toFile().isDirectory()) {
                    List<String> files = FileUtil.getFilesPath(new ArrayList<>(), str);
                    if (!CommonUtil.isEmpty(files)) {
                        files.parallelStream().forEach(Main::handleFile);
                    }
                } else {
                    handleFile(str);
                }
            });
        }
    }
}