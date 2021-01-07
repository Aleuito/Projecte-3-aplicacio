
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Persona {
    
    private int dni;
    private String nombre;
    private String apellido;
    private String mail;
    private String direccion; 
    private int codipostal;
    private int telefon;
    private int cuota;
    private String contrasenya;

    public Persona(int dni, String nombre, String apellido, String mail, String direccion, int codipostal, int telefon, int cuota, String contrasenya) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.direccion = direccion;
        this.codipostal = codipostal;
        this.telefon = telefon;
        this.cuota = cuota;
        this.contrasenya = contrasenya;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getMail() {
        return mail;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCodipostal() {
        return codipostal;
    }

    public void setCodipostal(int codipostal) {
        this.codipostal = codipostal;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public int getCuota() {
        return cuota;
    }

    public void setCuota(int cuota) {
        this.cuota = cuota;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        if (this.dni != other.dni) {
            return false;
        }
        if (this.codipostal != other.codipostal) {
            return false;
        }
        if (this.telefon != other.telefon) {
            return false;
        }
        if (this.cuota != other.cuota) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellido, other.apellido)) {
            return false;
        }
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        if (!Objects.equals(this.contrasenya, other.contrasenya)) {
            return false;
        }
        return true;
    }
    
    
    public void agregar(){
        
        try {
        ConexioMysql conexion = new ConexioMysql();
        Connection conexio = conexion.getConnection();
        
        String sentencia1 = "INSERT INTO personas (dni, nombre, apellido, mail, direccion, c_postal, telefono) "
                +"values ("
                + "'"+this.getDni()+"', "
                + "'"+this.getNombre()+"', "
                + "'"+this.getApellido()+"', "
                + "'"+this.getMail()+"', "
                + "'"+this.getDireccion()+"', "
                + "'"+this.getCodipostal()+"', "
                + "'"+this.getTelefon()+"' "
                +")";
        String sentencia2 = "INSERT INTO socis (dni, cuota, contraseña) "
                +"values ("
                + "'"+this.getDni()+"', "
                + "'"+this.getCuota()+"', "
                + "'"+this.getContrasenya()+"'"
                +")";
        
        Statement sentencia = conexio.createStatement();
        ResultSet resultat = sentencia.executeQuery(sentencia1);
        ResultSet resul = sentencia.executeQuery(sentencia2);

        } catch (SQLException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
  
    public void eliminar(){
        
        try {
        ConexioMysql conexion = new ConexioMysql();
        Connection conec = conexion.getConnection();
        
        String elim1 = "DELETE FROM personas WHERE dni = '"+this.getDni()+"'";

        String elim2 = "DELETE FROM socis WHERE dni = "+this.getDni()+"'";
        
        Statement sentencia = conec.createStatement();
        ResultSet resultat = sentencia.executeQuery(elim1);
        ResultSet resul = sentencia.executeQuery(elim2);

        } catch (SQLException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    public void modificar(){
        
        try {
        ConexioMysql conexion = new ConexioMysql();
        Connection conn = conexion.getConnection();
        
        String mod1 = "UPDATE personas SET (nombre = '"+this.getNombre()+"', " 
                + "apellido = '"+this.getApellido()+"', mail = '"+this.getMail()+"', direccion = '"+this.getDireccion()+"',"
                + "c_postal = '"+this.getCodipostal()+"', cuota = '"+this.getCuota()+"'," 
                + "telefono = '"+this.getTelefon()+") WHERE dni = '"+this.getDni()+"'";

        String mod2 = "UPDATE socis SET cuota = '"+this.getCuota()+"' "
                + "WHERE dni = '"+this.getDni()+"'";
        
        Statement sentencia = conn.createStatement();
        ResultSet resultat = sentencia.executeQuery(mod1);
        ResultSet resul = sentencia.executeQuery(mod2);

        } catch (SQLException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
}
