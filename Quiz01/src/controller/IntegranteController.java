package controller;

import model.Integrante;
import view.FormularioIntegrante;

import java.util.ArrayList;

public class IntegranteController {

    private final ArrayList<Integrante> integrantes = new ArrayList<>();
    private final FormularioIntegrante vista;

    public IntegranteController(FormularioIntegrante vista) {
        this.vista = vista;
        this.vista.setRegistrarListener(e -> registrar());
        this.vista.actualizarTabla(integrantes);
    }

    private void registrar() {
        String nombres = vista.getNombres().trim();
        String apellidos = vista.getApellidos().trim();
        String repositorio = vista.getRepositorio().trim();
        String tipo = vista.getTipoEstudiante();
        String proyecto = vista.getProyecto();

        String errores = validar(nombres, apellidos, repositorio, tipo);
        if (!errores.isEmpty()) {
            vista.mostrarError(errores);
            return;
        }

        integrantes.add(new Integrante(nombres, apellidos, repositorio, tipo, proyecto));
        vista.actualizarTabla(integrantes);
        vista.mostrarMensaje("Integrante registrado correctamente.\nTotal registrados: "
                + integrantes.size());
        vista.limpiarFormulario();
    }

    private String validar(String nombres, String apellidos, String repositorio, String tipo) {
        StringBuilder sb = new StringBuilder();

        if (!esTextoValido(nombres)) {
            sb.append("- Nombres: obligatorio, solo letras (sin números ni símbolos).\n");
        }
        if (!esTextoValido(apellidos)) {
            sb.append("- Apellidos: obligatorio, solo letras (sin números ni símbolos).\n");
        }
        if (!esUrlValida(repositorio)) {
            sb.append("- Repositorio: obligatorio, formato https://github.com/usuario/repositorio\n");
        }
        if (tipo == null) {
            sb.append("- Tipo de estudiante: seleccione Nuevo o Antiguo.\n");
        }
        return sb.toString();
    }

    private boolean esTextoValido(String texto) {
        return texto != null && texto.matches("\\p{L}+(\\s\\p{L}+)*");
    }

    private boolean esUrlValida(String url) {
        return url != null
                && url.matches("^https?://(www\\.)?github\\.com/[A-Za-z0-9_.-]+/[A-Za-z0-9_.-]+/?$");
    }
}