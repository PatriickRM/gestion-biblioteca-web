document.addEventListener('DOMContentLoaded', function() {
  // Toggle de tema oscuro
  const toggler = document.getElementById('theme-toggle');
  if (toggler) {
    toggler.addEventListener('change', function () {
      document.body.classList.toggle('dark', this.checked);
    });
  }

  // Sidebar: mantener funcionalidad
  const menuBar = document.querySelector('.content nav .bx.bx-menu');
  const sideBar = document.querySelector('.sidebar');
  if (menuBar && sideBar) {
    menuBar.addEventListener('click', () => {
      sideBar.classList.toggle('close');
    });
  }

 
  const currentPath = window.location.pathname;
  const sideLinks = document.querySelectorAll('.side-menu li a:not(.logout)');

  sideLinks.forEach(item => {
    const li = item.parentElement;
    const href = item.getAttribute('href');
    
    if (href && (currentPath === href || (href !== '/' && currentPath.startsWith(href)))) {
      sideLinks.forEach(i => i.parentElement.classList.remove('active'));
      li.classList.add('active');
    }
  });

  
  window.addEventListener('resize', () => {
    if (window.innerWidth < 768 && sideBar) {
      sideBar.classList.add('close');
    } else if (sideBar) {
      sideBar.classList.remove('close');
    }
  });
});
function mostrarDetalles(id) {
    fetch(`/usuarios/detalles-json/${id}`)
        .then(response => response.json())
        .then(usuario => {
            document.getElementById("detalleId").textContent = usuario.id_usuario;
            document.getElementById("detalleNombre").textContent = usuario.nombre + " " + usuario.apellido;
            document.getElementById("detalleDni").textContent = usuario.dniuser;
            document.getElementById("detalleCorreo").textContent = usuario.correouser;
            document.getElementById("detalleTelefono").textContent = usuario.telefonouser;
            document.getElementById("detalleTipo").textContent = usuario.tipo_user;
            document.getElementById("modalUsuario").style.display = "block";
        });
}

function cerrarModal() {
    document.getElementById("modalUsuario").style.display = "none";
}
