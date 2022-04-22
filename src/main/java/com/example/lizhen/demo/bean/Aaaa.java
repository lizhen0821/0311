package com.example.lizhen.demo.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Aaaa implements Serializable {
    private String fdId;
    private String fdParentId;
    private String fdName;

    public Aaaa(String fdId, String fdParentId, String fdName) {
        this.fdId = fdId;
        this.fdParentId = fdParentId;
        this.fdName = fdName;
    }
}
