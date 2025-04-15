package Dto;

public abstract class Cuenta {

    protected double saldo;
    protected int operaciones;

    public Cuenta(double saldo, int operaciones) {
        this.saldo = saldo;
        this.operaciones = operaciones;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(int operaciones) {
        this.operaciones = operaciones;
    }
}
