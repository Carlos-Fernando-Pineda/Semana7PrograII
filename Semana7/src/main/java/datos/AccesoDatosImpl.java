/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import dominio.*;
import excepciones.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author carlo
 */
public class AccesoDatosImpl implements AccesoDatos {

    @Override
    public boolean existe(String nombreArchivo) throws AccesoDatosEx {
         File archivo = new File(nombreArchivo);
         return archivo.exists();
    }

    @Override
    public List<mdEmpleados> listar(String nombreArchivo) throws LecturaDatosEx {
        List<mdEmpleados> empleados = new ArrayList();
        try {
            BufferedReader entrada = null;
            File archivo = new File(nombreArchivo);
            entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = entrada.readLine();
            while (linea != null){
            String[] DatosEmpleados = linea.split("\\|");
            mdEmpleados empleado = new mdEmpleados(DatosEmpleados[0], Double.valueOf(DatosEmpleados[1]), Double.valueOf(DatosEmpleados[2]), Double.valueOf(DatosEmpleados[3]));
            empleados.add(empleado);
            linea = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex){
            ex.printStackTrace(System.out);
        } catch (IOException ex){
            ex.printStackTrace(System.out);
        }
        return empleados;
    }

    @Override
    public void escribir(mdEmpleados empleado, String nombreArchivo, boolean anexar) throws EscrituraDatosEx {
        PrintWriter salida = null;
        try {
            File archivo = new File(nombreArchivo);
            salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(empleado.toString());
            salida.close();
        } catch (IOException ex){
            ex.printStackTrace(System.out);
        } finally {
            salida.close();
        }
    }

    @Override
    public String buscar(String nombreArchivo, Double buscar) throws LecturaDatosEx {
        BufferedReader entrada = null;
        String r = null;
        try {
            File archivo = new File(nombreArchivo);
            entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            int x = 1;
            linea = entrada.readLine();
            while (linea != null){
                String DatosEmpleados[] = linea.split("\\|");
                for(String datos : DatosEmpleados){
                    if(buscar != null && String.valueOf(buscar).equals(datos)){
                        r = linea + "indice" + x;
                        break;
                    }
                }
                linea = entrada.readLine();
                x++;
            }
            entrada.close();
        } catch(FileNotFoundException ex){
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                entrada.close();
            } catch (IOException ex){
                ex.printStackTrace(System.out);
            }
        }
        return r;
    }

    @Override
    public void crear(String nombreArchivo) throws AccesoDatosEx {
        PrintWriter salida = null;
        try {
            File archivo = new File(nombreArchivo);
            salida = new PrintWriter(new FileWriter(archivo));
            salida.close();
        } catch (IOException ex){
            ex.printStackTrace(System.out);
        } finally {
            salida.close();
        }
    }

    @Override
    public void borrar(String nombreArchivo) throws AccesoDatosEx {
        File archivo = new File(nombreArchivo);
        archivo.delete();
        System.out.println("El archivo ha sido eliminado");
    }
   
}
