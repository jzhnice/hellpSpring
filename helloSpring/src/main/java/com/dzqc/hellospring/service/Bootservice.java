package com.dzqc.hellospring.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dzqc.hellospring.exception.RollBackException;
import com.dzqc.hellospring.entity.TCoffee;
import com.dzqc.hellospring.mapper.TCofferMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
@EnableTransactionManagement
public class Bootservice {
    //展示数据源
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TCofferMapper mapper;

    public Map<String,Object> cofLogin(String cofName,Integer cofPrice){
        TCoffee coffee = mapper.selectOne(new QueryWrapper<TCoffee>().eq("name", cofName).eq("price", cofPrice));
        HashMap<String,Object> map = new HashMap<>();
        if (coffee == null) {
         map.put("isSuccess",false);
        }else{
            map.put("isSuccess",true);
        }
        map.put("cof",coffee);

        return  map;
    }

    @DS("master")
    public void showConnection() throws Exception {
        Connection connection = dataSource.getConnection();
        log.info(connection.toString());
    }

    //查询
//    public List<TCoffee> showData() {
////        mapper.selectList(new QueryWrapper<TCoffee>("SELECT * FROM t_coffee"))
////        查询表的的东西
//        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM t_coffee");
////        foreach循环的每一个都输出
//        maps.forEach(row -> log.info("select : {} " + row.toString()));
//        return  maps;
//    }

    public void insertRecurd() {
        //执行sql语句，所有的sql都可以
        TCoffee tCoffee = new TCoffee();
        Integer id = tCoffee.getId();
        for (int k = 1; k <= 20; k++) {
            jdbcTemplate.execute("INSERT INTO T_COFFEE" +
                    "(NAME ,PRICE,CREATE_TIME,UPDATE_TIME) " +
                    "VALUES " +
                    "(k,19,NOW(),NOW())");
        }
    }

    @Transactional(rollbackFor = RollBackException.class)
    public void insertRecordThenRollback() throws Exception {
        jdbcTemplate.execute("INSERT INTO T_COFFEE" +
                "(NAME ,PRICE,CREATE_TIME,UPDATE_TIME) " +
                "VALUES " +
                "('焦糖拿铁',19,NOW(),NOW())");
        throw new RollBackException();
    }

    //查询
    public List<TCoffee> showMPData() {
//        QueryWrapper 条件构造器
        List<TCoffee> tCoffees = mapper.selectList(new QueryWrapper<TCoffee>());
        log.warn("MybatisPlus 查询数据完成");
        tCoffees.forEach(row -> log.info("row -> {}", row));
        return tCoffees;
    }

    //删除
    public void deleteMP() {
        //根据id删除
//        log.info( "删除结果 -> {}",mapper.deleteById(14) == 1 ?"删除成功" : "删除失败");

        int delete = mapper.delete(new QueryWrapper<TCoffee>().ge("id", 1).le("id", 36));
        log.info("根据条件批量删除的结果 -> {} {} ", delete >= 1 ? "删除成功 " : "删除失败", delete);

//        ArrayList<Object> idList = new ArrayList<>();
//        Integer[] idArr = {};
//        idList.addAll(Arrays.asList(idArr));
//        int delete = mapper.deleteBatchIds(idList);
//                log.info("根据条件批量删除的结果 -> {} {} ",delete >=1 ? "删除成功 ":"删除失败",delete);
//    }
    }

    //批量修改
    public void batchUpdateMPRecord() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        TCoffee tCoffee = new TCoffee();
        tCoffee.setName("焦糖拿铁");
        tCoffee.setPrice(4800);
        tCoffee.setCreateTime(format.parse("2022-02-22 14:58:00"));

        UpdateWrapper<TCoffee> tCoffeeUpdateWrapper = new UpdateWrapper<>();
//        le 小于   ge 大于
        int result = mapper.update(tCoffee, tCoffeeUpdateWrapper.between("id", 15, 28));

        log.info("受影响行数 -> {}", result);

    }

    //修改
    public void updateMP() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        TCoffee tCoffee = new TCoffee();
        tCoffee.setCreateTime(format.parse("2028-04-22 22:22:22"));
        tCoffee.setUpdateTime(format.parse("2020-02-22 22:22:22"));
        tCoffee.setId(43);
        tCoffee.setName("樱花拿铁");
        tCoffee.setPrice(88888);
//        tCoffee.setCreateTime(new Date());
//        tCoffee.setUpdateTime(new Date());
        int i = mapper.updateById(tCoffee);
        log.info("修改结果 -> {} ", i == 1 ? "成功" : "失败");
    }

    // 1.作业插入
    public void insertMPRecurd(int i) {
        TCoffee tCoffee = new TCoffee();
        tCoffee.setName(i % 2 == 0 ? "蠢" : "拿铁");
//        for (int i = 1; i < 21; i++) {
//            tCoffee.setId(36 + i);
//            tCoffee.setName("测试数据" + i);
        tCoffee.setPrice(i % 2 == 0 ? 995 : 22);
        tCoffee.setCreateTime(new Date());
        tCoffee.setUpdateTime(new Date());
        int insert = mapper.insert(tCoffee);
        log.error("插入结果 -> {}", insert == 1 ? true : false);
//        }
    }

    //2.作业修改
    public void dtxg() {
        TCoffee tCoffee = new TCoffee();
        tCoffee.setId(54);
        tCoffee.setName("美式冷翠");
        tCoffee.setPrice(80);
        tCoffee.setCreateTime(new Date());
        tCoffee.setUpdateTime(new Date());
        int i = mapper.updateById(tCoffee);
        log.info("修改结果 -> {} ", i == 1 ? "成功" : "失败");
    }

    // 3.作业批量修改
    public void plxg() {
        TCoffee tCoffee = new TCoffee();
        tCoffee.setName("美式冷翠");
        tCoffee.setPrice(1000);
        tCoffee.setCreateTime(new Date());
        tCoffee.setUpdateTime(new Date());
        UpdateWrapper<TCoffee> tCoffeeUpdateWrapper = new UpdateWrapper<>();
        int result = mapper.update(tCoffee, tCoffeeUpdateWrapper.between("price", 15, 18));
        log.info("受影响行数 -> {}", result);
    }

    //  4.多条作业删除
    public void plsc() {
        int delete = mapper.delete(new QueryWrapper<TCoffee>().le("price", 5));
        log.info("根据条件批量删除的结果 -> {} {} ", delete >= 1 ? "删除成功 " : "删除失败", delete);
    }

    //    5.多条id删除
    public void idsc() {


        int delete = mapper.delete(new QueryWrapper<TCoffee>().ge("id", 42).le("id", 43));
        log.info("根据条件批量删除的结果 -> {} {} ", delete >= 1 ? "删除成功 " : "删除失败", delete);
    }

    //6多条查询
    public void dtcx() {
        List<Map<String, Object>> maps =
                jdbcTemplate.queryForList("SELECT * FROM t_coffee WHERE NAME NOT LIKE '%测试数据%' ");
//        foreach循环的每一个都输出
        maps.forEach(row -> log.info("select : {} " + row.toString()));

    }

    //多数据源
    @DS("master")
    public void showMPDataPageination( int currentPage ,int pageSize ){
//        QueryWrapper<TCoffee> objectQueryWrapper = new QueryWrapper<>();
//
//        Long aLong = mapper.selectCount(objectQueryWrapper);
//
//        log.info("表的长度：{}" ,aLong);
//        log.info("currentPage的长度：{}" ,currentPage);
//        log.info("pageSize的长度：{}" ,pageSize);
//        log.info("的长度：{}" ,pageSize*currentPage);
//
//        if (currentPage * pageSize >aLong) {
//
//            currentPage = (int)(aLong%pageSize) == 0 ? (int)(aLong%pageSize) : (int)(aLong%pageSize)  ;
//        }

        log.info("开始查询第{}页 的数据，页面大小为 {}",currentPage,pageSize);
        IPage<TCoffee> page = new Page<>( currentPage,pageSize);
        IPage<TCoffee> tCoffeeIPage = mapper.selectPage(page, new QueryWrapper<TCoffee>());
        tCoffeeIPage.getRecords().forEach(row -> log.info("分页开始 -> {}",row));
    }

    @DS("slave")
    public void slaveData(){
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT * FROM app_info");
        maps.forEach(row -> log.info("row ->{}"  ,row));
    }
}