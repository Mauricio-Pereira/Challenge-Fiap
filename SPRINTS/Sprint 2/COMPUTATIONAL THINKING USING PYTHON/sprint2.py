import os
# Dicionário para armazenar contas de usuário
contas = {}

# Verificar se usuário está logado
usuario_logado = False

# Função para Login
import os
def login():
    os.system('cls')  # Para sistemas Windows. Use 'clear' no lugar de 'cls' em sistemas Unix.
    global usuario_logado  # Indicar que estamos modificando a variável global
    print('Funcionalidade de Login:')
    username = input('Digite seu nome de usuário: ')
    password = input('Digite sua senha: ')

    try:
        if username in contas and contas[username] == password:
            usuario_logado = True  # Definir como True após um login bem-sucedido
            os.system('cls')
            print('Login bem-sucedido!')
        else:
            os.system('cls')
            print('Nome de usuário ou senha incorretos.')
    except NameError:
        os.system('cls')
        print('Erro: A variável "contas" não está definida. Certifique-se de que ela foi definida antes de chamar a função "login".')
    except Exception as e:
        os.system('cls')
        print(f'Ocorreu um erro inesperado: {e}')


# Função para Logout# Função para Logout
def logout():
    global usuario_logado
    usuario_logado = False
    os.system('cls')
    print('Logout realizado com sucesso!')

#  Função para Cadastro de Conta
import os

def cadastrar_conta():
    os.system('cls')
    print('Funcionalidade de Cadastro de Conta:')
    while True:
        try:
            username = input('Escolha um nome de usuário: ')
            password = input('Escolha uma senha: ')

            if not username or not password:
                raise ValueError('Nome de usuário e senha não podem ser vazios. Tente novamente.')

            if username in contas:
                raise ValueError('Esse nome de usuário já está em uso. Por favor, escolha outro.')

            contas[username] = password
            os.system('cls')
            print('Cadastro de conta concluído.')
            break
        except ValueError as error:
            os.system('cls')
            print(f'Erro: {error}')


# Função para Solicitar Serviço
# Função para Solicitar Serviço
def solicitar_servico():
    while True: #Laço para voltar ao menu de opções de serviço caso um valor invalido seja digitado
        os.system('cls')
        global usuario_logado  # Indicar que estamos acessando a variável global
        print('Funcionalidade de Solicitar Serviço:')

        if usuario_logado:
            print('Escolha o serviço que deseja solicitar:')
            print('1. Automação de Vendas')
            print('2. Data Cloud')
            print('3. Atendimento ao Cliente')
            print('4. Cancelar')

            servico = input("Escolha uma opção (1/2/3/4): ")

            try:
                servico = int(servico)
            except ValueError:
                print('Escolha de serviço inválida. Deve ser um número (1/2/3/4).')
                continue

            match servico:
                case 1:
                    print('Você escolheu Automação de Vendas.')
                case 2:
                    print('Você escolheu Data Cloud.')
                case 3:
                    print('Você escolheu Atendimento ao Cliente.')
                case 4:
                    print('Operação cancelada!')
                    return
                case _:
                    print('Opção inválida. Escolha novamente.')
                    continue

            if servico in (1, 2, 3):
                while True: #Laço para voltar ao menu de opções de tempo do serviço caso um valor invalido seja digitado
                    print('Selecione o tempo de solicitação de acesso ao serviço:')
                    print('1. 1 mês')
                    print('2. 3 meses')
                    print('3. 6 meses')
                    print('4. 12 meses')
                    print('5. Cancelar')

                    tempo_servico = input('Escolha o tempo (1/2/3/4/5): ')

                    try:
                        tempo_servico = int(tempo_servico)
                    except ValueError:
                        print('Escolha de tempo inválida. Deve ser um número (1/2/3/4/5).')
                        continue

                    match tempo_servico:
                        case 1:
                            print('Você tem 1 mês de acesso. Obrigado pela preferência!')
                            return
                        case 2:
                            print('Você tem 3 meses de acesso. Obrigado pela preferência!')
                            return
                        case 3:
                            print('Você tem 6 meses de acesso. Obrigado pela preferência!')
                            return
                        case 4:
                            print('Você tem 12 meses de acesso. Obrigado pela preferência!')
                            return
                        case 5:
                            print('Operação cancelada!')
                            return
                        case _:
                            print('Opção inválida. Escolha novamente.')
        else:
            print('Você precisa estar logado para solicitar um serviço. Faça o login primeiro.')
            return

# Função para exibir comandos de acessibilidade por teclado
def comandos_acessibilidade():
     os.system('cls')
     print('\nComandos de Acessibilidade por Teclado:')
     print('Teclas de seta: Navegam entre os elementos do site (cima, baixo, direita e esquerda).')
     print('Tecla ENTER: Confirma o acesso a um elemento do site, abre menus, submenus e aciona hiperlinks.')
# Função principal
def main():
    while True:
        print('\nMenu:')
        print('1. Login')
        print('2. Cadastro de Conta')
        print('3. Solicitar Serviço')
        print('4. Comandos de Acessibilidade por Teclado')
        print('5. Logout')
        print('6. Encerrar o Programa')

        escolha = input("Escolha uma opção: ")

        try:
            if escolha == '1':
                login()
            elif escolha == '2':
                cadastrar_conta()
            elif escolha == '3':
                solicitar_servico()
            elif escolha == '4':
                comandos_acessibilidade()
            elif escolha == '5':
                logout()
            elif escolha == '6':
                print('Programa encerrado.')
                break
            else:
                raise ValueError('Opção inválida. Escolha novamente.')
        except ValueError as error:
            os.system('cls')
            print(f'Erro: {error}')


if __name__ == '__main__':
    main()
