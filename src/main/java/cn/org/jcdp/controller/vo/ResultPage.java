package cn.org.jcdp.controller.vo;

import java.io.Serializable;
import java.util.Collection;

/**
 * ResultPage
 *
 * @author venson
 * @version 20180704
 */
public class ResultPage implements Serializable {
    private static final long serialVersionUID = -4602491386476403637L;
    private Object content;
    private long total;
    private int offset;
    private int limit;

    private <T extends Collection> ResultPage(T content, long total) {
        this.content = content;
        this.total = total;
    }

    private <T extends Collection> ResultPage(T content, long total, int offset, int limit) {
        this.content = content;
        this.total = total;
        this.offset = offset;
        this.limit = limit;
    }

    public static <T extends Collection> ResultPage of(Collection<T> content, long total) {
        return new ResultPage(content, total);
    }

    public static <T extends Collection> ResultPage of(Collection<T> content, long total, int offset, int limit) {
        return new ResultPage(content, total,offset,limit);
    }

    public Object getContent() {
        return content;
    }

    public <T extends Collection> void setContent(T content) {
        this.content = content;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
