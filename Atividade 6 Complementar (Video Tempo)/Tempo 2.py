import json

def ler_json(arquivo):
    with open(arquivo, mode='r') as file:
        dados = json.load(file)
        print(dados)

ler_json('C:/Users/Alex F/source/PARADIGMAS-CATOLICAPB/Atividade 6 Complementar (Video Tempo)/dados.json')
