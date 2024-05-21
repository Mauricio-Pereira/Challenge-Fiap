"use client"; // Certifique-se de que este hook é um componente de cliente

import { useState, useEffect } from 'react';
import { useRouter } from 'next/navigation';

const useAuth = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const router = useRouter();

  useEffect(() => {
    const email = localStorage.getItem('clienteEmail');
    if (email) {
      setIsAuthenticated(true);
    } else {
      setIsAuthenticated(false);
      router.push('/login'); // Redireciona para a página de login se não estiver autenticado
    }
  }, [router]);

  return { isAuthenticated };
};

export default useAuth;
