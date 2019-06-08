package club.p9j7.model;

import java.time.LocalDateTime;

public class SinaNews {
    private Integer id;
    private String title;
    private LocalDateTime newsTime;
    private String content;
    private String keyword;

    public SinaNews() {
    }

    public SinaNews(Integer id, String title, LocalDateTime localDateTime, String content, String keyword) {
        this.id = id;
        this.title = title;
        this.newsTime = localDateTime;
        this.content = content;
        this.keyword = keyword;
    }

    public LocalDateTime getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(LocalDateTime newsTime) {
        this.newsTime = newsTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "SinaNews{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", newsTime=" + newsTime +
                ", content='" + content + '\'' +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
