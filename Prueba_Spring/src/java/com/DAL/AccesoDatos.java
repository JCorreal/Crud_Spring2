package com.DAL;

import com.BO.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AccesoDatos implements IAccesoDatos
{

    private JdbcTemplate jdbcTemplate;
 
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

 
    @Override
    public void saveEmployee(Employee p)
    {
        String sql = "insert into crudspring(name,salary,designation) values('" 
                     + p.getName() 
                     + "'," + p.getSalary()
		     + ",'" + p.getDesignation() + "')";
        jdbcTemplate.update(sql);
    }

   
    @Override
    public Employee getEmployeeById(int id)
    {
        String sql = "select * from crudspring where id=?";
        Employee employee = (Employee) jdbcTemplate.queryForObject(sql, new Object[]
        { id }, new RowMapper<Employee>()
        {
            @Override
            public Employee mapRow(ResultSet rs, int rowNum) throws SQLException 
            {
                Employee employee = new Employee();
                employee.setId(rs.getInt(1));
                employee.setName(rs.getString(2));
                employee.setSalary(rs.getFloat(3));
                employee.setDesignation(rs.getString(4));                
                return employee;
            }
        });
        return employee;
    }

   
    @Override
    public List<Employee> getAllEmployees()
    {
        String sql = "select * from crudspring";

        List<Employee> employeeList = jdbcTemplate.query(sql, new ResultSetExtractor<List<Employee>>()
        {
            @Override
            public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException
            {
                List<Employee> list = new ArrayList<>();
                while (rs.next())
                {
                    Employee employee = new Employee();
                    employee.setId(rs.getInt(1));
                    employee.setName(rs.getString(2));
                    employee.setSalary(rs.getFloat(3));
                    employee.setDesignation(rs.getString(4));   
                    list.add(employee);
                }
                return list;
            }

        });
        return employeeList;
    }

    @Override
    public void updateEmployee(Employee employee)
    {
        String sql = "update crudspring set Name =?, Salary=?, Designation=? where Id=?";
        jdbcTemplate.update(sql, new Object[]
        { employee.getName(), employee.getSalary(), employee.getDesignation(), employee.getId() });
    }

    @Override
    public void deleteEmployee(int id)
    {
        String sql = "Delete from crudspring where id=?";
        jdbcTemplate.update(sql, new Object[]
        { id });
    }
}
