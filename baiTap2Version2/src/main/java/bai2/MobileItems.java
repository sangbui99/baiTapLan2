package bai2;

public class MobileItems {

    String contentTarget;
    String imageUrl;
    String title;

    public MobileItems() {
    }

    public MobileItems(String contentTarget, String imageUrl, String title) {
        this.contentTarget = contentTarget;
        this.imageUrl = imageUrl;
        this.title = title;
    }

    public String getContentTarget() {
        return contentTarget;
    }

    public void setContentTarget(String contentTarget) {
        this.contentTarget = contentTarget;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "MobileItems  {" +
                "contentTarget='" + contentTarget + '\'' +
                ",    imageUrl='" + imageUrl + '\'' +
                ",    title='" + title + '\'' +
                '}';
    }
}
