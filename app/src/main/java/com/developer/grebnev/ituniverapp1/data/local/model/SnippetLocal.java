package com.developer.grebnev.ituniverapp1.data.local.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Grebnev on 02.11.2017.
 */
@Table(name = "Snippet")
public class SnippetLocal extends Model{
    @Column(name = "requirement")
    private String requirement;
    @Column(name = "responsibility")
    private String responsibility;

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }
}
