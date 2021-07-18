package com.nmt.controller;

import com.nmt.dao.CityDAO;
import com.nmt.model.City;
import com.nmt.model.Country;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CityServlet", urlPatterns = "/city")
public class CityServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CityDAO cityDAO;
    private String txtSearch="";

    public void init(){
        cityDAO = new CityDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null) action = "";
        try{
            switch (action){
                case "create":
                    showNewForm(request,response);
                    break;
                case "edit":
                    showEditForm(request,response);
                    break;
                case "delete":
                    showDeleteForm(request,response);
                    break;
                case "show":
                    showInformation(request,response);
                    break;
                case "search":
                    showSearchCity(request,response);
                    break;
                default:
                    listCity(request,response);
                    break;
            }
        }catch (SQLException ex){
            throw new ServletException(ex);
        }
    }

    private void showSearchCity(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        request.setAttribute("countPage",paging(txtSearch));
        RequestDispatcher dispatcher = request.getRequestDispatcher("city/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showInformation(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        City showCity = cityDAO.selectCity(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("city/show.jsp");
        request.setAttribute("city",showCity);
        dispatcher.forward(request, response);
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("city/delete.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteCity(HttpServletRequest request, HttpServletResponse response)  throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        cityDAO.deleteCity(id);
        List<City> listCity = cityDAO.selectAllCitys();
        request.setAttribute("listCity",listCity);
        RequestDispatcher dispatcher = request.getRequestDispatcher("city/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        City existingCity = cityDAO.selectCity(id);
        List<Country> listCountry = cityDAO.showCountry();
        request.setAttribute("listCountry",listCountry);
        RequestDispatcher dispatcher = request.getRequestDispatcher("city/edit.jsp");
        request.setAttribute("city", existingCity);
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Country> listCountry = cityDAO.showCountry();
        request.setAttribute("listCountry",listCountry);
        RequestDispatcher dispatcher = request.getRequestDispatcher("city/create.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null) action = "";
        try{
            switch (action) {
                case "create":
                    insertCity(request,response);
                    break;
                case "edit":
                    updateCity(request,response);
                    break;
                case "delete":
                    deleteCity(request,response);
                    break;
                case "show":
                    showCity(request,response);
                    break;
                case "search":
                    searchCity(request,response);
            }
        }catch (SQLException ex){
            throw new ServletException(ex);
        }
    }

    private void searchCity(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        txtSearch = request.getParameter("txtSearch");
        RequestDispatcher dispatcher = request.getRequestDispatcher("city/list.jsp");
        dispatcher.forward(request, response);
    }

    private int paging( String txtsearch){
        int count = cityDAO.count(txtsearch);
        int pageSize = 3;
        int endPage = 0;
        endPage = count / pageSize;
        if(count % pageSize !=0) endPage++;
        return endPage;
    }

    private void showCity(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("city/show.jsp");
        dispatcher.forward(request, response);
    }

    private void updateCity(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        Double area = Double.parseDouble(request.getParameter("area"));
        Double population = Double.parseDouble(request.getParameter("population"));
        Float GDP = Float.parseFloat(request.getParameter("GDP"));
        String description = request.getParameter("description");
        City book = new City(id,name,area,population,GDP,description,country);
        cityDAO.updateCity(book);
        List<Country> listCountry = cityDAO.showCountry();
        request.setAttribute("listCountry",listCountry);
        List<City> listCity = cityDAO.selectAllCitys();
        request.setAttribute("listCity",listCity);
        RequestDispatcher dispatcher = request.getRequestDispatcher("city/list.jsp");
        dispatcher.forward(request, response);
    }

    private void insertCity(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        Double area = Double.parseDouble(request.getParameter("area"));
        Double population = Double.parseDouble(request.getParameter("population"));
        Float GDP = Float.parseFloat(request.getParameter("GDP"));
        String description = request.getParameter("description");
        City newCity = new City(name,area,population,GDP,description,country);
        cityDAO.insertCity(newCity);
        List<Country> listCountry = cityDAO.showCountry();
        request.setAttribute("listCountry",listCountry);
        RequestDispatcher dispatcher = request.getRequestDispatcher("city/create.jsp");
        dispatcher.forward(request, response);
    }

    private void listCity(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<City> listCity = cityDAO.selectAllCitys();
        request.setAttribute("listCity",listCity);
        request.setAttribute("countPage",paging(txtSearch));
        RequestDispatcher dispatcher = request.getRequestDispatcher("city/list.jsp");
        dispatcher.forward(request, response);
    }
}
