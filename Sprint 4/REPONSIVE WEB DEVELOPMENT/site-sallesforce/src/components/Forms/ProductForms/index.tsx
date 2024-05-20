'use client'

import axios from 'axios';
import { useState } from 'react';

import { Props } from "../interface";
import '../styles.css';

const ProductForms = (props: Props) => {
    // Estados para os campos de entrada
    const [productName, setProductName] = useState('');
    const [description, setDescription] = useState('');
    const [price, setPrice] = useState('');
    const [stock, setStock] = useState('');

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

  // Função para enviar os dados do formulário
  const handleSubmit = (event: any) => {
    event.preventDefault(); // Previne o comportamento padrão de recarga da página
    axios.post('http://localhost:8082/produtos', {
      nome: productName,
      descricao: description,
      preco: parseFloat(price),
      estoque: parseInt(stock),
    }).then((response) => {
      console.log('Produto cadastrado com sucesso:', response.data);
    }).catch((error) => {
      console.error('Erro ao cadastrar produto:', error);
    });
  };

    return(
        <>
        <div className='flex form'>
            <p>{props.text}</p>

            <form className='product-form' onSubmit={handleSubmit}>
                <label htmlFor='productName'>Nome do Produto</label>
                <input className="input" type="text" name="productName" value={productName} onChange={handleInputChange}/>
                
                <div>
                    <div>
                        <label htmlFor='price'>Preço do Produto</label>
                        <input className="input" type="text" name='price' value={price} onChange={handleInputChange}/>
                    </div>

                    <div>
                        <label htmlFor='stock'>Quantidade em Estoque</label>
                        <input className="input" type="number" name='stock' value={stock} onChange={handleInputChange}/>
                    </div>
                </div>
                
                <label htmlFor='description'>Descrição do Produto</label>
                <input className="input description" type="text" name='description' value={description} onChange={handleInputChange}/>
                
                <input type="submit" className="submit-btn" value="Adcionar Produtos"/>
            </form>
        </div>
        </>
    );
}

export default ProductForms;
