'use client'

import React, { useEffect, useRef } from 'react';

// Definindo um tipo unificado para os elementos focáveis
type FocusableElement = HTMLAnchorElement | HTMLButtonElement | HTMLInputElement;

interface NavigationProps {
  children: React.ReactNode;
}

const KeyboardNav: React.FC<NavigationProps> = ({ children }) => {
  const refContainer = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const handleKeyDown = (event: KeyboardEvent) => {
      // Verifica se activeElement é uma instância de FocusableElement
      const activeElement = document.activeElement as FocusableElement | null;

      const elements = Array.from(refContainer.current?.querySelectorAll('a, button, input') || []) as FocusableElement[];
      const focusableElements = elements.filter(el => el.focus!== undefined);
      
      if (!activeElement) {
        // Se activeElement é null, você pode decidir qual elemento focar primeiro,
        // ou simplesmente ignorar a lógica de navegação neste caso específico.
        // Por exemplo, focar no primeiro elemento focável:
        focusableElements[0]?.focus();
        return;
      }

      const currentIndex = focusableElements.indexOf(activeElement);

      switch (event.key) {
        case 'ArrowLeft':
        case 'ArrowUp':
          if (currentIndex > 0) {
            focusableElements[currentIndex - 1].focus();
          }
          break;
        case 'ArrowRight':
        case 'ArrowDown':
          if (currentIndex < focusableElements.length - 1) {
            focusableElements[currentIndex + 1].focus();
          }
          break;
      }
    };

    window.addEventListener('keydown', handleKeyDown);
    return () => window.removeEventListener('keydown', handleKeyDown);
  }, []);

  return (
    <div ref={refContainer}>
      {children}
    </div>
  );
};

export default KeyboardNav;
