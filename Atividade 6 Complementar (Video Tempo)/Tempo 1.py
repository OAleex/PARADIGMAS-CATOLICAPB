def verificar_par(n):
    if n % 2 == 0:
        return f"{n} é par."
    else:
        return f"{n} é ímpar."

resultado = verificar_par(7)
print(resultado)
