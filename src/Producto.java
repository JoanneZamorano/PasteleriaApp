import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Producto {
    static Scanner sc = new Scanner(System.in);


    private String tipoBollo;
    private String sabor;
    private double precio;


    //Constructor:
    public Producto(String tipoBollo, String sabor, double precio) {
        this.tipoBollo = tipoBollo;
        this.sabor = sabor;
        this.precio = precio;
    }


    //Getters
    public String getTipoBollo() {
        return tipoBollo;
    }

    public String getSabor() {
        return sabor;
    }

    public double getPrecio() {
        return precio;
    }

    public void setTipoBollo(String tipoBollo) {
        this.tipoBollo = tipoBollo;
    }


    //Setters
    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


}
