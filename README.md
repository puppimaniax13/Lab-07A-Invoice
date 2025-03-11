```mermaid
graph TD;
    InvoiceHeader --> Invoice;
    InvoiceItem --> Invoice;
    InvoiceItem --> Product;
    InvoiceItem --> LineItem;
    Invoice --> Customer;
    Invoice --> Payment;
    Invoice --> TaxRate;
    Invoice --> Discount;
    Customer --> Address;
    LineItem --> Product;
```
    Invoice : +generateInvoiceNumber()
    Invoice : +calculateTotalAmountDue()
    Invoice : +storeCustomerInformation()
    Invoice : +storeLineItems()
    Invoice : +generateInvoiceDocument()
    InvoiceHeader : +storeInvoiceNumber()
    InvoiceHeader : +storeInvoiceDate()
    InvoiceItem : +calculateTotalCost()
    InvoiceItem : +storeQuantity()
    InvoiceItem : +storeUnitPrice()
    Product : +storeProductName()
    Product : +storeUnitPrice()
    Customer : +storeCustomerName()
    Customer : +storeCustomerAddress()
    Address : +storeStreet()
    Address : +storeCity()
    Address : +storeState()
    Address : +storeZip()
    Payment : +storePaymentAmount()
    Payment : +storePaymentMethod()
    Payment : +storePaymentDate()
    TaxRate : +storeTaxRate()
    Discount : +storeDiscountAmount()
    Discount : +storeDiscountType()
