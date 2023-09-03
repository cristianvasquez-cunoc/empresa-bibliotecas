

const validarLibro = () => {

    let mensajeError = '';

    const seleccion = document.getElementById("seleccionCategoria");
    const isbn = document.getElementById("isbn").value;

    if (seleccion.value === "Selecciona una opcion")
        mensajeError = 'Por favor selecciona una categoria.';

    if (isNaN(parseInt(isbn)))
        mensajeError = 'El isbn debe ser un numero';


    if (mensajeError != '') {
        Swal.fire({
            icon: 'error',
            title: 'Campo invalido',
            text: mensajeError
        })
        return false;
    } else
        return true;
}

let formAgregarLibro = document.getElementById("formAgregarLibro");
if (formAgregarLibro != null) {

    formAgregarLibro.addEventListener("submit", (e) => {
        if (!validarLibro())
            e.preventDefault();
    })
}

let formModificarLibro = document.getElementById("formModificarLibro");

if (formModificarLibro != null) {
    formModificarLibro.addEventListener("submit", async e => {
        if (!validarLibro())
            e.preventDefault();
    });
}