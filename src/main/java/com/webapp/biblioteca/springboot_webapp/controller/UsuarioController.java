package com.webapp.biblioteca.springboot_webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webapp.biblioteca.springboot_webapp.models.Usuario;
import com.webapp.biblioteca.springboot_webapp.service.RolService;
import com.webapp.biblioteca.springboot_webapp.service.UsuarioService;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolService rolService;
    //Listar usuarios
    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.ListarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios"; 
    }
    //Registrar usuario
    @GetMapping("/nuevo")
    public String mostrarFormRegistro(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", rolService.listarRoles());
        return "usuarios_form"; 
    }

    @PostMapping("/registrar")
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        String claveEncriptada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(claveEncriptada);
        usuarioService.RegistrarUsuario(usuario);
        return "redirect:/usuarios"; 
    }

    //Editar usuario
    @GetMapping("/editar/{id}")
    public String mostrarFormEditar(@PathVariable Integer id, Model model) {
        Usuario usuario = usuarioService.BuscarUsuarioPorId(id);
        if (usuario == null) {
            return "redirect:/usuarios"; 
        }
        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", rolService.listarRoles());
        return "usuarios_form"; 
    }

    @PostMapping("/editar")
    public String editarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        Usuario usuarioExistente = usuarioService.BuscarUsuarioPorId(usuario.getIdUsuario());
        if(usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            String claveEncriptada = passwordEncoder.encode(usuario.getPassword());
            usuario.setPassword(claveEncriptada);
        } else {
            usuario.setPassword(usuarioExistente.getPassword());
        }
        usuarioService.ActualizarUsuario(usuario);
        return "redirect:/usuarios"; 
    }

    //Eliminar usuario
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") Integer id) {
        Usuario usuario = usuarioService.BuscarUsuarioPorId(id);
        if (usuario != null) {
            usuarioService.EliminarUsuario(usuario);
        }
        return "redirect:/usuarios"; 
    }

    // Ver detalles del usuario
    @GetMapping("/detalles/{id}")
    public String verDetallesUsuario(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = usuarioService.BuscarUsuarioPorId(id);
        if (usuario == null) {
            return "redirect:/usuarios"; 
        }
        model.addAttribute("usuario", usuario);
        return "usuario_detalles"; 
    }

    @GetMapping("/detalles-json/{id}")
    @ResponseBody
    public Usuario obtenerDetallesUsuario(@PathVariable("id") Integer id) {
        return usuarioService.BuscarUsuarioPorId(id);
    }

    //El Usuario se registra en la pagina
    @GetMapping("/registro")
    public String mostrarFormularioRegistroPublico(Model model) {
    System.out.println("Mostrando formulario de registro público");
    model.addAttribute("usuario", new Usuario());
    return "registro"; 
    }

    @PostMapping("/registro")
    public String registrarUsuarioPublico(@ModelAttribute("usuario") Usuario usuario) {
    String claveEncriptada = passwordEncoder.encode(usuario.getPassword());
    usuario.setPassword(claveEncriptada);
    usuario.setRol(rolService.findByNombreRol("LECTOR"));
    usuarioService.RegistrarUsuario(usuario);
    return "redirect:/login?registroExitoso";
    }


}
