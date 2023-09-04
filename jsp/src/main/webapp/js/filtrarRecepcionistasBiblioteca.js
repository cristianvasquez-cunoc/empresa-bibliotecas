const usuariosSection = document.getElementById("usuarios");
const usuarios = usuariosSection.children;
const selectBiblioteca = document.getElementById("seleccionBiblioteca");

const filtrarUsuariosRecepcionByBiblioteca = e => {
    let usuariosMostrar = document.createElement("section");
    usuariosMostrar.className = "container";
    usuariosMostrar.style = "display: flex;flex-wrap: wrap;justify-content: center;";
    const nombreBibliotecaFiltrar = selectBiblioteca.children[selectBiblioteca.selectedIndex].innerHTML;
    
    for(let usuario of usuarios) {
        const bibliotecaNombre = usuario.children[0].children[2].children[1].innerText.split('Biblioteca: ')[1];
        
        if(bibliotecaNombre === nombreBibliotecaFiltrar || nombreBibliotecaFiltrar === "Todas") {
            usuariosMostrar.append(usuario.cloneNode(true));
        }
    }
    
    const body = document.body;
    body.removeChild(body.children[3]);
    body.insertBefore(usuariosMostrar,body.children[3]);
    
}

selectBiblioteca.addEventListener('change', e => {filtrarUsuariosRecepcionByBiblioteca(e)});