import csv

def read_id_name(file_name):
    """Função o Id e o Nome do arquivo CSV."""
    try:
        with open(file_name, mode ='r')as file:
            csvFile = csv.DictReader(file)
            for lines in csvFile:
                print(f"ID: {lines['ID']} - {lines['Username']}")
    except Exception as e:
        print("Erro ao ler o arquivo: ", e)

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
    try :
        with open(file_name, mode='a') as file:
            writer = csv.DictWriter(file, fieldnames=data[0].keys())
            new_record = {}
            for field in data[0].keys():
                if field == 'ID':
                    new_record[field] = str(int(data[-1][field]) + 1)
                else:
                    valor = input(f"Digite {field}: ")
                    if valor == '':
                        while valor == '':
                            print("Campo obrigatório")
                            valor = input(f"Digite {field}: ")
                    new_record[field] = valor

            if any (new_record['Username'] in record.values() for record in data):
                print("Username já existe")
                return
            writer.writerow(new_record)
            data.append(new_record)

            print("Registro criado com sucesso")
    except Exception as e:
        print("Erro ao criar o registro: ", e)


def read_record_by_name(data):
    """Função para ler um registro pelo nome."""
    try:
        name_to_search = input("Digite o nome que deseja buscar: ")
        for record in data:
            if name_to_search.casefold() in str(record.values()).casefold():
                print("\n")
                for field in record.keys():
                    print(f"{field}: {record[field]}")

        if name_to_search.casefold() not in str(record.values()).casefold():
            print("Nome não encontrado")
    except Exception as e:
        print("Erro ao buscar o nome: ", e)



def read_all_records(data):
    """Função para ler todos os registros."""
    try:
        for record in data:
            for field in record.keys():
                print(f"{field}: {record[field]}")
            print("\n")
    except Exception as e:
        print("Erro ao imprimir os registros: ", e)


# Função para atualizar um registro
def update_record_by_id(data):
    """Função para atualizar um registro pelo ID."""
    try:
        read_id_name(file_name)
        id_to_search = input("Digite o ID que deseja atualizar: ")
        for record in data:
            if id_to_search in record.values():
                for field in record.keys():
                    if field == 'ID':
                        continue
                    record[field] = input(f"Digite novo {field}: ")
                print("Registro atualizado com sucesso")
                break
            elif record == data[-1]:
                print("ID não encontrado")
    except Exception as e:
        print("Erro ao atualizar o registro: ", e)

# Função para deletar um registro
def delete_record_by_Id(data):
    """Função para deletar um registro pelo ID."""
    try:
        read_id_name(file_name)
        id_to_search = input("Digite o ID que deseja deletar: ")
        if id_to_search in [record['ID'] for record in data]:
            data[:] = [record for record in data if record['ID'] != id_to_search]
            print("Registro deletado com sucesso")
    except Exception as e:
        print("Erro ao deletar o registro: ", e)


def exit_and_save(file_name, data):
    """Função para sair e salvar os dados no arquivo CSV."""
    with open(file_name, mode='w') as file:
        print("Deseja salvar as alterações?")
        save = input("Digite 's' para salvar, 'n' para sair sem salvar ou 'c' para cancelar: : ")
        while True:
            if save != 'n' and save != 's':
                save = input("Opção inválida. Digite 's' para salvar, 'n' para sair sem salvar ou 'c' para cancelar: ")
            elif save == 's':
                writer = csv.DictWriter(file, fieldnames=data[0].keys())
                writer.writeheader()
                writer.writerows(data)
                print("Alterações salvas com sucesso")
                exit()
            else:
                print("Alterações não salvas")
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
        match option:
            case '1':
                create_record(data)
            case '2':
                read_record_by_name(data)
            case '3':
                read_all_records(data)
            case '4':
                update_record_by_id(data)
            case '5':
                delete_record_by_Id(data)
            case '0':
                exit_and_save(file_name, data)
            case _:
                print("Opção inválida")

# Nome do arquivo CSV
file_name = 'arquivo.csv'

# Ler o arquivo CSV
data = read_csv_file(file_name)

# Manipular os dados
handle_data(data)
