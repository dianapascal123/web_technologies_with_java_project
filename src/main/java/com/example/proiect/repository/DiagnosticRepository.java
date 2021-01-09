package com.example.proiect.repository;

import com.example.proiect.model.Diagnostic;
import com.example.proiect.model.Hospital;
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
import java.util.List;
import java.util.Optional;

@Repository
public class DiagnosticRepository {
    private JdbcTemplate jdbcTemplate;
    private PatientRepository patientRepository;

    public DiagnosticRepository(JdbcTemplate jdbcTemplate, PatientRepository patientRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.patientRepository = patientRepository;
    }

    public Diagnostic createDiagnostic(Diagnostic diagnostic) {
        String sql = "insert into diagnostics values (?, ?, ?, ?, ?)";

        PreparedStatementCreator preparedStatementCreator = connection ->{
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setObject(2, diagnostic.getName());
            preparedStatement.setObject(3, diagnostic.getPatient().getId());
            preparedStatement.setObject(4, diagnostic.getStartDate());
            preparedStatement.setObject(5, diagnostic.getEndDate());
            return preparedStatement;
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);

        diagnostic.setId(generatedKeyHolder.getKey().intValue());

        return diagnostic;
    }

    public Optional<Diagnostic> getDiagnosticById(int id) {
        String sql = "select * from diagnostics where id = ?";

        try {
            Diagnostic diagnostic = jdbcTemplate.queryForObject(sql, new RowMapper<Diagnostic>() {
                @Override
                public Diagnostic mapRow(ResultSet resultSet, int i) throws SQLException {
                    Diagnostic diagnostic1 = new Diagnostic();
                    diagnostic1.setId(resultSet.getInt("id"));
                    diagnostic1.setName(resultSet.getString("name"));

                    Optional<Patient> patient = patientRepository.getPatientById(resultSet.getInt("patient_id"));
                    if (patient.isPresent()) {
                        diagnostic1.setPatient(patient.get());
                    }

                    diagnostic1.setStartDate(resultSet.getDate("start_date"));
                    diagnostic1.setEndDate(resultSet.getDate("end_date"));

                    return diagnostic1;
                }
            }, id);
            if (diagnostic != null)
                return Optional.of(diagnostic);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

        return Optional.empty();
    }

}
