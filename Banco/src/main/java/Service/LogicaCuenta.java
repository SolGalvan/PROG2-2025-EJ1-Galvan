package Service;

import Dto.Cuenta;
import Dto.IGestionSaldo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class LogicaCuenta {

    private static LogicaCuenta instancia;

    private final List<IGestionSaldo> cuentas;

    private LogicaCuenta() {
        cuentas = new ArrayList<>();
    }

    public static synchronized LogicaCuenta getInstancia() {
        if (instancia == null) {
            instancia = new LogicaCuenta();
        }
        return instancia;
    }

    private boolean validar(int i) {
        return i >= 0 && i < cuentas.size();
    }


    public synchronized void agregarCuenta(IGestionSaldo cuenta) {
        cuentas.add(cuenta);
    }

    public List<IGestionSaldo> getCuentas() {
        return cuentas;
    }

    public CompletableFuture<Boolean> agregarSaldo(int cuenta, double monto) {
        return CompletableFuture.supplyAsync(() -> {
            if (validar(cuenta)) {
                return cuentas.get(cuenta).agregarSaldo(monto);
            }
            return false;
        });
    }

    public CompletableFuture<Boolean> quitarSaldo(int cuenta, double monto) {
        return CompletableFuture.supplyAsync(() -> {
            if (validar(cuenta)) {
                return cuentas.get(cuenta).quitarSaldo(monto);
            }
            return false;
        });
    }

    public CompletableFuture<Double> consultarSaldo(int cuenta) {
        return CompletableFuture.supplyAsync(() -> {
            if (validar(cuenta)) {
                return cuentas.get(cuenta).getSaldo();
            }
            return -1.0;
        });
    }

    public CompletableFuture<Integer> getOperaciones(int cuenta) {
        return CompletableFuture.supplyAsync(() -> {
            if (validar(cuenta)) {
                return cuentas.get(cuenta).getOperaciones();
            }
            return -1;
        });
    }

    public void ejecutarOperacionesConcurrentes(int cantidadOperaciones) throws InterruptedException, ExecutionException {
        List<CompletableFuture<Void>> futureList = new ArrayList<>();

        for (int i = 0; i < cantidadOperaciones; i++) {
            final int cuentaIndex = (int) (Math.random() * cuentas.size());
            final double monto = 10 + Math.random() * 90;
            boolean esDeposito = Math.random() < 0.5;

            CompletableFuture<Void> futureOp;
            if (esDeposito) {
                futureOp = agregarSaldo(cuentaIndex, monto).thenAccept(result -> {
                    if (result) {
                        System.out.println("Se agregó $" + monto + " a la cuenta #" + cuentaIndex);
                    }
                });
            } else {
                futureOp = quitarSaldo(cuentaIndex, monto).thenAccept(result -> {
                    if (result) {
                        System.out.println("Se quitó $" + monto + " de la cuenta #" + cuentaIndex);
                    }
                });
            }

            futureList.add(futureOp);
        }

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]));
        allOf.join();
    }
}