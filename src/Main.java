import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

class Product {
    String name;
    double unitPrice;

    public Product(String name, double unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return name + " - $" + df.format(unitPrice);
    }
}

class Customer {
    String name;
    String address;

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return name + "\n" + address;
    }
}

class InvoiceItem {
    Product product;
    int quantity;
    double totalCost;

    public InvoiceItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.totalCost = product.unitPrice * quantity;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return product.toString() + ", Quantity: " + quantity + ", Total: $" + df.format(totalCost);
    }
}

class Invoice {
    Customer customer;
    List<InvoiceItem> lineItems;
    double totalAmountDue;

    public Invoice(Customer customer) {
        this.customer = customer;
        this.lineItems = new ArrayList<>();
        this.totalAmountDue = 0;
    }

    public void addLineItem(InvoiceItem item) {
        lineItems.add(item);
        totalAmountDue += item.totalCost;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        StringBuilder sb = new StringBuilder();
        sb.append("Invoice for: ").append(customer.toString()).append("\n");
        for (InvoiceItem item : lineItems) {
            sb.append(item.toString()).append("\n");
        }
        sb.append("Total Amount Due: $").append(df.format(totalAmountDue));
        return sb.toString();
    }
}

// Main application class for the Invoice Generator GUI.
public class Main extends JFrame {
    private JTextField productNameField, quantityField, unitPriceField;
    private JTextArea invoiceTextArea;
    private JButton addLineItemButton, generateInvoiceButton;
    private List<InvoiceItem> lineItems = new ArrayList<>();

    public Main() {
        setTitle("Invoice Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Product Name:"));
        productNameField = new JTextField();
        inputPanel.add(productNameField);
        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);
        inputPanel.add(new JLabel("Unit Price:"));
        unitPriceField = new JTextField();
        inputPanel.add(unitPriceField);
        addLineItemButton = new JButton("Add Line Item");
        inputPanel.add(addLineItemButton);
        generateInvoiceButton = new JButton("Generate Invoice");
        inputPanel.add(generateInvoiceButton);
        add(inputPanel, BorderLayout.NORTH);

        // Text area to display the generated invoice.
        invoiceTextArea = new JTextArea(20, 40);
        invoiceTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(invoiceTextArea);
        add(scrollPane, BorderLayout.CENTER);

        // Event handlers for buttons.
        addLineItemButton.addActionListener(e -> addLineItem());
        generateInvoiceButton.addActionListener(e -> generateInvoice());

        pack();
        setVisible(true);
    }

    // Adds a new line item to the invoice.
    private void addLineItem() {
        try {
            String productName = productNameField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double unitPrice = Double.parseDouble(unitPriceField.getText());

            Product product = new Product(productName, unitPrice);
            InvoiceItem item = new InvoiceItem(product, quantity);
            lineItems.add(item);

            productNameField.setText("");
            quantityField.setText("");
            unitPriceField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter numbers for quantity and unit price.");
        }
    }

    // Generates the invoice and displays it in the text area.
    private void generateInvoice() {
        String customerName = JOptionPane.showInputDialog("Enter customer name:");
        String customerAddress = JOptionPane.showInputDialog("Enter customer address:");
        Customer customer = new Customer(customerName, customerAddress);
        Invoice invoice = new Invoice(customer);
        for (InvoiceItem item : lineItems) {
            invoice.addLineItem(item);
        }
        invoiceTextArea.setText(invoice.toString());
    }

    public static void main(String[] args) {
        new Main();
    }
}
