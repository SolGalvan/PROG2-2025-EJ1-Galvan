package Dto;

public class CuentaCorriente extends Cuenta implements IGestionSaldo{

    public double giroDescubierto;

    public CuentaCorriente(double saldo, int operaciones, double giroDescubierto) {
        super(saldo, operaciones);
        this.giroDescubierto = giroDescubierto;
    }

    public double getgiroDescubierto() {
        return giroDescubierto;
    }

    public void setGiroDescubierto(double giroDescubierto) {
        this.giroDescubierto = giroDescubierto;
    }

    @Override
    public synchronized boolean agregarSaldo(double monto) {
        if (monto <= 0) {
            return false;
        } else {
            saldo=+ monto;
            operaciones++;
            return true;
        }
    }

    @Override
    public synchronized boolean quitarSaldo(double monto) {
        if ((saldo - monto) >= giroDescubierto && monto > 0) {
            saldo =- monto;
            operaciones++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public double getSaldo() {
        return super.getSaldo();
    }

    @Override
    public int getOperaciones() {
        return super.getOperaciones();
    }
}
