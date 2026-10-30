package controller;

import view.FormularioIntegrante;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormularioIntegrante vista = new FormularioIntegrante();
            new IntegranteController(vista);
            vista.setVisible(true);
        });
    }
}
