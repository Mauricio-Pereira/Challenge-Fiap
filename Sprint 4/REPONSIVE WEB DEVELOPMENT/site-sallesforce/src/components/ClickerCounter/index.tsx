// ClickerCounter.tsx

// Adiciona a diretiva "use client" para indicar que este é um Client Component
// Isso permite o uso de hooks do React que dependem do ambiente do navegador
'use client';

import React, { useEffect } from 'react';

const ClickerCounter = () => {

    // Array para armazenar os dados
    var data: { timestamp: string, xPosition: number, yPosition: number }[] = [];

    // Função para coletar cliques e posição do mouse
    function trackClickAndPosition(event: MouseEvent): void {
        var clickData = {
            timestamp: new Date().toISOString(),
            xPosition: event.pageX, // Use pageX to include scroll position in X
            yPosition: event.pageY // Use pageY to include scroll position in Y
            // xPosition: event.clientX,
            // yPosition: event.clientY
        };

        // Adiciona os dados ao array
        data.push(clickData);
    }

    
    // Função para salvar os dados em CSV
    function saveDataToCSV(): void {
        var csvContent = "data:text/csv;charset=utf-8,";
        csvContent += "Timestamp,X Position,Y Position\n";

        data.forEach(function (click) {
            csvContent += click.timestamp + "," + click.xPosition + "," + click.yPosition + "\n";
        });

        // Usando o título do documento como base
        var fileName = document.title.replace(/[^a-z0-9]/gi, '_').toLowerCase(); // Sanitizando titulo
        fileName += "_click_data.csv"; // colocando sufixo da extensao do arquivo

        // Cria um link para download do CSV
        var encodedUri = encodeURI(csvContent);
        var link = document.createElement("a");
        link.setAttribute("href", encodedUri);
        link.setAttribute("download", fileName);
        document.body.appendChild(link);

        // Simula um clique no link para iniciar o download
        link.click();
    }

    useEffect(() => {
        // Adiciona um ouvinte de eventos para o clique do mouse
        document.addEventListener('click', trackClickAndPosition);

        // Adiciona um botão para salvar os dados em CSV
        let button = document.createElement('button');
        button.textContent = "Save to CSV";
        button.onclick = saveDataToCSV;
        document.body.appendChild(button);

        // verifica se há informação de clicks e, se houver, faz o download antes de mudar a página
        window.onbeforeunload = function() {
            if (data.length > 0) {
                saveDataToCSV();
            }
        };

        // Limpeza ao desmontar o componente
        return () => {
        document.removeEventListener('click', trackClickAndPosition);
        document.body.removeChild(button);
        };
    }, []);

    return null; // Como este componente não renderiza nada, retorna null
};

export default ClickerCounter;