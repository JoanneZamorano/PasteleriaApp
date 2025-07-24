import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Venta {
    /*
    private Cliente cliente; private ArrayList<Pajaro> lineasDeVenta; String fecha;
    // cada venta contiene varios pájaros private
    // Constructor, mét-odo para añadir producto, calcular total, etc.*/
    private Cliente cliente;
    private ArrayList<Producto> lineasDeVenta;
    private String fecha;

    private double totalVenta;
    private String idVenta;

    //Constructor
    public Venta(Cliente cliente, String fecha) {
        this.cliente = cliente;
        this.lineasDeVenta = new ArrayList<>();
        this.fecha = fecha;

        this.idVenta = idVenta;
        this.totalVenta = 0.0;

        //this.productosVendidos = new ArrayList<>();
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
    public String getIdVenta() {
        return idVenta;
    }
    public double getTotalVenta() {
        return totalVenta;
    }


    public void addProducto(Producto producto) {
        this.lineasDeVenta.add(producto);
        calcularTotal();
    }

    private void calcularTotal() {
        this.totalVenta = 0.0;
        for (Producto p : lineasDeVenta) {
            this.totalVenta += p.getPrecio();
        }
    }

    public void mostrarTicket() {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("\n--- TICKET DE VENTA ---");
        //System.out.println("Fecha: " + fechaVenta.format(formatter));
        System.out.println("Cliente: " + cliente.getNombre() + " (DNI: " + cliente.getDni() + ")");
        System.out.println("Productos:");
        if (lineasDeVenta.isEmpty()) {
            System.out.println("  (No hay productos en esta venta)");
        } else {
            for (int i = 0; i < lineasDeVenta.size(); i++) {
                Producto p = lineasDeVenta.get(i);
                System.out.println((i + 1) + ". " + p.getTipoBollo() + "\t - " + p.getSabor() + "\t - " + p.getPrecio() + "€");
            }
        }
        System.out.println("\nTOTAL:" + totalVenta + "€");
        System.out.println("------------------------------------");
    }



}
