import Card from "../../components/Cards/Card";

export default function Salesforce() {
    return (
      <main className="flex-auto">
        <Card
          title="Quem somos nós?"
          text="A Salesforce é uma empresa de softwares 
          que foca na solução de gerenciamento de 
          relacionamento para aproximar empresas e 
          pessoas. É uma plataforma de CRM integrada 
          que oferece a todos os departamentos uma 
          visão única e compartilhada de cada cliente."
          img="/cardImg3.png"
          intitle="true"
        />
        <Card
          title="O que é um CRM?"
          text="O CRM armazena informações de clientes atuais e 
          potenciais – nome, endereço, número de telefone, e 
          suas atividades e pontos de contato com a empresa, 
          incluindo visitas a sites, ligações telefônicas, e-mails, 
          entre outras interações.Entretanto, o software de CRM 
          não é apenas uma lista de contatos elaborada: ele reúne 
          e integra dados valiosos para preparar e atualizar suas 
          equipes com informações pessoais dos clientes, 
          histórico e preferência de compras."
          img="/cardImg4.png"
          intitle="true"
          style="reverse"
        />
      </main>
    );
  }