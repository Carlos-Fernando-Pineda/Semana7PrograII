/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.*;
import dominio.mdEmpleados;
import excepciones.*;
import java.util.*;

/**
 *
 * @author carlo
 */
public class NominaEImpl implements NominaE {
    private final AccesoDatos datos;
    private final String ErrorAcceso = "Error de acceso de datos";
    private final String ErrorLectura = "Error en la lectura de datos";
    private final String ErrorEscritura = "Error en la escriturar de datos";
    
    public NominaEImpl(){
        this.datos = new AccesoDatosImpl();
    }
    
   @Override 
   public void InicializarArchivo(String nombreArchivo){
       try {
           if(datos.existe(nombreArchivo)){
               datos.borrar(nombreArchivo);
               datos.crear(nombreArchivo);
           } else {
               datos.crear(nombreArchivo);
           }
       } catch(AccesoDatosEx ex){
           System.out.println(ErrorAcceso);
           ex.printStackTrace(System.out);
       }
   }
    
    @Override
    public void AddEmpleado(String nombreArchivo, String nombreEmpleado, Double enero, Double febrero, Double marzo){
        mdEmpleados empleado = new mdEmpleados(nombreEmpleado, enero, febrero, marzo);
        boolean anexar = false;
        try {
            anexar = datos.existe(nombreArchivo);
            datos.escribir(empleado, nombreArchivo, anexar);
            System.out.println("Ha sido registrado");
        } catch(AccesoDatosEx ex){
            System.out.println(ErrorAcceso);
            ex.printStackTrace(System.out);
        }
    }
    
    @Override
    public void MayorYMenorPorMes(String nombreArchivo, int mes){
        Double M = 0.0;
        Double m = 0.0;
        String N = null;
        String n = null;
        try {
            List<mdEmpleados> empleados = datos.listar(nombreArchivo);
            for (mdEmpleados empleado : empleados){
                String[] DatosEmpleados = empleado.toString().split("\\|");
                if(Double.valueOf(DatosEmpleados[mes]) > M){
                    M = Double.valueOf(DatosEmpleados[mes]);
                    N = DatosEmpleados[0];
                }
                if (Double.valueOf(DatosEmpleados[mes]) < m){
                    m = Double.valueOf(DatosEmpleados[mes]);
                    n = DatosEmpleados[0];
                }
            }
            if (N != null && n != null){
                System.out.println("Mayor: "+ N + " con ventas de: "+ M);
                System.out.println("\nMenor: "+ n + " con ventas de: "+ m);
            }
        } catch (LecturaDatosEx ex){
            System.out.println(ErrorLectura);
            ex.printStackTrace(System.out);
        }
    }
    
    @Override
    public void listarEmpleados(String nombreArchivo){
        try {
            List<mdEmpleados> empleados = datos.listar(nombreArchivo);
            if(empleados.size() > 0){
                empleados.forEach(empleado -> {
                    System.out.println(empleado);
                });
            }else{
                System.out.println("No se encuentran registros");
            }
        }catch(LecturaDatosEx ex){
            System.out.println(ErrorAcceso);
            ex.printStackTrace(System.out);
        }
    }
    
    @Override
    public void MayorVendedor(String nombreArchivo){
        Double M = 0.0;
        String r = null;
        try {
            List<mdEmpleados> empleados = datos.listar(nombreArchivo);
            for(mdEmpleados empleado : empleados){
                if(empleado.getTotal() > M){
                    r = empleado.getNombre() + " con ventas de: "+ empleado.getTotal();
                }
            }
            System.out.println("Mayor Vendedor: "+ r);
        }catch(AccesoDatosEx ex){
            System.out.println(ErrorAcceso);
            ex.printStackTrace(System.out);
        }
    }
    
    @Override
    public void PorCantidad(String nombreArchivo, Double cantidad){
        String r = null;
        try {
            r = datos.buscar(nombreArchivo, cantidad);
        }catch(LecturaDatosEx ex){
            System.out.println(ErrorLectura);
            ex.printStackTrace(System.out);
        }
        System.out.println("El resultado es: "+ r);
    }
    
    @Override
    public void Editar(String nombreArchivo, String DatoNuevo, int registro, int columna) {
        registro--;
        try {
            List<mdEmpleados> empleados = datos.listar(nombreArchivo);
            String nombre = empleados.get(registro).getNombre();
            Double enero = empleados.get(registro).getEnero();
            Double febrero = empleados.get(registro).getFebrero();
            Double marzo = empleados.get(registro).getMarzo();
            switch(columna){
                case 1:
                    nombre = DatoNuevo;
                    break;
                case 2:
                    enero = Double.valueOf(DatoNuevo);
                    break;
                case 3:
                    febrero = Double.valueOf(DatoNuevo);
                    break;
                case 4:
                    marzo = Double.valueOf(DatoNuevo);
                    break;
                default:
                    System.out.println("Esta columna no existe");
                    break;
            }
            mdEmpleados empleado = new mdEmpleados(nombre, enero, febrero, marzo);
            empleados.set(registro, empleado);
            boolean anexar = false;
            for (mdEmpleados linea : empleados){
                datos.escribir(linea, nombreArchivo, anexar);
                anexar = datos.existe(nombreArchivo);
            }
        } catch(LecturaDatosEx ex){
            System.out.println(ErrorLectura);
            ex.printStackTrace(System.out);
        } catch(EscrituraDatosEx ex){
            System.out.println(ErrorEscritura);
            ex.printStackTrace(System.out);
        } catch(AccesoDatosEx ex){
            System.out.println(ErrorAcceso);
            ex.printStackTrace(System.out);
        } catch(NumberFormatException ex){
            System.out.println("Ha ocurrido un error, intente otro numero");
            ex.printStackTrace(System.out);
        }
    }
            
}
