package com.example.proiect.repository;

import com.example.proiect.model.Hospital;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class HospitalRepository {
    private JdbcTemplate jdbcTemplate;

    public HospitalRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Hospital> getHospitalByName(String name) {
        String sql = "select * from hospitals h where h.name = ?";

        try {
            Hospital hospital = jdbcTemplate.queryForObject(sql, new RowMapper<Hospital>() {
                @Override
                public Hospital mapRow(ResultSet resultSet, int i) throws SQLException {
                    Hospital hospital1 = new Hospital();
                    hospital1.setId(resultSet.getInt("id"));
                    hospital1.setName(resultSet.getString("name"));
                    hospital1.setCity(resultSet.getString("city"));
                    hospital1.setAddress(resultSet.getString("address"));
                    return hospital1;
                }
            }, name);
            if (hospital != null)
                return Optional.of(hospital);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

        return Optional.empty();
    }

    public Optional<Hospital> getHospitalById(int id) {
        String sql = "select * from hospitals h where h.id = ?";

        try {
            Hospital hospital = jdbcTemplate.queryForObject(sql, new RowMapper<Hospital>() {
                @Override
                public Hospital mapRow(ResultSet resultSet, int i) throws SQLException {
                    Hospital hospital1 = new Hospital();
                    hospital1.setId(resultSet.getInt("id"));
                    hospital1.setName(resultSet.getString("name"));
                    hospital1.setCity(resultSet.getString("city"));
                    hospital1.setAddress(resultSet.getString("address"));
                    return hospital1;
                }
            }, id);
            if (hospital != null)
                return Optional.of(hospital);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

        return Optional.empty();
    }
}
