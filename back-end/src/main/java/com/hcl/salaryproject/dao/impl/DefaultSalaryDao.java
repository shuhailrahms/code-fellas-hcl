package com.hcl.salaryproject.dao.impl;

import com.hcl.salaryproject.dao.SalaryDao;
import com.hcl.salaryproject.entity.Salary;
import com.hcl.salaryproject.entity.Salary;
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
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class DefaultSalaryDao implements SalaryDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSalaryDao.class);

    private final JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Salary> findAll() {
        final String sql = "select * from salaries";
        try {
            return jdbcTemplate.query(
                    sql,
                    (resultSet, i) -> {
                        Salary salary = getSalary(resultSet);
                        return salary;
                    });
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Error while getting all salaries", ex);
            return null;
        }
    }

    @Override
    public Optional<Salary> getSalaryByCategory(Integer categoryId) {
        final String sql = "select * from salaries where id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
                Salary salary = getSalary(resultSet);
                return salary;
            }, categoryId));
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Error while getting salary by category", ex);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Salary> findById(Integer id) {
        final String sql = "select * from salaries where id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
                Salary salary = getSalary(resultSet);
                return salary;
            }, id));
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Error while getting salary by id", ex);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Salary> save(Salary salary) {
        final String sql = "INSERT INTO salaries " +
                "(basic, hra, food_allowance, special_allowances, category, net_salary) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        final String sqlAfterUpdate =  "select * from salaries where id = ?";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        Float basic = salary.getBasic();
        Float hra = basic * 0.1F;
        Float foodAllowance = basic * 0.18F;
        Float specialAllowances = basic * 0.05F;
        Float netSalary = basic + hra + foodAllowance + specialAllowances;

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            int index = 1;
            ps.setObject(index++, basic);
            ps.setObject(index++, hra);
            ps.setObject(index++, foodAllowance);
            ps.setObject(index++, specialAllowances);
            ps.setObject(index++, salary.getCategory());
            ps.setObject(index++, netSalary);
            return ps;
        }, keyHolder);

        return Optional.of(jdbcTemplate.queryForObject(sqlAfterUpdate, (resultSet, i) -> {
            Salary newSalary = getSalary(resultSet);
            return newSalary;
        }, keyHolder.getKey().intValue()));
    }

    @Override
    public Optional<Salary> update(Integer id, Salary salary) {
        final String sql = "UPDATE salaries SET basic = ?, hra = ?, food_allowance = ?, special_allowances = ?, category = ?, net_salary = ? where id = ?";
        final String sqlAfterUpdate = "select * from salaries where id = ?";

        Float basic = salary.getBasic();
        Float hra = basic * 0.1F;
        Float foodAllowance = basic * 0.18F;
        Float specialAllowances = basic * 0.05F;
        Float netSalary = basic + hra + foodAllowance + specialAllowances;

        try {
            jdbcTemplate.update(sql,  salary.getBasic(), hra, foodAllowance, specialAllowances, salary.getCategory(), netSalary, id);

            return Optional.of(jdbcTemplate.queryForObject(sqlAfterUpdate, (resultSet, i) -> {
                Salary updatedSalary = getSalary(resultSet);
                return updatedSalary;
            }, id));
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Error while updating salary by id", ex);
            return Optional.empty();
        }
    }

    @Override
    public Boolean deleteById(Integer id) {
        String sql = "delete from salaries where id = ?";

        try {
            int affectedRows = jdbcTemplate.update(sql, id);
            return affectedRows >= 1;
        }catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Error while deleting salary by id", ex);
            return false;
        }
    }

    private Salary getSalary(ResultSet resultSet) throws SQLException {
        Salary newSalary = new Salary();
        newSalary.setId(resultSet.getInt("id"));
        newSalary.setBasic(resultSet.getFloat("basic"));
        newSalary.setHra(resultSet.getFloat("hra"));
        newSalary.setFoodAllowance(resultSet.getFloat("food_allowance"));
        newSalary.setSpecialAllowances(resultSet.getFloat("special_allowances"));
        newSalary.setCategory(resultSet.getString("category"));
        newSalary.setNetSalary(resultSet.getFloat("net_salary"));
        return newSalary;
    }
}
