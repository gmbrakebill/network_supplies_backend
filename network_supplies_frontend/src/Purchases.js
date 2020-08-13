import React, { useState } from 'react';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';
import TableBody from '@material-ui/core/TableBody';

class Supplier extends React.Component{

    constructor(props){
        super(props);
        this.state ={
            Purchase:[],
            Product: [],
            Supplier: [],
            purchasedDate: [],
            startingInventory: [],
            
        };
    }

    async componentDidMount(){

        let resp = await fetch("http://localhost:8080/purchases/all");
        let purchaseData = await resp.json();

        this.setState({Purchase: purchaseData})
  
    }
    async add() {
        await fetch("http://localhost:8080/purchases/add-purchase",
            {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    product: this.state.product,
                    supplier: this.state.customer,
                    date: this.state.date,
                    purchasedDate: this.state.purchasedDate,
                    startingInventory: this.state.startingInventory,
                })
            });

    }
    render()
    {
        return(
            <div>
                <TableHead>
                <TableRow>
                    <TableCell>Order Id</TableCell>
                    <TableCell>Product Id</TableCell>
                    <TableCell>Product Name</TableCell>
                    <TableCell>Stock Number</TableCell>
                    <TableCell>Supplier Name</TableCell>
                    <TableCell>Purchased Date</TableCell>
                    <TableCell>QTY on hand</TableCell>
                </TableRow>
                </TableHead>
                <TableBody>
                {this.state.Purchase.map((row) =>
                (
                    <TableRow key = {row.id}>
                        <TableCell component="th" scope = "row">{row.id}</TableCell>
                        <TableCell>{row.product.id}</TableCell>
                        <TableCell>{row.product.name}</TableCell>
                        <TableCell>{row.product.stockNumber}</TableCell>
                        <TableCell>{row.supplier.supplierName}</TableCell>
                        <TableCell>{row.purchasedDate}</TableCell>
                        <TableCell>{row.onHandInventory}</TableCell>
                    </TableRow>
                ))}
                </TableBody>
                <input type="addItem" class="form-control" onfocus="this.value=''" id="addpurchase" placeholder="Enter product Id"></input>
                <input type="addItem" class="form-control" id="addSupplierId" placeholder="Enter Supplier Id"></input>
                <input type="addItem" class="form-control" id="add_amount_purchased" placeholder="Enter purchase amount"></input>
                <button type="submit" class="btn btn-primary" onClick={() => this.add(this.product), this.add(this.supplier), this.add(this.startingInventory)}>Submit</button>
            </div>
        )
    }
}

export default Supplier;