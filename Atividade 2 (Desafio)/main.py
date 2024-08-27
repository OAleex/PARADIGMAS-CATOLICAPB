soma = 0
for linha in open('calibration_text.txt'):
    digitos = ''.join(filter(str.isdigit, linha))
    if digitos:
        soma += int(digitos[0] + digitos[-1])
print(soma)
