import React, { useState } from 'react';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';
import TableBody from '@material-ui/core/TableBody';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';

class Customers extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            name: [],

        };
    }

    async componentDidMount() {

        let resp = await fetch("http://localhost:8080/customers/all");
        let customerData = await resp.json();

        this.setState({ name: customerData })


    }
    async remove(id) {
        await fetch("http://localhost:8080/customers/delete-customer/" + id,

            {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            }).then(response => response.text())
            .then((response) => {
                alert(response)
            })

    }
    async add(name) {

        const data = { name: name };
        fetch('http://localhost:8080/customers/add-customer', {
            method: 'POST', // or 'PUT'
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
            .then(response => response.json())
            .then(data => {
                alert("Added");
            })
            .catch((error) => {
                alert('Error:' + error);
            });


    }
    async componentDidUpdate() {

        let resp = await fetch("http://localhost:8080/customers/all");
        let customerData = await resp.json();

        this.setState({ name: customerData })
    }

    componentdidupdate
    render() {
        return (
            <div>
                <TableHead>
                    <TableRow>
                        <TableCell>Customer ID</TableCell>
                        <TableCell>Customer Name</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {this.state.name.map((row) =>
                        (
                            <TableRow key={row.name}>
                                <TableCell component="th" scope="row">{row.id}</TableCell>
                                <TableCell>{row.name}</TableCell>
                                <Button size="sm" color="danger" onClick={() => this.remove(row.id)}>Delete</Button>
                            </TableRow>
                        ))}


                </TableBody>
                <input type="addCustomer" class="form-control" id="addCustomer1" placeholder="Enter customer"></input>
                <button type="submit" class="btn btn-primary" onClick={() => this.add(document.getElementById('addCustomer1').value)}>Submit</button>

            </div>
        )
    }
}

export default Customers;




