import Card from "../components/Card"
import Image from "next/image";

export default function Home() {
  return (
    <main className="flex-auto">
      <Card
        title="Unifique esforços e encante os clientes com o Customer 360 e o Salesforce Data Cloud."
        text="A magia dos dados ao alcance de todas as equipes. Com o Data Cloud, nossa plataforma de dados do Customer 360, você reúne as equipes de marketing, vendas, comércio, serviços e TI, todas em torno do cliente. Conecte todos aos dados em tempo real e facilite a colaboração entre equipes, entregando experiências que vão encantar os clientes."
        img="/cardImg1.png"
        style="default"
      />
      <Card
        title="Uma plataforma. Inúmeras possibilidades."
        text="O Customer 360 é a plataforma nº 1 de gestão de relacionamento com o cliente (Customer Relationship Management, ou CRM). Da conversão de leads à retenção de clientes pós-venda, temos o app perfeito para cada etapa da jornada do cliente. Quanto mais apps você integrar, mais benefícios você aproveita. Integrando seus dados e utilizando nossos recursos de big data e analytics, você verá toda a mágica do Customer 360 em ação."
        img="/cardImg2.png"
        style="reverse"
      />
    </main>
  );
}
