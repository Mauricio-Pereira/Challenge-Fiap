import { Props } from "../interface";
import Button from "../../Button";
import '../styles.css';

const FreeForms = (props: Props) => {
    return (
       <>
         <div className="flex form">
           <p>{props.text}</p>
   
           <form>
             <div>
               <label htmlFor="nome"></label>
               <input type="text" id="nome" name="nome" placeholder="Nome"/>
   
               <label htmlFor="sobrenome"></label>
               <input type="text" id="sobrenome" name="sobrenome" placeholder="Sobrenome"/>
             </div>
   
             <label htmlFor="dataNascimento"></label>
             <input type="date" id="dataNascimento" name="dataNascimento" placeholder="Data de nascimento"/>
   
             <label htmlFor="telefone"></label>
             <input type="tel" id="telefone" name="telefone" placeholder="Telefone"/>
   
             <label htmlFor="emailCorporativo"></label>
             <input type="email" id="emailCorporativo" name="emailCorporativo" placeholder="Email corporativo"/>
   
             <label htmlFor="cep"></label>
             <input type="text" id="cep" name="cep" placeholder="CEP"/>
   
             <label htmlFor="rua"></label>
             <input type="text" id="rua" name="rua" placeholder="Rua"/>
             
             <div>
               <label htmlFor="numero"></label>
               <input type="number" id="numero" name="numero" placeholder="Número"/>
   
               <label htmlFor="complemento"></label>
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
   
             <label htmlFor="nomeUsuario"></label>
             <input type="text" id="nomeUsuario" name="nomeUsuario" placeholder="Nome de usuario"/>
   
             <label htmlFor="senha"></label>
             <input type="password" id="senha" name="senha" placeholder="Senha"/>
   
             <Button type="submit" style="submit-btn" link="/Free-Trial" text="INICIAR TESTE GRATUITO" />
           </form>
         </div>
       </>
    );
   };

export default FreeForms;