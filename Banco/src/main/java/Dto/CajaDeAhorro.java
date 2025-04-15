package Dto;

public class CajaDeAhorro extends Cuenta implements IGestionSaldo {

    public CajaDeAhorro(double saldo, int operaciones) {
        super(saldo, operaciones);
    }

    @Override
    public double getSaldo() {
        return super.getSaldo();
    }

    @Override
    public synchronized boolean agregarSaldo(double monto) {
        if (monto <= 0) {
            return false;
        } else {
            saldo += monto;
            operaciones++;
            return true;
        }
    }

    @Override
    public synchronized boolean quitarSaldo(double monto) {
        if (monto > 0 && (super.getSaldo() > monto)) {
            saldo -= monto;
            operaciones++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getOperaciones() {
        return super.getOperaciones();
    }
}
