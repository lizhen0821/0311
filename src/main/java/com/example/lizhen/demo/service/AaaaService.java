package com.example.lizhen.demo.service;

import com.example.lizhen.demo.bean.Aaaa;
import com.example.lizhen.demo.mapper.AaaaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AaaaService {

    @Autowired
    AaaaMapper aaaaMapper;

    public Aaaa getAaaa(String fdId){
      return   aaaaMapper.getAaaa(fdId);
    }
}
