import MembersCard from "../../components/Cards/MembersCard";

export default function Members() {
    return (
      <main className="flex-auto">
        <MembersCard
        name="Vitor"
        git_link="https://github.com/VitorOnofreRamos"
        lin_link="https://www.linkedin.com/in/vitor-onofre-ramos-837908273/"
        img="/profileVitor.png"
        />
      </main>
    );
  }