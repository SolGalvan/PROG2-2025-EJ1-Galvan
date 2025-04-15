package Builder;

import Dto.CajaDeAhorro;
import Dto.CuentaCorriente;

public class CuentaCorrienteBuilder implements ICuentaBuilder {

    private double saldo;
    private int operaciones;
    private double giroDescubierto;

    @Override
    public CuentaCorrienteBuilder withSaldo(double saldo) {
        this.saldo = saldo;
        return this;
    };

    @Override
    public CuentaCorrienteBuilder withOperaciones(int operaciones) {
        this.operaciones = operaciones;
        return this;
    }

    public CuentaCorrienteBuilder withGiroDescubierto(double giroDescubierto) {
        this.giroDescubierto = giroDescubierto;
        return this;
    }

    public CuentaCorriente build() {
        return new CuentaCorriente(saldo, operaciones, giroDescubierto);
    }
}
