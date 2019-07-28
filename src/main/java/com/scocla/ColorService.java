package com.scocla;

import java.util.List;
import java.sql.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/colorService")
public class ColorService {

    private ColorDao colorDao = new ColorDao();
    private int currentColorInt;
    private String error;

    public ColorService() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://darpin.interserver.net/darpin_LED_Controller","darpin_LED_API","password1234");

            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("select * from current_state");
            while(rs.next()) {
                currentColorInt = rs.getInt(1);
            }
            connection.close();

        } catch (ClassNotFoundException e) {
            error = e.getMessage() + " - class not found\n";
            e.printStackTrace();
        } catch (SQLException e) {
            error = e.getMessage() + " - sql\n";
            e.printStackTrace();
        }
    }


    @GET
    @Path("/color")
    @Produces(MediaType.APPLICATION_XML)
    public Color getColorXml() {
        Color currentColor = new Color();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://darpin.interserver.net/darpin_LED_Controller",
                    "darpin_LED_API",
                    "password1234");

            Statement stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery("select * from current_state");
            while(rs.next()) {
                currentColor.setStatus(rs.getBoolean(3));
                currentColor.setColorInt(rs.getInt(1));
                currentColor.setBrightness(rs.getInt(2));
            }
            connection.close();

        } catch (ClassNotFoundException e) {
            error = e.getMessage() + " - class not found\n";
            e.printStackTrace();
        } catch (SQLException e) {
            error = e.getMessage() + " - sql\n";
            e.printStackTrace();
        }

        if (currentColor.getColorInt() == 0)
            return new Color(true, 9895880, 100);
        else
            return currentColor;
    }


    @GET
    @Path("/presets")
    @Produces(MediaType.APPLICATION_XML)
    public List<Color> getColorsXML() {
        return colorDao.getAllColors();
    }


    @GET
    @Path("set/{color}/{brightness}/{state}")
    public String setColor(@PathParam("color") int color,
                         @PathParam("brightness") int brightness,
                         @PathParam("state") boolean state) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://darpin.interserver.net/darpin_LED_Controller",
                    "darpin_LED_API",
                    "password1234");

            PreparedStatement stmt = connection.prepareStatement(
                    "update current_state set color=?, brightness=?, state=? where color=?");
            stmt.setInt(1, color);
            stmt.setInt(2, brightness);
            stmt.setBoolean(3, state);
            stmt.setInt(4, currentColorInt);
            int i = stmt.executeUpdate();

            connection.close();

            currentColorInt = color;

            return "entries updated: "+i;

        } catch (ClassNotFoundException e) {
            error = e.getMessage() + " - class not found\n";
            e.printStackTrace();
            return error;
        } catch (SQLException e) {
            error = e.getMessage() + " - sql\n";
            e.printStackTrace();
            return error;
        }
    }


    @GET
    @Path("set/state/{state}")
    public String setState(@PathParam("state") boolean state) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://darpin.interserver.net/darpin_LED_Controller",
                    "darpin_LED_API",
                    "password1234");

            PreparedStatement stmt = connection.prepareStatement(
                    "update current_state set state=? where color=?");
            stmt.setBoolean(1, state);
            stmt.setInt(2, currentColorInt);
            int i = stmt.executeUpdate();

            connection.close();

            return "entries updated: " + i;

        } catch (ClassNotFoundException e) {
            error = e.getMessage() + " - class not found\n";
            e.printStackTrace();
            return error;
        } catch (SQLException e) {
            error = e.getMessage() + " - sql\n";
            e.printStackTrace();
            return error;
        }
    }

    @GET
    @Path("set/brightness/{brightness}")
    public String setBrightness(@PathParam("brightness") int brightness) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://darpin.interserver.net/darpin_LED_Controller",
                    "darpin_LED_API",
                    "password1234");

            PreparedStatement stmt = connection.prepareStatement(
                    "update current_state set brightness=? where color=?");
            stmt.setInt(1, brightness);
            stmt.setInt(2, currentColorInt);
            int i = stmt.executeUpdate();

            connection.close();

            return "entries updated: " + i;

        } catch (ClassNotFoundException e) {
            error = e.getMessage() + " - class not found\n";
            e.printStackTrace();
            return error;
        } catch (SQLException e) {
            error = e.getMessage() + " - sql\n";
            e.printStackTrace();
            return error;
        }
    }


    @GET
    @Path("err")
    public String getErr() {
        error += " " + currentColorInt;
        return error;
    }
}
