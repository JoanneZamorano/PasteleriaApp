import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Clase Venta
 * @author Joanne Zamorano
 * @version 1.0
 */

public class Venta {

    private Cliente cliente;
    private ArrayList<Producto> lineasDeVenta;
    private String fecha;

    private double totalVenta;

    //Constructor
    /**
     * Constructor de la clase Venta
     * @param cliente Cliente al que se le hace la venta
     */
    public Venta(Cliente cliente) {
        this.cliente = cliente;
        this.lineasDeVenta = new ArrayList<>();

        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.fecha = hoy.format(formato);

        this.totalVenta = 0.0;
    }


    //Getters
    /**
     * Devuelve el Cliente de la venta
     * @return Cliente de la venta
     */
    public Cliente getCliente() {

        return this.cliente;
    }

    /**
     * Devuelve la lista de productos de la venta
     * @return líneas de Venta (1 o más productos de la venta)
     */
    public ArrayList<Producto> getLineasDeVenta() {

        return this.lineasDeVenta;
    }

    /**
     * Devuelve la fecha de la venta
     * @return Fecha de la venta
     */
    public String getFecha() {

        return this.fecha;
    }

    /**
     * Devuelve el importe total de la venta
     * @return Importe total de la venta
     */
    public double getTotalVenta() {

        return totalVenta;
    }


    //Métodos
    /**
     * Añadir un tipo de bollo (Producto) a la Venta
     * @param producto Producto que se añade a la venta
     */
    public void addProducto(Producto producto) {
        this.lineasDeVenta.add(producto);
        calcularTotal();
    }



    /**
     * Calcular el importe TOTAL de la Venta
     */
    private void calcularTotal() {
        this.totalVenta = 0.0;
        for (Producto p : lineasDeVenta) {
            this.totalVenta += p.getPrecio();
        }
    }

    /**
     * Obtener un "Ticket" de la venta donde se imprime:
     * Fecha de la venta, Cliente que compró, DNI del cliente, lineas de venta, Importe total Venta
     */
    public void mostrarTicket() {


        System.out.println("---------------------------");
        System.out.println("Fecha: " + getFecha() + " | Cliente: " + cliente.getNombre() + " | DNI: " + cliente.getDni());

        System.out.println("Productos:");
            for (int i = 0; i < lineasDeVenta.size(); i++) {
                Producto p = lineasDeVenta.get(i);
                System.out.println("\t" + (i + 1) + ". " + p.getTipoBollo() + "\t - " + p.getSabor() + "\t - " + p.getPrecio() + "€");
            }

        System.out.printf("\nTOTAL: %.2f €", totalVenta);
        System.out.println("\n---------------------------");
    }


}
