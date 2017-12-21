package util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件处理类
 *
 * @author yutong
 * @version 1.0
 * @description
 * @since 2017/12/19 21:14
 */
public class FileUtil {
    /**
     * 文件讀取 一次讀取一行 適合打文件讀取
     *
     * @param path     {@link String}
     * @param charsets {@link Charset}
     * @return context {@link List <String>} 返回文件內容
     */
    public static List<String> read(String path, Charset charsets) throws IOException {
        if (null == charsets) {
            charsets = StandardCharsets.UTF_8;
        }
        return Files.readAllLines(Paths.get(path), charsets);
    }

    /**
     * 文件讀取 一次讀取一行 并轉化為String 且在末尾添加換行符 適合打文件讀取
     *
     * @param path     {@link String}
     * @param charsets {@link Charset}
     * @return context {@link String} 返回文件內容
     */
    public static String readFile(String path, Charset charsets) throws IOException {
        List<String> contentLines = read(path, charsets);
        StringBuilder context = new StringBuilder();
        final String finalNewline = System.getProperty("line.separator");
        contentLines.forEach(contentLine -> context.append(contentLine).append(finalNewline));
        return context.toString();
    }

    /**
     * 文件寫 按行寫文件
     *
     * @param path    路径 {@link String}
     * @param context 内容 {@link List<String>}
     * @return path 路径 {@link String}
     */
    public static String writeByLine(String path, List<String> context) throws IOException {
        Files.write(Paths.get(path), context, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        return path;
    }

    /**
     * 文件寫 字節流寫入
     *
     * @param path    路径 {@link String}
     * @param context 内容 {@link Charset}
     * @return path 路径 {@link String}
     */
    public static String writeByBytes(String path, String context) throws IOException {
        byte[] contentBytes = context.getBytes(StandardCharsets.UTF_8);
        Files.write(Paths.get(path), contentBytes, StandardOpenOption.CREATE);
        return path;
    }

    /**
     * 获取某一路径下所有文件夹的路径
     *
     * @param path 路径
     * @return List<String>
     */
    public static List<String> getFilesPath(List<String> paths, String path) {
        File[] files = Paths.get(path).toFile().listFiles();
        if (null == files || 0 == files.length) {
            return paths;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                getFilesPath(paths, file.getPath());
            }
            if (file.isFile()) {
                paths.add(file.getPath());
            }
        }
        return paths;
    }
}