import java.util.Scanner;

/**
 * Clase Producto
 * @author Joanne Zamorano
 * @version 1.0
 */
public class Producto {
    static Scanner sc = new Scanner(System.in);

    private String tipoBollo;
    private String sabor;
    private double precio;


    //Constructor:

    /**
     * Constructor de la clase Producto
     * @param tipoBollo Tipo de Producto (tarta, galleta...)
     * @param sabor Sabor del Producto (Vainilla, chocolate...)
     * @param precio Precio del Producto
     */
    public Producto(String tipoBollo, String sabor, double precio) {
        this.tipoBollo = tipoBollo;
        this.sabor = sabor;
        this.precio = precio;
    }


    //Getters

    /**
     * Devuelve el tipo de Producto (tarta, galleta...)
     * @return Tipo de bollo
     */
    public String getTipoBollo() {
        return tipoBollo;
    }

    /**
     * Devuelve el sabor del Producto (vainilla, chocolate...)
     * @return Sabor del Producto
     */
    public String getSabor() {
        return sabor;
    }

    /**
     * Devuelve el Precio del Producto
     * @return Precio Producto
     */
    public double getPrecio() {
        return precio;
    }


    //Setters -- No utilizados, no tengo función para modificar los productos:
    /**
     * Método para modificar el tipo de producto
     * @param tipoBollo nuevo tipo de producto
     */
    public void setTipoBollo(String tipoBollo) {
        this.tipoBollo = tipoBollo;
    }

    /**
     * Método para modificar el sabor del producto
     * @param sabor nuevo sabor del producto
     */
    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    /**
     * Método para modificar el precio del producto
     * @param precio Nuevo precio del producto
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }


}
