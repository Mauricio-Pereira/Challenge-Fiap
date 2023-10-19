# Dicionário para armazenar contas de usuário
contas = {}

# Verificar se usuário está logado
usuario_logado = False

# Função para Login
def login():
    global usuario_logado  # Indicar que estamos modificando a variável global
    print("Funcionalidade de Login:")
    username = input("Digite seu nome de usuário: ")
    password = input("Digite sua senha: ")

    if username in contas and contas[username] == password:
        usuario_logado = True  # Definir como True após um login bem-sucedido
        print("Login bem-sucedido!")
    else:
        print("Nome de usuário ou senha incorretos.")

# Função para Logout
def logout():
    global usuario_logado
    usuario_logado = False
    print("Logout realizado com sucesso!")

# Função para Cadastro de Conta
def cadastrar_conta():
    print("Funcionalidade de Cadastro de Conta:")
    username = input("Escolha um nome de usuário: ")
    password = input("Escolha uma senha: ")
    contas[username] = password
    print("Cadastro de conta concluído.")

# Função para Solicitar Serviço
def solicitar_servico():
    global usuario_logado  # Indicar que estamos acessando a variável global
    print("Funcionalidade de Solicitar Serviço:")
    if usuario_logado:
        print("Preencha o formulário de solicitação de serviço (simulação).")
    else:
        print("Você precisa estar logado para solicitar um serviço. Faça o login primeiro.")

# Função para exibir comandos de acessibilidade por teclado
def comandos_acessibilidade():
    print("\nComandos de Acessibilidade por Teclado:")
    print("Teclas de seta: Navegam entre os elementos do site (cima, baixo, direita e esquerda).")
    print("Tecla ENTER: Confirma o acesso a um elemento do site, abre menus, submenus e aciona hiperlinks.")

# Função principal
def main():
    while True:
        print("\nMenu:")
        print("1. Login")
        print("2. Cadastro de Conta")
        print("3. Solicitar Serviço")
        print("4. Comandos de Acessibilidade por Teclado")
        print("5. Logout")
        print("6. Encerrar o Programa")

        escolha = input("Escolha uma opção: ")

        if escolha == '1':
            login()
        elif escolha == '2':
            cadastrar_conta()
        elif escolha == '3':
            solicitar_servico()
        elif escolha == '4':
            comandos_acessibilidade()
        elif escolha =='5':
            logout()
        elif escolha == '6':
            print("Programa encerrado.")
            break
        else:
            print("Opção inválida. Escolha novamente.")

if __name__ == "__main__":
    main()
