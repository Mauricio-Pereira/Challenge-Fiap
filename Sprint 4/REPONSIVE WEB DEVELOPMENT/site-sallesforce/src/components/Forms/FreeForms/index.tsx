"use client";

import axios from 'axios';
import { useState } from 'react';
import '../styles.css';

const FreeForms: React.FC = () => {
  // Estados para cada campo do formulário
  const [formData, setFormData] = useState({
    nome: '',
    sobrenome: '',
    dataNascimento: '',
    telefone: '',
    email: '',
    usuario: '',
    senha: '',
    cep: '',
    rua: '',
    numero: '',
    complemento: '',
    bairro: '',
    cidade: '',
    estado: '',
    pais: ''
  });

  const [errorMessage, setErrorMessage] = useState('');
  const [successMessage, setSuccessMessage] = useState('');

  const handleChange = (event: any) => {
    const { name, value } = event.target;
    setFormData((prevState) => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = async (event: any) => {
    event.preventDefault();

    // Formatar data de nascimento no formato dd/MM/yyyy
    const formattedData = {
      ...formData,
      dataNascimento: new Date(formData.dataNascimento).toLocaleDateString('pt-BR', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric'
      })
    };

    console.log('Data de nascimento enviada:', formattedData.dataNascimento); // Log para verificar a data

    try {
      const response = await axios.post(`${process.env.NEXT_PUBLIC_API_URL}/salesforcedirections/cliente-endereco`, formattedData, {
        headers: {
          'Content-Type': 'application/json',
        },
      });
      console.log('Cliente cadastrado com sucesso:', response.data);
      setSuccessMessage('Cliente cadastrado com sucesso!');
      setErrorMessage('');
      setFormData({
        nome: '',
        sobrenome: '',
        dataNascimento: '',
        telefone: '',
        email: '',
        usuario: '',
        senha: '',
        cep: '',
        rua: '',
        numero: '',
        complemento: '',
        bairro: '',
        cidade: '',
        estado: '',
        pais: ''
      });
    } catch (error) {
      if (axios.isAxiosError(error)) {
        console.error('Erro ao cadastrar cliente:', error.response?.data || error.message);
        setErrorMessage(error.response?.data || 'Erro ao cadastrar cliente.');
        setSuccessMessage('');
      } else {
        console.error('Erro desconhecido ao cadastrar cliente:', error);
        setErrorMessage('Erro desconhecido ao cadastrar cliente.');
        setSuccessMessage('');
      }
    }
  };

  return (
    <div className="flex form">
      <form onSubmit={handleSubmit}>
        <input type="text" name="nome" placeholder="Nome" value={formData.nome} onChange={handleChange} />
        <input type="text" name="sobrenome" placeholder="Sobrenome" value={formData.sobrenome} onChange={handleChange} />
        <input type="date" name="dataNascimento" placeholder="Data de nascimento" value={formData.dataNascimento} onChange={handleChange} />
        <input type="tel" name="telefone" placeholder="Telefone" value={formData.telefone} onChange={handleChange} />
        <input type="email" name="email" placeholder="Email" value={formData.email} onChange={handleChange} />
        <input type="text" name="usuario" placeholder="Nome de usuario" value={formData.usuario} onChange={handleChange} />
        <input type="password" name="senha" placeholder="Senha" value={formData.senha} onChange={handleChange} />
        <input type="text" name="cep" placeholder="CEP" value={formData.cep} onChange={handleChange} />
        <input type="text" name="rua" placeholder="Rua" value={formData.rua} onChange={handleChange} />
        <input type="text" name="numero" placeholder="Número" value={formData.numero} onChange={handleChange} />
        <input type="text" name="complemento" placeholder="Complemento" value={formData.complemento} onChange={handleChange} />
        <input type="text" name="bairro" placeholder="Bairro" value={formData.bairro} onChange={handleChange} />
        <input type="text" name="cidade" placeholder="Cidade" value={formData.cidade} onChange={handleChange} />
        <input type="text" name="estado" placeholder="Estado" value={formData.estado} onChange={handleChange} />
        <input type="text" name="pais" placeholder="País" value={formData.pais} onChange={handleChange} />
        <input type="submit" className="submit-btn" value="INICIAR TESTE GRATUITO" />

        {errorMessage && <p className="error-message">{errorMessage}</p>}
        {successMessage && <p className="success-message">{successMessage}</p>}
      </form>
    </div>
  );
};

export default FreeForms;
