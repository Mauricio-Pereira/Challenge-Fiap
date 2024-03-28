import MembersCard from "../../components/Cards/MembersCard";

export default function Members() {
    return (
      <main className="flex-auto">
        <MembersCard
        name="Maurício Vieira Pereira"
        git_link="https://github.com/Mauricio-Pereira"
        lin_link="https://www.linkedin.com/in/mauriciovpereira/"
        img="/profileMaurício.png"
        />
        <MembersCard
        name="Luiz Otávio Leitão Silva"
        git_link="https://github.com/Luiz1614"
        lin_link="https://www.linkedin.com/in/luizotavioleitaosilva/"
        img="/profileLuiz.png"
        />
        <MembersCard
        name="Vitor Onofre Ramos"
        git_link="https://github.com/VitorOnofreRamos"
        lin_link="https://www.linkedin.com/in/vitor-onofre-ramos-837908273/"
        img="/profileVitor.png"
        />
      </main>
    );
  }