
package classes;


public class Administracion {
    
    int diasSuspension;
    double multa;
    double costoDomicilio;
    double costoSuscripcion;
    double descuentoDomicilioPremium;
    int limiteDias;
    int limiteLibros;
    int limiteDiasPremium;
    int limiteLibrosPremium;

    public Administracion(int diasSuspension, double multa, double costoDomicilio, double costoSuscripcion, double descuentoDomicilioPremium, int limiteDias, int limiteLibros, int limiteDiasPremium, int limiteLibrosPremium) {
        this.diasSuspension = diasSuspension;
        this.multa = multa;
        this.costoDomicilio = costoDomicilio;
        this.costoSuscripcion = costoSuscripcion;
        this.descuentoDomicilioPremium = descuentoDomicilioPremium;
        this.limiteDias = limiteDias;
        this.limiteLibros = limiteLibros;
        this.limiteDiasPremium = limiteDiasPremium;
        this.limiteLibrosPremium = limiteLibrosPremium;
    }

    public int getDiasSuspension() {
        return diasSuspension;
    }

    public double getMulta() {
        return multa;
    }

    public double getCostoDomicilio() {
        return costoDomicilio;
    }

    public double getCostoSuscripcion() {
        return costoSuscripcion;
    }

    public double getDescuentoDomicilioPremium() {
        return descuentoDomicilioPremium;
    }

    public int getLimiteDias() {
        return limiteDias;
    }

    public int getLimiteLibros() {
        return limiteLibros;
    }

    public int getLimiteDiasPremium() {
        return limiteDiasPremium;
    }

    public int getLimiteLibrosPremium() {
        return limiteLibrosPremium;
    }

    
    
    
}
