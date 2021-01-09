package com.example.proiect.repository;

import com.example.proiect.model.Patient;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@Repository
public class PatientRepository {
    private JdbcTemplate jdbcTemplate;

    public PatientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Patient createPatient(Patient patient) {
        String sql = "insert into patients values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatementCreator preparedStatementCreator = connection ->{
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setObject(2, patient.getFirstName());
            preparedStatement.setObject(3, patient.getLastName());
            preparedStatement.setObject(4, patient.getBirthdate());
            preparedStatement.setObject(5, patient.getCNP());
            preparedStatement.setObject(6, patient.getWeight());
            preparedStatement.setObject(7, patient.getHeight());
            return preparedStatement;
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);

        patient.setId(generatedKeyHolder.getKey().intValue());
        return patient;
    }

    public Optional<Patient> getPatientByName(String firstName, String lastName) {
        String sql = "select * from patients p where p.first_name = ? and p.last_name = ? LIMIT 1";

        try {
            Patient patient = jdbcTemplate.queryForObject(sql, new RowMapper<Patient>() {
                @Override
                public Patient mapRow(ResultSet resultSet, int i) throws SQLException {
                    Patient patient1 = new Patient();
                    patient1.setId(resultSet.getInt("id"));
                    patient1.setFirstName(resultSet.getString("first_name"));
                    patient1.setLastName(resultSet.getString("last_name"));
                    patient1.setBirthdate(resultSet.getDate("birthdate"));
                    patient1.setCNP(resultSet.getString("cnp"));
                    patient1.setWeight(resultSet.getDouble("weight"));
                    patient1.setHeight(resultSet.getByte("height"));
                    return patient1;
                }
            }, firstName, lastName);
            if (patient != null)
                return Optional.of(patient);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

        return Optional.empty();
    }

    public Optional<Patient> getPatientById(int id) {
        String sql = "select * from patients p where p.id = ?";

        try {
            Patient patient = jdbcTemplate.queryForObject(sql, new RowMapper<Patient>() {
                @Override
                public Patient mapRow(ResultSet resultSet, int i) throws SQLException {
                    Patient patient1 = new Patient();
                    patient1.setId(resultSet.getInt("id"));
                    patient1.setFirstName(resultSet.getString("first_name"));
                    patient1.setLastName(resultSet.getString("last_name"));
                    patient1.setBirthdate(resultSet.getDate("birthdate"));
                    patient1.setCNP(resultSet.getString("cnp"));
                    patient1.setWeight(resultSet.getDouble("weight"));
                    patient1.setHeight(resultSet.getByte("height"));
                    return patient1;
                }
            }, id);
            if (patient != null)
                return Optional.of(patient);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

        return Optional.empty();
    }

}
