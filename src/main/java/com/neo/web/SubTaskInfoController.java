package com.neo.web;

import com.neo.entity.po.RelationInfo;
import com.neo.entity.po.SubTaskInfo;
import com.neo.mapper.test1.RelationInfoMapper;
import com.neo.mapper.test1.SubTaskInfoMapper;
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
public class SubTaskInfoController {

    @Autowired
    private SubTaskInfoMapper subTaskInfoMapper;

    @RequestMapping("/getSubTaskInfos")
    public List<SubTaskInfo> getSubTaskInfos() {
        return subTaskInfoMapper.getAll();
    }

    @RequestMapping("/getSubTaskInfo/{id}")
    public SubTaskInfo getSubTaskInfo(@PathVariable("id") int id) {
        return subTaskInfoMapper.getSubTaskInfo(id);
    }

    @RequestMapping("/addSubTaskInfo")
    public void save(@RequestBody SubTaskInfo subTaskInfo) {
        subTaskInfoMapper.insert(subTaskInfo);
    }

    @RequestMapping(value="updateSubTaskInfo")
    public void update(@RequestBody SubTaskInfo subTaskInfo) {
        subTaskInfoMapper.update(subTaskInfo);
    }

    @RequestMapping(value="/deletesubTaskInfo/{id}")
    public void delete(@PathVariable("id") int id) {
        subTaskInfoMapper.delete(id);
    }



}
