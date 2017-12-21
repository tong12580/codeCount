package util;

/**
 * 结果集收集类
 *
 * @author yutong
 * @version 1.0
 * @description
 * @since 2017/12/19 23:44
 */
public class Result {
    private String file;
    private Integer total;
    private Integer empty;
    private Integer effective;
    private Integer comment;

    public String getFile() {
        return file;
    }

    public Result setFile(String file) {
        this.file = file;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public Result setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public Integer getEmpty() {
        return empty;
    }

    public Result setEmpty(Integer empty) {
        this.empty = empty;
        return this;
    }

    public Integer getEffective() {
        return effective;
    }

    public Result setEffective(Integer effective) {
        this.effective = effective;
        return this;
    }

    public Integer getComment() {
        return comment;
    }

    public Result setComment(Integer comment) {
        this.comment = comment;
        return this;
    }

    @Override
    public String toString() {
        return "file:" + file + " "
                + "total:" + total + " "
                + "empty:" + empty + " "
                + "effective:" + effective + " "
                + "comment:" + comment;
    }
}