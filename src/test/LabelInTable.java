package test;

import java.awt.*;
import java.util.Random;

import javax.swing.*;
import javax.swing.table.*;

public class LabelInTable extends JFrame {

	LabelInTable() {
		setLayout(new BorderLayout());
		JScrollPane sp = new JScrollPane(new MyTable()); 
		add(sp, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	// tester  to display the JTable
	public static void main(String[] argv) {
		LabelInTable lit = new LabelInTable();
		lit.setBounds(10, 10, 350, 100);
		lit.setVisible(true);
	}

	// my class that extends JTable. I need to extend JTable because I also need to implement TableCellRendere
	class MyTable extends JTable implements TableCellRenderer {
		MyTable() {
			// call my father and pass to it my TableModel
			super(new MyModel());
		}

		// this method tell the JTable drawer that it has to call me back to provide
		// the JComponent that will be used to draw the cell
		public TableCellRenderer getCellRenderer(int row, int col) {
			// Call me back for all rows/columns
			return this;
		}

		// this is called to get the Component that will be used to display the cell
		// the 3rd argument says if the cell has the focus or not
		public Component getTableCellRendererComponent(JTable arg0,
				Object component, boolean focus, boolean arg3, int arg4, int arg5) {
			
			return (Component) component;
		}
	}

	// the TableModel for the JTable
	class MyModel extends AbstractTableModel {

		// and array of JComponet that will be the object returned by the tableModel
		JComponent[][] comp = new JComponent[2][2];

		MyModel() {
			// just put some different JComponent in our array
			JLabel label = new JLabel("This is a JLabel");
			JButton button = new JButton("This is a JButton");
			JCheckBox checkBox = new JCheckBox("This is a JCheckBox");
			JRadioButton radio = new JRadioButton("This a JRadioButton");
			// fill our array with different JComponent
			comp[0][0] = label;
			comp[0][1] = button;
			comp[1][0] = checkBox;
			comp[1][1] = radio;
		}
		// return the size of the JLabel array for the number of column
		public int getColumnCount() {
			return comp[0].length;
		}
		// returns the array size for the number of rows
		public int getRowCount() {
			return comp.length;
		}
        // the object displayed at row: X column: Y is the JLabel from my array
		public Object getValueAt(int row, int count) {
			return comp[row][count];
		}

	}
}

