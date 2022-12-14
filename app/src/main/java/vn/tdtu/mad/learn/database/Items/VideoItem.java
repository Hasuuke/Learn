package vn.tdtu.mad.learn.database.Items;

public class VideoItem {
    private String name;
    private String author;
    private String type;
    private float duration;
    private float rating;
    private String videoID;


    public VideoItem(String name,String videoId, String author, String type, float duration, float rating) {
        this.name = name;
        this.author = author;
        this.type = type;
        this.duration = duration;
        this.rating = rating;
        this.videoID = videoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }



    public double getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String getVideoID() {
        return videoID;
    }
}
