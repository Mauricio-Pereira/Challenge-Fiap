import csv

def read_id_name(file_name):
    print("\n")
    for line in open(file_name, mode ='r'):
        if line.startswith('ID'):
            print(line)x
        if line.startswith('Name'):
            print(line)

def read_csv_file(file_name):
    """Função para ler o arquivo CSV e armazenar os dados em uma lista de dicionários"""
    data = []
    try:
        with open(file_name, mode ='r')as file:
            csvFile = csv.DictReader(file)
            for lines in csvFile:
                data.append(lines)
    except Exception as e:
        print("Erro ao ler o arquivo: ", e)
    finally:
        return data

def create_record(data):
    """Função para criar um novo registro."""
    new_record = {}
    for field in data[0].keys():
        if field == 'ID':
            new_record[field] = str(int(data[-1][field]) + 1)
        else:
            new_record[field] = input(f"Digite {field}: ")
    data.append(new_record)


def read_record_by_Id(data):
    """Função para ler um registro pelo ID."""
    id_to_search = input("Digite o ID que deseja ver as informações: ")
    for record in data:
        if record['ID'] == id_to_search:
            for field in record.keys():
                print(f"{field}: {record[field]}")
            break
        elif record == data[-1]:
            print("ID não encontrado")



def read_all_records(data):
    """Função para ler todos os registros."""
    for record in data:
        for field in record.keys():
            print(f"{field}: {record[field]}")
        print("\n")


# Função para atualizar um registro
def update_record_by_id(data):
    id_to_search = input("Digite o ID que deseja atualizar: ")
    for record in data:
        if record['ID'] == id_to_search:
            for field in record.keys():
                record[field] = input(f"Digite novo {field}: ")
            break
        else :
            print("ID não encontrado")

# Função para deletar um registro
def delete_record_by_Id(data):
    """Função para deletar um registro pelo ID."""
    for record in data:
        for field in record.keys():
            if field == 'ID':
                print(f"{field}: {record[field]} - {record['Username']}")


    id_to_search = input("Digite o ID que deseja deletar: ")
    if id_to_search in [record['ID'] for record in data]:
        data[:] = [record for record in data if record['ID'] != id_to_search]
        print("Registro deletado com sucesso")


def display_menu():
    """Função para exibir o menu"""
    print("\nMenu:")
    print("1. Create")
    print("2. Read")
    print("3. Read All")
    print("4. Update")
    print("5. Delete")
    print("0. Exit")

# Função para manipular os dados
def handle_data(data):
    """Função para manipular os dados."""
    while True:
        display_menu()
        option = input("Digite a opção desejada: ")
        if option == '1':
            create_record(data)
        elif option == '2':
            read_record_by_Id(data)
        elif option == '3':
            read_all_records(data)
        elif option == '4':
            update_record_by_id(data)
        elif option == '5':
            delete_record_by_Id(data)
        elif option == '0':
            break
        else:
            print("Opção inválida")

# Nome do arquivo CSV
file_name = 'arquivo.csv'

# Ler o arquivo CSV
data = read_csv_file(file_name)

# Manipular os dados
handle_data(data)
