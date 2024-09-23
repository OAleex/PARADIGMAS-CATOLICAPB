carrinho = []

def mostrar_menu():
    print("\nMenu:")
    print("1 - Inserir item ao carrinho")
    print("2 - Acréscimo de item")
    print("3 - Desconto de item")
    print("4 - Finalizar venda")
    print("0 - Sair")
    return int(input("Escolha uma opção: "))

def inserir_item():
    codigo = int(input("Digite o código do item: "))
    descricao = input("Digite a descrição do item: ")
    valor = float(input("Digite o valor do item: "))
    item = {'codigo': codigo, 'descricao': descricao, 'valor': valor, 'acrescimo': 0, 'desconto': 0}
    carrinho.append(item)
    print("Item inserido com sucesso!")

def buscar_item_por_codigo(codigo):
    for item in carrinho:
        if item['codigo'] == codigo:
            return item
    return None

def acrescimo_item():
    codigo = int(input("Digite o código do item: "))
    item = buscar_item_por_codigo(codigo)
    if item:
        acrescimo = float(input("Digite o valor do acréscimo: "))
        item['acrescimo'] += acrescimo
        item['valor'] += acrescimo
        print("Acréscimo aplicado com sucesso!")
    else:
        print("Item não encontrado.")

def desconto_item():
    codigo = int(input("Digite o código do item: "))
    item = buscar_item_por_codigo(codigo)
    if item:
        desconto = float(input("Digite o valor do desconto: "))
        item['desconto'] += desconto
        item['valor'] -= desconto
        print("Desconto aplicado com sucesso!")
    else:
        print("Item não encontrado.")

def finalizar_venda():
    print("\nItens do Carrinho:")
    for item in carrinho:
        print(f"Código: {item['codigo']}, Descrição: {item['descricao']}, Valor: {item['valor']:.2f}")
    valor_total = sum(item['valor'] for item in carrinho)
    print(f"\nValor Total: {valor_total:.2f}")

def main():
    while True:
        opcao = mostrar_menu()
        if opcao == 1:
            inserir_item()
        elif opcao == 2:
            acrescimo_item()
        elif opcao == 3:
            desconto_item()
        elif opcao == 4:
            finalizar_venda()
        elif opcao == 0:
            print("Saindo do sistema...")
            break
        else:
            print("Opção inválida. Tente novamente.")

if __name__ == "__main__":
    main()
