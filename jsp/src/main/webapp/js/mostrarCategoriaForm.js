const seleccionCategoria = document.getElementById("seleccionCategoria");

seleccionCategoria.addEventListener("change", () => {

    const valorSeleccionado = seleccionCategoria.value;
    const categoriaForm = document.getElementById("categoriaForm");

    if (valorSeleccionado == "otra") {
        categoriaForm.className = "";
    } else {
        categoriaForm.className = "d-none"
    }

});