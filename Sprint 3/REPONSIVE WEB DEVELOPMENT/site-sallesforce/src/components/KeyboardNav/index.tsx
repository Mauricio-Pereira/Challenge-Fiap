'use client'

import React, { useEffect, useRef } from 'react';

interface NavigationProps {
  children: React.ReactNode;
}

const KeyboardNav: React.FC<NavigationProps> = ({ children }) => {
  const refContainer = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const handleKeyDown = (event: KeyboardEvent) => {
      const activeElement = document.activeElement;
      const elements = Array.from(refContainer.current?.querySelectorAll('a') || []);
      const offset = (el: HTMLElement) => ({
        left: el.getBoundingClientRect().left + window.scrollX,
        top: el.getBoundingClientRect().top + window.scrollY,
      });

      switch (event.keyCode) {
        case 37: // Seta esquerda
        const previousElement = elements.find((el) => el === activeElement);
        if (previousElement && previousElement.previousElementSibling) {
          (previousElement.previousElementSibling as HTMLElement).focus();
        }
        break;
        case 38: // Seta cima
        let nextAboveElement = elements.find((el) => el === activeElement);
        elements.forEach((el) => {
          if (!nextAboveElement || offset(el).top < offset(nextAboveElement).top) {
            nextAboveElement = el;
          }
        });
        if (nextAboveElement) {
          (nextAboveElement as HTMLElement).focus();
        }
        break;
      case 39: // Seta direita
        const nextElement = elements.find((el) => el === activeElement);
        if (nextElement && nextElement.nextElementSibling) {
          (nextElement.nextElementSibling as HTMLElement).focus();
        }
        break;
      case 40: // Seta baixo
        let nextBelowElement = elements.find((el) => el === activeElement);
        elements.forEach((el) => {
          if (!nextBelowElement || offset(el).top > offset(nextBelowElement).top) {
            nextBelowElement = el;
          }
        });
        if (nextBelowElement) {
          (nextBelowElement as HTMLElement).focus();
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
