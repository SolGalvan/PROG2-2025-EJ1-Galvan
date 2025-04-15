package Builder;

import Dto.IGestionSaldo;

public interface ICuentaBuilder {
    ICuentaBuilder withSaldo(double saldo);
    ICuentaBuilder withOperaciones(int operaciones);
}
