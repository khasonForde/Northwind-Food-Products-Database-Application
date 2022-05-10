package com.example.northwindfoodproductsdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        public void CustomerCountries(View Northwinds){
        Intent NorthwindIntent = new Intent(this, ResultsActivity.class);
        NorthwindIntent.putExtra("Query","SELECT DISTINCT country FROM Customers;");
        startActivity(NorthwindIntent);
        }

        public void CustomersInUSA(View Northwinds){
            Intent NorthwindIntent = new Intent(this, ResultsActivity.class);
            NorthwindIntent.putExtra("Query","SELECT customerid, contactname, address FROM Customers WHERE country = 'USA' ORDER BY contactname;");
            startActivity(NorthwindIntent);
        }

        public void EmployeesAvailableToCustomers(View Northwinds){
            Intent NorthwindIntent = new Intent(this, ResultsActivity.class);
            NorthwindIntent.putExtra("Query","SELECT Customers.country, customerid, employeeid FROM Customers, Employees WHERE Customers.country = Employees.country GROUP BY Customers.country;");
            startActivity(NorthwindIntent);
        }

        public void NumberOfCustomersByRegion(View Northwinds){
            Intent NorthwindIntent = new Intent(this, ResultsActivity.class);
            NorthwindIntent.putExtra("Query","SELECT region, count(customerid) FROM Customers GROUP BY region;");
            startActivity(NorthwindIntent);
        }

        public void InventoryRemaining(View Northwinds){
            Intent NorthwindIntent = new Intent(this, ResultsActivity.class);
            NorthwindIntent.putExtra("Query","SELECT OrderDetails.orderid, Products.UnitsInStock FROM Orders, OrderDetails, Products WHERE Orders.orderid = OrderDetails.orderid AND OrderDetails.productid = Products.ProductID;");
            startActivity(NorthwindIntent);
        }

}