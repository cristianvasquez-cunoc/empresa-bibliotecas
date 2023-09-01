
package com.db.administacion;

public class Administracion {
    
    int diasSuspension;
    double multa;
    double costoDomicilio;
    double costoSuscripcion;
    double descuentoDomicilioPremium;
    int limiteDias;
    int limiteLibros;

    public Administracion(int diasSuspension, double multa, double costoDomicilio, double costoSuscripcion, double descuentoDomicilioPremium, int limiteDias, int limiteLibros) {
        this.diasSuspension = diasSuspension;
        this.multa = multa;
        this.costoDomicilio = costoDomicilio;
        this.costoSuscripcion = costoSuscripcion;
        this.descuentoDomicilioPremium = descuentoDomicilioPremium;
        this.limiteDias = limiteDias;
        this.limiteLibros = limiteLibros;
    }
    
    
}
