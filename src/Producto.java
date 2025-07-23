import javax.swing.plaf.synth.SynthOptionPaneUI;
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

    //Métodos:

    //----1 ALTA PRODUCTO---------------------------------------------------------------------------
    public static void altaProducto(){
        System.out.println("-- 1.1 GESTIÓN PRODUCTOS | ALTA PRODUCTO -- \nIntroduce los siguientes datos:");

        System.out.print("Tipo producto: ");
        String tipoBollo = sc.nextLine();

        System.out.print("Sabor: ");
        String sabor = sc.nextLine();

        System.out.print("Precio: ");
        Double precio = sc.nextDouble();
        sc.nextLine();

        Main.productos.add(new Producto(tipoBollo, sabor, precio));
        System.out.println("Producto " + tipoBollo + " añadido correctamente.");
    }


    //----2 LISTAR PRODUCTO---------------------------------------------------------------------------
    public static void listarProducto(){
        System.out.println("-- 1.2 GESTIÓN PRODUCTOS | LISTAR PRODUCTOS --");

        for (int i = 0; i < Main.productos.size(); i++){
            Producto p = Main.productos.get(i);
            System.out.println((i+1) + ". Producto: " + p.getTipoBollo() +
                    "\n\tSabor: " + p.getSabor() +
                    "\n\tPrecio: " + p.getPrecio());
            System.out.println("\t- - - - -");
        }
    }


    //----3 BUSCAR PRODUCTO POR SABOR---------------------------------------------------------------------------
    public static List<Producto> buscarProductoPorSabor(String sabor) {
        List<Producto> productosEncontrados = new ArrayList<>();
        for (Producto p : Main.productos) {
            if (p.getSabor().equalsIgnoreCase(sabor)) {
                productosEncontrados.add(p);
            }
        }
        return productosEncontrados;
    }

}
