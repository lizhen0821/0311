package com.example.lizhen.demo.mapper;

import com.example.lizhen.demo.bean.Aaaa;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AaaaMapper {

    public Aaaa getAaaa(String fdId);
}
