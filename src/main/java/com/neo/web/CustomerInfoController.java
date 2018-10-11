package com.neo.web;

import com.neo.entity.po.CustomerInfo;
import com.neo.mapper.test1.CustomerInfoMapper;
import com.neo.util.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerInfoController {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private CustomerInfoMapper customerInfoMapper;
	
	@RequestMapping("/getCustomerInfos")
	public List<CustomerInfo> getCustomerInfos() {
		List<CustomerInfo> customerInfos = customerInfoMapper.getAll();
		return customerInfos;
	}
    
    @RequestMapping("/addCustomerInfo")
    public void save(@RequestBody CustomerInfo customerInfo) {
        customerInfoMapper.insert(customerInfo);
    }
    
    @RequestMapping(value="/deleteCustomerInfo")
    public void delete(@RequestBody CustomerInfo customerInfo) {
        customerInfoMapper.delete(customerInfo);
    }

    @RequestMapping(value="/test")
    public String test() {
        ApplicationContext applicationContext = SpringUtils.getApplicationContext();
        return applicationContext.toString();
    }
}