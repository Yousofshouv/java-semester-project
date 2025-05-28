import javax.swing.*;
import java.awt.event.*;
// import utilities..

public class Aiub extends JFrame implements ActionListener {                        
    private JTextField nameField, idField, searchField;                        
    private JTextArea resultArea;                                                  
    private JButton addButton, showButton, searchButton;
    private JRadioButton cseBtn, eeeBtn, bbaBtn, searchByNameBtn, searchByIdBtn;  
     
    private ButtonGroup deptGroup, searchGroup;                               
    private Department cse = new Department("CSE");     
    private Department eee = new Department("EEE");
    private Department bba = new Department("BBA");

    public Aiub() {                                                  
        setSize(550, 750);                            
        setLayout(null);                                           
        setDefaultCloseOperation(EXIT_ON_CLOSE);                
        setTitle("AIUB Department Student Manager");    

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 20, 100, 25);         
        add(nameLabel);                              

        nameField = new JTextField();
        nameField.setBounds(150, 20, 200, 25);         
        add(nameField);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(30, 60, 100, 25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(150, 60, 200, 25);
        add(idField);                           

        cseBtn = new JRadioButton("CSE");       
        cseBtn.setBounds(30, 100, 60, 25);         
        eeeBtn = new JRadioButton("EEE");      
        eeeBtn.setBounds(100, 100, 60, 25);
        bbaBtn = new JRadioButton("BBA");
        bbaBtn.setBounds(170, 100, 60, 25);

        deptGroup = new ButtonGroup();              
        deptGroup.add(cseBtn);
        deptGroup.add(eeeBtn);
        deptGroup.add(bbaBtn);

        add(cseBtn);
        add(eeeBtn);                               
        add(bbaBtn);
 
        addButton = new JButton("Add Student");
        addButton.addActionListener(this);
        addButton.setBounds(30, 140, 400, 40);
        add(addButton);

        showButton = new JButton("Show Students");
        showButton.addActionListener(this);                  
        showButton.setBounds(30, 190, 400, 40);
        add(showButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(30, 240, 470, 200);
        add(scrollPane);

        JLabel searchLabel = new JLabel("Search Student:");
        searchLabel.setBounds(30, 460, 200, 25);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(150, 460, 200, 25);
        add(searchField);

        searchByNameBtn = new JRadioButton("By Name");
        searchByNameBtn.setBounds(150, 490, 100, 25);
        searchByIdBtn = new JRadioButton("By ID");
        searchByIdBtn.setBounds(260, 490, 100, 25);

        searchGroup = new ButtonGroup();                     
        searchGroup.add(searchByNameBtn);
        searchGroup.add(searchByIdBtn);

        add(searchByNameBtn);
        add(searchByIdBtn);

        searchButton = new JButton("Search");
        searchButton.setBounds(150, 520, 200, 40);
        searchButton.addActionListener(this);
        add(searchButton);
    }

    public void actionPerformed(ActionEvent e) {                     
        Department selected = getSelectedDepartment();      
        if (selected == null) {
            resultArea.setText("Please select a department.");      
            return;
        }

        if (e.getSource() == addButton) {                             
            String name = nameField.getText().trim();                     
            String id = idField.getText().trim();

            if (name.isEmpty() || id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both name and ID.");             
                return;
            }

            boolean added = selected.addStudent(name, id);                          
            if (added) {
                resultArea.setText("Student added to " + selected.getName() + ". " +              
                        selected.getRemainingSlots() + " slot(s) remaining.");
                nameField.setText("");                                                   
                idField.setText("");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Cannot add student. Limit of 10 students reached in " + selected.getName() + ".");
            }

        } else if (e.getSource() == showButton) {
            resultArea.setText(selected.getAllStudents());

        } else if (e.getSource() == searchButton) {
            String input = searchField.getText().trim();
            if (input.isEmpty()) {
                resultArea.setText("Enter a name or ID to search.");
                return;
            }

            String output;
            if (searchByNameBtn.isSelected()) {
                output = selected.searchByName(input);
            } else if (searchByIdBtn.isSelected()) {
                output = selected.searchById(input);
            } else {
                output = "Please select search type: by Name or by ID.";
            }

            resultArea.setText(output);
        }
    }

    private Department getSelectedDepartment() {         
        if (cseBtn.isSelected()) return cse;               
        if (eeeBtn.isSelected()) return eee;
        if (bbaBtn.isSelected()) return bba;
        return null;
    }

    public static void main(String[] args) {
        Aiub app = new Aiub();
        app.setVisible(true);
    }
}
