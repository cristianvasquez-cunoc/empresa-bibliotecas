const librosSection = document.getElementById('libros');
const librosCards = librosSection.children;
const searchBar = document.getElementById('search');
const searchButton = document.getElementById('searchButton');
const checkbox = document.getElementById('mostrarSoloDisponiblesBiblioteca');

searchBar.addEventListener('change', e => {
    const text = e.target.value;
    filtrar(text);
})

searchButton.addEventListener('click', e => {
    filtrar(searchBar.value)
})

checkbox.addEventListener('change', e => {
    filtrar(searchBar.value);
})

const filtrar = (text = "") => {
    text = text.toLowerCase();
    
    let librosMostrar = document.createElement("section");
    librosMostrar.className = "container";
    librosMostrar.style = "display: flex;flex-wrap: wrap;justify-content: center;";

    for (let i = 0; i < librosCards.length; i++) {
        const card = librosCards[i];

        const titulo = card.firstElementChild.children[0].innerText.toLowerCase();
        const autor = card.firstElementChild.children[1].innerText.split('Autor/a: ')[1];
        const categoria = card.firstElementChild.children[2].firstElementChild.innerText.split('Categoría: ')[1].toLowerCase();
        const isbn = card.firstElementChild.children[2].children[1].innerText.split('ISBN: ')[1].toLowerCase();
        console.log(titulo, autor, categoria, isbn)
        if ((titulo.includes(text) || autor.includes(text) || categoria.includes(text) || isbn.includes(text)) ) {
            if(!(checkbox.checked && card.firstElementChild.children[3].innerText === 'No hay unidades disponibles'))
            librosMostrar.append(card.cloneNode(true));
        }
    }

    const body = document.body;
    body.removeChild(body.children[3]);
    body.insertBefore(librosMostrar, body.children[3]);

}

filtrar();