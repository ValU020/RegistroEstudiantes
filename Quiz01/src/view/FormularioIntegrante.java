package view;

import model.Integrante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class FormularioIntegrante extends JFrame {

    private final JTextField txtNombres = new JTextField(20);
    private final JTextField txtApellidos = new JTextField(20);
    private final JTextField txtRepositorio = new JTextField(20);
    private final JRadioButton rbNuevo = new JRadioButton("Nuevo");
    private final JRadioButton rbAntiguo = new JRadioButton("Antiguo");
    private final ButtonGroup grupoTipo = new ButtonGroup();
    private final JComboBox<String> cbProyecto = new JComboBox<>(new String[]{
            "Ingeniería de Sistemas", "Ingeniería Electrónica",
            "Ingeniería Industrial", "Ingeniería Civil"});
    private final JButton btnRegistrar = new JButton("Registrar");
    private final JButton btnLimpiar = new JButton("Limpiar");
    private final DefaultTableModel modeloTabla = new DefaultTableModel(
            new String[]{"#", "Nombres", "Apellidos", "Tipo", "Proyecto", "Repositorio"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) { return false; }
    };
    private final JTable tabla = new JTable(modeloTabla);

    public FormularioIntegrante() {
        super("Registro de Integrantes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        grupoTipo.add(rbNuevo);
        grupoTipo.add(rbAntiguo);

        soloLetras(txtNombres);
        soloLetras(txtApellidos);
        sinEspacios(txtRepositorio);

        add(crearPanelFormulario(), BorderLayout.NORTH);
        add(crearPanelTabla(), BorderLayout.CENTER);

        setSize(820, 560);
        setLocationRelativeTo(null);
    }

    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Datos del integrante"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        agregarFila(panel, gbc, 0, "Nombres:", txtNombres);
        agregarFila(panel, gbc, 1, "Apellidos:", txtApellidos);
        agregarFila(panel, gbc, 2, "Repositorio (GitHub):", txtRepositorio);

        JPanel panelTipo = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelTipo.add(rbNuevo);
        panelTipo.add(rbAntiguo);
        agregarFila(panel, gbc, 3, "Tipo de estudiante:", panelTipo);

        agregarFila(panel, gbc, 4, "Proyecto educativo:", cbProyecto);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnRegistrar);
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        panel.add(panelBotones, gbc);

        btnLimpiar.addActionListener(e -> limpiarFormulario());
        return panel;
    }

    private void agregarFila(JPanel panel, GridBagConstraints gbc, int fila,
                             String etiqueta, Component campo) {
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = fila; gbc.weightx = 0;
        panel.add(new JLabel(etiqueta), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        panel.add(campo, gbc);
    }

    private JScrollPane crearPanelTabla() {
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createTitledBorder("Integrantes registrados"));
        return scroll;
    }

    private void soloLetras(JTextField campo) {
        ((AbstractDocument) campo.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
                    throws BadLocationException {
                if (text != null && text.matches("[\\p{L} ]*")) super.insertString(fb, offset, text, attr);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text != null && text.matches("[\\p{L} ]*")) super.replace(fb, offset, length, text, attrs);
            }
        });
    }

    private void sinEspacios(JTextField campo) {
        ((AbstractDocument) campo.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
                    throws BadLocationException {
                if (text != null && !text.contains(" ")) super.insertString(fb, offset, text, attr);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if (text != null && !text.contains(" ")) super.replace(fb, offset, length, text, attrs);
            }
        });
    }

    public String getNombres() { return txtNombres.getText(); }
    public String getApellidos() { return txtApellidos.getText(); }
    public String getRepositorio() { return txtRepositorio.getText(); }

    /** Devuelve "Nuevo", "Antiguo" o null si no se ha seleccionado nada. */
    public String getTipoEstudiante() {
        if (rbNuevo.isSelected()) return "Nuevo";
        if (rbAntiguo.isSelected()) return "Antiguo";
        return null;
    }

    public String getProyecto() { return (String) cbProyecto.getSelectedItem(); }

    public void setRegistrarListener(ActionListener listener) {
        btnRegistrar.addActionListener(listener);
    }

    public void limpiarFormulario() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtRepositorio.setText("");
        grupoTipo.clearSelection();
        cbProyecto.setSelectedIndex(0);
        txtNombres.requestFocus();
    }

    public void actualizarTabla(List<Integrante> integrantes) {
        modeloTabla.setRowCount(0);
        int n = 1;
        for (Integrante i : integrantes) {
            modeloTabla.addRow(new Object[]{n++, i.getNombres(), i.getApellidos(),
                    i.getTipoEstudiante(), i.getProyecto(), i.getRepositorio()});
        }
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de validación", JOptionPane.ERROR_MESSAGE);
    }
}