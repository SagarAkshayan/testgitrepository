package in.edureka.controller;

import in.edureka.model.Employee;
import in.edureka.service.EmployeeService;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
*@author sagar.akshayan
*/
@Controller
@RequestMapping("/empCrud")
public class EmployeeController {

	//Spring injection
	//EmployeeService empService=new EmployeeService()
	@Autowired
	EmployeeService empService;

	//pass employee object
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody String saveEmployee(@RequestBody Employee emp) {
        boolean status =empService.saveOrUpdateEmployee(emp);
        if(status) return "Employee "+ emp.getName() + " saved succesfully";
        else return "Employee "+ emp.getName() + " not saved";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Collection<Employee> getEmployees() {
        return empService.getAllEmployees();        		
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public @ResponseBody Employee getEmployee(@PathVariable int id) {
		return empService.getEmployee(id);
	}

	@RequestMapping(value="/fetch", method = RequestMethod.GET)
	public @ResponseBody Employee getEmployeeByQueryParam(@RequestParam("id") int id) {
		return empService.getEmployee(id);
	}
	
	@RequestMapping(value="/{name}/{age}", method = RequestMethod.GET)
	public @ResponseBody Employee getEmployee(@PathVariable String name,@PathVariable int age) {
		return empService.getEmployeeByAgeName(age, name);
	}


	@RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT)
	public @ResponseBody String deleteEmployee(@PathVariable int id) {
        boolean status =empService.deleteEmployee(id);
        if(status) return "Employee delete succesfully";
        else return "Employee deletion failed";
	}
}