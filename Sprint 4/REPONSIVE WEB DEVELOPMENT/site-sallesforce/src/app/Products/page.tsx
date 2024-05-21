"use client";

import React from 'react';
import useAuth from '../../hooks/useAuth';
import ProductsCard from "../../components/Cards/ProductsCard";

export default function Products() {
  const { isAuthenticated } = useAuth();

  if (!isAuthenticated) {
    return <p>Redirecionando para a página de login...</p>;
  }

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
