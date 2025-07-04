document.addEventListener("DOMContentLoaded", function () {
    const formularios = document.querySelectorAll('.form-devolver');

    formularios.forEach(form => {
        form.addEventListener("submit", function (e) {
            e.preventDefault();

            alertify.confirm(
                "Confirmar devolución",
                "¿Estás seguro que el libro fue devuelto?",
                function () {
                    form.submit(); // Confirmado
                },
                function () {
                    alertify.error("Acción cancelada");
                }
            ).set('labels', {ok:'Sí, devolver', cancel:'Cancelar'}).set('transition', 'zoom');
        });
    });
});