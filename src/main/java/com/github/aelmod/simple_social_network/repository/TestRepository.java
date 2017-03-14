package com.github.aelmod.simple_social_network.repository;

import com.github.aelmod.simple_social_network.model.City;
import com.github.aelmod.simple_social_network.model.Country;
import com.github.aelmod.simple_social_network.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class TestRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    public User getUserById(int id) {
        //Timestamp.valueOf()
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(
                sql, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
    }

    public User getUser(String id) {
        //Timestamp.valueOf()
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(
                sql, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
    }



    public void saveUser(User user) {
        String sql = "INSERT INTO users" +
                "(name, nickname, password, birthday, email, phone, countryId, cityId, address)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        jdbcTemplate.update(sql, user.getName(), user.getNickname(), user.getPassword(), user.getBirthday(),
                user.getEmail(), user.getPhone(), user.getCountryId(), user.getCityId(), user.getAddress());
    }

    public List<Country> getCountry() {
        String sql = "SELECT * FROM countries";
        ArrayList<Country> countries = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            Country country = new Country();
            country.setId((int) row.get("id"));
            country.setName((String) row.get("name"));
            countries.add(country);
        }
        return countries;
    }

    public List<City> getCities() {
        String sql = "SELECT * FROM cities";
        ArrayList<City> cities = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : rows) {
            City city = new City();
            city.setId((int) row.get("id"));
            city.setName((String) row.get("name"));
            cities.add(city);
        }
        return cities;
    }
}
