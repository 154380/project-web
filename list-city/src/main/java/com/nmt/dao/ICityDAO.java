package com.nmt.dao;

import com.nmt.model.City;

import java.sql.SQLException;
import java.util.List;

public interface ICityDAO {
    public void insertCity(City city) throws SQLException;

    public City selectCity(int id);

    public List<City> selectAllCitys();

    public boolean deleteCity(int id) throws SQLException;

    public boolean updateCity(City city) throws SQLException;

    public int count(String search);
}
