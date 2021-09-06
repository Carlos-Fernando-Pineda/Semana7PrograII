/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author carlo
 */
public interface NominaE {
    public void InicializarArchivo(String nombreArchivo);
    public void AddEmpleado(String nombreArchivo, String nombreEmpleado, Double enero, Double febrero, Double marzo);
    public void MayorYMenorPorMes(String nombreArchivo, int mes);
    public void MayorVendedor(String nombreArchivo);
    public void Editar(String nombreArchivo, String DatoNuevo, int registro, int columna);
    public void PorCantidad(String nombreArchivo, Double cantidad);
    public void listarEmpleados(String nombreArchivo);
}
