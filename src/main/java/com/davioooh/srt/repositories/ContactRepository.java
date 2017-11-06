package com.davioooh.srt.repositories;

import com.davioooh.srt.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

@Repository
public class ContactRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Contact> findAll() {
        return jdbcTemplate.query(
                "select * from contacts",
                new ContactRowMapper());
    }

    public Contact findById(long id) {
        return jdbcTemplate.queryForObject(
                "select * from contacts where id = ?",
                new ContactRowMapper(),
                id);
    }

    public Contact create(Contact contact)
    {
        String sql = "insert into contacts (first_name, last_name, phone, email) values (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement
                        = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, contact.getFirstName());
                preparedStatement.setString(2, contact.getLastName());
                preparedStatement.setString(3, contact.getPhone());
                preparedStatement.setString(4, contact.getEmail());
                return preparedStatement;
            }
        }, keyHolder);

        contact.setId(keyHolder.getKey().longValue());
        return contact;
    }

    //

    static class ContactRowMapper implements RowMapper<Contact> {
        @Override
        public Contact mapRow(ResultSet resultSet, int i) throws SQLException {
            Contact contact = new Contact();
            contact.setId(resultSet.getLong("id"));
            contact.setFirstName(resultSet.getString("first_name"));
            contact.setLastName(resultSet.getString("last_name"));
            contact.setPhone(resultSet.getString("phone"));
            contact.setEmail(resultSet.getString("email"));
            return contact;
        }
    }
}
