import java.util.ArrayList;

public class Venta {
    /*
    private Cliente cliente; private ArrayList<Pajaro> lineasDeVenta; String fecha;
    // cada venta contiene varios pájaros private
    // Constructor, mét-odo para añadir producto, calcular total, etc.*/
    private Cliente cliente;
    private ArrayList<Producto> lineasDeVenta;
    private String fecha;


    //Constructor
    public Venta(Cliente cliente, ArrayList<Producto> lineasDeVenta, String fecha) {
        this.cliente = cliente;
        this.lineasDeVenta = new ArrayList<>();
        this.fecha = fecha;
    }

    //Getters
    public Cliente getCliente() {
        return cliente;
    }
    public ArrayList<Producto> getLineasDeVenta() {
        return lineasDeVenta;
    }
    public String getFecha() {
        return fecha;
    }

    //Métodos
    public void añadirProducto(Producto p){
        this.lineasDeVenta.add(p);
    }

    public void generarTicket(){
        System.out.println("Cliente: " + this.cliente.getNombre() + "\nFecha: " + this.fecha + "\nProductos:\n");

        for (Producto p: lineasDeVenta){
            System.out.println(" - " + p.getTipoBollo() + "\t" + p.getSabor() + "\t" + p.getPrecio() +" €");
        }

        System.out.println("----Total: ");
    }

}
