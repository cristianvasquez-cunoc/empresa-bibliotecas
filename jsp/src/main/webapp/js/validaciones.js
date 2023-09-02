

const validarAgregarArchivos = () => {

    const mensajeError = '';

    const seleccion = document.getElementById("seleccionCategoria");
    const isbn = document.getElementById("isbn");
    const costo = document.getElementById("costo");
    

    

    if (seleccion.value === "Selecciona una opcion")
        mensajeError = 'Por favor selecciona una categoria.';
    
    if(parseInt(isbn) === NaN)
        mensajeError = 'El isbn debe ser un numero';
    

    if (mensajeError != '') {
        Swal.fire({
            icon: 'error',
            title: 'Campo invalido',
            text: mensajeError
        })
        return false;
    } else return true;
}

let formAgregarLibro = document.getElementById("formAgregarLibro");
formAgregarLibro.addEventListener("submit", (e) => {
    if (!validarAgregarArchivos())
        e.preventDefault();
})