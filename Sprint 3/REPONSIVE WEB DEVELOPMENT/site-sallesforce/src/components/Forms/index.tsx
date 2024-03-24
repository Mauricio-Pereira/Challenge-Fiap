import { Props } from "./interface";
import { ICadastroState } from "./interface";
import Button from "../Button";
import './styles.css';

const Forms: React.FC<Props> = (props) => {
    return (
       <>
         <div className="flex form">
           <p>{props.text}</p>
   
           <form>
             <label htmlFor="nome">Nome:</label>
             <input type="text" id="nome" name="nome" />
   
             <label htmlFor="sobrenome">Sobrenome:</label>
             <input type="text" id="sobrenome" name="sobrenome" />
   
             <label htmlFor="dataNascimento">Data de Nascimento:</label>
             <input type="date" id="dataNascimento" name="dataNascimento" />
   
             <label htmlFor="telefone">Telefone:</label>
             <input type="tel" id="telefone" name="telefone" />
   
             <label htmlFor="emailCorporativo">Email Corporativo:</label>
             <input type="email" id="emailCorporativo" name="emailCorporativo" />
   
             <label htmlFor="cep">CEP:</label>
             <input type="text" id="cep" name="cep" />
   
             <label htmlFor="rua">Rua:</label>
             <input type="text" id="rua" name="rua" />
   
             <label htmlFor="numero">Número:</label>
             <input type="number" id="numero" name="numero" />
   
             <label htmlFor="complemento">Complemento:</label>
             <input type="text" id="complemento" name="complemento" />
   
             <label htmlFor="bairro">Bairro:</label>
             <input type="text" id="bairro" name="bairro" />
   
             <label htmlFor="cidade">Cidade:</label>
             <input type="text" id="cidade" name="cidade" />
   
             <label htmlFor="estado">Estado:</label>
             <input type="text" id="estado" name="estado" />
   
             <label htmlFor="pais">País:</label>
             <input type="text" id="pais" name="pais" />
   
             <label htmlFor="nomeUsuario">Nome de Usuário:</label>
             <input type="text" id="nomeUsuario" name="nomeUsuario" />
   
             <label htmlFor="senha">Senha:</label>
             <input type="password" id="senha" name="senha" />
   
             <Button type="submit" link="/Free-Trial" text="INICIAR TESTE GRATUITO" />
           </form>
         </div>
       </>
    );
   };

export default Forms;