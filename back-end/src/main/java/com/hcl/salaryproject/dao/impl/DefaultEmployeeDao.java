package com.hcl.salaryproject.dao.impl;

import com.hcl.salaryproject.dao.EmployeeDao;
import com.hcl.salaryproject.entity.Employee;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.text.SimpleDateFormat;
@Component
@AllArgsConstructor
public class DefaultEmployeeDao implements EmployeeDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultEmployeeDao.class);
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private final JdbcTemplate jdbcTemplate;


    @Override
    public List<Employee> findAll() {
        final String sql = "select * from employees";
        try {
            return jdbcTemplate.query(
                    sql,
                    (resultSet, i) -> {
                        Employee employee = getEmployee(resultSet);
                        return employee;
                    });
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Error while getting all employees", ex);
            return null;
        }
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        final String sql = "select * from employees where id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
                Employee employee = getEmployee(resultSet);
                return employee;
            }, id));
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Error while getting employee by id", ex);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Employee> save(Employee employee) {
        final String sql = "INSERT INTO employees " +
                "(callingname, fullname, dobirth, nic, address, mobile, land, dorecruit, tocreation, gender, civilstatus, designation_id, nametitle, email) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, current_timestamp, ?, ?, ?, ?, ?)";
        final String sqlAfterUpdate =  "select * from employees where id = ?";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            int index = 1;
            ps.setString(index++, employee.getCallingname());
            ps.setObject(index++, employee.getFullname());

            try {
                ps.setObject(index++, sdf.parse(employee.getDobirth()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ps.setObject(index++, employee.getNic());
            ps.setObject(index++, employee.getAddress());
            ps.setObject(index++, employee.getMobile());
            ps.setObject(index++, employee.getLand());
            try {
                ps.setObject(index++, sdf.parse(employee.getDorecruit()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ps.setObject(index++, employee.getGender());
            ps.setObject(index++, employee.getCivilstatus());
            ps.setObject(index++, employee.getDesignationId());
            ps.setObject(index++, employee.getNametitle());
            ps.setObject(index++, employee.getEmail());
            return ps;
        }, keyHolder);

        return Optional.of(jdbcTemplate.queryForObject(sqlAfterUpdate, (resultSet, i) -> {
            Employee newEmployee = getEmployee(resultSet);
            return newEmployee;
        }, keyHolder.getKey().intValue()));
    }

    @Override
    public Optional<Employee> update(Integer id, Employee employee) {
        final String sql = "UPDATE employees SET callingname = ?, fullname = ?, dobirth = ?, nic = ?, address = ?, mobile = ?, land = ?, dorecruit = ?, gender = ?, civilstatus = ?, designation_id = ?, nametitle = ?, email = ? where id = ?";
        final String sqlAfterUpdate = "select * from employees where id = ?";

        try {
            jdbcTemplate.update(sql,  employee.getCallingname(), employee.getFullname(), sdf.parse(employee.getDobirth()), employee.getNic(), employee.getAddress(), employee.getMobile(), employee.getLand(), sdf.parse(employee.getDorecruit()), employee.getGender(), employee.getCivilstatus(), employee.getDesignationId(), employee.getNametitle(), employee.getEmail(), id);

            return Optional.of(jdbcTemplate.queryForObject(sqlAfterUpdate, (resultSet, i) -> {
                Employee updatedEmployee = getEmployee(resultSet);
                return updatedEmployee;
            }, id));
        } catch (EmptyResultDataAccessException | ParseException ex) {
            LOGGER.error("Error while updating employee by id", ex);
            return Optional.empty();
        }
    }

    @Override
    public Boolean deleteById(Integer id) {
        String sql = "delete from employees where id = ?";

        try {
            int affectedRows = jdbcTemplate.update(sql, id);
            return affectedRows >= 1;
        }catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Error while deleting employee by id", ex);
            return false;
        }
    }

    private Employee getEmployee(ResultSet resultSet) throws SQLException {
        Employee newEmployee = new Employee();
        newEmployee.setId(resultSet.getInt("id"));
        newEmployee.setCallingname(resultSet.getString("callingname"));
        newEmployee.setFullname(resultSet.getString("fullname"));
        newEmployee.setDobirth( sdf.format(resultSet.getDate("dobirth")) );
        newEmployee.setNic(resultSet.getString("nic"));
        newEmployee.setAddress(resultSet.getString("address"));
        newEmployee.setMobile(resultSet.getString("mobile"));
        newEmployee.setLand(resultSet.getString("land"));
        newEmployee.setDorecruit( sdf.format(resultSet.getDate("dorecruit")));
        newEmployee.setTocreation(resultSet.getDate("tocreation"));
        newEmployee.setGender(resultSet.getString("gender"));
        newEmployee.setCivilstatus(resultSet.getString("civilstatus"));
        newEmployee.setDesignationId(resultSet.getInt("designation_id"));
        newEmployee.setNametitle(resultSet.getString("nametitle"));
        newEmployee.setEmail(resultSet.getString("email"));
        return newEmployee;
    }
}
