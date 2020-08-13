import React, { useState } from 'react';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';
import TableBody from '@material-ui/core/TableBody';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';

class Supplier extends React.Component{

    constructor(props){
        super(props);
        this.state ={
            Orders: [],
            product:[],
            customer: [],
            date: [],
            amountOrdered: []
            
        };
    }

    async componentDidMount(){

        let resp = await fetch("http://localhost:8080/orders/all");
        let orderData = await resp.json();

        this.setState({Orders: orderData})
  
    }
    async remove(id) {
        await fetch("http://localhost:8080/orders/delete-order/${id}",

            {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            }).then(() => {
                let updatedOrders = [...this.state.Orders].filter(i => i.id !== id);
                this.setState({ name: updatedOrders })
            })
    }
    async add() {
        await fetch("http://localhost:8080/orders/add-order",
            {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    product: this.state.product,
                    customer: this.state.customer,
                    date: this.state.date,
                    amountOrdered: this.state.amountOrdered,
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
                    <TableCell>Customer Name</TableCell>
                    <TableCell>Order Date</TableCell>
                    <TableCell>Amount Ordered</TableCell>
                </TableRow>
                </TableHead>
                <TableBody>
                {this.state.Orders.map((row) =>
                (
                    <TableRow key = {row.id}>
                        <TableCell component="th" scope = "row">{row.id}</TableCell>
                        <TableCell>{row.product.id}</TableCell>
                        <TableCell>{row.product.name}</TableCell>
                        <TableCell>{row.product.stockNumber}</TableCell>
                        <TableCell>{row.customer.name}</TableCell>
                        <TableCell>{row.date}</TableCell>
                        <TableCell>{row.amountOrdered}</TableCell>
                        <Button size="sm" color="danger" onClick={() => this.remove(row.id)}>Delete</Button>
                    </TableRow>
                ))}
                </TableBody>
                <input type="addItem" class="form-control" onfocus="this.value=''" id="addproduct" placeholder="Enter product Id"></input>
                <input type="addItem" class="form-control" id="addCustomerID" placeholder="Enter customer Id"></input>
                <input type="addItem" class="form-control" id="addAmountOrder" placeholder="Enter order Amount"></input>
                <button type="submit" class="btn btn-primary" onClick={() => this.add(this.product), this.add(this.customer), this.add(this.amountOrdered)}>Submit</button>
                <button onclick="document.getElementById('addproduct').value = ''">Clear product</button>
            </div>
        )
    }
}

export default Supplier;
