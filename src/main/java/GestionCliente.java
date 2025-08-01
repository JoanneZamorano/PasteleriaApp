import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Funciones para el menú de Clientes
 * @author Joanne Zamorano
 * @version 1.0
 */

public class GestionCliente {


    /**
     * Función para gestionar el alta de un nuevo Cliente
     */
    public static void altaCliente(){
        System.out.println("-- 1.1 GESTIÓN CLIENTES | ALTA CLIENTE-- \nIntroduce los siguientes datos:");

        System.out.print("Nombre: ");
        String nombre = Main.sc.nextLine();

        System.out.print("DNI: ");
        String dni = Main.sc.nextLine();

        for (Cliente c: Main.clientes){
            if(c.getDni().equalsIgnoreCase(dni)){
                System.out.println("Ya existe un cliente con este DNI");
                return;
            }
        }

        System.out.print("Teléfono: ");
        String telefono = Main.sc.nextLine();

        System.out.print("Email: ");
        String email = Main.sc.nextLine();

        Main.clientes.add(new Cliente(nombre, dni, telefono, email));
        System.out.println("Cliente " + nombre + " añadido correctamente.");
    }


    /**
     * Función para gestionar la baja de un cliente existente
     */
    public static void bajaCliente(){
        System.out.println("Estos son los clientes dados de alta:\n");
        for (int i = 0; i < Main.clientes.size(); i++){
            Cliente c = Main.clientes.get(i);
            System.out.println((i+1) + ".Nombre: " + c.getNombre() + "\tDNI: " + c.getDni());
        }
        System.out.println("\nIntroduce la posición del cliente a borrar: ");
        try{
            int pos = Main.sc.nextInt();
            Main.sc.nextLine();
            pos--;

            if (pos >= 0 && pos < Main.clientes.size()){
                Main.clientes.remove(pos);
                System.out.println("Se ha borrado correctamente.");
            }else{
                System.out.println("Introduce una posición valida.");
            }
        }catch (java.util.InputMismatchException e) {
            System.out.println("Error: Debes introducir un número entero. Por favor, inténtalo de nuevo.");
            Main.sc.nextLine(); // Limpiar el buffer del scanner en caso de error
        }
    }


    /**
     * Función para gestionar la modificación de un cliente existente
     */
    public static void modificarCliente(){
        System.out.println("Estos son los clientes dados de alta:\n");
        for (int i = 0; i < Main.clientes.size(); i++){
            Cliente c = Main.clientes.get(i);
            System.out.println((i+1) + ".Nombre: " + c.getNombre() + "\tDNI: " + c.getDni());
        }

        System.out.println("\nIntroduce la posición del cliente a modificar: ");
        int pos = Main.sc.nextInt();
        Main.sc.nextLine();
        pos--;

        if (pos >= 0 && pos < Main.clientes.size()) {
            Cliente clienteAModificar = Main.clientes.get(pos);

            System.out.println("Modifica su nombre (actual: " + clienteAModificar.getNombre() + "):");
            String newName = Main.sc.nextLine();
            clienteAModificar.setNombre(newName);

            System.out.println("Modifica su DNI (actual: " + clienteAModificar.getDni() + "):");
            String newID = Main.sc.nextLine();
            clienteAModificar.setDni(newID);

            System.out.println("Modifica su teléfono (actual: " + clienteAModificar.getTelefono() + "):");
            String newPhone = Main.sc.nextLine();
            clienteAModificar.setTelefono(newPhone);

            System.out.println("Modifica su email (actual: " + clienteAModificar.getEmail() + "):");
            String newEmail = Main.sc.nextLine();
            clienteAModificar.setEmail(newEmail);

        }else{
            System.out.println("Introduce una posición valida.");
        }
    }


    /**
     * Función para realizar la búsqueda de un cliente existente mediante DNI
     * @param dni DNI del Cliente
     * @return Cliente que corresponde con el DNI introducido
     */
    public static Cliente buscarClientePorDNI(String dni){

        for(Cliente c: Main.clientes){
            if (c.getDni().equalsIgnoreCase(dni)){
                return c;
            }
        }
        return null;
    }

    /**
     * Función para mostrar al cliente existente buscado por el DNI
     */
    public static void mostrarClientePorDNI(){
        System.out.print("Introduce el DNI del cliente a buscar: ");
        String dniBuscado = Main.sc.nextLine();
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


    /**
     * Función para listar a Todos los clientes existentes con sus datos, ya ordenados alfabeticamente
     */
    public static void listarClientes(){
        //Ordenar los nombres de los clientes alfabeticamente:
        Collections.sort(Main.clientes, (c1, c2) -> c1.getNombre().compareToIgnoreCase(c2.getNombre()));

        for (int i = 0; i < Main.clientes.size(); i++){
            Cliente c = Main.clientes.get(i);
            System.out.println((i+1) + ". Nombre: " + c.getNombre() + "\n\tDNI: " + c.getDni() + "\n\tTeléfono: " + c.getTelefono() + "\n\tEmail: " + c.getEmail());
            System.out.println("\t- - - - -");
        }
    }


    /**
     * Función para seleccionar cliente para realizar una venta
     * @param listaClientes muestra la lista de Todos los clientes para seleccionarlos
     * @param sc Scanner para introducir datos
     * @return cliente seleccionado
     */
    public static Cliente seleccionarCliente(ArrayList<Cliente> listaClientes, Scanner sc) {
        if (listaClientes.isEmpty()) {
            System.out.println("Error: No hay clientes, añádelos primero");
            return null;
        }

        System.out.println("Clientes disponibles:\n");
        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente c = listaClientes.get(i);
            System.out.println("\t"+ (i + 1) + ". " + c.getNombre() + " (DNI: " + c.getDni() + ")");
        }

        System.out.print("\nIntroduce el número del cliente: ");
        int numCliente = sc.nextInt();
        sc.nextLine();

        if (numCliente > 0 && numCliente <= listaClientes.size()) {
            return listaClientes.get(numCliente - 1);
        } else {
            System.out.println("Número de cliente no válido");
            return null;
        }
    }

}
