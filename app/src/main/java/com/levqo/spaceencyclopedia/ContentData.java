package com.levqo.spaceencyclopedia;

/**
 * Created by user on 15.05.2018.
 */

public class ContentData {

    private String Name;
    private String Image;
    private String Description;
    private String Paragraph;

    public ContentData(){

    }

    public ContentData(String Name, String Image, String Description, String Paragraph) {
        this.Name = Name;
        this.Image = Image;
        this.Description = Description;
        this.Paragraph = Paragraph;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getParagraph() {
        return Paragraph;
    }

    public void setParagraph(String Paragraph) {
        this.Paragraph = Paragraph;
    }
}
