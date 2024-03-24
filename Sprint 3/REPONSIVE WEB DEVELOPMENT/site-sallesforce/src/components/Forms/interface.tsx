export interface Props {
    text?: string;
    style?: string;
    description?: string;
};

export interface ICadastroState {
    nome: string;
    sobrenome: string;
    dataNascimento: string;
    telefone: string;
    emailCorporativo: string;
    cep: string;
    rua: string;
    numero: string;
    complemento: string;
    bairro: string;
    cidade: string;
    estado: string;
    pais: string;
    nomeUsuario: string;
    senha: string;
}