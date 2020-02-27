package com.example.datawriter.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "data")
public class Data {
    @Id
    private String id;
    private String currrentDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrrentDate() {
        return currrentDate;
    }

    public void setCurrrentDate(String currrentDate) {
        this.currrentDate = currrentDate;
    }
}


