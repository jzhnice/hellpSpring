package com.dzqc.hellospring.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dzqc.hellospring.entity.TCoffee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TCofferMapper extends BaseMapper<TCoffee> {



}
