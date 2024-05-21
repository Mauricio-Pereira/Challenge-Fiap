"use client";

import axios from 'axios';
import { useState, useEffect } from 'react';
import '../styles.css';

interface Props {
  text: string;
}

const ProductForms: React.FC<Props> = ({ text }) => {
  const [productName, setProductName] = useState('');
  const [description, setDescription] = useState('');
  const [price, setPrice] = useState('');
  const [stock, setStock] = useState('');
  const [errorMessage, setErrorMessage] = useState(''); // Estado para armazenar a mensagem de erro
  const [successMessage, setSuccessMessage] = useState(''); // Estado para armazenar a mensagem de sucesso
  const [clienteEmail, setClienteEmail] = useState(''); // Estado para armazenar o email do cliente

  // Recupera o email do cliente do localStorage ao carregar o componente
  useEffect(() => {
    const email = localStorage.getItem('clienteEmail');
    if (email) {
      setClienteEmail(email);
    } else {
      setErrorMessage('Email do cliente não encontrado. Faça login novamente.');
    }
  }, []);

  // Função para lidar com a mudança nos campos
  const handleInputChange = (event: any) => {
    switch (event.target.name) {
      case 'productName':
        setProductName(event.target.value);
        break;
      case 'description':
        setDescription(event.target.value);
        break;
      case 'price':
        setPrice(event.target.value);
        break;
      case 'stock':
        setStock(event.target.value);
        break;
      default:
        break;
    }
  };

  // Função para buscar o ID do cliente pelo email
  const fetchClienteIdByEmail = async (email: string) => {
    try {
      const response = await axios.get(`${process.env.NEXT_PUBLIC_API_URL}/salesforcedirections/cliente/id`, {
        params: { email: email }
      });
      console.log("ID do cliente:", response.data);
      return response.data; // Retorna o ID do cliente
    } catch (error) {
      if (axios.isAxiosError(error)) {
        console.error('Erro ao buscar ID do cliente:', error.response?.data || error.message);
        setErrorMessage(error.response?.data || 'Erro ao buscar ID do cliente.');
      } else {
        console.error('Erro desconhecido ao buscar ID do cliente:', error);
        setErrorMessage('Erro desconhecido ao buscar ID do cliente.');
      }
      return null;
    }
  };

  // Função para enviar os dados do formulário
  const handleSubmit = async (event: any) => {
    event.preventDefault(); // Previne o comportamento padrão de recarga da página

    const clienteId = await fetchClienteIdByEmail(clienteEmail);
    if (!clienteId) return; // Se não encontrar o cliente, não continua

    try {
      const response = await axios.post(`${process.env.NEXT_PUBLIC_API_URL}/salesforcedirections/produto/${clienteId}`, {
        nome: productName,
        descricao: description,
        preco: parseFloat(price),
        estoque: parseInt(stock),
      }, {
        headers: {
          'Content-Type': 'application/json',
        },
      });

      console.log('Produto cadastrado com sucesso:', response.data);
      setSuccessMessage('Produto cadastrado com sucesso!');
      setErrorMessage(''); // Limpa a mensagem de erro

      // Limpa os campos do formulário
      setProductName('');
      setDescription('');
      setPrice('');
      setStock('');
    } catch (error) {
      if (axios.isAxiosError(error)) {
        console.error('Erro ao cadastrar produto:', error.response?.data || error.message);
        setErrorMessage(error.response?.data || 'Erro ao cadastrar produto.');
        setSuccessMessage(''); // Limpa a mensagem de sucesso
      } else {
        console.error('Erro desconhecido ao cadastrar produto:', error);
        setErrorMessage('Erro desconhecido ao cadastrar produto.');
        setSuccessMessage(''); // Limpa a mensagem de sucesso
      }
    }
  };

  return (
    <div className='flex form'>
      <p>{text}</p>

      <form className='product-form' onSubmit={handleSubmit}>
        <label htmlFor='productName'>Nome do Produto</label>
        <input className="input" type="text" name="productName" value={productName} onChange={handleInputChange} />

        <div>
          <div>
            <label htmlFor='price'>Preço do Produto</label>
            <input className="input" type="text" name='price' value={price} onChange={handleInputChange} />
          </div>

          <div>
            <label htmlFor='stock'>Quantidade em Estoque</label>
            <input className="input" type="number" name='stock' value={stock} onChange={handleInputChange} />
          </div>
        </div>

        <label htmlFor='description'>Descrição do Produto</label>
        <input className="input description" type="text" name='description' value={description} onChange={handleInputChange} />

        <input type="submit" className="submit-btn" value="Adicionar Produto" />

        {errorMessage && <p className="error-message">{errorMessage}</p>} {/* Exibe a mensagem de erro */}
        {successMessage && <p className="success-message">{successMessage}</p>} {/* Exibe a mensagem de sucesso */}
      </form>
    </div>
  );
}

export default ProductForms;
