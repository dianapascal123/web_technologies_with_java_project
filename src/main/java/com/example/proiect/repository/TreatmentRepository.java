package com.example.proiect.repository;

import com.example.proiect.model.Treatment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class TreatmentRepository {
    private JdbcTemplate jdbcTemplate;

    public TreatmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Treatment createTreatment(Treatment treatment) {
        String sql = "insert into treatments values (?, ?, ?, ?, ?)";

        PreparedStatementCreator preparedStatementCreator = connection ->{
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setObject(2, treatment.getMedicine());
            preparedStatement.setObject(3, treatment.getDiagnostic().getId());
            preparedStatement.setObject(4, treatment.getStartDate());
            preparedStatement.setObject(5, treatment.getEndDate());
            return preparedStatement;
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);

        treatment.setId(generatedKeyHolder.getKey().intValue());

        return treatment;
    }
}
