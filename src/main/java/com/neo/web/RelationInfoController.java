package com.neo.web;

import com.neo.entity.po.RelationInfo;
import com.neo.entity.po.User;
import com.neo.mapper.test1.RelationInfoMapper;
import com.neo.mapper.test2.User2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by songcj on 2018/10/15.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@RestController
public class RelationInfoController {

    @Autowired
    private RelationInfoMapper relationInfoMapper;

    @RequestMapping("/getRelationInfos")
    public List<RelationInfo> getRelationInfos() {
        List<RelationInfo> relationInfoList = relationInfoMapper.getAll();
        return relationInfoList;
    }

    @RequestMapping("/getRelationInfo/{id}")
    public RelationInfo getRelationInfo(@PathVariable("id") int id) {
        return relationInfoMapper.getRelationInfo(id);
    }

    @RequestMapping("/addRelationInfo")
    public void save(@RequestBody RelationInfo relationInfo) {
        relationInfoMapper.insert(relationInfo);
    }

    @RequestMapping(value="updateRelationInfo")
    public void update(@RequestBody RelationInfo relationInfo) {
        relationInfoMapper.update(relationInfo);
    }

    @RequestMapping(value="/deleteRelationInfo/{id}")
    public void delete(@PathVariable("id") int id) {
        relationInfoMapper.delete(id);
    }

}
