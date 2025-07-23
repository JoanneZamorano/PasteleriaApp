import java.util.ArrayList;
import java.util.Scanner;


public class Cliente {
    static Scanner sc = new Scanner(System.in);

    private String nombre;
    private String dni;
    private String telefono;
    private String email;

    //Constructor:
    public Cliente(String nombre, String dni, String telefono, String email) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
    }

    //getters:
    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }


    //Setters:
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Métodos

    //----1 ALTA CLIENTE---------------------------------------------------------------------------
    public static void altaCliente(){
        System.out.println("-- 1.1 GESTIÓN CLIENTES | ALTA CLIENTE-- \nIntroduce los siguientes datos:");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("DNI: ");
        String dni = sc.nextLine();
        /* Para comprobar si hay otro cliente igual:

        for (Cliente c: clientes){
            if(c.getDni().equalsIgnoreCase(dni)){
            System.out.println("Ya existe un cliente con este DNI");
            return;
            }
        }*/
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        Main.clientes.add(new Cliente(nombre, dni, telefono, email));
        sc.nextLine();
        System.out.println("Cliente " + nombre + " añadido correctamente.");
    }
    //----2 BAJA CLIENTE---------------------------------------------------------------------------
    public static void bajaCliente(){
        System.out.println("Estos son los clientes dados de alta:\n");
        for (int i = 0; i < Main.clientes.size(); i++){
            Cliente c = Main.clientes.get(i);
            System.out.println((i+1) + ".Nombre: " + c.getNombre() + "\tDNI: " + c.getDni());
        }
        System.out.println("\nIntroduce la posición del cliente a borrar: ");
        int pos = sc.nextInt();
        sc.nextLine();
        pos--;

        if (pos >= 0 && pos < Main.clientes.size()){
            Main.clientes.remove(pos);
            System.out.println("Se ha borrado correctamente.");
        }else{
            System.out.println("Introduce una posición valida.");
        }
    }

    //----3 MODIFICAR CLIENTE---------------------------------------------------------------------------
    public static void modificarCliente(){
        System.out.println("Estos son los clientes dados de alta:\n");
        for (int i = 0; i < Main.clientes.size(); i++){
            Cliente c = Main.clientes.get(i);
            System.out.println((i+1) + ".Nombre: " + c.getNombre() + "\tDNI: " + c.getDni());
        }

        System.out.println("\nIntroduce la posición del cliente a modificar: ");
        int pos = sc.nextInt();
        sc.nextLine();
        pos--;

        if (pos >= 0 && pos < Main.clientes.size()) {
            Cliente clienteAModificar = Main.clientes.get(pos);

            System.out.println("Modifica su nombre (actual: " + clienteAModificar.getNombre() + "):");
            String newName = sc.nextLine();
            clienteAModificar.setNombre(newName);

            System.out.println("Modifica su DNI (actual: " + clienteAModificar.getDni() + "):");
            String newID = sc.nextLine();
            clienteAModificar.setDni(newID);

            System.out.println("Modifica su teléfono (actual: " + clienteAModificar.getTelefono() + "):");
            String newPhone = sc.nextLine();
            clienteAModificar.setTelefono(newPhone);

            System.out.println("Modifica su email (actual: " + clienteAModificar.getEmail() + "):");
            String newEmail = sc.nextLine();
            clienteAModificar.setEmail(newEmail);

        }else{
            System.out.println("Introduce una posición valida.");
        }
    }

    //----4 BUSCAR CLIENTE POR DNI---------------------------------------------------------------------------
    public static Cliente buscarClientePorDNI(String dni){

        for(Cliente c: Main.clientes){
            if (c.getDni().equals(dni)){
                return c;
            }
        }
        return null;
    }

    //----5 LISTAR CLIENTE---------------------------------------------------------------------------
    public static void listarClientes(){
        for (int i = 0; i < Main.clientes.size(); i++){
            Cliente c = Main.clientes.get(i);
            System.out.println((i+1) + ". Nombre: " + c.getNombre() + "\nDNI: " + c.getDni() + "\nTeléfono: " + c.getTelefono() + "\nEmail: " + c.getEmail());
            System.out.println("\t- - - - -");
        }

    }













}
