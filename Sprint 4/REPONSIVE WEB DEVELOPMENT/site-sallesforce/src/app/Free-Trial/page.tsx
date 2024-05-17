import Card from "../../components/Cards/Card";
import FreeCard from "../../components/Cards/FreeCard"

export default function FreeTrial() {
    return (
      <main className="flex-auto">
        <Card
        title="Sales Cloud Professional Edition"
        text="Com o Sales Cloud Professional Edition, você tem acesso a:
        Dados pré-carregados e recursos para integrar os dados da sua empresa;
        Processos, relatórios e dashboards pré-configurados;
        Experiências guiadas para representantes, líderes e gestores de vendas;
        Guias e outros materiais sobre boas práticas de vendas;
        Onboarding integrado, treinamentos e webinars online;
        Configuração de pontuação e roteamento de leads;
        Ferramentas para automação de tarefas recorrentes;
        Uma visão completa da performance de seus representantes e equipes de vendas.
        Dúvidas? Entre em contato e fale com um de nossos especialistas: 0800 891 1887."
        img="/cardImg5.png"
        />
        <FreeCard
        text="Inscreva-se para começar sua avaliação gratuita.
        Preencha o formulário abaixo e em breve entraremos em contato sobre seu teste gratuito."
        img="/cardImg6.png"
        style="freeforms"
        />
      </main>
    );
  }