package com.fireCloud.tradCity.test.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fireCloud.tradCity.basic.model.CallBackModel;
import com.fireCloud.tradCity.basic.model.Pagination;
import com.fireCloud.tradCity.test.model.User;


@RestController //该注解相当于同时有Controller和responseBody
public class TESTController {
	
	@RequestMapping(value="{version}/users",method=RequestMethod.GET)
	public CallBackModel get(@PathVariable("version") Double version,User user,Pagination pagination){
		CallBackModel r = new CallBackModel();
		if(version == 1.0){
			r.setMsg("get方法，获取成功，版本号="+version+",name="+user.getName()+",age="+user.getAge()+",page="+pagination.getCurrentPage() + ",size="+pagination.getNumPerPage());
		}
		return r;
	}
	
	@RequestMapping(value="{version}/users/{id}/products",method=RequestMethod.GET)
	public CallBackModel getall(@PathVariable Integer id,@PathVariable Double version){
		CallBackModel r = new CallBackModel();
		if(version == 1.0){
			r.setMsg("get方法，获取成功，版本号="+version+"，ID="+id);
		}
		return r;
	}
	
	@RequestMapping(value="{version}/users",method=RequestMethod.POST)
	public CallBackModel post(User user,@PathVariable("version") Double version){
		CallBackModel r = new CallBackModel();
		if(version == 1.0){
			r.setMsg("post方法，创建成功，版本号="+version+"name="+user.getName()+"age="+user.getAge());
		}
		return r;
	}
	
	@RequestMapping(value="{version}/users/{id}",method=RequestMethod.PUT)
	public CallBackModel put(User user,@PathVariable("id") Integer id,@PathVariable("version") Double version){
		CallBackModel r = new CallBackModel();
		if(version == 1.0){
			r.setMsg("put方法，更新成功，版本号="+version+"ID="+id);
		}
		return r;
	}
	
	@RequestMapping(value="{version}/users/{id}",method=RequestMethod.PATCH)
	public CallBackModel patch(User user,@PathVariable("id") Integer id,@PathVariable("version") Double version){
		CallBackModel r = new CallBackModel();
		if(version == 1.0){
			r.setMsg("patch方法，更新成功，版本号="+version+"ID="+id);
		}
		return r;
	}
	
	@RequestMapping(value="{version}/users/{id}",method=RequestMethod.DELETE)
	public CallBackModel del(@PathVariable("id") Integer id,@PathVariable("version") Double version){
		CallBackModel r = new CallBackModel();
		if(version == 1.0){
			r.setMsg("delete方法，删除成功，版本号="+version+"ID="+id);
		}
		return r;
	}
}
