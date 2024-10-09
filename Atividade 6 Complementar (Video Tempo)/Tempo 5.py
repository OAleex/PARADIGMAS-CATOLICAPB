import time

def contagem_regressiva(n):
    while n > 0:
        print(n)
        time.sleep(1)
        n -= 1
    print("Tempo esgotado!")

n = 5
start_time = time.time()
contagem_regressiva(n)
end_time = time.time()

print(f"Tempo total da contagem: {end_time - start_time:.2f} segundos")
