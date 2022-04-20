package com.example.lizhen.demo.bean;

import lombok.Data;

@Data
public class Aaaa {
    private String fdId;
    private String fdParentId;
    private String fdName;

    public Aaaa(String fdId, String fdParentId, String fdName) {
        this.fdId = fdId;
        this.fdParentId = fdParentId;
        this.fdName = fdName;
    }
}
