package com.nmt.dao;

import com.nmt.model.City;
import com.nmt.model.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAO implements ICityDAO{
    private String jdbcURL ="jdbc:mysql://localhost:3306/city?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String SELECT_ALL_CITYS = "select * from city";
    private static final String INSERT_CITYS_SQL= "INSERT INTO city" + "  (name, area, population, GDP, description, country) VALUES "
            + " (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_CITY_BY_ID = "select id,name, area, population, GDP, description, country from city where id =?";
    private static final String DELETE_CITYS_SQL = "delete from city where id = ?;";
    private static final String UPDATE_CITYS_SQL = "update city set name = ?,area = ?, population =?, GDP = ?, description = ?, country = ? where id = ?;";
    private static final String SELECT_COUNT = "select count(*) from city where name like ?";

    public CityDAO() {
    }

    protected Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public City selectCity(int id){
        City city = null;
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CITY_BY_ID);){
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                Double area = rs.getDouble("area");
                Double population = rs.getDouble("population");
                Float GDP = rs.getFloat("GDP");
                String description = rs.getString("description");
                String country = rs.getString("country");
                city = new City(id,name,area,population,GDP,description,country);
            }
        }catch (SQLException e) {
            printSQLException(e);
        }
        return city;
    }

    public List<City> selectAllCitys(){
        List<City> citys = new ArrayList<>();
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CITYS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Double area = rs.getDouble("area");
                Double population = rs.getDouble("population");
                Float GDP = rs.getFloat("GDP");
                String description = rs.getString("description");
                String country = rs.getString("country");
                citys.add(new City(id,name,area,population,GDP,description,country));
            }
        }catch (SQLException e) {
            printSQLException(e);
        }
        return citys;
    }

    public List<Country> showCountry(){
        List<Country> countrys = new ArrayList<>();
        try(Connection connection = getConnection() ; PreparedStatement preparedStatement = connection.prepareStatement("select * from country");){
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int countryID = rs.getInt("countryID");
                String name = rs.getString("name");
                countrys.add(new Country(countryID,name));
            }
        }catch (SQLException e) {
            printSQLException(e);
        }
        return countrys;
    }

    public int count(String search) {
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNT);){
            preparedStatement.setString(1,"%"+search+"%");
//            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                return rs.getInt(1);
            }
        }catch (SQLException e) {
            printSQLException(e);
        }
        return 0;
    }

//    public static void main(String[] args) {
//        CityDAO DAO = new CityDAO();
//        int counts = DAO.count("o");
//        System.out.println(counts);
//    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public void insertCity(City city) throws SQLException{
        System.out.println(INSERT_CITYS_SQL);
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CITYS_SQL);){
            preparedStatement.setString(1,city.getName());
            preparedStatement.setDouble(2,city.getArea());
            preparedStatement.setDouble(3,city.getPopulation());
            preparedStatement.setFloat(4,city.getGDP());
            preparedStatement.setString(5,city.getDescription());
            preparedStatement.setString(6,city.getCountry());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            printSQLException(e);
        }
    }

    public boolean deleteCity(int id) throws SQLException{
        boolean rowDeleted;
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CITYS_SQL);){
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() >0 ;
        }
        return rowDeleted;
    }

    public boolean updateCity(City city) throws SQLException{
        boolean rowUpdated;
        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CITYS_SQL);){
            preparedStatement.setString(1,city.getName());
            preparedStatement.setDouble(2,city.getArea());
            preparedStatement.setDouble(3,city.getPopulation());
            preparedStatement.setFloat(4,city.getGDP());
            preparedStatement.setString(5,city.getDescription());
            preparedStatement.setString(6,city.getCountry());
            preparedStatement.setInt(7,city.getId());

            rowUpdated = preparedStatement.executeUpdate() >0;
        }
        return rowUpdated;
    }
}
