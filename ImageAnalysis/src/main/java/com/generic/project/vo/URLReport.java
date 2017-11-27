package com.generic.project.vo;

/**
 * Maps a url to the number of images the linked html page contains.
 */
public class URLReport {

    public URLReport() {}

    public URLReport(String url , int count) {
        this.url = url;
        this.count = count;
    }

    private String url;
    private int count;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "URLReport{" +
                "url='" + url + '\'' +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        URLReport urlReport = (URLReport) o;

        if (count != urlReport.count) return false;
        return url.equals(urlReport.url);
    }

    @Override
    public int hashCode() {
        int result = url.hashCode();
        result = 31 * result + count;
        return result;
    }
}
