package com.example.proiect.repository;

import com.example.proiect.model.Appointment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class AppointmentRepository {
    private JdbcTemplate jdbcTemplate;

    public AppointmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Appointment createAppointment(Appointment appointment) {
        String sql = "insert into appointments values(?, ?, ?, ?)";

        PreparedStatementCreator preparedStatementCreator = connection ->{
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setObject(2, appointment.getDate());
            preparedStatement.setObject(3, appointment.getPatient().getId());
            preparedStatement.setObject(4, appointment.getDoctor().getId());
            return preparedStatement;
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);

        appointment.setId(generatedKeyHolder.getKey().intValue());

        return appointment;
    }

    public boolean updateById(Appointment appointment) {
        String sql = "update appointments set date = ? where id = ?";

        return jdbcTemplate.update(sql,
                appointment.getDate(),
                appointment.getId()) != 0;
    }

    public boolean deleteById(int id) {
        String sql = "delete from appointments where id = ?";
        return jdbcTemplate.update(sql, id) != 0;
    }
}
