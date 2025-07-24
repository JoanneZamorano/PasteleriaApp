import java.util.ArrayList;
import java.util.Scanner;

public class GestionCliente {
    static Scanner sc = new Scanner(System.in);

    //----1 ALTA CLIENTE---------------------------------------------------------------------------
    public static void altaCliente(){
        System.out.println("-- 1.1 GESTIÓN CLIENTES | ALTA CLIENTE-- \nIntroduce los siguientes datos:");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("DNI: ");
        String dni = sc.nextLine();

        for (Cliente c: Main.clientes){
            if(c.getDni().equalsIgnoreCase(dni)){
                System.out.println("Ya existe un cliente con este DNI");
                return;
            }
        }

        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        Main.clientes.add(new Cliente(nombre, dni, telefono, email));
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
            if (c.getDni().equalsIgnoreCase(dni)){
                return c;
            }
        }
        return null;
    }

    public static void mostrarClientePorDNI(){
        System.out.print("Introduce el DNI del cliente a buscar: ");
        String dniBuscado = sc.nextLine();
        Cliente clienteEncontrado = GestionCliente.buscarClientePorDNI(dniBuscado);

        if (clienteEncontrado != null) {
            System.out.println("\n--- Cliente Encontrado ---");
            System.out.println("Nombre: " + clienteEncontrado.getNombre());
            System.out.println("DNI: " + clienteEncontrado.getDni());
            System.out.println("Teléfono: " + clienteEncontrado.getTelefono());
            System.out.println("Email: " + clienteEncontrado.getEmail());
            System.out.println("--------------------------\n");
        } else {
            System.out.println("No se encontró ningún cliente con el DNI: " + dniBuscado + "\n");
        }
    }


    //----5 LISTAR CLIENTE---------------------------------------------------------------------------
    public static void listarClientes(){
        for (int i = 0; i < Main.clientes.size(); i++){
            Cliente c = Main.clientes.get(i);
            System.out.println((i+1) + ". Nombre: " + c.getNombre() + "\n\tDNI: " + c.getDni() + "\n\tTeléfono: " + c.getTelefono() + "\n\tEmail: " + c.getEmail());
            System.out.println("\t- - - - -");
        }

    }


    //6. seleccionar cliente para venta:
    public static Cliente seleccionarCliente(ArrayList<Cliente> listaClientes, Scanner sc) {
        if (listaClientes.isEmpty()) {
            System.out.println("No hay clientes, añade clientes primero");
            return null;
        }

        System.out.println("\n--- SELECCIONAR CLIENTE PARA LA VENTA ---");
        System.out.println("Clientes disponibles:");
        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente c = listaClientes.get(i);
            System.out.println((i + 1) + ". " + c.getNombre() + " (DNI: " + c.getDni() + ")");
        }

        System.out.print("Introduce el número del cliente: ");
        int numCliente = sc.nextInt();
        sc.nextLine();

        if (numCliente > 0 && numCliente <= listaClientes.size()) {
            return listaClientes.get(numCliente - 1);
        } else {
            System.out.println("Número de cliente inválido.");
            return null;
        }
    }

}
