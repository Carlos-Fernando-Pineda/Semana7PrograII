/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author carlo
 */
public class mdEmpleados {
    private final String nombre;
    private final Double enero;
    private final Double febrero;
    private final Double marzo;
    private final Double total;
    private final Double promedio;
    
    public mdEmpleados(String nombre, Double enero, Double febrero, Double marzo){
        this.nombre = nombre;
        this.enero = enero;
        this.febrero = febrero;
        this.marzo = marzo;
        this.total = enero + febrero + marzo;
        this.promedio = (enero + febrero + marzo) / 3;
    }
    
    @Override
    public String toString(){
        return this.getNombre() + "|" + this.getEnero() + "|" + this.getFebrero() + "|" + this.getMarzo() + "|" 
                + this.getTotal() + "|" + this.getPromedio();
    }
     /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the enero
     */
    public Double getEnero() {
        return enero;
    }

    /**
     * @return the febrero
     */
    public Double getFebrero() {
        return febrero;
    }

    /**
     * @return the marzo
     */
    public Double getMarzo() {
        return marzo;
    }

    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }
    
    /**
     * @return the promedio
     */
    public Double getPromedio(){
        return promedio;
    }
}
