import React, { useState } from 'react';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';
import TableBody from '@material-ui/core/TableBody';
import TableContainer from '@material-ui/core/TableContainer';
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';

class Product extends React.Component{

    constructor(props){
        super(props);
        this.state ={
          
            Product:[],
            name: [],
            stockNumber: [],
            partNumber: [],
            description: [],
            
        };
    }
   

    async componentDidMount(){

        let resp = await fetch("http://localhost:8080/products/all");
        let productData = await resp.json();

        this.setState({Product: productData})
  
    }
    async add() {
        await fetch("http://localhost:8080/products/add-product",
            {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    name: this.state.name,
                    stockNumber: this.state.stockNumber,
                    partNumber: this.state.partNumber,
                    description: this.state.description,

                })
            });

    }
    render()
    {
        return(
            
            <div>
                <TableContainer component={Paper}>
                <Table size="medium">
                <TableHead>
                <TableRow>
                    <TableCell>Nomenclature</TableCell>
                    <TableCell>Stock Number</TableCell>
                    <TableCell>Part Number</TableCell>
                    <TableCell>Description</TableCell>
                </TableRow>
                </TableHead>
                <TableBody>
                {this.state.Product.map((row) =>
                (
                    <TableRow key = {row.id}>
                        <TableCell component="th" scope = "row">{row.id}</TableCell>
                        <TableCell>{row.name}</TableCell>
                        <TableCell>{row.stockNumber}</TableCell>
                        <TableCell>{row.partNumber}</TableCell>
                        <TableCell>{row.description}</TableCell>
                    </TableRow>
                ))}
                </TableBody>
                </Table>
                </TableContainer>
                <input type="addItem" class="form-control" onfocus="this.value=''" id="addName" placeholder="Enter product name"></input>
                <input type="addItem" class="form-control" id="addNSN" placeholder="Enter product NSN"></input>
                <input type="addItem" class="form-control" id="addPN" placeholder="Enter part Number"></input>
                <button type="submit" class="btn btn-primary" onClick={() => this.add(this.name), this.add(this.stockNumber), this.add(this.partNumber)}>Submit</button>
            </div>
        )
    }
}

export default Product;