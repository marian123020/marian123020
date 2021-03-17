

import java.sql.*;

public class DatebaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }
    public void KategorijaPrideti(Kategorija a) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.KATEGORIJA_TABLE + "(" + Const.KATEGORIJA_kategorija  + "," + Const.KATEGORIJA_pavadinimas + "," + Const.KATEGORIJA_kodas + "," + Const.KATEGORIJA_kaina + "," + Const.KATEGORIJA_subkat + ")" + "VALUES(?,?,?,?,?)";
        PreparedStatement prSt = getDbConnection().prepareStatement(insert);
        String b = "";
        for(String c : a.getSubkategorija())
        {
            b= b + c + ";";
        }
        prSt.setString(1, a.getKategorija());
        prSt.setString(2, a.getPavadinimas());
        prSt.setInt(3, a.getKodas());
        prSt.setInt(4, a.getKaina());
        prSt.setString(5, b);
        prSt.executeUpdate();
    }
    public ResultSet ArYrKategorija(Kategorija a) throws SQLException, ClassNotFoundException {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.KATEGORIJA_TABLE + " WHERE " + Const.KATEGORIJA_kategorija + "=? AND " + Const.KATEGORIJA_pavadinimas + "=? AND " + Const.KATEGORIJA_kodas + "=?";
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setString(1, a.getKategorija());
        prSt.setString(2, a.getPavadinimas());
        prSt.setInt(3, a.getKodas());
        resSet = prSt.executeQuery();
        return resSet;
    }
    public void KategorijaSalinimas(Kategorija a) throws SQLException, ClassNotFoundException {
        String select = "DELETE FROM " + Const.KATEGORIJA_TABLE + " WHERE " + Const.KATEGORIJA_kategorija + "=? AND " + Const.KATEGORIJA_pavadinimas + "=? AND " + Const.KATEGORIJA_kodas + "=?";
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setString(1, a.getKategorija());
        prSt.setString(2, a.getPavadinimas());
        prSt.setInt(3, a.getKodas());
        prSt.executeUpdate();
    }
    public ResultSet KategorijaVisasSarasas() throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM " + Const.KATEGORIJA_TABLE;
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        return prSt.executeQuery();
    }
    public void UpdateNameKategorija(String kategorija, String name, int kodas) throws SQLException, ClassNotFoundException {
        String select = "UPDATE " + Const.KATEGORIJA_TABLE + " SET " + Const.KATEGORIJA_pavadinimas + "=? WHERE " + Const.KATEGORIJA_kategorija + "=? AND " + Const.KATEGORIJA_kodas + "=?";
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setString(1, name);
        prSt.setString(2, kategorija);
        prSt.setInt(3, kodas);
        prSt.executeUpdate();
    }
    public void UpdateKodasKategorija(String kategorija, int naujas_kodas, int kodas) throws SQLException, ClassNotFoundException {
        String select = "UPDATE " + Const.KATEGORIJA_TABLE + " SET " + Const.KATEGORIJA_kodas + "=? WHERE " + Const.KATEGORIJA_kategorija + "=? AND " + Const.KATEGORIJA_kodas + "=?";
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setInt(1, kodas);
        prSt.setString(2, kategorija);
        prSt.setInt(3, naujas_kodas);
        prSt.executeUpdate();
    }
    public void UpdateKainaKategorija(String kategorija, int kaina, int kodas) throws SQLException, ClassNotFoundException {
        String select = "UPDATE " + Const.KATEGORIJA_TABLE + " SET " + Const.KATEGORIJA_kaina + "=? WHERE " + Const.KATEGORIJA_kategorija + "=? AND " + Const.KATEGORIJA_kodas + "=?";
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setInt(1, kaina);
        prSt.setString(2, kategorija);
        prSt.setInt(3, kodas);
        prSt.executeUpdate();
    }

    public ResultSet KategorijaKaina(String kategorija, int kodas) throws SQLException, ClassNotFoundException {
        String select = "SELECT " + Const.KATEGORIJA_kaina + " FROM " + Const.KATEGORIJA_TABLE + " WHERE " + Const.KATEGORIJA_kategorija + "=? AND "+ Const.KATEGORIJA_kodas +"=?";
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setString(1,kategorija);
        prSt.setInt(2,kodas);
        return prSt.executeQuery();
    }
    public ResultSet KategorijaSub(String kategorija, int kodas) throws SQLException, ClassNotFoundException {
        String select = "SELECT " + Const.KATEGORIJA_subkat + " FROM " + Const.KATEGORIJA_TABLE + " WHERE " + Const.KATEGORIJA_kategorija + "=? AND "+ Const.KATEGORIJA_kodas +"=?";
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setString(1,kategorija);
        prSt.setInt(2,kodas);
        return prSt.executeQuery();
    }
    public void UpdateSubkategorija(String kategorija, String subkategorija, int kodas) throws SQLException, ClassNotFoundException {
        String select = "UPDATE " + Const.KATEGORIJA_TABLE + " SET " + Const.KATEGORIJA_subkat + "=? WHERE " + Const.KATEGORIJA_kategorija + "=? AND " + Const.KATEGORIJA_kodas + "=?";
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setString(1, subkategorija);
        prSt.setString(2, kategorija);
        prSt.setInt(3, kodas);
        prSt.executeUpdate();
    }
    public void VartotojaPrideti(AsmuoAtsakingasUzSistema a) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.VARTOTOJAMS_TABLE + "(" + Const.VARTOTOJAMS_name  + "," + Const.VARTOTOJAMS_code + "," + Const.VARTOTOJAMS_password + ")" + "VALUES(?,?,?)";
        PreparedStatement prSt = getDbConnection().prepareStatement(insert);
        prSt.setString(1, a.getName());
        prSt.setInt(2, a.getKodas());
        prSt.setString(3, a.getPassword());
        prSt.executeUpdate();
    }
    public ResultSet ArYrVartotojas(AsmuoAtsakingasUzSistema a) throws SQLException, ClassNotFoundException {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.VARTOTOJAMS_TABLE + " WHERE " + Const.VARTOTOJAMS_name + "=? AND " + Const.VARTOTOJAMS_code + "=?";
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setString(1, a.getName());
        prSt.setInt(2, a.getKodas());
        resSet = prSt.executeQuery();
        return resSet;
    }
    public void VartotojoSalinimas(AsmuoAtsakingasUzSistema a) throws SQLException, ClassNotFoundException {
        String select = "DELETE FROM " + Const.VARTOTOJAMS_TABLE + " WHERE " + Const.VARTOTOJAMS_name + "=? AND " + Const.VARTOTOJAMS_code + "=?";
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setString(1, a.getName());
        prSt.setInt(2, a.getKodas());
        prSt.executeUpdate();
    }
    public ResultSet VartotojoPerziuretiSarasa() throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM " + Const.VARTOTOJAMS_TABLE;
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        return prSt.executeQuery();
    }
    public void UpdateNameVartotojas(String name, int kodas) throws SQLException, ClassNotFoundException {
        String select = "UPDATE " + Const.VARTOTOJAMS_TABLE + " SET " + Const.VARTOTOJAMS_name + "=? WHERE " + Const.VARTOTOJAMS_code+ "=?";
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setString(1, name);
        prSt.setInt(2, kodas);
        prSt.executeUpdate();
    }
    public void UpdateKodasVartotojas(int kodass, int kodas) throws SQLException, ClassNotFoundException {
        String select = "UPDATE " + Const.VARTOTOJAMS_TABLE + " SET " + Const.VARTOTOJAMS_code + "=? WHERE " + Const.VARTOTOJAMS_code+ "=?";
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setInt(1, kodass);
        prSt.setInt(2, kodas);
        prSt.executeUpdate();
    }
    public void UpdatePassVartotojas(String pass, int kodas) throws SQLException, ClassNotFoundException {
        String select = "UPDATE " + Const.VARTOTOJAMS_TABLE + " SET " + Const.VARTOTOJAMS_password + "=? WHERE " + Const.VARTOTOJAMS_code+ "=?";
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setString(1, pass);
        prSt.setInt(2, kodas);
        prSt.executeUpdate();
    }
}
