public class Producto {
    /*
    private String tipoBollo; private String sabor; private double precio;
    // Constructor, getters y setters*/

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

    //Métodos:
    public void listarProductos(){
        System.out.println("Producto: " + this.tipoBollo + "\nSabor: " + this.sabor + "\nPrecio: " + this.precio);
        System.out.println("\t- - - - -");
    }



}
