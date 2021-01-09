package com.example.proiect.repository;

import com.example.proiect.model.Doctor;
import com.example.proiect.model.Hospital;
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
import java.util.List;
import java.util.Optional;

@Repository
public class DoctorRepository {
    private JdbcTemplate jdbcTemplate;
    private HospitalRepository hospitalRepository;

    public DoctorRepository(JdbcTemplate jdbcTemplate, HospitalRepository hospitalRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.hospitalRepository = hospitalRepository;
    }

    public Doctor createDoctor(Doctor doctor) {
        String sql = "insert into doctors values (?, ?, ?, ?, ?)";

            PreparedStatementCreator preparedStatementCreator = connection ->{
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setObject(1, null);
                preparedStatement.setObject(2, doctor.getFirstName());
                preparedStatement.setObject(3, doctor.getLastName());
                preparedStatement.setObject(4, doctor.getSpecialization());
                preparedStatement.setObject(5, doctor.getHospital().getId());
                return preparedStatement;
            };
            GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);

            doctor.setId(generatedKeyHolder.getKey().intValue());

        return doctor;
    }

    public Optional<Doctor> getDoctorByName(String firstName, String lastName) {
        String sql = "select * from doctors p where p.first_name = ? and p.last_name = ? LIMIT 1";

        try {
            Doctor doctor = jdbcTemplate.queryForObject(sql, new RowMapper<Doctor>() {
                @Override
                public Doctor mapRow(ResultSet resultSet, int i) throws SQLException {
                    Doctor doctor1 = new Doctor();
                    doctor1.setId(resultSet.getInt("id"));
                    doctor1.setFirstName(resultSet.getString("first_name"));
                    doctor1.setLastName(resultSet.getString("last_name"));
                    doctor1.setSpecialization(resultSet.getString("specialization"));

                    Optional<Hospital> hospital = hospitalRepository.getHospitalById(resultSet.getInt("hospital_id"));
                    if (hospital.isPresent()) {
                        doctor1.setHospital(hospital.get());
                    }
                    return doctor1;
                }
            }, firstName, lastName);
            if (doctor != null) {
                return Optional.of(doctor);
            }
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

        return Optional.empty();
    }

    public List<Doctor> getAll() {
        String sql = "select * from doctors";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new Doctor(
                                rs.getInt("id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("specialization"),
                                hospitalRepository.getHospitalById(rs.getInt("hospital_id")).get()
                        )
        );
    }
}
