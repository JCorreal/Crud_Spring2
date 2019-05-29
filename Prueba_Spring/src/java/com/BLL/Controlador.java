package com.BLL;

import java.util.List;
import com.BO.Employee;
import com.DAL.IAccesoDatos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Controlador {
    @Autowired
    private IAccesoDatos iaccesoDatos;

    @RequestMapping(value = "/employee",method=RequestMethod.POST)
    public ModelAndView saveEmployee(@ModelAttribute("employee") Employee employee)
    {
        try
        {
            if (employee.getId()!=0)
            {
               iaccesoDatos.getEmployeeById(employee.getId()); 
               iaccesoDatos.updateEmployee(employee);
            }
            else
            {                   
                iaccesoDatos.saveEmployee(employee);
            }
        }
        catch(EmptyResultDataAccessException ex)
        {        
            System.out.println(ex);
        }
        return new ModelAndView("redirect:/employees");
    }
    
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editEmployee(@ModelAttribute("employee") Employee employee,@PathVariable("id") int id)
    {
        ModelAndView model = new ModelAndView("employees");        
        employee = iaccesoDatos.getEmployeeById(id);
        List<Employee> employeeList = iaccesoDatos.getAllEmployees();        
        model.addObject("employee",employee);        
        model.addObject("employeeList",employeeList);        
        return model;
        
    }
    
    @RequestMapping(value = "/delete/{id}")
    public ModelAndView deleteEmployee(@ModelAttribute("employee") Employee employee,@PathVariable("id") int id)
    {
        iaccesoDatos.deleteEmployee(id);        
        return new ModelAndView("redirect:/employees");
    }

    @RequestMapping(value = "/employees")
    public ModelAndView listEmployees(@ModelAttribute("employee") Employee employee)
    {
        ModelAndView model = new ModelAndView("employees");
        List<Employee> employeeList = iaccesoDatos.getAllEmployees();     
        model.addObject("employeeList", employeeList);
        return model;
    }
}


