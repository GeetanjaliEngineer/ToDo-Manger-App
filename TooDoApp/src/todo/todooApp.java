package todo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class todooApp {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("ToDo Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("ToDo List Application", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(70, 130, 180));
        titleLabel.setForeground(Color.WHITE);
        frame.add(titleLabel, BorderLayout.NORTH);

        // Table model and JTable
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Task", "Completed"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 1 ? Boolean.class : String.class;
            }
        };
        JTable table = new JTable(tableModel);
        table.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Input panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        JTextField taskField = new JTextField();
        JButton addButton = new JButton("Add Task");
        inputPanel.add(taskField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);
        frame.add(inputPanel, BorderLayout.SOUTH);

        // Control panel
        JPanel controlPanel = new JPanel(new FlowLayout());
        JButton deleteButton = new JButton("Delete Task");
        JButton updateButton = new JButton("Update Task");
        controlPanel.add(deleteButton);
        controlPanel.add(updateButton);
        frame.add(controlPanel, BorderLayout.NORTH);

        // Add Task Button Action
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = taskField.getText().trim();
                if (!task.isEmpty()) {
                    tableModel.addRow(new Object[]{task, false});
                    taskField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Task cannot be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Delete Task Button Action
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(frame, "Select a task to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Update Task Button Action
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String updatedTask = JOptionPane.showInputDialog(frame, "Update Task:", table.getValueAt(selectedRow, 0));
                    if (updatedTask != null && !updatedTask.trim().isEmpty()) {
                        tableModel.setValueAt(updatedTask.trim(), selectedRow, 0);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Task cannot be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Select a task to update!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Display the frame
        frame.setVisible(true);
    }
}
