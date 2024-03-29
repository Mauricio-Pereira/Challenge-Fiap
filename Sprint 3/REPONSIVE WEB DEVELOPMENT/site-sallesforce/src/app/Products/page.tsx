import ProductsCard from "../../components/Cards/ProductsCard";

export default function Products() {
    return (
      <main className="flex-auto">
        <ProductsCard
        title="Cadastro de Produtos"
        style="product_card"
        img="/cardImg7.png"
        />
      </main>
    );
  }