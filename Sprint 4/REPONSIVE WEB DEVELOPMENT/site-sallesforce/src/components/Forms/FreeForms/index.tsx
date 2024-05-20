'use client'

import axios from 'axios';
import { useState } from 'react';

import { Props } from "../interface";
import '../styles.css';

const FreeForms = (props: Props) => {
    // Estados para cada campo do formulário
    const [nome, setNome] = useState('');
    const [sobrenome, setSobrenome] = useState('');
    const [dataNascimento, setDataNascimento] = useState('');
    const [telefone, setTelefone] = useState('');
    const [email, setEmail] = useState('');
    const [usuario, setUsuario] = useState('');
    const [senha, setSenha] = useState('');

    // Função para lidar com a mudança nos campos
  const handleChange = (event: any) => {
    switch (event.target.name) {
      case 'nome':
        setNome(event.target.value);
        break;
      case 'sobrenome':
        setSobrenome(event.target.value);
        break;
      case 'dataNascimento':
        setDataNascimento(event.target.value);
        break;
      case 'telefone':
        setTelefone(event.target.value);
        break;
      case 'email':
        setEmail(event.target.value);
        break;
      case 'usuario':
        setUsuario(event.target.value);
        break;
      case 'senha':
        setSenha(event.target.value);
        break;
      default:
        break;
    }
  };

  // Função para enviar os dados do formulário
  const send = () => {
    axios.post('http://localhost:8082/salesforcedirections/cliente', {
      nome: nome,
      sobrenome: sobrenome,
      dataNascimento: dataNascimento,
      telefone: telefone,
      email: email,
      usuario: usuario,
      senha: senha,
    }).then((response) => {
      console.log('Dados enviados com sucesso:', response.data);
    }).catch((error) => {
      console.error('Erro ao enviar dados:', error);
    });
  };

    return (
       <>
         <div className="flex form">
           <p>{props.text}</p>
   
           <form onSubmit={(e) => e.preventDefault}>
             <div>
               <label className="none" htmlFor="nome"></label>
               <input type="text" id="nome" name="nome" placeholder="Nome" value={nome} onChange={handleChange}/>
   
               <label className="none" htmlFor="sobrenome"></label>
               <input type="text" id="sobrenome" name="sobrenome" placeholder="Sobrenome" value={sobrenome} onChange={handleChange}/>
             </div>
   
             <label htmlFor="dataNascimento"></label>
             <input type="date" id="dataNascimento" name="dataNascimento" placeholder="Data de nascimento" value={dataNascimento} onChange={handleChange}/>
   
             <label htmlFor="telefone"></label>
             <input type="tel" id="telefone" name="telefone" placeholder="Telefone" value={telefone} onChange={handleChange}/>
   
             <label htmlFor="email"></label>
             <input type="email" id="email" name="email" placeholder="Email" value={email} onChange={handleChange}/>
   
             <label htmlFor="cep"></label>
             <input type="text" id="cep" name="cep" placeholder="CEP"/>
   
             <label htmlFor="rua"></label>
             <input type="text" id="rua" name="rua" placeholder="Rua"/>
             
             <div>
               <label className="none" htmlFor="numero"></label>
               <input type="number" id="numero" name="numero" placeholder="Número"/>
   
               <label className="none" htmlFor="complemento"></label>
               <input type="text" id="complemento" name="complemento" placeholder="Complemento"/>
             </div>

             <label htmlFor="bairro"></label>
             <input type="text" id="bairro" name="bairro" placeholder="Bairro"/>
   
             <label htmlFor="cidade"></label>
             <input type="text" id="cidade" name="cidade" placeholder="Cidade"/>
   
             <label htmlFor="estado"></label>
             <input type="text" id="estado" name="estado" placeholder="Estado"/>
   
             <label htmlFor="pais"></label>
             <input type="text" id="pais" name="pais" placeholder="Pais"/>
   
             <label htmlFor="usuario"></label>
             <input type="text" id="usuario" name="usuario" placeholder="Nome de usuario" value={usuario} onChange={handleChange}/>
   
             <label htmlFor="senha"></label>
             <input type="password" id="senha" name="senha" placeholder="Senha" value={senha} onChange={handleChange}/>
   
             <input type="submit" className="submit-btn" onClick={send} value="INICIAR TESTE GRATUITO"/>
           </form>
         </div>
       </>
    );
   };

export default FreeForms;