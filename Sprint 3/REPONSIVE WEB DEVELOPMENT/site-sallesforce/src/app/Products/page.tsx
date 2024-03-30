import ProductsCard from "../../components/Cards/ProductsCard";

export default function Products() {
    return (
      <main className="flex-auto">
        <ProductsCard
        text="Adicione seus produtos para uma melhor organização"
        title="Cadastro de Produtos"
        style="product_card"
        img="/cardImg7.png"
        />
      </main>
    );
  }