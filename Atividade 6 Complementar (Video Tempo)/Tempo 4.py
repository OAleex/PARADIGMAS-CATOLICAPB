import time

def carregar_dados():
    time.sleep(3)
    return "Dados carregados com sucesso!"

start_time = time.time()
resultado = carregar_dados()
end_time = time.time()

print(resultado)
print(f"Tempo de carregamento: {end_time - start_time:.2f} segundos")
