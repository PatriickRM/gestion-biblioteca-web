/*
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
    const input = document.querySelector('.search-input'); 
    const cards = document.querySelectorAll('.book-card');

    input.addEventListener('input', () => {
        const value = input.value.toLowerCase();
        cards.forEach(card => {
            const title = card.querySelector('h4').textContent.toLowerCase();
            card.style.display = title.includes(value) ? 'block' : 'none';
        });
    });
}
*/

function setupContactForm() {
    const form = document.querySelector('.contact-form');
    if (!form) return; // Si no existe el formulario, no hacer nada

    form.addEventListener('submit', function(e) {
        e.preventDefault(); 
        const toast = document.getElementById('toast');
        if (!toast) return; 

        toast.classList.add('show');
        setTimeout(() => {
            toast.classList.remove('show');
        }, 3000); 
        this.reset();
    });
}

function setupFAQ() {
    document.querySelectorAll('.faq-item').forEach(item => {
        item.addEventListener('click', () => {
            item.classList.toggle('active');
        });
    });
}

// Inicializar todo al cargar
window.addEventListener('DOMContentLoaded', () => {
    /*
	checkNotifications();
    setupSearch();
	*/
	setupFAQ();
	setupContactForm(); 
});