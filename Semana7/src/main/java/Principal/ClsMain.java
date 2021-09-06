package Principal;

import java.util.*;
import negocio.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author carlo
 */
public class ClsMain {
    boolean exit;
    private static final Scanner scanner = new Scanner(System.in);
    private static final String nombreArchivo = "C:\\Users\\carlo\\OneDrive\\Documentos\\Universidad\\Cuarto Semestre\\Programación II\\Pruebas\\empleados.txt";
    private static final NominaE empleados = new NominaEImpl(); 
    
    public static void main(String[] args) {
        ClsMain menu = new ClsMain();
        menu.correrMenu();
    }
    
    public void correrMenu(){
        while(!exit){
        System.out.println("\n Haga una seleccion: ");
        System.out.println("\n1) Agregar empleado. ");
        System.out.println("\n2) Mayor y Menor Vendedor por Mes. ");
        System.out.println("\n4) Mayor Vendedor. ");
        System.out.println("\n5) Editar. ");
        System.out.println("\n6) Buscar por Cantidad. ");
        System.out.println("\n7) Listar informacion. ");
        System.out.println("\n0) Salir. ");
            int op = getInput();
            realizarAccion(op);
        }
    }
    private int getInput(){
        Scanner kb = new Scanner(System.in);
        int op = -1;
        while(op < 0 || op > 7){
            try {
                op = Integer.parseInt(kb.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Seleccion Invalida. Intente de nuevo.");
            }
        }
        return op;
    }
    
    private void realizarAccion(int op){
        switch(op){
            case 0:
                exit = true;
                System.out.println("Gracias! tenga un buen dia.");
                break;
            case 1:
                empleados.InicializarArchivo(nombreArchivo);
                break;
            case 2:
                AgregarEmpleado();
                break;
            case 3:
                MayorYMenor();
                break;
            case 4:
                empleados.MayorVendedor(nombreArchivo);
                break;
            case 5:
                EditarRegistros();
                break;
            case 6:
                BuscarPorCantidad();
                break;
            case 7:
                empleados.listarEmpleados(nombreArchivo);
                break;
            default:
                System.out.println("Un error ha ocurrido. ");
                break;
        }
    }
    public void MayorYMenor(){
        int mes;
        System.out.println("Ingrese el mes: ");
        System.out.println("\n1) Enero. \n2) Febrero. \n3) Marzo.");
        mes = Integer.parseInt(scanner.nextLine());
        empleados.MayorYMenorPorMes(nombreArchivo, mes);
    }
    public void AgregarEmpleado(){
        String nombre;
        Double enero;
        Double febrero;
        Double marzo;
        System.out.println("Ingrese el nombre: ");
        nombre = scanner.nextLine();
        System.out.println("Ingrese enero: ");
        enero = scanner.nextDouble();
        System.out.println("Ingrese febrero: ");
        febrero = scanner.nextDouble();
        System.out.println("Ingrese Marzo: ");
        marzo = scanner.nextDouble();
        empleados.AddEmpleado(nombreArchivo, nombre, enero, febrero, marzo);
        scanner.nextLine();
    }
    public void EditarRegistros(){
        String nuevo;
        int registro, columna;
        System.out.println("Ingrese el no. de registro: ");
        registro = Integer.parseInt(scanner.nextLine());
        System.out.println("¿Que dato desea editar?");
        System.out.println("\n1) Nombre. \n2)Enero. \n3)Febrero. \n4)Marzo.");
        columna = Integer.parseInt(scanner.nextLine());
        System.out.println("Ingrese su nuevo dato: ");
        nuevo = scanner.nextLine();
        empleados.Editar(nombreArchivo, nuevo, registro, columna);
    }
    public void BuscarPorCantidad(){
        Double numero;
        System.out.println("Ingrese la cantidad: ");
        numero = scanner.nextDouble();
        scanner.nextLine();
        empleados.PorCantidad(nombreArchivo, numero);
    }
}
