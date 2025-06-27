// Notificación activa si hay alertas de préstamo o reservas
function checkNotifications() {
    // Aquí simulas una notificación activa
    const hasNotifications = true; // Cambia según tu lógica real

    const bell = document.querySelector('.notification');
    if (hasNotifications) {
        const dot = document.createElement('span');
        dot.classList.add('dot');
        bell.appendChild(dot);
    }
}

// Filtro de búsqueda por nombre
function setupSearch() {
    const input = document.querySelector('#searchInput');
    const cards = document.querySelectorAll('.book-card');

    input.addEventListener('input', () => {
        const value = input.value.toLowerCase();
        cards.forEach(card => {
            const title = card.querySelector('h4').textContent.toLowerCase();
            card.style.display = title.includes(value) ? 'block' : 'none';
        });
    });
}

// Inicializar todo al cargar
window.addEventListener('DOMContentLoaded', () => {
    checkNotifications();
    setupSearch();
});
