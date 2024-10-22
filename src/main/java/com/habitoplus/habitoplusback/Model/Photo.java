package com.habitoplus.habitoplusback.Model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.annotation.Id;


@Document(collection = "photos")
public class Photo {
    
    @Id
    @Field()
    private String id;

    private String idProfile;
    private String idPhoto;
    private String idImage;

    public Photo() {
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getIdProfile() {
        return idProfile;
    }
    public void setIdProfile(String idProfile) {
        this.idProfile = idProfile;
    }
    public String getIdPhoto() {
        return idPhoto;
    }
    public void setIdPhoto(String idPhoto) {
        this.idPhoto = idPhoto;
    }
    
    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }
    public Photo(String idProfile, String idPhoto) {
        this.idProfile = idProfile;
        this.idPhoto = idPhoto;
    }

}
