import oracledb
import re
import json
from datetime import datetime, timedelta

# Configurações de conexão
connection = oracledb.connect(
    user="rm553748",
    password="291096",
    dsn="oracle.fiap.com.br:1521/orcl"
)
cursor = connection.cursor()


def is_valid_email(email):
    regex = r'^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$'
    return re.match(regex, email)


def is_valid_phone(phone):
    return re.match(r'^\d{10,14}$', phone)


def is_valid_date(date_str):
    try:
        birth_date = datetime.strptime(date_str, '%Y-%m-%d')
        today = datetime.now()
        age = today.year - birth_date.year - ((today.month, today.day) < (birth_date.month, birth_date.day))
        return age >= 18
    except ValueError:
        return False


def create_cliente():
    nome = input("Nome: ")
    sobrenome = input("Sobrenome: ")

    while True:
        data_nascimento = input("Data de Nascimento (YYYY-MM-DD): ")
        if not is_valid_date(data_nascimento):
            print("O usuário deve ter no mínimo 18 anos de idade e a data deve estar no formato YYYY-MM-DD.")
        else:
            break

    while True:
        telefone = input("Telefone: ")
        if not is_valid_phone(telefone):
            print("O telefone deve conter entre 10 a 14 dígitos numéricos.")
        else:
            break

    while True:
        email_corporativo = input("Email Corporativo: ")
        if not is_valid_email(email_corporativo):
            print("Email inválido. Por favor, insira um email válido.")
        else:
            break

    nome_usuario = input("Nome de Usuário: ")

    while True:
        senha = input("Senha: ")
        if len(senha) < 8:
            print("A senha deve ter no mínimo 8 dígitos.")
        else:
            break

    cod_cliente = cursor.var(int)

    cursor.execute("""
        INSERT INTO ch_cliente (nome, sobrenome, data_nascimento, telefone, email_corporativo, nome_usuario, senha) 
        VALUES (:1, :2, TO_DATE(:3, 'YYYY-MM-DD'), :4, :5, :6, :7) 
        RETURNING cod_cliente INTO :8""",
                   (nome, sobrenome, data_nascimento, telefone, email_corporativo, nome_usuario, senha, cod_cliente)
                   )

    cod_cliente = cod_cliente.getvalue()[0]

    cursor.execute("""
        INSERT INTO ch_estoque (ch_cliente_cod_cliente) 
        VALUES (:1)""",
                   (cod_cliente,)
                   )

    connection.commit()
    print("Cliente e estoque criados com sucesso.")


def read_cliente():
    cod_cliente = input("Código do Cliente: ")
    cursor.execute("SELECT * FROM ch_cliente WHERE cod_cliente = :1", (cod_cliente,))
    cliente = cursor.fetchone()
    if cliente:
        formatted_date = cliente[3].strftime('%Y-%m-%d') if cliente[3] else 'N/A'
        print(f"Cliente encontrado:\n"
              f"Código: {cliente[0]}\n"
              f"Nome: {cliente[1]}\n"
              f"Sobrenome: {cliente[2]}\n"
              f"Data de Nascimento: {formatted_date}\n"
              f"Telefone: {cliente[4]}\n"
              f"Email Corporativo: {cliente[5]}\n"
              f"Nome de Usuário: {cliente[6]}\n"
              f"Senha: {cliente[7]}")
    else:
        print("Cliente não encontrado.")


def read_all_clientes():
    cursor.execute("SELECT * FROM ch_cliente")
    clientes = cursor.fetchall()
    if clientes:
        for cliente in clientes:
            formatted_date = cliente[3].strftime('%Y-%m-%d') if cliente[3] else 'N/A'
            print(f"Código: {cliente[0]}, Nome: {cliente[1]}, Sobrenome: {cliente[2]}, Data de Nascimento: {formatted_date}, "
                  f"Telefone: {cliente[4]}, Email Corporativo: {cliente[5]}, Nome de Usuário: {cliente[6]}, Senha: {cliente[7]}")
    else:
        print("Nenhum cliente encontrado.")

def update_cliente():
    cod_cliente = input("Código do Cliente: ")
    nome = input("Nome: ")
    sobrenome = input("Sobrenome: ")
    data_nascimento = input("Data de Nascimento (YYYY-MM-DD): ")
    telefone = input("Telefone: ")
    email_corporativo = input("Email Corporativo: ")
    nome_usuario = input("Nome de Usuário: ")
    senha = input("Senha: ")

    cursor.execute("""
        UPDATE ch_cliente SET 
            nome = :1, sobrenome = :2, data_nascimento = TO_DATE(:3, 'YYYY-MM-DD'), telefone = :4, 
            email_corporativo = :5, nome_usuario = :6, senha = :7 
        WHERE cod_cliente = :8""",
        (nome, sobrenome, data_nascimento, telefone, email_corporativo, nome_usuario, senha, cod_cliente)
    )

    connection.commit()
    print("Cliente atualizado com sucesso.")



def delete_cliente():
    cod_cliente = input("Código do Cliente: ")
    cursor.execute("DELETE FROM ch_cliente WHERE cod_cliente = :1", (cod_cliente,))
    connection.commit()
    print("Cliente deletado com sucesso.")

def export_to_json(data, filename):
    with open(filename, 'w') as f:
        json.dump(data, f, default=str)

def read_with_filter(table, where_condition=None):
    query = f"SELECT * FROM {table}"
    if where_condition:
        query += f" WHERE {where_condition}"
    cursor.execute(query)
    return cursor.fetchall()

def export_filtered_data():
    table = input("Tabela (ex: ch_cliente): ")
    where_condition = input("Insira a condição WHERE (ou deixe em branco para selecionar todos): ")
    result = read_with_filter(table, where_condition if where_condition else None)
    filename = input("Insira o nome do arquivo para exportar (ex: resultado.json): ")
    export_to_json(result, filename)
    print(f"Dados exportados para {filename} com sucesso.")


def menu():
    while True:
        print("\n1. Criar Cliente")
        print("2. Ler Cliente")
        print("3. Ler Todos os Clientes")
        print("4. Atualizar Cliente")
        print("5. Deletar Cliente")
        print("6. Consultas com Filtros e Exportação para JSON")
        print("7. Sair")
        escolha = input("Escolha uma opção: ")
        if escolha == '1':
            create_cliente()
        elif escolha == '2':
            read_cliente()
        elif escolha == '3':
            read_all_clientes()
        elif escolha == '4':
            update_cliente()
        elif escolha == '5':
            delete_cliente()
        elif escolha == '6':
            table = input("Tabela (ex: ch_cliente): ")
            where_condition = input("Insira a condição WHERE (ou deixe em branco para selecionar todos): ")
            result = read_with_filter(table, where_condition if where_condition else None)
            filename = input("Insira o nome do arquivo para exportar (ex: resultado.json): ")
            export_to_json(result, filename)
            print(f"Dados exportados para {filename} com sucesso.")
        elif escolha == '7':
            break
        else:
            print("Opção inválida.")

if __name__ == "__main__":
    menu()
    cursor.close()
    connection.close()
