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
            document.getElementById("detalleId").textContent = usuario.idUsuario;
            document.getElementById("detalleNombre").textContent = usuario.nombres + " " + usuario.apellidos;
            document.getElementById("detalleDni").textContent = usuario.dni;
            document.getElementById("detalleCorreo").textContent = usuario.correo;
            document.getElementById("detalleTelefono").textContent = usuario.telefono;
            document.getElementById("detalleTipo").textContent = usuario.rol.nombreRol;
            document.getElementById("detalleUsername").textContent = usuario.username;
            document.getElementById("modalUsuario").style.display = "block";
        });
}

function cerrarModal() {
    document.getElementById("modalUsuario").style.display = "none";
}

function mostrarDetallesLibro(id) {
    fetch(`/libros/detalles-json-libro/${id}`)
        .then(response => response.json())
        .then(libro => {
            document.getElementById("detalleIdLibro").textContent = libro.idLibro;
            document.getElementById("detalleTitulo").textContent = libro.titulo;
            document.getElementById("detalleAutor").textContent = libro.autor.nombre;
            document.getElementById("detalleCategoria").textContent = libro.categoria.nombre_categoria;
            document.getElementById("detalleEditorial").textContent = libro.editorial;
            document.getElementById("detalleAnioPublicacion").textContent = libro.anio_publicacion;
            document.getElementById("detalleEstado").textContent = libro.estado;
            document.getElementById("detalleISBN").textContent = libro.isbn;
            document.getElementById("detalleImagen").src = "/images/" + libro.imagen;
            
            document.getElementById("modalLibro").style.display = "block";
        });
}

function cerrarModalLibro() {
    document.getElementById("modalLibro").style.display = "none";
  }

  
