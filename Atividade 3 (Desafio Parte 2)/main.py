nums = {'zero': '0', 'one': '1', 'two': '2', 'three': '3', 'four': '4', 'five': '5', 'six': '6', 'seven': '7',
        'eight': '8', 'nine': '9'}


def converte_linha(linha):
    linha = linha.lower()
    convertido = []
    i, n = 0, len(linha)
    max_len = max(len(palavra) for palavra in nums)

    while i < n:
        matched = False
        for length in range(max_len, 0, -1):
            substring = linha[i:i + length]
            if substring in nums:
                convertido.append(nums[substring])
                i += length - 1
                matched = True
                break
        if not matched:
            i += 1

    if convertido:
        if len(convertido) > 1:
            return int(convertido[0] + convertido[-1])
        else:
            return int(convertido[0] * 2)
    return 0


soma = sum(converte_linha(linha.strip()) for linha in open('calibration_text.txt'))

print(soma)
